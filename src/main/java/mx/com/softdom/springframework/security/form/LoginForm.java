package mx.com.softdom.springframework.security.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
