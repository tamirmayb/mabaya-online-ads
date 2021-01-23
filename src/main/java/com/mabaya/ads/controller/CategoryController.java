package com.mabaya.ads.controller;

import com.mabaya.ads.model.Category;
import com.mabaya.ads.repository.CategoryRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="category")
public class CategoryController {

    private static Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/create")
    @ApiOperation(value = "",  notes = "")
    public ResponseEntity<Category> createCategory(@RequestParam String name,
                                                   @RequestParam String code) {
        try {
            return ResponseEntity.ok(categoryRepository.save(Category.of(name, code)));
        } catch (Exception e) {
            logger.warn("Caught exception in createCategory: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "Gets all categories without filtering (for your convenience)",  notes = "")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            return ResponseEntity.ok(categoryRepository.findAll());
        } catch (Exception e) {
            logger.warn("Caught exception in all categories: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
