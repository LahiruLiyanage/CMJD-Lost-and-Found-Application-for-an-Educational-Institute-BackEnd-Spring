package lk.ijse.cmjd.lostandfoundappbackend.repository;

import lk.ijse.cmjd.lostandfoundappbackend.entity.User;
import lk.ijse.cmjd.lostandfoundappbackend.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findByRole(UserRole role);
}
