package dev.crashteam.snatcher.mapper;

import dev.crashteam.snatcher.model.domain.UserProduct;
import dev.crashteam.snatcher.model.dto.product.UserProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<UserProduct, UserProductDto>{
}
