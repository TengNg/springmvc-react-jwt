package com.ndt.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ndt.pojo.Product;
import com.ndt.repositories.CategoryRepository;
import com.ndt.repositories.ProductRepository;
import com.ndt.repositories.UserRepository;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private UserRepository userRepo;
	@Autowired
    private Cloudinary cloudinary;

    public List<Product> getProducts() {
        return this.productRepo.getProducts();
    }

    public List<Product> getProducts(int page, int size) {
        return this.productRepo.getProducts(page, size);
    }

    public Product getProductById(String id) {
        return this.productRepo.getProductById(id);
    }

	public long getTotalPages(int size) {
		return this.productRepo.getTotalPages(size);
	}

	public void deleteProductById(String id) {
		this.productRepo.deleteProductById(id);
	}

	public List<Product> getProductsBySellerId(String sellerId) {
		return this.productRepo.getProductsBySellerId(sellerId);
	}

    public Product addProduct(Map<String, String> params, MultipartFile image) {
		Product product = new Product();
		String name = (String) params.get("name");
		String description = (String) params.get("description");
		BigDecimal price = new BigDecimal((String) params.get("price"));
		Integer categoryId = Integer.parseInt(params.get("categoryId"));
		String sellerName = params.get("sellerName");

		product.setProductId(UUID.randomUUID().toString());
		product.setCreatedAt(new Date());
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setCategoryId(this.categoryRepo.getCategoryById(categoryId));
		product.setUserId(this.userRepo.getUserByUsername(sellerName));

		if (!image.isEmpty()) {
			try {
				Map res = this.cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
				product.setImageUrl(res.get("secure_url").toString());
			} catch (IOException ex) {
				return null;
			}
		}

		this.productRepo.addProduct(product);
		return product;
	}
}
