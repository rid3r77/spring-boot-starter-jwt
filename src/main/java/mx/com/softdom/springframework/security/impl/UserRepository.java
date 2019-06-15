package mx.com.softdom.springframework.security.impl;

import mx.com.softdom.springframework.security.contracts.JwtUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JwtUserRepository, JpaRepository<User, Long> {
}
