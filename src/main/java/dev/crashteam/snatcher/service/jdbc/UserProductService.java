package dev.crashteam.snatcher.service.jdbc;

import dev.crashteam.snatcher.mapper.ProductMapper;
import dev.crashteam.snatcher.model.domain.UserProduct;
import dev.crashteam.snatcher.model.dto.product.UserProductDto;
import dev.crashteam.snatcher.model.dto.product.UserProductUpdateDto;
import dev.crashteam.snatcher.repository.jdbc.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductRepository userProductRepository;
    private final ProductMapper productMapper;

    public List<UserProductDto> getProductsByUserId(Long userId) {
        return productMapper.domainsToDtos(userProductRepository.findUserProductByUserId(userId));
    }

    @Transactional
    public UserProductDto saveProduct(UserProduct userProduct) {
        return productMapper.domainToDto(userProductRepository.save(userProduct));
    }

    @Transactional
    public UserProductDto updateProduct(UserProductUpdateDto userProductDto) {
        UserProduct userProduct = new UserProduct();
        userProduct.setName(userProductDto.getName());
        userProduct.setAddress(userProductDto.getAddress());
        userProduct.setQuery(userProductDto.getQuery());
        userProduct.setPrice(userProductDto.getPrice());

        return productMapper.domainToDto(userProductRepository.save(userProduct));
    }


}
