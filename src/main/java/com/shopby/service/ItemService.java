package com.shopby.service;

import com.shopby.model.Item;
import com.shopby.model.dto.CartItemDto;
import com.shopby.model.dto.ItemDto;
import com.shopby.model.dto.ItemInfoDto;
import com.shopby.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

    public List<ItemDto> findAll() {
        List<Item> all = itemRepository.findAll();
        return all.stream().map(ItemDto::new).collect(Collectors.toList());
    }

    public ItemInfoDto findById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이템 = " + id);
        }
        return new ItemInfoDto(optional.get());
    }

    public List<Item> findItemsByIdList(List<Long> idList) {
        Optional<ArrayList<Item>> optional = itemRepository.findItemsByIdIn(idList);
        if (optional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이템들 = " + idList);
        }
        return new ArrayList<>(optional.get());
    }

    public List<CartItemDto> findCartItemsByIdList(HashMap<Long, Integer> cart) {
        if (cart == null)
            return new ArrayList<>();
        List<Long> idList = new ArrayList<>(cart.keySet());
        Optional<ArrayList<Item>> optional = itemRepository.findItemsByIdIn(idList);
        if (optional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이템들 = " + idList);
        }
        return optional.get().stream()
                .map(i -> new CartItemDto(i, cart))
                .collect(Collectors.toList());
    }
}
