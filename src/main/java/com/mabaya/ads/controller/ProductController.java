package com.mabaya.ads.controller;

import com.mabaya.ads.model.Category;
import com.mabaya.ads.model.Product;
import com.mabaya.ads.repository.CategoryRepository;
import com.mabaya.ads.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="product")
public class ProductController {

    private static Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    @ApiOperation(value = "",  notes = "")
    public ResponseEntity<Product> createProduct(@RequestParam String title,
                                                  @RequestParam String categoryCode,
                                                  @RequestParam double price) {
        try {
            Category category = categoryRepository.findByCode(categoryCode)
                    .orElseThrow(IllegalArgumentException::new);
            return ResponseEntity.ok(productRepository.save(Product.of(title, category, price)));
        } catch (Exception e) {
            logger.warn("Caught exception in create product: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "Gets all products without filtering (for your convenience)",  notes = "")
    public ResponseEntity<List<Product>> getAll() {
        try {
            return ResponseEntity.ok(productRepository.findAll());
        } catch (Exception e) {
            logger.warn("Caught exception in all products: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
