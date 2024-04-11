package LabManagement.WebSecurityConfig;


import LabManagement.ClassSuport.SecretKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /** Config datasource security*/
    @Autowired //world wide
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /** Tất cả các request tới web đều phải authorize - kiểm tra quyền*/
                .antMatchers("/Lab/admin/AccEmployeesManager/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/Lab/admin/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
                /** ' ** ' để có thể authorize các link con phía sau*/
                .antMatchers("/Lab/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER')")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                /** Bất kì request nào cũng cần authenticated - đăng nhập*/
                .and()
                .formLogin()
                .loginPage("/login")
//                        .defaultSuccessUrl("/")
                /** Chỉ định Controller Mapping tới login page custom*/
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                /** .permitAll() dùng để bỏ qua Mapping này không cần authorizeRequests và authenticated*/
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }
}

