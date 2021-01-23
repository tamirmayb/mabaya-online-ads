package com.mabaya.ads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "campaigns")
public class Campaign {
    private static final long TEN_DAYS_MILLIS = 10 * 24 * 60 * 60 * 1000;

    @Id
    private @NonNull
    String id = UUID.randomUUID().toString();

    @JsonProperty
    private String name;

    @JsonProperty
    private Long startDateTs;

    @JsonProperty
    private Long endDateTs;

    @JsonProperty
    private Category category;

    @JsonProperty
    private List<Product> products;

    @JsonProperty
    private double bid;

    public static Campaign of(String name, Long startDateTs, Category category,
                              double bid, List<Product> products) {

        long endDateTs = Instant.ofEpochMilli(startDateTs)
                .plusMillis(TEN_DAYS_MILLIS)
                .toEpochMilli();

        return Campaign.builder()
        .name(name)
        .startDateTs(startDateTs)
        .endDateTs(endDateTs)
        .category(category)
        .bid(bid)
        .products(products)
        .build();
    }
}