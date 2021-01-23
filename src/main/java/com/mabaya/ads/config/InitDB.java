package com.mabaya.ads.config;

import com.mabaya.ads.model.Category;
import com.mabaya.ads.model.Product;
import com.mabaya.ads.repository.CampaignRepository;
import com.mabaya.ads.repository.CategoryRepository;
import com.mabaya.ads.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class InitDB {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final CampaignRepository campaignRepository;

    public InitDB(ProductRepository productRepository, CategoryRepository categoryRepository, CampaignRepository campaignRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.campaignRepository = campaignRepository;
    }


    @PostConstruct
    public void initDB() {
        campaignRepository.deleteAll();
        categoryRepository.deleteAll();
        Category smpn = categoryRepository.save(Category.of("SMPN", "Smart Phones"));
        Category shoe = categoryRepository.save(Category.of("SHOE", "Shoes"));
        Category shrt = categoryRepository.save(Category.of("SHRT", "Shirts"));

        productRepository.deleteAll();
        productRepository.save(Product.of("iphone 11", smpn, 369.99));
        productRepository.save(Product.of("iphone 8", smpn, 150.65));
        productRepository.save(Product.of("iphone X", smpn, 250.99));

        productRepository.save(Product.of("Man's shoe", shoe, 10.65));
        productRepository.save(Product.of("Women's shoe", shoe, 15.69));
        productRepository.save(Product.of("Kid's shoe", shoe, 2.99));

        productRepository.save(Product.of("Man's shirt", shrt, 24.90));
        productRepository.save(Product.of("Women's shirt", shrt, 12.80));
        productRepository.save(Product.of("Kid's shirt", shrt, 7.99));
    }
}
