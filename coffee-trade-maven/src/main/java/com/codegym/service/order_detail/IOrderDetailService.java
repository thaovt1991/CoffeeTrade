package com.codegym.service.order_detail;

import com.codegym.model.OrderDetail;
import com.codegym.model.dto.IOrderDetailSumDTO;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    List<OrderDetail> findOrderDetailByOrderId(Long id);

    List<IOrderDetailSumDTO> getAllIOrderDetailSumDTOByOrderId(Long id);

    List<OrderDetail> findAllNotDeletedOfOrder(Long id);
}
