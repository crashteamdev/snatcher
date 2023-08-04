package dev.crashteam.snatcher.repository.jdbc;

import dev.crashteam.snatcher.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
