package mx.com.softdom.springframework.security.contracts;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtUserRepository {
    Optional<JwtUser> findByUsername(String username);
}
