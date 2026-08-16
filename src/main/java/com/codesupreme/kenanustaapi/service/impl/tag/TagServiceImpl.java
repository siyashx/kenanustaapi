package com.codesupreme.kenanustaapi.service.impl.tag;

import com.codesupreme.kenanustaapi.dao.tag.TagRepository;
import com.codesupreme.kenanustaapi.dto.tag.TagBulkResultDto;
import com.codesupreme.kenanustaapi.dto.tag.TagDto;
import com.codesupreme.kenanustaapi.model.tag.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TagServiceImpl {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    public List<TagDto> getAllTag() {
        return tagRepository.findAllByOrderByIdAsc().stream()
                .map(det -> modelMapper.map(det, TagDto.class))
                .toList();
    }

    public TagDto getTagById(Long id) {
        return tagRepository.findById(id)
                .map(det -> modelMapper.map(det, TagDto.class))
                .orElse(null);
    }

    public TagDto getTagBySlug(String slug) {
        return tagRepository.findBySlugIgnoreCase(normalizeSlug(slug))
                .map(det -> modelMapper.map(det, TagDto.class))
                .orElse(null);
    }

    public TagDto createTag(TagDto dto) {
        String slug = normalizeSlug(dto.getSlug());
        String label = normalizeLabel(dto.getLabel());

        if (slug.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "slug etibarlı deyil");
        }
        if (tagRepository.existsBySlugIgnoreCase(slug)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bu slug artıq mövcuddur: " + slug);
        }

        Tag tag = Tag.builder()
                .label(label)
                .slug(slug)
                .build();
        tag = tagRepository.save(tag);
        return modelMapper.map(tag, TagDto.class);
    }

    public TagBulkResultDto createTagsIfMissing(List<TagDto> dtos) {
        TagBulkResultDto result = TagBulkResultDto.builder().build();

        for (TagDto dto : dtos) {
            try {
                String slug = normalizeSlug(dto.getSlug());
                Optional<Tag> existing = tagRepository.findBySlugIgnoreCase(slug);
                if (existing.isPresent()) {
                    result.getSkipped().add(modelMapper.map(existing.get(), TagDto.class));
                    continue;
                }
                result.getCreated().add(createTag(dto));
            } catch (Exception ex) {
                String slug = dto != null ? String.valueOf(dto.getSlug()) : "null";
                result.getFailed().add(slug + ": " + ex.getMessage());
            }
        }
        return result;
    }

    public TagDto updateTag(Long tagId, TagDto tagDto) {
        Optional<Tag> optional = tagRepository.findById(tagId);
        if (optional.isEmpty()) {
            return null;
        }

        Tag tag = optional.get();

        if (tagDto.getSlug() != null && !tagDto.getSlug().isBlank()) {
            String newSlug = normalizeSlug(tagDto.getSlug());
            Optional<Tag> owner = tagRepository.findBySlugIgnoreCase(newSlug);
            if (owner.isPresent() && !owner.get().getId().equals(tagId)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Bu slug artıq mövcuddur: " + newSlug);
            }
            tag.setSlug(newSlug);
        }

        if (tagDto.getLabel() != null && !tagDto.getLabel().isBlank()) {
            tag.setLabel(normalizeLabel(tagDto.getLabel()));
        }

        tag = tagRepository.save(tag);
        return modelMapper.map(tag, TagDto.class);
    }

    public Boolean deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            return false;
        }
        tagRepository.deleteById(id);
        return true;
    }

    private String normalizeLabel(String label) {
        return label == null ? "" : label.trim().replaceAll("\\s+", " ");
    }

    private String normalizeSlug(String input) {
        if (input == null) return "";
        String s = input.trim().toLowerCase(Locale.ROOT)
                .replace('ə', 'e')
                .replace('ı', 'i')
                .replace('ş', 's')
                .replace('ç', 'c')
                .replace('ö', 'o')
                .replace('ü', 'u')
                .replace('ğ', 'g');
        s = s.replaceAll("[^a-z0-9]+", "-");
        s = s.replaceAll("-+", "-");
        s = s.replaceAll("^-|-$", "");
        return s;
    }
}
