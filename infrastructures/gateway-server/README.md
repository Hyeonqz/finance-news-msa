# Cloud Gateway-Server

위 모듈은 API Gateway 를 담당한다.

```text
-- Client (외부)
        ↓
-- API Gateway (8080) - 라우팅, 인증, 로깅만 담
        ↓
-- Internal Network
    ├─ crawler-service (9010) - 
    ├─ intelligence-service (9020) - @RestController로 API 노출
    └─ notification-service (9030) - @RestController로 API 노출
```

## 요청 흐름 상세

### Before Gateway (현재 구조)
```
Client → crawler-service:9010/api/crawler/crawl
Client → intelligence-service:9020/api/intelligence/analyze
Client → notification-service:9030/api/notification/send
```

**문제점:**
- 각 서비스의 호스트/포트를 클라이언트가 알아야 함
- 인증/인가를 각 서비스마다 중복 구현
- CORS 설정을 각 서비스마다 설정
- Rate Limiting을 각 서비스마다 구현

### After Gateway (목표 구조)
```
Client → gateway:8080/api/crawler/crawl
            ↓ (라우팅)
         crawler-service:9010/api/crawler/crawl

Client → gateway:8080/api/intelligence/analyze
            ↓ (라우팅)
         intelligence-service:9020/api/intelligence/analyze
```

### Gateway의 역할
1. 라우팅 (핵심)
```markdown
# URL 패턴 기반 라우팅
- Path=/api/crawler/**   → crawler-service
- Path=/api/intelligence/**   → intelligence-service
- Path=/api/notification/**        → notification-service
```


