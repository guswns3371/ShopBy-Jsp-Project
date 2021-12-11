package com.shopby.model.dto;

import com.shopby.model.PayHistory;
import lombok.Data;

@Data
public class DeliveryItemDto {
    private int count;
    private ItemDto itemDto;

    public DeliveryItemDto(PayHistory entity) {
        this.count = entity.getCount();
        this.itemDto = new ItemDto(entity.getItem());
    }
}
