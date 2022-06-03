package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                    .antMatchers("/cats", "/cats/addpairfriends", "/cats/ownerscats").hasRole(RoleUsers.USER.name())
                    .antMatchers("/owners/all",
                            "/owners/bypassport",
                            "/owners/post",
                            "/owners/delete",
                            "/cats/all",
                            "/cats",
                            "/cats/ownerscats",
                            "/cats/friends",
                            "/cats/addpairfriends").hasRole(RoleUsers.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
//                .roles(USER.name())
                .authorities(RoleUsers.USER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("dan")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(RoleUsers.ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
