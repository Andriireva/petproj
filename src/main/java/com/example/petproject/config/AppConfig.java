package com.example.petproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

@Configuration
public class AppConfig {

  public static String helloString = "Hello from Pet project " + Instant.
        now()
        .minus(2L, ChronoUnit.DAYS);


  @Bean
  @Primary
  String applicationName() {
    return "Pet Project";
  }

  @Bean
  public Instant time() {
    return Instant.
          now()
          .minus(2L, ChronoUnit.DAYS);
  }

  @Bean("tspl")
  public Supplier<Instant> timeSupplier() {
    return () -> Instant.
          now()
          .minus(2L, ChronoUnit.DAYS);
  }

  @Bean
  @Primary
  public Supplier<Instant> timeSupplierAdvanced() {
    return () -> Instant.
          now()
          .minus(2L, ChronoUnit.SECONDS);
  }

//  @Bean
//  public JdbcTemplate postgresJdbcTemplate(DataSource postgresDataSource) {
//    return new JdbcTemplate(postgresDataSource);
//  }
//
//  @Bean
//  public JdbcTemplate mysqlJdbcTemplate() {
//    return new JdbcTemplate();
//  }
//
//  @Bean
//  public DataSource postgresDataSource() {
//
//  }


//  @Bean
//  public CatRepository catRepository() {
//    return new CatRepositoryImpl("ZZZZ");
//  }












  @Bean
//  public String helloString(@Qualifier("tspl") Supplier<Instant> instantSupplier) {
  public String helloString(Supplier<Instant> supplier) {
    return "Hello from Pet project " + supplier.get();
  }
}
