CREATE TABLE clients
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL COMMENT '클라이언트 이름',
    email         VARCHAR(255) NOT NULL UNIQUE COMMENT '이메일',
    api_key       VARCHAR(512) NOT NULL UNIQUE COMMENT 'API 인증 키',
    issuer_info   VARCHAR(255) NOT NULL COMMENT '발급자 정보',
    issued_at     TIMESTAMP COMMENT '발급일시',
    request_count BIGINT COMMENT 'API 요청 횟수',
    expires_at    TIMESTAMP COMMENT '만료일시'
);

CREATE TABLE client_permissions
(
    client_id  BIGINT COMMENT '클라이언트 ID',
    permission VARCHAR(255) COMMENT '권한',
    PRIMARY KEY (client_id, permission),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE client_allowed_ips
(
    client_id  BIGINT COMMENT '클라이언트 ID',
    ip_address VARCHAR(255) COMMENT '허용된 IP 주소',
    PRIMARY KEY (client_id, ip_address),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);
