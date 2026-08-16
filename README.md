<div>
<img src="./image.png"/>
</div>

# Meerkatgram (미어캣그램)

> **Vue 3와 Spring Boot 기반의 사진 공유 SNS 서비스**

## 1. 프로젝트 소개
**Meerkatgram**은 인스타그램을 참고하여 제작한 SNS 서비스입니다. 
RESTful API 아키텍처를 기반으로 백엔드와 프론트엔드가 분리된 구조를 가지며, JWT 기반의 안전한 인증 시스템과 대용량 이미지 파일 처리 기능을 구현했습니다.

* **개발 기간** : 2026.05.04 ~ 2026.06.05
* **개발 인원** : 1인

---

## 2. 기술 스택

### 🎨 Frontend
* **Framework** : Vue 3
* **Build Tool** : Vite
* **State Management** : Pinia
* **Routing** : Vue Router
* **HTTP Client** : Axios
* **Utils** : Dayjs, jwt-decode

### ⚙️ Backend
* **Language & Framework** : Java 17, Spring Boot 3.5.x
* **Security** : Spring Security, JWT
* **Database & ORM** : MySQL, Spring Data JPA, QueryDSL, Hibernate
* **API Docs** : SpringDoc OpenAPI (Swagger UI)
* **Build Tool** : Gradle

---

## 3. 주요 기능

### 🔐 1. 사용자 인증 및 인가
* **JWT 기반 로그인** : Access Token 및 Refresh Token을 활용한 안전한 세션 관리
* **회원가입 및 소셜 로그인 (OAuth2)** : 다양한 방식의 사용자 로그인 지원
* **보안 필터** : `TokenAuthenticationFilter`를 커스텀하여 API 요청 시 권한 검증

### 🖼️ 2. 프로필 및 파일 관리
* **이미지 파일 업로드** : 로컬 스토리지에 프로필 사진 및 게시물 이미지 저장
* **파일 검증** : 파일 크기 제한(10MB) 및 허용된 확장자(JPG, PNG, GIF, WEBP 등) 필터링 처리를 통한 보안 강화

### 📝 3. 게시글 관리
* **CRUD 구현** : 게시물 작성, 상세 조회, 전체 게시물 목록 조회 기능
* **QueryDSL 활용** : 복잡한 검색 조건이나 동적 쿼리가 필요한 게시물 조회 로직을 안전하고 효율적으로 처리

### 🛠️ 4. 예외 처리
* **Global Exception Handler** : `@ControllerAdvice`를 활용해 전역적으로 발생하는 예외를 일관된 에러 응답(JSON)으로 클라이언트에 전달

---

## 4. 아키텍처 및 폴더 구조

본 프로젝트는 백엔드와 프론트엔드가 독립적으로 동작하는 구조로 설계되었습니다.

* **Backend** : 
  * 도메인 주도 설계(DDD) 관점을 반영하여 `auth`, `file`, `post`, `user` 도메인별로 패키지를 분리했습니다.
* **Frontend** : 
  * 컴포넌트(`components`)와 페이지(`pages`)를 분리하고, Pinia(`store`)를 이용해 전역 상태를 관리합니다.

---
