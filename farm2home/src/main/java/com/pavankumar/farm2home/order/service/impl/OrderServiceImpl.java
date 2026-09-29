package com.pavankumar.farm2home.order.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.customer.entity.CustomerAddress;
import com.pavankumar.farm2home.customer.repository.CustomerAddressRepository;
import com.pavankumar.farm2home.order.dto.OrderResponse;
import com.pavankumar.farm2home.order.dto.PlaceOrderRequest;
import com.pavankumar.farm2home.order.dto.UpdateOrderStatusRequest;
import com.pavankumar.farm2home.order.entity.Order;
import com.pavankumar.farm2home.order.enums.OrderStatus;
import com.pavankumar.farm2home.order.repository.OrderRepository;
import com.pavankumar.farm2home.order.service.OrderService;
import com.pavankumar.farm2home.product.entity.Product;
import com.pavankumar.farm2home.product.repository.ProductRepository;
import com.pavankumar.farm2home.security.jwt.JwtService;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomerAddressRepository customerAddressRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            JwtService jwtService,
            CustomerAddressRepository customerAddressRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.customerAddressRepository = customerAddressRepository;
    }

    @Override
    @Transactional
    public ApiResponse placeOrder(
            PlaceOrderRequest request,
            HttpServletRequest httpServletRequest) {

        // Extract JWT
        String authHeader =
                httpServletRequest.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            return new ApiResponse(
                    false,
                    "Authorization token is missing.",
                    null);
        }

        String token = authHeader.substring(7);

        String phoneNumber =
                jwtService.extractUsername(token);

        // Find customer
        User customer =
                userRepository
                        .findByPhoneNumber(phoneNumber)
                        .orElse(null);

        if (customer == null) {

            return new ApiResponse(
                    false,
                    "Customer not found.",
                    null);
        }

        /*
         * Check saved delivery address.
         * Customer must add an address before ordering.
         */
        CustomerAddress deliveryAddress =
                customerAddressRepository
                        .findByUser(customer)
                        .orElse(null);

        if (deliveryAddress == null) {

            return new ApiResponse(
                    false,
                    "Please add your delivery address before placing an order.",
                    null);
        }

        // Find product
        Product product =
                productRepository
                        .findById(request.getProductId())
                        .orElse(null);

        if (product == null) {

            return new ApiResponse(
                    false,
                    "Product not found.",
                    null);
        }

        if (!product.getIsAvailable()) {

            return new ApiResponse(
                    false,
                    "Product is not available.",
                    null);
        }

        // Validate minimum order quantity
        if (request.getQuantity()
                .compareTo(product.getMinimumOrderQuantity()) < 0) {

            return new ApiResponse(
                    false,
                    "Minimum order quantity is "
                            + product.getMinimumOrderQuantity(),
                    null);
        }

        // Validate available stock
        if (request.getQuantity()
                .compareTo(product.getAvailableQuantity()) > 0) {

            return new ApiResponse(
                    false,
                    "Insufficient stock available.",
                    null);
        }

        BigDecimal totalAmount =
                product.getPrice()
                        .multiply(request.getQuantity());

        // Create order
        Order order = new Order();

        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(request.getQuantity());
        order.setPricePerUnit(product.getPrice());
        order.setTotalAmount(totalAmount);
        order.setOrderStatus(OrderStatus.PENDING);

        /*
         * Copy the customer's current saved address
         * into the order.
         *
         * This is an order snapshot.
         */
        order.setDeliveryAddressLine1(
                deliveryAddress.getAddressLine1());

        order.setDeliveryAddressLine2(
                deliveryAddress.getAddressLine2());

        order.setDeliveryVillage(
                deliveryAddress.getVillage());

        order.setDeliveryMandal(
                deliveryAddress.getMandal());

        order.setDeliveryDistrict(
                deliveryAddress.getDistrict());

        order.setDeliveryState(
                deliveryAddress.getState());

        order.setDeliveryPincode(
                deliveryAddress.getPincode());

        order.setDeliveryLandmark(
                deliveryAddress.getLandmark());

        Order savedOrder =
                orderRepository.save(order);

        OrderResponse response =
                mapOrderToResponse(savedOrder);

        return new ApiResponse(
                true,
                "Order placed successfully.",
                response);
    }

    @Override
    public ApiResponse getMyOrders(
            HttpServletRequest httpServletRequest) {

        String authHeader =
                httpServletRequest.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            return new ApiResponse(
                    false,
                    "Authorization token is missing.",
                    null);
        }

        String token = authHeader.substring(7);

        String phoneNumber =
                jwtService.extractUsername(token);

        User customer =
                userRepository
                        .findByPhoneNumber(phoneNumber)
                        .orElse(null);

        if (customer == null) {

            return new ApiResponse(
                    false,
                    "Customer not found.",
                    null);
        }

        var orders =
                orderRepository
                        .findByCustomerOrderByCreatedAtDesc(customer);

        var responseList =
                orders.stream()
                        .map(this::mapOrderToResponse)
                        .toList();

        return new ApiResponse(
                true,
                "My orders fetched successfully.",
                responseList);
    }

    @Override
    public ApiResponse getFarmerOrders(
            HttpServletRequest httpServletRequest) {

        String authHeader =
                httpServletRequest.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            return new ApiResponse(
                    false,
                    "Authorization token is missing.",
                    null);
        }

        String token = authHeader.substring(7);

        String phoneNumber =
                jwtService.extractUsername(token);

        User farmer =
                userRepository
                        .findByPhoneNumber(phoneNumber)
                        .orElse(null);

        if (farmer == null) {

            return new ApiResponse(
                    false,
                    "Farmer not found.",
                    null);
        }

        var orders =
                orderRepository
                        .findByProductFarmerProfileUserOrderByCreatedAtDesc(
                                farmer);

        var responseList =
                orders.stream()
                        .map(this::mapOrderToResponse)
                        .toList();

        return new ApiResponse(
                true,
                "Farmer orders fetched successfully.",
                responseList);
    }

    @Override
    @Transactional
    public ApiResponse updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequest request,
            HttpServletRequest httpServletRequest) {

        String authHeader =
                httpServletRequest.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            return new ApiResponse(
                    false,
                    "Authorization token is missing.",
                    null);
        }

        String token = authHeader.substring(7);

        String phoneNumber =
                jwtService.extractUsername(token);

        User farmer =
                userRepository
                        .findByPhoneNumber(phoneNumber)
                        .orElse(null);

        if (farmer == null) {

            return new ApiResponse(
                    false,
                    "Farmer not found.",
                    null);
        }

        Order order =
                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if (order == null) {

            return new ApiResponse(
                    false,
                    "Order not found.",
                    null);
        }

        /*
         * Ensure only the farmer who owns
         * this product can update this order.
         */
        User productOwner =
                order.getProduct()
                        .getFarmerProfile()
                        .getUser();

        if (productOwner == null
                || !productOwner.getId()
                        .equals(farmer.getId())) {

            return new ApiResponse(
                    false,
                    "You are not authorized to update this order.",
                    null);
        }

        /*
         * Only pending orders can be processed.
         */
        if (order.getOrderStatus()
                != OrderStatus.PENDING) {

            return new ApiResponse(
                    false,
                    "Only pending orders can be updated.",
                    null);
        }

        if (request.getStatus()
                == OrderStatus.ACCEPTED) {

            order.setOrderStatus(
                    OrderStatus.ACCEPTED);

            order.setRejectionReason(null);

        } else if (request.getStatus()
                == OrderStatus.REJECTED) {

            if (request.getRejectionReason() == null
                    || request.getRejectionReason()
                            .trim()
                            .isEmpty()) {

                return new ApiResponse(
                        false,
                        "Rejection reason is required.",
                        null);
            }

            order.setOrderStatus(
                    OrderStatus.REJECTED);

            order.setRejectionReason(
                    request.getRejectionReason().trim());

        } else {

            return new ApiResponse(
                    false,
                    "Status must be ACCEPTED or REJECTED.",
                    null);
        }

        Order savedOrder =
                orderRepository.save(order);

        OrderResponse response =
                mapOrderToResponse(savedOrder);

        return new ApiResponse(
                true,
                savedOrder.getOrderStatus()
                        == OrderStatus.ACCEPTED
                        ? "Order accepted successfully."
                        : "Order rejected successfully.",
                response);
    }

    /*
     * Common mapping method.
     *
     * Used by:
     * placeOrder
     * getMyOrders
     * getFarmerOrders
     * updateOrderStatus
     */
    private OrderResponse mapOrderToResponse(
            Order order) {

        OrderResponse response =
                new OrderResponse();

        response.setOrderId(
                order.getId());

        response.setProductName(
                order.getProduct()
                        .getProductName());

        response.setFarmerName(
                order.getProduct()
                        .getFarmerProfile()
                        .getFarmerName());

        if (order.getCustomer() != null) {

            response.setCustomerName(
                    order.getCustomer()
                            .getFullName());

            response.setCustomerPhoneNumber(
                    order.getCustomer()
                            .getPhoneNumber());
        }

        response.setQuantity(
                order.getQuantity());

        response.setPricePerUnit(
                order.getPricePerUnit());

        response.setTotalAmount(
                order.getTotalAmount());

        response.setOrderStatus(
                order.getOrderStatus().name());

        response.setRejectionReason(
                order.getRejectionReason());

        response.setOrderDate(
                order.getCreatedAt() != null
                        ? order.getCreatedAt().toString()
                        : null);

        /*
         * Delivery address snapshot
         */
        response.setDeliveryAddressLine1(
                order.getDeliveryAddressLine1());

        response.setDeliveryAddressLine2(
                order.getDeliveryAddressLine2());

        response.setDeliveryVillage(
                order.getDeliveryVillage());

        response.setDeliveryMandal(
                order.getDeliveryMandal());

        response.setDeliveryDistrict(
                order.getDeliveryDistrict());

        response.setDeliveryState(
                order.getDeliveryState());

        response.setDeliveryPincode(
                order.getDeliveryPincode());

        response.setDeliveryLandmark(
                order.getDeliveryLandmark());

        return response;
    }
}