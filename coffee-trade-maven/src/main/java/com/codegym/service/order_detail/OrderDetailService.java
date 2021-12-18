package com.codegym.service.order_detail;

import com.codegym.model.OrderDetail;
import com.codegym.model.dto.IOrderDetailSumDTO;
import com.codegym.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService{

    @Autowired
    private IOrderDetailRepository orderDetailRepository ;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAllNotDeleted() {
        return orderDetailRepository.findAllNotDeleted();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
      OrderDetail orderDetail = findById(id).get() ;
      orderDetail.setDeleted(true);
      save(orderDetail) ;
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(Long id){
        return orderDetailRepository.findOrderDetailByOrderId(id);
    };

    @Override
    public List<IOrderDetailSumDTO> getAllIOrderDetailSumDTOByOrderId(Long id) {
        return orderDetailRepository.getAllIOrderDetailSumDTOByOrderId(id);
    }
}
