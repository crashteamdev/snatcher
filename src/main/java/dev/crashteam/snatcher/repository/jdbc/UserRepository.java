package dev.crashteam.snatcher.repository.jdbc;

import dev.crashteam.snatcher.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
