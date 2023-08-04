package dev.crashteam.snatcher.service.jdbc;

import dev.crashteam.snatcher.mapper.ProductMapper;
import dev.crashteam.snatcher.mapper.UserMapper;
import dev.crashteam.snatcher.model.domain.User;
import dev.crashteam.snatcher.model.domain.UserProduct;
import dev.crashteam.snatcher.model.dto.product.UserProductCreateDto;
import dev.crashteam.snatcher.model.dto.product.UserProductDto;
import dev.crashteam.snatcher.model.dto.user.UserCreateDto;
import dev.crashteam.snatcher.model.dto.user.UserDto;
import dev.crashteam.snatcher.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserProductService userProductService;

    @Transactional
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserDto userDto = userMapper.domainToDto(user);
        setProducts(userDto);
        return userDto;
    }

    @Transactional
    public UserDto createUser(UserCreateDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        return userMapper.domainToDto(userRepository.save(user));
    }

    @Transactional
    public UserDto addProduct(Long userId, UserProductCreateDto userProductDto) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserProduct userProduct = new UserProduct();
        userProduct.setName(userProductDto.getName());
        userProduct.setAddress(userProductDto.getAddress());
        userProduct.setQuery(userProductDto.getQuery());
        userProduct.setPrice(userProductDto.getPrice());
        userProduct.setSkuId(userProductDto.getSkuId());
        userProduct.setUser(user);
        userProductService.saveProduct(userProduct);

        UserDto userDto = userMapper.domainToDto(user);
        setProducts(userDto);
        return userDto;
    }

    private void setProducts(UserDto userDto) {
        List<UserProductDto> products = userProductService.getProductsByUserId(userDto.getId());
        userDto.setProducts(products);
    }
}
