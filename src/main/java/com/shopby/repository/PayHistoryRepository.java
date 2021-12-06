package com.shopby.repository;

import com.shopby.model.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayHistoryRepository extends JpaRepository<PayHistory, Long> {
}
