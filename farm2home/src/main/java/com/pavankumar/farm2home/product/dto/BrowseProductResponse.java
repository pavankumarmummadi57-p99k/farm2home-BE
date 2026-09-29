//package com.pavankumar.farm2home.product.dto;
//
//import java.math.BigDecimal;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class BrowseProductResponse {
//
//    private Long productId;
//
//    private String productName;
//
//    private String categoryName;
//
//    private String farmerName;
//
//    private BigDecimal price;
//
//    private String unit;
//
//    private String imagePath;
//}




package com.pavankumar.farm2home.product.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseProductResponse {

    private Long productId;
    private String productName;
    private String categoryName;
    private String farmerName;
    private BigDecimal price;
    private String unit;
    private String imagePath;

    // Farmer-controlled product availability.
    private Boolean isAvailable;
}
