package ro.easybites.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import ro.easybites.app.user_files.EasyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    EasyUserService easyUserService;

    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(easyUserService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/.well-known/pki-validation/CE6E7B6DB528DF070D26FDE6816EA859.txt","/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",  "/json/**", "/favicon.ico", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/deactivate-site/**").hasRole("super_admin")
                .antMatchers("/admin").hasRole("admin")
                .antMatchers(
                        "/recuperare-parola", "/reset_pass", "/parola-pierduta", "/generate_reset_pass",
                        "/na/**", "/shop",
                        "/pay",
                        "/register", "/registeruser",
                        "/activate-account",
                        "/", "/preturi", "/contact", "/retete", "/retete/**", "/about-us", "/contacted", "/g9nnrkiayjrxwr56qdj8oysxdkve3i.html").permitAll()
                .anyRequest().authenticated()
                .and()
//                .httpBasic()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/userpage")
                    .failureUrl("/login?error")
                    .permitAll()
                .and().logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .requiresChannel(channel ->
//                        channel.anyRequest().requiresSecure())
//                .authorizeRequests(authorize ->
//                        authorize.anyRequest().permitAll())
//                .build();
//    }
}
