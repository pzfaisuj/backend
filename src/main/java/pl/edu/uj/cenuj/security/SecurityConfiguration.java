package pl.edu.uj.cenuj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    private final AuthenticationProvider firebaseAuthenticationProvider;

//    @Autowired
//    public SecurityConfiguration(AuthenticationProvider firebaseAuthenticationProvider) {
//        this.firebaseAuthenticationProvider = firebaseAuthenticationProvider;
//    }

    @Autowired
    public void ConfigureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(firebaseAuthenticationProvider);
        auth.inMemoryAuthentication().withUser("user").password("test").roles("USER");
        auth.inMemoryAuthentication().withUser("superuser").password("test2").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().
                authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
                .anyRequest().permitAll().and().csrf().disable();
    }

    @Bean
    public AuthenticationProvider firebaseAuthenticationProvider() {
        return new FirebaseAuthenticationProvider();
    }
}
