# Demo Template

[한글 버전](README.kr.md)

A Spring Boot based multi-module template project.

This template provides a basic structure that follows domain-driven design while maintaining practicality.

## Project Structure

```
root/
├── api/                # API module (web layer, executable module)
├── core/
│   ├── base/          # Common utilities
│   └── domain/        # Pure domain logic, interface definitions
└── storage/
    └── rdb/           # Repository implementations
```

## Core Design Principles

### 1. Domain-Centered Design

```
api -----> domain <----- base
            ↑
            |
       storage/rdb
```

- Domain is the center of the project
- Domain is separated from infrastructure through interfaces
- Implementations depend on domain interfaces

### 2. Module Roles

#### api

- Application entry point
- Handles web layer
- Executable application

#### core/domain

- Pure domain models
- Repository interface definitions
- Business logic implementation
- Uses only JPA annotations (no implementation)

#### core/base

- Common utilities
- Provides common functionality needed by domain

#### storage/rdb

- Repository interface implementation
- Includes JPA implementation
- Actual database integration

### 3. Dependency Management

#### api/build.gradle

```gradle
dependencies {
    runtimeOnly(project(":storage:rdb"))    // Implementation needed only at runtime
    implementation project(':core:base')     // Compile-time dependency
    implementation project(':core:domain')
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

#### core/domain/build.gradle

```gradle
dependencies {
    implementation project(':core:base')
    implementation 'org.springframework:spring-context'
    compileOnly 'jakarta.persistence:jakarta.persistence-api'  // JPA annotations only
}
```

#### storage/rdb/build.gradle

```gradle
dependencies {
    implementation project(':core:domain')   // Implements domain interfaces
    implementation project(':core:base')
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'         // DB driver
}
```

## Key Features

### 1. Domain Purity

- Domain logic does not depend on infrastructure
- Loose coupling through Repository interfaces
- Structure focused on business logic

### 2. Runtime Dependency Management

- Separation of compile-time and runtime dependencies
- Storage implementation injected only at runtime
- Flexible implementation management using Spring DI

### 3. Easy Extension

- Easy to add new storage implementations
- Infrastructure layer can be changed without affecting domain logic

## Configuration File Management

- api/application.yml: Basic configuration
- storage/rdb/application-rdb.yml: DB related configuration
- Configuration separation through profiles

This template provides a structure that follows the core principles of domain-driven design while enabling practical
implementation.
