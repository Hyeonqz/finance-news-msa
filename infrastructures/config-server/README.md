# Config Server  
  
## 개요  
Spring Cloud Config Server를 사용하여 MSA 환경에서 중앙 집중식 설정 관리를 제공합니다.  
  
## 기술 스택  
- Java 21  
- Spring Boot 3.5.6  
- Spring Cloud Config Server 2025.0.0  
- Spring Boot Actuator  
  
## 프로젝트 구조  
```  
infrastructures/config-server/  
├── src/  
│   ├── main/  
│   │   ├── java/io/github/hyeonqz/configserver/  
│   │   │   └── ConfigServerApplication.java  
│   │   └── resources/  
│   │       ├── application.yml  
│   │       └── bootstrap.yml  
│   └── test/  
├── build.gradle  
└── settings.gradle
```  
  
## 설정 파일 저장소 구조  
```  
config-repo/  
├── application-local.yml                          # 공통 설정 (local 프로파일)  
├── crawler-service-repo/  
│   ├── crawler-service-local.yml  
│   └── crawler-service-dev.yml  
├── intelligence-service-repo/  
│   ├── intelligence-service-local.yml  
│   └── intelligence-service-dev.yml  
└── notification-service-repo/  
├── notification-service-local.yml  
└── notification-service-dev.yml
```  
  
## 주요 기능  
  
### 1. Native File System 방식  
- 로컬 파일 시스템에서 설정 파일을 읽어옵니다  
- 개발 환경에서 빠른 설정 변경이 가능합니다  
- 설정 파일 위치: `${user.dir}/../../config-repo`  
  
### 2. Profile 기반 설정  
- `application-{profile}.yml` 형식으로 환경별 설정 관리  
- 지원 프로파일: `local`, `dev`  
  
### 3. Actuator Endpoints  
- `/actuator/health`: 헬스 체크  
- `/actuator/info`: 애플리케이션 정보  
- `/actuator/refresh`: 설정 갱신  
  
## 사용 방법  
  
### Config Server 실행  
```bash  
cd infrastructures/config-server  
./gradlew bootRun  
```  

### 설정 파일 조회
```bash  
# 서비스별 설정 조회  
curl http://localhost:8888/crawler-service/local  
curl http://localhost:8888/intelligence-service/local  
curl http://localhost:8888/notification-service/local  
  
# 공통 설정 조회  
curl http://localhost:8888/application/local  
```  

### 클라이언트 설정 (각 마이크로서비스)
각 서비스의 `bootstrap.yml` 또는 `application.yml`에 추가:
```yaml  
spring:  
  cloud:  
    config:  
      uri: http://localhost:8888  
      name: {service-name}  
      profile: local  
      fail-fast: true  
```  

## 설정 우선순위
1. `{service-name}-{profile}.yml` (가장 높음)
2. `{service-name}.yml`
3. `application-{profile}.yml`
4. `application.yml` (가장 낮음)

## Git 연동 (향후 프로덕션 환경)
현재는 native 방식을 사용하지만, 프로덕션 환경에서는 Git 저장소 사용 권장:
```yaml  
spring:  
  cloud:  
    config:  
      server:  
        git:  
          uri: https://github.com/your-org/config-repo  
          default-label: main  
          search-paths: '/'  
          clone-on-start: true  
```  

## 보안 고려사항
- ⚠️ 민감정보(DB password, API key 등)는 암호화 필요
- Spring Cloud Config의 암호화 기능 사용 권장
- 프로덕션 환경에서는 Config Server에 인증 설정 필수

## 참고사항
- Config Server는 8888 포트에서 실행됩니다
- 설정 변경 후 클라이언트에서 `/actuator/refresh` 호출로 갱신 가능
- @RefreshScope 어노테이션을 사용하여 런타임 설정 갱신 지원
