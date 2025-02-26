package asel.java.spring.repository;

import asel.java.spring.model.Role;
import asel.java.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findRolesById(@Param("id") Long id);
}
