package com.mabaya.ads.service;

import com.mabaya.ads.model.Campaign;
import com.mabaya.ads.model.Category;
import com.mabaya.ads.model.Product;
import com.mabaya.ads.repository.CampaignRepository;
import com.mabaya.ads.repository.CategoryRepository;
import com.mabaya.ads.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdsService {

    private static final Logger logger = LogManager.getLogger(AdsService.class);

    private final ProductRepository productRepository;

    private final CampaignRepository campaignRepository;

    private final CategoryRepository categoryRepository;

    public AdsService(ProductRepository productRepository,
                      CampaignRepository campaignRepository,
                      CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.categoryRepository = categoryRepository;
    }

    public Campaign createCampaign(String name, Date startDate,
                                   String categoryCode, double bid) throws NoSuchElementException {

        Category category = categoryRepository
                .findByCode(categoryCode).orElseThrow(NoSuchElementException::new);

        List<Product> productsByCategory =
                new ArrayList<>(productRepository.getProductsByCategory(category));
        if(!productsByCategory.isEmpty()) {
            long ts = startDate.toInstant().toEpochMilli();
            Campaign save = campaignRepository.save(Campaign.of(name, ts, category, bid, productsByCategory));
            logger.info("Campaign " + name +
                    " created with category " + category +
                    " contains " + productsByCategory.size() + " products");
            return save;
        } else {
            logger.error("No products found for Category " + category);
        }
        return null;
    }

    public Product serveAd(String categoryCode) throws NoSuchElementException{
        long nowTs = System.currentTimeMillis();

        Category category = categoryRepository
                .findByCode(categoryCode).orElseThrow(NoSuchElementException::new);

        // find active campaigns with matching category
        List<Campaign> campaigns =
                new ArrayList<>(campaignRepository
                        .findByEndDateTsGreaterThanEqualAndCategoryIs(nowTs, category));

        // if list is empty find active campaigns ignore category
        if(campaigns.isEmpty()) {
            campaigns.addAll(campaignRepository
                    .findByEndDateTsGreaterThanEqual(nowTs));
        }

        // find campaign with highest bid
        Campaign campaign = campaigns.stream()
                .max(Comparator.comparing(Campaign::getBid))
                .orElseThrow(NoSuchElementException::new);

        // return random product from highest bid campaign
        return campaign.getProducts()
                .stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
