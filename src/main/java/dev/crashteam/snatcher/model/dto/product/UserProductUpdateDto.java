package dev.crashteam.snatcher.model.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.crashteam.snatcher.model.UserProductStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProductUpdateDto {
    private String name;
    private BigDecimal price;
    private String query;
    private String address;
    private String userId;
    private String imageUrl;
    private UserProductStatus productStatus;
}
