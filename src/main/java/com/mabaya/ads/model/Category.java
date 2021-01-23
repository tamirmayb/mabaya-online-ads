package com.mabaya.ads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private @NonNull
    String id = UUID.randomUUID().toString();

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    public static Category of(String code, String name) {
        return Category.builder()
                .id(UUID.randomUUID().toString())
                .code(code)
                .name(name)
                .build();
    }

    @Override
    public String toString() {
        return name;
    }
}
