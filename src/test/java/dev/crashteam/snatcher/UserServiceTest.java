package dev.crashteam.snatcher;

import dev.crashteam.snatcher.container.ContainerConfiguration;
import dev.crashteam.snatcher.model.UserProductStatus;
import dev.crashteam.snatcher.model.dto.product.UserProductCreateDto;
import dev.crashteam.snatcher.model.dto.user.UserCreateDto;
import dev.crashteam.snatcher.model.dto.user.UserDto;
import dev.crashteam.snatcher.service.jdbc.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest extends ContainerConfiguration {

    @Autowired
    UserService userService;

    @Test
    public void createUserAndAddProducts() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmail("test@test.net");
        userCreateDto.setName("Test");
        UserDto createdUser = userService.createUser(userCreateDto);

        UserDto user = userService.getUser(createdUser.getId());

        UserProductCreateDto productDto = new UserProductCreateDto();
        productDto.setQuery("test product query");
        productDto.setPrice(BigDecimal.valueOf(100.00));
        productDto.setImageUrl("someUrl");
        productDto.setAddress("Test st.");
        productDto.setUserId(user.getId());
        productDto.setSkuId(4324234L);
        productDto.setName("Test product");
        productDto.setQuantity(3L);

        UserProductCreateDto secondProductDto = new UserProductCreateDto();
        secondProductDto.setQuery("test product query");
        secondProductDto.setPrice(BigDecimal.valueOf(100.00));
        secondProductDto.setImageUrl("someUrl");
        secondProductDto.setAddress("Test st.");
        secondProductDto.setUserId(user.getId());
        secondProductDto.setSkuId(4324234L);
        secondProductDto.setName("Test product");
        secondProductDto.setQuantity(4L);

        userService.addProduct(user.getId(), productDto);
        userService.addProduct(user.getId(), secondProductDto);

        UserDto userWithProducts = userService.getUser(createdUser.getId());

        Assertions.assertFalse(CollectionUtils.isEmpty(userWithProducts.getProducts()));
        Assertions.assertTrue(userWithProducts.getProducts().stream().allMatch(it ->
                it.getProductStatus().equals(UserProductStatus.created)));
    }

}
