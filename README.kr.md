# Demo Template

[English Version](README.md)

스프링 부트 기반의 멀티모듈 템플릿입니다.

도메인 중심의 설계를 지향하면서도 실용적인 기본 구조를 제공합니다.

## 프로젝트 구조

```
root/
├── api/                # API 모듈 (웹 레이어, 실행 모듈)
├── core/
│   ├── base/          # 공통 유틸리티
│   └── domain/        # 순수한 도메인 로직, 인터페이스 정의
└── storage/
    └── rdb/           # 저장소 구현체
```

## 핵심 설계 원칙

### 1. 도메인 중심 설계

```
api -----> domain <----- base
            ↑
            |
       storage/rdb
```

- 도메인이 프로젝트의 중심
- 도메인은 인터페이스를 통해 인프라스트럭처와 분리
- 구현체는 도메인의 인터페이스에 의존

### 2. 모듈별 역할

#### api

- 애플리케이션 진입점
- 웹 레이어 담당
- 실행 가능한 애플리케이션

#### core/domain

- 순수한 도메인 모델
- 저장소 인터페이스 정의
- 비즈니스 로직 구현
- JPA 어노테이션만 사용 (구현체 없음)

#### core/base

- 공통 유틸리티
- 도메인에서 필요한 공통 기능 제공

#### storage/rdb

- 저장소 인터페이스 구현
- JPA 구현체 포함
- 실제 데이터베이스 연동

### 3. 의존성 관리

#### api/build.gradle

```gradle
dependencies {
    runtimeOnly(project(":storage:rdb"))    // 실행 시점에만 필요한 구현체
    implementation project(':core:base')     // 컴파일 시점 의존성
    implementation project(':core:domain')
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

#### core/domain/build.gradle

```gradle
dependencies {
    implementation project(':core:base')
    implementation 'org.springframework:spring-context'
    compileOnly 'jakarta.persistence:jakarta.persistence-api'  // JPA 어노테이션만
}
```

#### storage/rdb/build.gradle

```gradle
dependencies {
    implementation project(':core:domain')   // 도메인 인터페이스 구현
    implementation project(':core:base')
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'         // DB 드라이버
}
```

## 주요 특징

### 1. 도메인 순수성

- 도메인 로직이 인프라스트럭처에 의존하지 않음
- 저장소 인터페이스를 통한 느슨한 결합
- 비즈니스 로직에만 집중 가능한 구조

### 2. 실행 시점 의존성 관리

- 컴파일 시점과 실행 시점 의존성 분리
- 저장소 구현체는 실행 시점에만 주입
- 스프링의 DI를 활용한 유연한 구현체 관리

### 3. 확장 용이성

- 새로운 저장소 구현체 추가 용이
- 도메인 로직 영향 없이 인프라 계층 변경 가능

## 설정 파일 관리

- api/application.yml: 기본 설정
- storage/rdb/application-rdb.yml: DB 관련 설정
- 프로필을 통한 설정 분리

이 템플릿은 도메인 주도 설계의 핵심 원칙을 따르면서도, 실용적인 구현이 가능한 구조를 제공합니다.
