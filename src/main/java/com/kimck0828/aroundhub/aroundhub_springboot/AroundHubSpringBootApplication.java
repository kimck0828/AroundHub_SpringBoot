package com.kimck0828.aroundhub.aroundhub_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// ★JPA Auditingを実行するためのアノテーション
@EnableJpaAuditing
public class AroundHubSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(AroundHubSpringBootApplication.class, args);
  }

}
