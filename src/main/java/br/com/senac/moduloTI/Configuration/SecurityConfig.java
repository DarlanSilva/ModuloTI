package br.com.senac.moduloTI.Configuration;

import br.com.senac.moduloTI.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Darlan.Silva
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static PasswordEncoder plainPasswordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String hashSenha) {
                return hashSenha != null && hashSenha.equals(cs.toString());
            }
        };
    }

    public static PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bcryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/TechMode/Login/**",
                            "*/css/**",
                            "*/img/**",
                            "*/js/**").permitAll()
                .antMatchers("/TechMode/Painel/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/TechMode/Login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/TechMode/Sucess").permitAll()
                .failureUrl("/TechMode/Login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/Logout"))
                .logoutSuccessUrl("/TechMode/Login")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/erro/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}