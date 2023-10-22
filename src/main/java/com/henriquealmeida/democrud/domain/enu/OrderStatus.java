package com.henriquealmeida.democrud.domain.enu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "Order status", description = "Status of order of the customers")
@Getter
public enum OrderStatus {
    AWAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }


    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order status code" + code);
    }
}
