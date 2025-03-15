package com.katatest.service.impl;

import com.kataTest.back.enteties.*;
import com.kataTest.back.repositoy.CartRepository;
import com.kataTest.back.repositoy.OrderItemRepository;
import com.kataTest.back.repositoy.OrderRepository;
import com.kataTest.back.repositoy.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Order createOrder() {
        // Récupérer l'utilisateur connecté
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // Récupérer le panier
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Panier vide"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Le panier est vide !");
        }

        // Créer une nouvelle commande
        Order order = new Order();
        order.setUser(user);
        order.setStatus("En cours");

        // Convertir les CartItems en OrderItems
        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProducts(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        BigDecimal totalAmount = BigDecimal.valueOf(orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());
        order.setTotalPrice(totalAmount);

        // Sauvegarder la commande
        orderRepository.save(order);

        // Vider le panier après validation
        cart.getItems().clear();
        cartRepository.save(cart);

        return order;
    }




     /*   public Order createOrder(Long userId, List<OrderItem> orderItems) {
            // Create a new order
            Order order = new Order();
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            order.setUser((User) principal);  // Set the user (fetch the user by userId if needed)
            order.setDatePaiement(LocalDateTime.now());
            order.setItems(orderItems);

            // Calculate total amount
            BigDecimal totalAmount = BigDecimal.valueOf(orderItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum());
            order.setTotalPrice(totalAmount);

            // Save the order and associated items
            orderRepository.save(order);

            // Link order items to the order
            orderItems.forEach(item -> item.setOrder(order));
            orderItemRepository.saveAll(orderItems);

            return order;
        }*/

        public List<Order> getOrdersByUser(Long userId) {
            return orderRepository.findByUserId(userId);
        }

        public Order getOrderById(Long orderId) {
            return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        }
    }




