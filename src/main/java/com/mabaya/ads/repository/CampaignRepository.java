package com.mabaya.ads.repository;

import com.mabaya.ads.model.Campaign;
import com.mabaya.ads.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {
    public List<Campaign> findByEndDateTsGreaterThanEqualAndCategoryIs(long ts, Category category);
    public List<Campaign> findByEndDateTsGreaterThanEqual(long ts);
}