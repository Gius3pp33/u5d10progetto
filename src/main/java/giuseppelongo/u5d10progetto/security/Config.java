package giuseppelongo.u5d10progetto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Se voglio poter dichiarare le regole di autorizzazione direttamente sui singoli endpoint, allora questa annotazione è OBBLIGATORIA
public class Config {

    //Bean apposito per disabilitare dei comportamenti default
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(http -> http.disable()); //Form login
        httpSecurity.csrf(http -> http.disable()); //Protezione attacchi CSRF
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //sessioni
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());// Evitare il 401
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder(10);
    }

}
