package com.pavankumar.farm2home.order.repository;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import com.pavankumar.farm2home.order.entity.Order; 
import com.pavankumar.farm2home.user.entity.User; 
public interface OrderRepository extends JpaRepository<Order, Long> { 
	List<Order> findByCustomer(User customer);
	List<Order> findByCustomerOrderByCreatedAtDesc(User customer);
	List<Order> findByProductFarmerProfileUserOrderByCreatedAtDesc(User farmer);
}