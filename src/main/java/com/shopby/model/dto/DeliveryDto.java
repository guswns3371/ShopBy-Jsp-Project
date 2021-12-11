package com.shopby.model.dto;

import com.shopby.model.Delivery;
import com.shopby.utils.StringSplitBuilder;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DeliveryDto {

    private Long id;
    private String createdDate;
    private String status;
    private String totalPrice;
    private List<DeliveryItemDto> itemDtoList;

    public DeliveryDto(Delivery entity) {
        this.id = entity.getId();
        this.createdDate = entity.getCreatedData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.status = entity.getDeliveryStatus().getTitle();
        this.totalPrice = StringSplitBuilder.getSplitPrice(entity.getTotalPrice());
        this.itemDtoList = entity.getPayHistories().stream().map(DeliveryItemDto::new).collect(Collectors.toList());
    }
}
