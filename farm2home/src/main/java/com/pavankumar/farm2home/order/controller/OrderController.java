//package com.pavankumar.farm2home.order.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pavankumar.farm2home.common.dto.ApiResponse;
//import com.pavankumar.farm2home.order.dto.PlaceOrderRequest;
//import com.pavankumar.farm2home.order.service.OrderService;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ApiResponse> placeOrder(
//            @RequestBody PlaceOrderRequest request,
//            HttpServletRequest httpServletRequest) {
//
//        ApiResponse response = orderService.placeOrder(request, httpServletRequest);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    
//    
//    @GetMapping("/my-orders") 
//    public ResponseEntity<ApiResponse> getMyOrders(HttpServletRequest httpServletRequest) { 
//    	ApiResponse response = orderService.getMyOrders(httpServletRequest);
//    	return new ResponseEntity<>(response, HttpStatus.OK); 
//    }
//    
//    @GetMapping("/farmer-orders") 
//    public ResponseEntity<ApiResponse> getFarmerOrders(HttpServletRequest httpServletRequest) {
//    	ApiResponse response = orderService.getFarmerOrders(httpServletRequest); 
//        return new ResponseEntity<>(response, HttpStatus.OK);
//      }
//}



package com.pavankumar.farm2home.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.order.dto.PlaceOrderRequest;
import com.pavankumar.farm2home.order.dto.UpdateOrderStatusRequest;
import com.pavankumar.farm2home.order.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> placeOrder(
            @RequestBody PlaceOrderRequest request,
            HttpServletRequest httpServletRequest) {

        ApiResponse response =
                orderService.placeOrder(
                        request,
                        httpServletRequest);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse> getMyOrders(
            HttpServletRequest httpServletRequest) {

        ApiResponse response =
                orderService.getMyOrders(
                        httpServletRequest);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @GetMapping("/farmer-orders")
    public ResponseEntity<ApiResponse> getFarmerOrders(
            HttpServletRequest httpServletRequest) {

        ApiResponse response =
                orderService.getFarmerOrders(
                        httpServletRequest);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse> updateOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request,
            HttpServletRequest httpServletRequest) {

        ApiResponse response =
                orderService.updateOrderStatus(
                        orderId,
                        request,
                        httpServletRequest);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }
}