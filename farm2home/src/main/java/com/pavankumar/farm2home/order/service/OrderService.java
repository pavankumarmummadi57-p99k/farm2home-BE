//package com.pavankumar.farm2home.order.service; 
//import com.pavankumar.farm2home.common.dto.ApiResponse;
//import com.pavankumar.farm2home.order.dto.PlaceOrderRequest;
//import jakarta.servlet.http.HttpServletRequest;
//public interface OrderService {
//	ApiResponse placeOrder(PlaceOrderRequest request, HttpServletRequest httpServletRequest); 
//	ApiResponse getMyOrders(HttpServletRequest httpServletRequest);
//	ApiResponse getFarmerOrders(HttpServletRequest httpServletRequest);
//}


package com.pavankumar.farm2home.order.service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.order.dto.PlaceOrderRequest;
import com.pavankumar.farm2home.order.dto.UpdateOrderStatusRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {

    ApiResponse placeOrder(
            PlaceOrderRequest request,
            HttpServletRequest httpServletRequest);

    ApiResponse getMyOrders(
            HttpServletRequest httpServletRequest);

    ApiResponse getFarmerOrders(
            HttpServletRequest httpServletRequest);

    ApiResponse updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequest request,
            HttpServletRequest httpServletRequest);
}