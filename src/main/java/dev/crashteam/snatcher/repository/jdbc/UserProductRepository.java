package dev.crashteam.snatcher.repository.jdbc;

import dev.crashteam.snatcher.model.domain.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

    List<UserProduct> findUserProductByUserId(Long userId);
}
