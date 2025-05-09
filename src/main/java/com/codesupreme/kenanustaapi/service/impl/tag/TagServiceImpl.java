package com.codesupreme.kenanustaapi.service.impl.tag;

import com.codesupreme.kenanustaapi.dao.tag.TagRepository;
import com.codesupreme.kenanustaapi.dto.tag.TagDto;
import com.codesupreme.kenanustaapi.model.tag.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    //ALL
    public List<TagDto> getAllTag() {
        List<Tag> list = tagRepository.findAll();
        return list.stream()
                .map(det -> modelMapper.map(det, TagDto.class))
                .toList();
    }

    //ById
    public TagDto getTagById(Long id) {
        Optional<Tag> optional = tagRepository.findById(id);
        return optional.map(det -> modelMapper.map(det, TagDto.class)).orElse(null);
    }

    //Create
    public TagDto createTag(TagDto dto) {
        Tag det = modelMapper.map(dto, Tag.class);
        det = tagRepository.save(det);
        return modelMapper.map(det, TagDto.class);
    }

    //Update
    public TagDto updateTag(Long tagId, TagDto tagDto) {
        Optional<Tag> optional = tagRepository.findById(tagId);
        if (optional.isPresent()) {
            Tag tag = optional.get();


            if (tagDto.getCourierId() != null) {
                tag.setCourierId(tagDto.getCourierId());
            }

            if (tagDto.getCustomerId() != null) {
                tag.setCustomerId(tagDto.getCustomerId());
            }

            if (tagDto.getFromAddress() != null) {
                tag.setFromAddress(tagDto.getFromAddress());
            }

            if (tagDto.getToAddress() != null) {
                tag.setToAddress(tagDto.getToAddress());
            }

            if (tagDto.getCancelledCourierIds() != null) {
                tag.setCancelledCourierIds(tagDto.getCancelledCourierIds());
            }

            if (tagDto.getStatus() != null) {
                tagDto.setStatus(tagDto.getStatus());
            }

            if (tagDto.getPrice() != null) {
                tagDto.setPrice(tagDto.getPrice());
            }

            if (tagDto.getDistance() != null) {
                tagDto.setDistance(tagDto.getDistance());
            }

            if (tagDto.getIsDisable() != null) {
                tagDto.setIsDisable(tagDto.getIsDisable());
            }

            tag = tagRepository.save(tag);

            return modelMapper.map(tag, TagDto.class);
        }
        return null;
    }

    //Delete
    public Boolean deleteTag(Long id) {
        Optional<Tag> optional = tagRepository.findById(id);
        if (optional.isPresent()) {
            Tag det = optional.get();
            tagRepository.delete(det);
            return true;
        }
        return false;
    }


}


