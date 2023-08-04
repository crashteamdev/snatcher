package dev.crashteam.snatcher.model.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProductCreateDto {

    private Long skuId;
    private String name;
    private BigDecimal price;
    private String query;
    private String address;
    private Long userId;
    private String imageUrl;
}
