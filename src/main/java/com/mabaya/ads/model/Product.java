package com.mabaya.ads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private @NonNull
    String id = UUID.randomUUID().toString();

    private @NonNull
    String serialNumber = UUID.randomUUID().toString();

    @JsonProperty
    private String title;

    @JsonProperty
    private Category category;

    @JsonProperty
    private double price;

    public static Product of(String title, Category category, double price) {
        return Product.builder()
        .title(title)
        .category(category)
        .price(price)
        .build();
    }
}