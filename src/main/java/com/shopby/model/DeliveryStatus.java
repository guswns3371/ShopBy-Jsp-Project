package com.shopby.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {
    READY("READY", "배송 준비중"),
    ONGOING("ONGOING", "배송중"),
    COMPLETE("COMPLETE", "배송 완료");

    private final String key;
    private final String title;
}
