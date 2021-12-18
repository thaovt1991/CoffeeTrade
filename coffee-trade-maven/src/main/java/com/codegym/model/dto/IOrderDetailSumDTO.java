package com.codegym.model.dto;

import java.math.BigDecimal;

public interface IOrderDetailSumDTO {

    String getDrinksName();
    BigDecimal getPrice();
    int getQuantity();
    BigDecimal getAmount();
}
