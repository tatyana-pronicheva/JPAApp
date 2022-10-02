package demo.auth.requests;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
