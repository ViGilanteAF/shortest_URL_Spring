# shortest-url Spring Migration

NestJS `shotest_URL` 프로젝트의 `Spring Boot` 마이그레이션 프로젝트

## Migrated Features

- `POST /shortest-urls`: 원본 URL을 받아 7자리 단축 키 생성
- `GET /shortest-urls/{shortestUrlKey}`: 원본 URL로 리다이렉트
- `GET /shortest-urls?pageNumber=1&pageSize=10`: 단축 URL 목록 조회
- MongoDB `shortest_urls`, `counts` 컬렉션 사용
- Redis cache miss 시 MongoDB fallback 후 cache 재적재
- Kafka `increaseVisitCountByKey` 토픽으로 조회수 증가 비동기 처리

## Local Run

```bash
docker compose up -d
mvn spring-boot:run
```


