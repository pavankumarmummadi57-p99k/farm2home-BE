package com.pavankumar.farm2home.order.dto;

import com.pavankumar.farm2home.order.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

    @NotNull
    private OrderStatus status;

    private String rejectionReason;
}