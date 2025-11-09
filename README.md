## 제목 — 간략 소개(한 문장)

## 문제 정의(배경, 비기능 요구사항)

## 기술 스택(이유 포함)

## 아키텍처(그림 + 컴포넌트 설명)

## How to run (실행 가이드)
### 0. docker-compose 실행
> 1. mysql <br>
> 2. kafka <br>
> 3. nexus <br>
> 4. prometheus <br>
> 5. grafana <br>


<br>

### 1. infrastructure
> msa 환경 구성을 위한 기본 설정 실행
> 1. config-server <br>
> 2. eureka-server <br>
> 3. gateway-server <br>
> 4. monitoring-server <br>


### 2. services
> msa 환경에 따른 비즈니스 로직 처리 서비스 실행
> 1. crawler-service <br>
> 2. intelligence-service <br>
> 3. notification-service <br>


## 핵심 설계/코드 스니펫(멱등 처리 / 정산 알고리즘 / 트랜잭션 경계 등)
