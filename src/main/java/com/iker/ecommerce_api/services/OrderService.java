package com.iker.ecommerce_api.services;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.Order;
import com.iker.ecommerce_api.entities.User;
import com.iker.ecommerce_api.exceptions.Cart.CartIsNullException;
import com.iker.ecommerce_api.exceptions.Cart.UserIsNullException;
import com.iker.ecommerce_api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Order> viewById(Long id) {
        return repository.findById(id);
    }

    public Order createOrder(User user, Cart cart) {
        if (user == null) {
            throw new UserIsNullException("User cannot be null");
        }

        Cart selectedCart = cart != null ? cart : user.getCart();
        if (selectedCart == null) {
            throw new CartIsNullException("Cart cannot be null");
        }

        Order order = user.getOrder();
        if (order == null) {
            order = new Order();
            user.setOrder(order);
        }

        order.setClient(user);
        order.setCart(selectedCart);
        user.setCart(selectedCart);
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        Optional<Order> opOrder = repository.findById(id);
        opOrder.ifPresentOrElse(
                o -> repository.delete(o),
                () -> System.out.println("Order not found")
        );
    }
}
