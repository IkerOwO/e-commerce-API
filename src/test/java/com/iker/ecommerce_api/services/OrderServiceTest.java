package com.iker.ecommerce_api.services;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.Order;
import com.iker.ecommerce_api.entities.User;
import com.iker.ecommerce_api.repositories.OrderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    void createOrder_usesPassedCartAndAssociatesUser() {
        OrderRepository repository = mock(OrderRepository.class);
        OrderService service = new OrderService(repository);

        User user = new User();
        Cart cart = new Cart();
        when(repository.save(any(Order.class))).thenAnswer(
            invocation -> invocation.getArgument(0)
        );

        Order order = service.createOrder(user, cart);

        assertNotNull(order);
        assertEquals(user, order.getClient());
        assertEquals(cart, order.getCart());
    }
}
