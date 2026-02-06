package com.codesupreme.kenanustaapi.dao.deal;

import com.codesupreme.kenanustaapi.model.deal.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
