package com.mabaya.ads.controller;

import com.mabaya.ads.model.Campaign;
import com.mabaya.ads.model.Category;
import com.mabaya.ads.model.Product;
import com.mabaya.ads.repository.CampaignRepository;
import com.mabaya.ads.repository.CategoryRepository;
import com.mabaya.ads.service.AdsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="ads")
public class AdsController {

    private static Logger logger = LogManager.getLogger(AdsController.class);

    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    private AdsService adsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping("/create-campaign")
    @ApiOperation(value = "",  notes = "for start date use dd.MM.yyyy format")
    public ResponseEntity<Campaign> createCampaign(@RequestParam(name = "Campaign Name") String name,
                                                   @RequestParam(name = "Start Date (dd.MM.yyyy)") String startDate,
                                                   @RequestParam(name = "Category Code") String categoryCode,
                                                   @RequestParam double bid) {
        try {
            return ResponseEntity.ok(adsService.createCampaign(name, FORMATTER.parse(startDate), categoryCode, bid));
        } catch (Exception e) {
            logger.warn("Caught exception in createHouseStaticData: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/serve/{categoryCode}")
    @ApiOperation(value = "Serve product for ad according to serve logic",  notes = "")
    public ResponseEntity<Product> serve(@PathVariable String categoryCode) {
        try {
            return ResponseEntity.ok(adsService.serveAd(categoryCode));
        } catch (NoSuchElementException e) {
            logger.warn("Category Code: " + categoryCode + " does not exist or has no campaigns");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.warn("Caught exception in all categories: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "Gets all campaigns without filtering (for your convenience)",  notes = "")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        try {
            return ResponseEntity.ok(campaignRepository.findAll());
        } catch (Exception e) {
            logger.warn("Caught exception in all campaigns: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}