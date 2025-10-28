package io.github.hyeonqz.crawlerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing      // jpa-auditing 활성화
@EnableDiscoveryClient  // eureka-client 활성화
@SpringBootApplication
public class CrawlerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerServiceApplication.class, args);
    }

}
