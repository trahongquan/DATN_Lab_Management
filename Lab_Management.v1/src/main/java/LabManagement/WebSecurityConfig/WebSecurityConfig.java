package LabManagement.WebSecurityConfig;


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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /** Config datasource security*/
    @Autowired //world wide
    // add a reference to our security data source
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        // Use jdbc authentication... ah yeah!!!
        auth.jdbcAuthentication().dataSource(securityDataSource);
       /*     // Sét đặt dịch vụ để tìm kiếm User trong Database.
            // Và sét đặt PasswordEncoder.
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(securityDataSource); // Cung cấp DataSource
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
    /**  ************************************************************* */

//   public void configureGlobal (AuthenticationManagerBuilder auth) {
//        try {
//            auth.inMemoryAuthentication()
//            /** Dùng để tạo nhiều tài khoản login theo quyền*/
//                .withUser("admin").password("{bcrypt}$2a$10$r4g4kdnInz89MRwU4LIn0.V7uu71gTNgqjrYzsyyPKLf75DNPQYd6").roles("ADMIN");
//                /** {noop} chỉ định không cần mã hóa pasword*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /** Tất cả các request tới web đều phải authorize - kiểm tra quyền*/
                .antMatchers("/Lab/admin/AccEmployeesManager/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/Lab/admin/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
                /** ' ** ' để có thể authorize các link con phía sau*/
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
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .rememberMe()
                .tokenValiditySeconds(86400) // Thời gian hết hạn của cookie (ở đây là 1 ngày)
                .tokenRepository(persistentTokenRepository()); // Sử dụng PersistentTokenRepository

    }
}

