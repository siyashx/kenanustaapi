package com.codesupreme.kenanustaapi.service.impl.elan;

import com.codesupreme.kenanustaapi.dao.elan.ElanRepository;
import com.codesupreme.kenanustaapi.dto.elan.ElanDto;
import com.codesupreme.kenanustaapi.model.elan.Elan;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElanServiceImpl {

    private final ElanRepository elanRepository;
    private final ModelMapper modelMapper;

    public ElanServiceImpl(ElanRepository elanRepository, ModelMapper modelMapper) {
        this.elanRepository = elanRepository;
        this.modelMapper = modelMapper;
    }

    //ALL
    public List<ElanDto> getAllElan() {
        List<Elan> list = elanRepository.findAll();
        return list.stream()
                .map(det -> modelMapper.map(det, ElanDto.class))
                .toList();
    }

    //ById
    public ElanDto getElanById(Long id) {
        Optional<Elan> optional = elanRepository.findById(id);
        return optional.map(det -> modelMapper.map(det, ElanDto.class)).orElse(null);
    }

    //Save
    public ElanDto saveElan(ElanDto dto) {
        Elan det = modelMapper.map(dto, Elan.class);
        det = elanRepository.save(det);
        return modelMapper.map(det, ElanDto.class);
    }

    //Update
    public ElanDto updateElan(Long elanId, ElanDto elanDto) {
        Optional<Elan> optional = elanRepository.findById(elanId);
        if (optional.isPresent()) {
            Elan elan = optional.get();


            if (elanDto.getTitle() != null) {
                elan.setTitle(elanDto.getTitle());
            }

            if (elanDto.getDescription() != null) {
                elan.setDescription(elanDto.getDescription());
            }

            if (elanDto.getCategory() != null) {
                elan.setCategory(elanDto.getCategory());
            }

            if (elanDto.getCity() != null) {
                elan.setCity(elanDto.getCity());
            }

            if (elanDto.getImagePath() != null) {
                elan.setImagePath(elanDto.getImagePath());
            }

            if (elanDto.getSlug() != null) {
                elan.setSlug(elanDto.getSlug());
            }

            if (elanDto.getCreatedAt() != null) {
                elanDto.setCreatedAt(elanDto.getCreatedAt());
            }

            if (elanDto.getUpdatedAt() != null) {
                elanDto.setUpdatedAt(elanDto.getUpdatedAt());
            }

            elan = elanRepository.save(elan);

            return modelMapper.map(elan, ElanDto.class);
        }
        return null;
    }

    //Delete
    public Boolean deleteElan(Long id) {
        Optional<Elan> optional = elanRepository.findById(id);
        if (optional.isPresent()) {
            Elan det = optional.get();
            elanRepository.delete(det);
            return true;
        }
        return false;
    }


}


