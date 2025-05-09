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


            if (elanDto.getChatId() != null) {
                elan.setChatId(elanDto.getChatId());
            }

            if (elanDto.getCourierId() != null) {
                elan.setCourierId(elanDto.getCourierId());
            }

            if (elanDto.getCustomerId() != null) {
                elan.setCustomerId(elanDto.getCustomerId());
            }

            if (elanDto.getIsRead() != null) {
                elan.setIsRead(elanDto.getIsRead());
            }

            if (elanDto.getType() != null) {
                elan.setType(elanDto.getType());
            }

            if (elanDto.getIsDeleted() != null) {
                elan.setIsDeleted(elanDto.getIsDeleted());
            }

            if (elanDto.getMessage() != null) {
                elanDto.setMessage(elanDto.getMessage());
            }

            if (elanDto.getCreatedAt() != null) {
                elanDto.setCreatedAt(elanDto.getCreatedAt());
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


