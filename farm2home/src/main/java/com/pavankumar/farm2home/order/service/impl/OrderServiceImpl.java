package com.pavankumar.farm2home.order.service.impl; 
import java.math.BigDecimal; 
import org.springframework.stereotype.Service; 
import com.pavankumar.farm2home.common.dto.ApiResponse; 
import com.pavankumar.farm2home.order.dto.OrderResponse;
import com.pavankumar.farm2home.order.dto.PlaceOrderRequest; 
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
@Service public class OrderServiceImpl implements OrderService { 
	private final OrderRepository orderRepository; 
	private final ProductRepository productRepository; 
	private final UserRepository userRepository;
	private final JwtService jwtService;
	public OrderServiceImpl( OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, JwtService jwtService) { 
		 this.orderRepository = orderRepository;
		 this.productRepository = productRepository;
		 this.userRepository = userRepository; 
		 this.jwtService = jwtService; 
	}
	@Override 
	public ApiResponse placeOrder(PlaceOrderRequest request, HttpServletRequest httpServletRequest) {
		// Extract token 
		String authHeader = httpServletRequest.getHeader("Authorization"); 
		if (authHeader == null || !authHeader.startsWith("Bearer ")) { 
			return new ApiResponse(false, "Authorization token is missing.", null);
		}
		String token = authHeader.substring(7);
		String phoneNumber = jwtService.extractUsername(token);
		// Find customer
		User customer = userRepository.findByPhoneNumber(phoneNumber).orElse(null); 
		if (customer == null) {
			return new ApiResponse(false, "Customer not found.", null);
		}
		// Find product
		Product product = productRepository.findById(request.getProductId()).orElse(null);
		if (product == null) {
			return new ApiResponse(false, "Product not found.", null); 
		} if (!product.getIsAvailable()) {
			return new ApiResponse(false, "Product is not available.", null); 
		}
		// Validate minimum order quantity 
		if (request.getQuantity().compareTo(product.getMinimumOrderQuantity()) < 0) { 
			return new ApiResponse(false, "Minimum order quantity is " + product.getMinimumOrderQuantity(), null);
		}
		// Validate stock
		if (request.getQuantity().compareTo(product.getAvailableQuantity()) > 0) {
			return new ApiResponse(false, "Insufficient stock available.", null); 
		}
		BigDecimal totalAmount = product.getPrice().multiply(request.getQuantity());
		// Create order
		Order order = new Order(); 
		order.setCustomer(customer);
		order.setProduct(product); 
		order.setQuantity(request.getQuantity()); 
		order.setPricePerUnit(product.getPrice()); 
		order.setTotalAmount(totalAmount);
		order.setOrderStatus(OrderStatus.PENDING); 
		Order savedOrder = orderRepository.save(order); 
		// Response
		OrderResponse response = new OrderResponse(); 
		response.setOrderId(savedOrder.getId());
		response.setProductName(product.getProductName()); 
		response.setFarmerName(product.getFarmerProfile().getFarmerName());
		response.setQuantity(savedOrder.getQuantity());
		response.setPricePerUnit(savedOrder.getPricePerUnit()); 
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setOrderStatus(savedOrder.getOrderStatus().name()); 
		return new ApiResponse(true, "Order placed successfully.", response); 
		} 
	
	@Override 
	public ApiResponse getMyOrders(HttpServletRequest httpServletRequest) {
		// Extract token
		String authHeader = httpServletRequest.getHeader("Authorization"); 
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return new ApiResponse(false, "Authorization token is missing.", null);
		}
		String token = authHeader.substring(7); 
		String phoneNumber = jwtService.extractUsername(token); 
		// Find customer
		User customer = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
		if (customer == null) {
			return new ApiResponse(false, "Customer not found.", null); 
		}
		var orders = orderRepository.findByCustomerOrderByCreatedAtDesc(customer);
		var responseList = orders.stream().map(order -> { OrderResponse response = new OrderResponse();
		response.setOrderId(order.getId());
		response.setProductName(order.getProduct().getProductName());
		response.setFarmerName(order.getProduct().getFarmerProfile().getFarmerName());
		response.setQuantity(order.getQuantity());
		response.setPricePerUnit(order.getPricePerUnit()); 
		response.setTotalAmount(order.getTotalAmount()); 
		response.setOrderStatus(order.getOrderStatus().name());
		return response; }).toList(); 
		return new ApiResponse(true, "My orders fetched successfully.", responseList); 
		}
	
	@Override
	public ApiResponse getFarmerOrders(HttpServletRequest httpServletRequest) { 
		// Extract token 
		String authHeader = httpServletRequest.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) { 
			return new ApiResponse(false, "Authorization token is missing.", null); 
		}
		String token = authHeader.substring(7); 
		String phoneNumber = jwtService.extractUsername(token); 
		// Find farmer user
		User farmer = userRepository.findByPhoneNumber(phoneNumber).orElse(null); 
		if (farmer == null) { 
			return new ApiResponse(false, "Farmer not found.", null); 
		}
		var orders = orderRepository .findByProductFarmerProfileUserOrderByCreatedAtDesc(farmer); 
		var responseList = orders.stream().map(order -> { OrderResponse response = new OrderResponse();
		response.setOrderId(order.getId()); response.setProductName(order.getProduct().getProductName());
		response.setFarmerName(order.getProduct().getFarmerProfile().getFarmerName());
		response.setQuantity(order.getQuantity());
		response.setPricePerUnit(order.getPricePerUnit());
		response.setTotalAmount(order.getTotalAmount());
		response.setOrderStatus(order.getOrderStatus().name()); 
		return response; 
		}).toList(); 
		return new ApiResponse(true, "Farmer orders fetched successfully.", responseList);
		}
	}
	
	
	
	
