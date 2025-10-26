package io.github.hyeonqz.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.time.LocalDateTime;

/**
 * Eureka Server Application
 * <p>
 * MSA 환경에서 Service Discovery 역할을 수행하는 Eureka Server
 * <br>
 * <b>주요 기능:</b>
 * <ul>
 *   <li>마이크로서비스 등록 및 관리</li>
 *   <li>서비스 인스턴스의 위치 정보 제공 (IP:Port)</li>
 *   <li>서비스 Health Check (Heartbeat 기반)</li>
 *   <li>서비스 디스커버리 (서비스 이름으로 조회)</li>
 * </ul>
 */
@EnableEurekaServer // Eureka Server 기능 활성화
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
