package com.shopby.repository;

import com.shopby.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<ArrayList<Item>> findItemsByIdIn(List<Long> idList);
}
