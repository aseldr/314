package asel.java.spring.repository;

import asel.java.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstname(String username);

    @Query("select u from User u join fetch u.roles where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
