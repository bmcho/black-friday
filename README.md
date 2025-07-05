# 🛒 Black-Friday 프로젝트

**Black-Friday**는 대규모 트래픽 상황에서 안정적으로 동작하는 마이크로서비스 기반 이커머스 시스템 예제입니다. 실제 블랙프라이데이와 같은 이벤트를 가정하여, 서비스 분리와 확장성, 유지보수성을 고려한 구조로 설계되었습니다.

---

## 🚀 주요 특징

- **Java 기반 마이크로서비스 아키텍처(MSA)**
- **REST API를 통한 서비스 간 통신**
- **Docker Compose로 손쉬운 로컬 환경 구축**
- **독립적 배포 및 확장 가능**
- **실제 이커머스 시나리오 반영**

---

## 🧩 서비스 구성

| 서비스명           | 역할 및 설명                |
|-------------------|----------------------------|
| catalog-service   | 상품 카탈로그 관리          |
| delivery-service  | 배송 처리 및 추적           |
| member-service    | 회원 정보 및 인증 관리      |
| order-service     | 주문 생성 및 관리           |
| payment-service   | 결제 처리                   |
| search-service    | 상품 검색                   |

각 서비스는 독립적으로 개발 및 배포가 가능하며, REST API를 통해 상호작용합니다.

---

## 📁 프로젝트 구조
```
black-friday/
├── catalog-service/ # 상품 카탈로그 서비스
├── delivery-service/ # 배송 서비스
├── member-service/ # 회원 서비스
├── order-service/ # 주문 서비스
├── payment-service/ # 결제 서비스
├── search-service/ # 검색 서비스
├── gradle/ # Gradle 관련 파일
├── .gitignore
├── build.gradle
├── docker-compose.yml # 전체 서비스 오케스트레이션
├── README.md
└── settings.gradle
```

---

## 🛠️ 기술 스택

| 범주      | 기술                         |
|-----------|------------------------------|
| Backend   | Java, Spring Boot, Gradle    |
| Infra     | Docker, Docker Compose       |

---

> 본 README는 저장소의 폴더 구조와 파일명, 일반적인 MSA 이커머스 예제의 관례를 바탕으로 작성되었습니다. 실제 서비스별 상세 기능 및 API 명세는 각 서비스의 소스코드를 참고하시기 바랍니다.
