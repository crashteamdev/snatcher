package dev.crashteam.snatcher.mapper;

import dev.crashteam.snatcher.model.domain.Review;
import dev.crashteam.snatcher.model.dto.review.ReviewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<Review, ReviewDto>{
}
