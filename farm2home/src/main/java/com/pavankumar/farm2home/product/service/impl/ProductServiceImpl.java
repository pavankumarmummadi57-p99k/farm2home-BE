//package com.pavankumar.farm2home.product.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//
//import com.pavankumar.farm2home.category.repository.CategoryRepository;
//import com.pavankumar.farm2home.common.dto.ApiResponse;
//import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
//import com.pavankumar.farm2home.farmer.repository.FarmerProfileRepository;
//import com.pavankumar.farm2home.farmer.repository.FarmerVerificationRepository;
//import com.pavankumar.farm2home.product.dto.AddProductRequest;
//import com.pavankumar.farm2home.product.dto.BrowseProductResponse;
//import com.pavankumar.farm2home.product.dto.ProductDetailsResponse;
//import com.pavankumar.farm2home.product.dto.ProductResponse;
//import com.pavankumar.farm2home.product.repository.ProductRepository;
//import com.pavankumar.farm2home.product.service.ProductService;
//import com.pavankumar.farm2home.security.jwt.JwtService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import com.pavankumar.farm2home.category.entity.Category;
//import com.pavankumar.farm2home.farmer.entity.FarmerVerification;
//import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
//import com.pavankumar.farm2home.product.entity.Product;
//import com.pavankumar.farm2home.product.enums.Unit;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import com.pavankumar.farm2home.product.entity.Product;
//
//import com.pavankumar.farm2home.common.file.FileStorageService;
//import com.pavankumar.farm2home.product.dto.UploadProductImagesResponse;
//import com.pavankumar.farm2home.product.entity.ProductImage;
//import com.pavankumar.farm2home.product.repository.ProductImageRepository;
//import java.util.ArrayList;
//import java.util.List; 
//import com.pavankumar.farm2home.product.dto.ProductSummaryResponse;
//import com.pavankumar.farm2home.product.dto.UpdateProductRequest;
//import com.pavankumar.farm2home.product.entity.ProductImage;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    private final ProductRepository productRepository;
//    private final CategoryRepository categoryRepository;
//    private final FarmerProfileRepository farmerProfileRepository;
//    private final FarmerVerificationRepository verificationRepository;
//    private final JwtService jwtService;
//    
//    private final ProductImageRepository productImageRepository;
//    private final FileStorageService fileStorageService;
//
//    public ProductServiceImpl(
//            ProductRepository productRepository,
//            CategoryRepository categoryRepository,
//            FarmerProfileRepository farmerProfileRepository,
//            FarmerVerificationRepository verificationRepository,
//            JwtService jwtService,
//            ProductImageRepository productImageRepository,
//            FileStorageService fileStorageService) {
//
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.farmerProfileRepository = farmerProfileRepository;
//        this.verificationRepository = verificationRepository;
//        this.jwtService = jwtService;
//        this.productImageRepository = productImageRepository;
//        this.fileStorageService = fileStorageService;
//    }
//
//    @Override
//    public ApiResponse addProduct(
//            AddProductRequest request,
//            HttpServletRequest httpServletRequest) {
//
//        // Extract JWT Token
//        String authHeader = httpServletRequest.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return new ApiResponse(false, "Authorization token is missing.", null);
//        }
//
//        String token = authHeader.substring(7);
//
//        // Extract Phone Number
//        String phoneNumber = jwtService.extractUsername(token);
//
//        // Find Farmer Profile
//        FarmerProfile farmer = farmerProfileRepository
//                .findByPhoneNumber(phoneNumber)
//                .orElse(null);
//
//        if (farmer == null) {
//            return new ApiResponse(false,
//                    "Farmer profile not found.",
//                    null);
//        }
//
//        // 🔥 Improvement 3



package com.pavankumar.farm2home.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pavankumar.farm2home.category.entity.Category;
import com.pavankumar.farm2home.category.repository.CategoryRepository;
import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.common.file.FileStorageService;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.entity.FarmerVerification;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.farmer.repository.FarmerProfileRepository;
import com.pavankumar.farm2home.farmer.repository.FarmerVerificationRepository;
import com.pavankumar.farm2home.product.dto.AddProductRequest;
import com.pavankumar.farm2home.product.dto.BrowseProductResponse;
import com.pavankumar.farm2home.product.dto.ProductDetailsResponse;
import com.pavankumar.farm2home.product.dto.ProductResponse;
import com.pavankumar.farm2home.product.dto.ProductSummaryResponse;
import com.pavankumar.farm2home.product.dto.UpdateProductRequest;
import com.pavankumar.farm2home.product.dto.UploadProductImagesResponse;
import com.pavankumar.farm2home.product.entity.Product;
import com.pavankumar.farm2home.product.entity.ProductImage;
import com.pavankumar.farm2home.product.enums.Unit;
import com.pavankumar.farm2home.product.repository.ProductImageRepository;
import com.pavankumar.farm2home.product.repository.ProductRepository;
import com.pavankumar.farm2home.product.service.ProductService;
import com.pavankumar.farm2home.security.jwt.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FarmerProfileRepository farmerProfileRepository;
    private final FarmerVerificationRepository verificationRepository;
    private final JwtService jwtService;
    private final ProductImageRepository productImageRepository;
    private final FileStorageService fileStorageService;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            FarmerProfileRepository farmerProfileRepository,
            FarmerVerificationRepository verificationRepository,
            JwtService jwtService,
            ProductImageRepository productImageRepository,
            FileStorageService fileStorageService) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.farmerProfileRepository = farmerProfileRepository;
        this.verificationRepository = verificationRepository;
        this.jwtService = jwtService;
        this.productImageRepository = productImageRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ApiResponse addProduct(
            AddProductRequest request,
            HttpServletRequest httpServletRequest) {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ApiResponse(false, "Authorization token is missing.", null);
        }

        String token = authHeader.substring(7);
        String phoneNumber = jwtService.extractUsername(token);

        FarmerProfile farmer = farmerProfileRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        FarmerVerification verification = verificationRepository
                .findByFarmerProfile(farmer)
                .orElse(null);

        if (verification == null
                || verification.getVerificationStatus() != VerificationStatus.APPROVED) {

            return new ApiResponse(
                    false,
                    "Your account is not approved by admin.",
                    null);
        }

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return new ApiResponse(false, "Category not found.", null);
        }

        Product product = new Product();

        product.setFarmerProfile(farmer);
        product.setCategory(category);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUnit(Unit.valueOf(request.getUnit().toUpperCase()));
        product.setAvailableQuantity(request.getAvailableQuantity());
        product.setMinimumOrderQuantity(request.getMinimumOrderQuantity());

        // New products start as Available.
        product.setIsAvailable(true);

        productRepository.save(product);

        ProductResponse response = new ProductResponse();

        response.setProductId(product.getId());
        response.setFarmerName(farmer.getFarmerName());
        response.setCategoryName(category.getName());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setUnit(product.getUnit().name());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setMinimumOrderQuantity(product.getMinimumOrderQuantity());
        response.setIsAvailable(product.getIsAvailable());

        return new ApiResponse(
                true,
                "Product added successfully.",
                response);
    }

    @Override
    public ApiResponse uploadProductImages(
            Long productId,
            MultipartFile[] images,
            HttpServletRequest httpServletRequest) {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ApiResponse(false, "Authorization token is missing.", null);
        }

        String token = authHeader.substring(7);
        String phoneNumber = jwtService.extractUsername(token);

        FarmerProfile farmer = farmerProfileRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        Product product = productRepository
                .findById(productId)
                .orElse(null);

        if (product == null) {
            return new ApiResponse(false, "Product not found.", null);
        }

        if (!product.getFarmerProfile().getId().equals(farmer.getId())) {
            return new ApiResponse(
                    false,
                    "You are not authorized to upload images for this product.",
                    null);
        }

        if (images == null || images.length == 0) {
            return new ApiResponse(false, "Please upload at least one image.", null);
        }

        if (images.length > 5) {
            return new ApiResponse(false, "Maximum 5 images are allowed.", null);
        }

        UploadProductImagesResponse response = new UploadProductImagesResponse();
        List<String> imagePaths = new ArrayList<>();

        int displayOrder = 1;

        for (MultipartFile image : images) {

            String imagePath =
                    fileStorageService.storeFile(image, "product");

            ProductImage productImage = new ProductImage();

            productImage.setProduct(product);
            productImage.setImagePath(imagePath);
            productImage.setDisplayOrder(displayOrder++);

            productImageRepository.save(productImage);
            imagePaths.add(imagePath);
        }

        response.setProductId(product.getId());
        response.setImagePaths(imagePaths);

        return new ApiResponse(
                true,
                "Product images uploaded successfully.",
                response);
    }

    @Override
    public ApiResponse getMyProducts(
            HttpServletRequest httpServletRequest) {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ApiResponse(false, "Authorization token is missing.", null);
        }

        String token = authHeader.substring(7);
        String phoneNumber = jwtService.extractUsername(token);

        FarmerProfile farmer = farmerProfileRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        List<Product> products =
                productRepository.findByFarmerProfile(farmer);

        List<ProductSummaryResponse> responseList =
                new ArrayList<>();

        for (Product product : products) {

            ProductSummaryResponse response =
                    new ProductSummaryResponse();

            response.setProductId(product.getId());
            response.setProductName(product.getProductName());
            response.setDescription(product.getDescription());
            response.setPrice(product.getPrice());
            response.setUnit(product.getUnit().name());
            response.setAvailableQuantity(product.getAvailableQuantity());
            response.setIsAvailable(product.getIsAvailable());

            List<ProductImage> productImages =
                    productImageRepository.findByProduct(product);

            List<String> imagePaths = new ArrayList<>();

            for (ProductImage image : productImages) {
                imagePaths.add(image.getImagePath());
            }

            response.setImagePaths(imagePaths);
            responseList.add(response);
        }

        return new ApiResponse(
                true,
                "My products fetched successfully.",
                responseList);
    }

    @Override
    public ApiResponse getProductDetails(Long productId) {

        Product product =
                productRepository.findById(productId).orElse(null);

        if (product == null) {
            return new ApiResponse(false, "Product not found.", null);
        }

        ProductDetailsResponse response =
                new ProductDetailsResponse();

        response.setProductId(product.getId());
        response.setFarmerName(product.getFarmerProfile().getFarmerName());
        response.setCategoryName(product.getCategory().getName());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setUnit(product.getUnit().name());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setMinimumOrderQuantity(product.getMinimumOrderQuantity());
        response.setIsAvailable(product.getIsAvailable());

        List<ProductImage> productImages =
                productImageRepository.findByProduct(product);

        List<String> imagePaths = new ArrayList<>();

        for (ProductImage image : productImages) {
            imagePaths.add(image.getImagePath());
        }

        response.setImagePaths(imagePaths);

        return new ApiResponse(
                true,
                "Product details fetched successfully.",
                response);
    }

    @Override
    public ApiResponse updateProduct(
            Long productId,
            UpdateProductRequest request,
            HttpServletRequest httpServletRequest) {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ApiResponse(false, "Authorization token is missing.", null);
        }

        String token = authHeader.substring(7);
        String phoneNumber = jwtService.extractUsername(token);

        FarmerProfile farmer = farmerProfileRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        Product product =
                productRepository.findById(productId).orElse(null);

        if (product == null) {
            return new ApiResponse(false, "Product not found.", null);
        }

        if (!product.getFarmerProfile().getId().equals(farmer.getId())) {
            return new ApiResponse(
                    false,
                    "You are not authorized to update this product.",
                    null);
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getAvailableQuantity() != null) {
            product.setAvailableQuantity(request.getAvailableQuantity());
        }

        if (request.getMinimumOrderQuantity() != null) {
            product.setMinimumOrderQuantity(request.getMinimumOrderQuantity());
        }

        if (request.getIsAvailable() != null) {
            product.setIsAvailable(request.getIsAvailable());
        }

        productRepository.save(product);

        return new ApiResponse(
                true,
                "Product updated successfully.",
                null);
    }

    @Override
    public ApiResponse deleteProduct(
            Long productId,
            HttpServletRequest httpServletRequest) {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ApiResponse(false, "Authorization token is missing.", null);
        }

        String token = authHeader.substring(7);
        String phoneNumber = jwtService.extractUsername(token);

        FarmerProfile farmer = farmerProfileRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        Product product =
                productRepository.findById(productId).orElse(null);

        if (product == null) {
            return new ApiResponse(false, "Product not found.", null);
        }

        if (!product.getFarmerProfile().getId().equals(farmer.getId())) {
            return new ApiResponse(
                    false,
                    "You are not authorized to delete this product.",
                    null);
        }

        List<ProductImage> productImages =
                productImageRepository.findByProduct(product);

        productImageRepository.deleteAll(productImages);
        productRepository.delete(product);

        return new ApiResponse(
                true,
                "Product deleted successfully.",
                null);
    }

    @Override
    public ApiResponse browseProducts() {

        // Return both Available and Unavailable products.
        List<Product> products = productRepository.findAll();

        List<BrowseProductResponse> responseList =
                new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapBrowseProduct(product));
        }

        return new ApiResponse(
                true,
                "Products fetched successfully.",
                responseList);
    }

    @Override
    public ApiResponse browseProductsByCategory(Long categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElse(null);

        if (category == null) {
            return new ApiResponse(false, "Category not found.", null);
        }

        // Return both Available and Unavailable products.
        List<Product> products =
                productRepository.findByCategory(category);

        List<BrowseProductResponse> responseList =
                new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapBrowseProduct(product));
        }

        return new ApiResponse(
                true,
                "Products fetched by category successfully.",
                responseList);
    }

    @Override
    public ApiResponse searchProducts(String keyword) {

        // Search both Available and Unavailable products.
        List<Product> products =
                productRepository
                        .findByProductNameContainingIgnoreCase(keyword);

        List<BrowseProductResponse> responseList =
                new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapBrowseProduct(product));
        }

        return new ApiResponse(
                true,
                "Products searched successfully.",
                responseList);
    }

    private BrowseProductResponse mapBrowseProduct(Product product) {

        BrowseProductResponse response =
                new BrowseProductResponse();

        response.setProductId(product.getId());
        response.setProductName(product.getProductName());
        response.setCategoryName(product.getCategory().getName());
        response.setFarmerName(product.getFarmerProfile().getFarmerName());
        response.setPrice(product.getPrice());
        response.setUnit(product.getUnit().name());
        response.setIsAvailable(product.getIsAvailable());

        List<ProductImage> images =
                productImageRepository.findByProduct(product);

        if (!images.isEmpty()) {
            response.setImagePath(images.get(0).getImagePath());
        }

        return response;
    }
}

//        // Check Verification Status
//        FarmerVerification verification = verificationRepository
//                .findByFarmerProfile(farmer)
//                .orElse(null);
//
//        if (verification == null ||
//                verification.getVerificationStatus() != VerificationStatus.APPROVED) {
//
//            return new ApiResponse(false,
//                    "Your account is not approved by admin.",
//                    null);
//        }
//
//        // Find Category
//        Category category = categoryRepository
//                .findById(request.getCategoryId())
//                .orElse(null);
//
//        if (category == null) {
//            return new ApiResponse(false,
//                    "Category not found.",
//                    null);
//        }
//
//        // Create Product
//        Product product = new Product();
//
//        product.setFarmerProfile(farmer);
//        product.setCategory(category);
//        product.setProductName(request.getProductName());
//        product.setDescription(request.getDescription());
//        product.setPrice(request.getPrice());
//        product.setUnit(Unit.valueOf(request.getUnit().toUpperCase()));
//        product.setAvailableQuantity(request.getAvailableQuantity());
//        product.setMinimumOrderQuantity(request.getMinimumOrderQuantity());
//        product.setIsAvailable(true);
//
//        productRepository.save(product);
//
//        // Response
//        ProductResponse response = new ProductResponse();
//
//        response.setProductId(product.getId());
//        response.setFarmerName(farmer.getFarmerName());
//        response.setCategoryName(category.getName());
//        response.setProductName(product.getProductName());
//        response.setDescription(product.getDescription());
//        response.setPrice(product.getPrice());
//        response.setUnit(product.getUnit().name());
//        response.setAvailableQuantity(product.getAvailableQuantity());
//        response.setMinimumOrderQuantity(product.getMinimumOrderQuantity());
//        response.setIsAvailable(product.getIsAvailable());
//
//        return new ApiResponse(
//                true,
//                "Product added successfully.",
//                response);
//    }
//    
//    @Override
//    public ApiResponse uploadProductImages(
//            Long productId,
//            MultipartFile[] images,
//            HttpServletRequest httpServletRequest) {
//
//        // Extract JWT Token
//        String authHeader = httpServletRequest.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return new ApiResponse(
//                    false,
//                    "Authorization token is missing.",
//                    null);
//        }
//
//        String token = authHeader.substring(7);
//
//        // Extract Phone Number
//        String phoneNumber = jwtService.extractUsername(token);
//
//        // Find Farmer
//        FarmerProfile farmer = farmerProfileRepository
//                .findByPhoneNumber(phoneNumber)
//                .orElse(null);
//
//        if (farmer == null) {
//            return new ApiResponse(
//                    false,
//                    "Farmer profile not found.",
//                    null);
//        }
//
//        // Find Product
//        Product product = productRepository
//                .findById(productId)
//                .orElse(null);
//
//        if (product == null) {
//            return new ApiResponse(
//                    false,
//                    "Product not found.",
//                    null);
//        }
//
//        // Verify Product Owner
//        if (!product.getFarmerProfile().getId().equals(farmer.getId())) {
//
//            return new ApiResponse(
//                    false,
//                    "You are not authorized to upload images for this product.",
//                    null);
//        }
//
//        // Validate Images
//        if (images == null || images.length == 0) {
//
//            return new ApiResponse(
//                    false,
//                    "Please upload at least one image.",
//                    null);
//        }
//
//        if (images.length > 5) {
//
//            return new ApiResponse(
//                    false,
//                    "Maximum 5 images are allowed.",
//                    null);
//        }
//
//        UploadProductImagesResponse response = new UploadProductImagesResponse();
//
//        List<String> imagePaths = new ArrayList<>();
//
//        int displayOrder = 1;
//
//        for (MultipartFile image : images) {
//
//            String imagePath =
//                    fileStorageService.storeFile(image, "product");
//
//            ProductImage productImage = new ProductImage();
//
//            productImage.setProduct(product);
//            productImage.setImagePath(imagePath);
//            productImage.setDisplayOrder(displayOrder++);
//
//            productImageRepository.save(productImage);
//
//            imagePaths.add(imagePath);
//        }
//
//        response.setProductId(product.getId());
//        response.setImagePaths(imagePaths);
//
//        return new ApiResponse(
//                true,
//                "Product images uploaded successfully.",
//                response);
//        
//        
//    }
//    
//    @Override 
//    public ApiResponse getMyProducts(HttpServletRequest httpServletRequest) {
//    	// Extract JWT Token
//    	String authHeader = httpServletRequest.getHeader("Authorization"); 
//    	if (authHeader == null || !authHeader.startsWith("Bearer ")) { 
//    		return new ApiResponse(false, "Authorization token is missing.", null); 
//    		}
//    	String token = authHeader.substring(7); 
//    	// Extract Phone Number 
//    	String phoneNumber = jwtService.extractUsername(token); 
//    	// Find Farmer
//    	FarmerProfile farmer = farmerProfileRepository .findByPhoneNumber(phoneNumber) .orElse(null); 
//    	if (farmer == null) { 
//    		return new ApiResponse(false, "Farmer profile not found.", null);
//    		}
//    	List<Product> products = productRepository.findByFarmerProfile(farmer); 
//    	List<ProductSummaryResponse> responseList = new ArrayList<>(); 
//    	for (Product product : products) { 
//    		ProductSummaryResponse response = new ProductSummaryResponse(); 
//    		response.setProductId(product.getId());
//    		response.setProductName(product.getProductName());
//    		response.setDescription(product.getDescription()); 
//    		response.setPrice(product.getPrice()); 
//    		response.setUnit(product.getUnit().name());
//    		response.setAvailableQuantity(product.getAvailableQuantity()); 
//    		response.setIsAvailable(product.getIsAvailable());
//    		// Load Images 
//    		List<ProductImage> productImages = productImageRepository.findByProduct(product); 
//    		List<String> imagePaths = new ArrayList<>(); 
//    		for (ProductImage image : productImages) {
//    			imagePaths.add(image.getImagePath()); 
//    			} response.setImagePaths(imagePaths); 
//    			responseList.add(response); 
//    			} 
//    	return new ApiResponse( true, "My products fetched successfully.", responseList); 
//    	}
//    	
//    @Override 
//    public ApiResponse getProductDetails(Long productId) {
//    	Product product = productRepository.findById(productId).orElse(null); 
//    	if (product == null) {
//    		return new ApiResponse(false, "Product not found.", null); 
//    	}
//    	ProductDetailsResponse response = new ProductDetailsResponse();
//    	response.setProductId(product.getId()); 
//    	response.setFarmerName(product.getFarmerProfile().getFarmerName());
//    	response.setCategoryName(product.getCategory().getName()); 
//    	response.setProductName(product.getProductName()); 
//    	response.setDescription(product.getDescription()); 
//    	response.setPrice(product.getPrice());
//    	response.setUnit(product.getUnit().name());
//    	response.setAvailableQuantity(product.getAvailableQuantity()); 
//    	response.setMinimumOrderQuantity(product.getMinimumOrderQuantity()); 
//    	response.setIsAvailable(product.getIsAvailable()); 
//    	List<ProductImage> productImages = productImageRepository.findByProduct(product);
//    	List<String> imagePaths = new ArrayList<>(); 
//    	for (ProductImage image : productImages) { 
//    		imagePaths.add(image.getImagePath()); 
//    	} 
//    	response.setImagePaths(imagePaths);
//    	return new ApiResponse( true, "Product details fetched successfully.", response); 
//    }
//    
//    
//    @Override 
//    public ApiResponse updateProduct( Long productId, UpdateProductRequest request, HttpServletRequest httpServletRequest) {
//    	// Extract JWT Token 
//    	String authHeader = httpServletRequest.getHeader("Authorization"); 
//    	if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//    		return new ApiResponse(false, "Authorization token is missing.", null); 
//    	} 
//    	String token = authHeader.substring(7); 
//    	// Extract Phone Number 
//    	String phoneNumber = jwtService.extractUsername(token); 
//    	// Find Farmer 
//    	FarmerProfile farmer = farmerProfileRepository .findByPhoneNumber(phoneNumber) .orElse(null); 
//    	if (farmer == null) { 
//    		return new ApiResponse(false, "Farmer profile not found.", null); 
//    	} 
//    	// Find Product 
//    	Product product = productRepository.findById(productId).orElse(null); 
//    	if (product == null) {
//    		return new ApiResponse(false, "Product not found.", null); 
//    	}
//    	// Verify Owner
//    	if (!product.getFarmerProfile().getId().equals(farmer.getId())) {
//    		return new ApiResponse(false, "You are not authorized to update this product.", null);
//    	}
//    	// Update fields 
//    	if (request.getDescription() != null) { 
//    		product.setDescription(request.getDescription()); 
//    	}
//    	if (request.getPrice() != null) { 
//    		product.setPrice(request.getPrice()); 
//    	} 
//    	if (request.getAvailableQuantity() != null) {
//    		product.setAvailableQuantity(request.getAvailableQuantity()); 
//    	}
//    	if (request.getMinimumOrderQuantity() != null) { 
//    		product.setMinimumOrderQuantity(request.getMinimumOrderQuantity()); 
//    	}
//    	if (request.getIsAvailable() != null) {
//    		product.setIsAvailable(request.getIsAvailable()); 
//    	} productRepository.save(product);
//    	return new ApiResponse(true, "Product updated successfully.", null); 
//    	}
//    
//    @Override 
//    public ApiResponse deleteProduct( Long productId, HttpServletRequest httpServletRequest) {
//    	// Extract JWT Token 
//    	String authHeader = httpServletRequest.getHeader("Authorization");
//    	if (authHeader == null || !authHeader.startsWith("Bearer ")) { 
//    		return new ApiResponse(false, "Authorization token is missing.", null);
//    	} 
//    	String token = authHeader.substring(7);
//    	// Extract Phone Number 
//    	String phoneNumber = jwtService.extractUsername(token); 
//    	// Find Farmer 
//    	FarmerProfile farmer = farmerProfileRepository .findByPhoneNumber(phoneNumber) .orElse(null);
//    	if (farmer == null) {
//    		return new ApiResponse(false, "Farmer profile not found.", null); 
//    	} 
//    	// Find Product
//    	Product product = productRepository.findById(productId).orElse(null); 
//    	if (product == null) {
//    		return new ApiResponse(false, "Product not found.", null); 
//    	} 
//    	// Verify Owner 
//    	if (!product.getFarmerProfile().getId().equals(farmer.getId())) { 
//    		return new ApiResponse(false, "You are not authorized to delete this product.", null);
//    	}
//    	// Delete image records first 
//    	List<ProductImage> productImages = productImageRepository.findByProduct(product);
//    	productImageRepository.deleteAll(productImages); 
//    	// Delete product
//    	productRepository.delete(product);
//    	return new ApiResponse(true, "Product deleted successfully.", null);
//    	}
//    
//    
//    @Override 
//    public ApiResponse browseProducts() { 
//    	List<Product> products = productRepository.findByIsAvailableTrue(); 
//    	List<BrowseProductResponse> responseList = new ArrayList<>(); 
//    	for (Product product : products) { 
//    		BrowseProductResponse response = new BrowseProductResponse(); 
//    		response.setProductId(product.getId());
//    		response.setProductName(product.getProductName());
//    		response.setCategoryName(product.getCategory().getName());
//    		response.setFarmerName(product.getFarmerProfile().getFarmerName()); 
//    		response.setPrice(product.getPrice());
//    		response.setUnit(product.getUnit().name()); 
//    		// First image only 
//    		List<ProductImage> images = productImageRepository.findByProduct(product); 
//    		if (!images.isEmpty()) { 
//    			response.setImagePath(images.get(0).getImagePath()); 
//    		} responseList.add(response);
//    	} return new ApiResponse( true, "Products fetched successfully.", responseList); 
//    	
//    }
//    
//    @Override 
//    public ApiResponse browseProductsByCategory(Long categoryId) {
//    	Category category = categoryRepository.findById(categoryId).orElse(null);
//    	if (category == null) {
//    		return new ApiResponse(false, "Category not found.", null); 
//    	} 
//    	List<Product> products = productRepository.findByCategoryAndIsAvailableTrue(category);
//    	List<BrowseProductResponse> responseList = new ArrayList<>();
//    	for (Product product : products) {
//    		BrowseProductResponse response = new BrowseProductResponse(); 
//    		response.setProductId(product.getId()); 
//    		response.setProductName(product.getProductName());
//    		response.setCategoryName(product.getCategory().getName());
//    		response.setFarmerName(product.getFarmerProfile().getFarmerName()); 
//    		response.setPrice(product.getPrice());
//    		response.setUnit(product.getUnit().name());
//    		// First image only 
//    		List<ProductImage> images = productImageRepository.findByProduct(product); 
//    		if (!images.isEmpty()) { 
//    			response.setImagePath(images.get(0).getImagePath());
//    		} responseList.add(response); 
//    	} 
//    	return new ApiResponse( true, "Products fetched by category successfully.", responseList); 
//    }
//    
//    
//    
//    @Override 
//    public ApiResponse searchProducts(String keyword) {
//    	List<Product> products = productRepository .findByProductNameContainingIgnoreCaseAndIsAvailableTrue(keyword); 
//    	List<BrowseProductResponse> responseList = new ArrayList<>(); 
//    	for (Product product : products) {
//    		BrowseProductResponse response = new BrowseProductResponse(); 
//    		response.setProductId(product.getId()); 
//    		response.setProductName(product.getProductName()); 
//    		response.setCategoryName(product.getCategory().getName());
//    		response.setFarmerName(product.getFarmerProfile().getFarmerName());
//    		response.setPrice(product.getPrice()); 
//    		response.setUnit(product.getUnit().name()); 
//    		// First image only 
//    		List<ProductImage> images = productImageRepository.findByProduct(product);
//    		if (!images.isEmpty()) { 
//    			response.setImagePath(images.get(0).getImagePath()); 
//    		} responseList.add(response); 
//    	} return new ApiResponse( true, "Products searched successfully.", responseList);
//    	}
//    	
//    }
//    	
//    
//    	
//    
//    
//    
//    
//    
//    
//
//    
//  
