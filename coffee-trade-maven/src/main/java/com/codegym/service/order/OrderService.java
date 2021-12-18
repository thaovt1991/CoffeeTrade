package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository ;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllNotDeleted() {
        return orderRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
      Order order = findById(id).get() ;
      order.setDeleted(true);
      save(order) ;
    }

    @Override
    public Order getOrderByDeskId(Long id){
        return orderRepository.getOrderByDeskId(id) ;
    };

}
