package softwire.training.myface.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration //attaches password config to each class
public class PasswordConfig {

    @Bean
    PasswordEncoder passwordEncoder() { //Password encoder is a Java Class we've called it passwordEncoder
        return new BCryptPasswordEncoder(); //returns BCrypt which contains methods to turn password in hashs
    }

}
