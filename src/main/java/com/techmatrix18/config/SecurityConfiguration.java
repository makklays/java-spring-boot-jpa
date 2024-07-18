package com.techmatrix18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password(encoder.encode("user")).roles("USER").build();
        UserDetails alex41 = User.builder().username("alex41").password(encoder.encode("alex41")).roles("ADMIN", "USER").build();

        //UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).build();
        //UserDetails user = User.builder().username("user").password(encoder.encode("user")).build();
        //UserDetails alex41 = User.builder().username("alex41").password(encoder.encode("alex41")).build();

        return new InMemoryUserDetailsManager(admin, user, alex41);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/welcome").permitAll()
                .requestMatchers("/api/v1/**").authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    //---- old realization ----
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService_old() {
        UserDetails user = User.withUsername("user")
                .password("user") // passwordEncoder().encode("user")
                .roles("USER")
                //.authorities(Role.USER.getAuthorities())
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("admin") // passwordEncoder().encode("admin")
                .roles("ADMIN")
                //.authorities(Role.ADMIN.getAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain_old(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());

        /*http
                //.csrf()
                //.disable()
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/anonymous*")
                .anonymous()
                .antMatchers("/login*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // ...
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/login.html?error=true")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler());

                //.csrf().disable();
                //.authorizeRequests()
                //.antMatchers("/").permitAll()
                ////.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                ////.antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
                ////.antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
                //.antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
                //.antMatchers(HttpMethod.POST, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                //.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                //.anyRequest()
                //.authenticated()
                //.and()
                //.httpBasic();

        return http.build();
    }*/

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        // my setting with roles
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            //.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
            //.antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
            //.antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
            .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
            .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
            .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }*/

    /*@Bean
    protected UserDetailsService UserDetailsService() {

        /*User user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user.getPassword());*/

        /*return new InMemoryUserDetailsManager(
            User.withDefaultPasswordEncoder()
                    .username("user")
                    .password(new BCryptPasswordEncoder(12).encode("user"))
                    //.roles("USER")
                    .authorities(Role.USER.getAuthorities())
                    .build(),
            User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder(12).encode("admin"))
                    //.roles("USER","ADMIN")
                    .authorities(Role.ADMIN.getAuthorities())
                    .build()
        );*/

        /*return new InMemoryUserDetailsManager(
            User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    //.roles(Role.ADMIN.name())
                    .authorities(Role.ADMIN.getAuthorities())
                    .build(),
            User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user"))
                    //.roles(Role.USER.name())
                    .authorities(Role.USER.getAuthorities())
                    .build()
        );
    }*/

}

