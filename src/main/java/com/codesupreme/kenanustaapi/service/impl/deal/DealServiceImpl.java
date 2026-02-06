package com.codesupreme.kenanustaapi.service.impl.deal;

import com.codesupreme.kenanustaapi.dao.deal.DealRepository;
import com.codesupreme.kenanustaapi.dto.deal.DealDto;
import com.codesupreme.kenanustaapi.model.deal.Deal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealServiceImpl {

    private final DealRepository dealRepository;
    private final ModelMapper modelMapper;

    public DealServiceImpl(DealRepository dealRepository, ModelMapper modelMapper) {
        this.dealRepository = dealRepository;
        this.modelMapper = modelMapper;
    }

    //ALL
    public List<DealDto> getAllDeal() {
        List<Deal> list = dealRepository.findAll();
        return list.stream()
                .map(det -> modelMapper.map(det, DealDto.class))
                .toList();
    }

    //ById
    public DealDto getDealById(Long id) {
        Optional<Deal> optional = dealRepository.findById(id);
        return optional.map(det -> modelMapper.map(det, DealDto.class)).orElse(null);
    }

    //Save
    public DealDto saveDeal(DealDto dto) {
        Deal det = modelMapper.map(dto, Deal.class);
        det = dealRepository.save(det);
        return modelMapper.map(det, DealDto.class);
    }

    //Update
    public DealDto updateDeal(Long dealId, DealDto dealDto) {
        Optional<Deal> optional = dealRepository.findById(dealId);
        if (optional.isPresent()) {
            Deal deal = optional.get();


            if (dealDto.getProductId() != null) {
                deal.setProductId(dealDto.getProductId());
            }

            if (dealDto.getNewPrice() != null) {
                deal.setNewPrice(dealDto.getNewPrice());
            }


            if (dealDto.getCreatedAt() != null) {
                dealDto.setCreatedAt(dealDto.getCreatedAt());
            }

            if (dealDto.getUpdatedAt() != null) {
                dealDto.setUpdatedAt(dealDto.getUpdatedAt());
            }

            deal = dealRepository.save(deal);

            return modelMapper.map(deal, DealDto.class);
        }
        return null;
    }

    //Delete
    public Boolean deleteDeal(Long id) {
        Optional<Deal> optional = dealRepository.findById(id);
        if (optional.isPresent()) {
            Deal det = optional.get();
            dealRepository.delete(det);
            return true;
        }
        return false;
    }


}


