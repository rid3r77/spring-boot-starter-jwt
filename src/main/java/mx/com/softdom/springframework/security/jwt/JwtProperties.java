package mx.com.softdom.springframework.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(prefix = "security.jwt.token")
@Data
@Validated
public class JwtProperties {

    @NotBlank
    private String base64SecretKey;
    @Min(900)
    private long expireLength;
    @NotBlank
    private String issuer;

}
