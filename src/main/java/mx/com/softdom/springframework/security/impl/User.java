package mx.com.softdom.springframework.security.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.softdom.springframework.security.contracts.JwtUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends JwtUser {

    @Column(unique = true, length = 200)
    @Email
    private String email;

    @Column(length = 300)
    @NotEmpty
    private String name;

}
