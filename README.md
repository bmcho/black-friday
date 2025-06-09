# 🛒 Black Friday Microservices Project

**Black Friday Microservices Project**는 마이크로서비스 아키텍처 기반으로 설계된 전자상거래 시스템 예제입니다.
각 도메인을 독립적인 서비스로 분리하여 **확장성**과 **유지보수성**을 극대화하는 데 중점을 두었습니다.

---

## 🔧 주요 특징

✅ **Spring Boot 기반 Java 마이크로서비스** 

✅ **서비스별 독립 배포 및 확장 가능**

✅ **Docker Compose를 통한 로컬 환경 손쉬운 구축**

✅ **도메인 중심 설계**: 주문, 결제, 배송, 회원, 상품, 검색 등


---

## 📁 프로젝트 구조

```
black-friday/
├── catalog-service/       # 상품(카탈로그) 관리 서비스
├── delivery-service/      # 배송 서비스
├── member-service/        # 회원 관리 서비스
├── order-service/         # 주문 서비스
├── payment-service/       # 결제 서비스
├── search-service/        # 상품/주문 검색 서비스
├── docker-compose.yml     # 전체 서비스 로컬 구동을 위한 Docker Compose 설정
├── build.gradle           # 공통 Gradle 빌드 설정
├── settings.gradle
└── ...
```


---

## 📦 서비스별 설명

> **Black Friday 프로젝트는 다음과 같은 마이크로서비스들로 구성됩니다:**
>
> 🛍️ **`catalog-service`**  
> 상품 등록, 조회, 수정, 삭제 등 **상품 관리 기능**을 제공합니다.
>
> 🚚 **`delivery-service`**  
> 주문 건에 대한 **배송 처리 및 상태 관리 기능**을 담당합니다.
>
> 👤 **`member-service`**  
> **회원 가입**, 정보 조회, 수정 등 **회원 관련 기능**을 제공합니다.
>
> 🧾 **`order-service`**  
> 상품 **주문 요청**, 주문 내역 조회, 주문 상태 변경 기능을 처리합니다.
>
> 💳 **`payment-service`**  
> **결제 요청**, 결제 내역 관리 및 결제 상태 업데이트 기능을 담당합니다.
>
> 🔍 **`search-service`**  
> 상품 및 주문 데이터를 대상으로 한 **검색 기능**을 제공합니다.

---

## 🛠 기술 스택

> | 범주        | 기술                                   |
> |-------------|----------------------------------------|
> | Backend     | `Java`, `Spring Boot`, `Gradle`        |
> | Infra       | `Docker`, `Docker Compose`             |
> | Data Store  | `MySQL`, `Cassandra`, `Redis`          |
> | Messaging   | `Apache Kafka`                         |
> | Testing     | `Apache JMeter`                        |

---

## 🚀 로컬 실행 방법

> 1. **Docker 및 Docker Compose 설치**
> 2. 프로젝트 루트에서 아래 명령어 실행:
> 
> ```bash
> docker-compose up --build
> ```

---

## 🌐 기본 서비스 포트 안내

> | 서비스 이름         | 포트             |
> |--------------------|------------------|
> | Catalog Service    | `localhost:8081` |
> | Delivery Service   | `localhost:8082` |
> | Member Service     | `localhost:8083` |
> | Order Service      | `localhost:8084` |
> | Payment Service    | `localhost:8085` |
> | Search Service     | `localhost:8086` |

---

## ⚠️ 참고 사항

> ⚠️ **이 프로젝트는 학습 및 구조 설계 목적의 예제입니다.**  
> 실 서비스에 적용하려면 인증, 보안, 장애 복구, 모니터링 등 추가적인 요소들이 반드시 고려되어야 합니다.

