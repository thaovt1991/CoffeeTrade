package com.codegym.model.dto;

import java.math.BigDecimal;

public interface IOrderDetailSumDTO {

    String getDrinksName();
    int getQuantity();
    BigDecimal getPrice();
    BigDecimal getAmount();
}
