# redis-cache

## О Проекте
Приложение - реализация Redis in-memory cache на основе Spring Boot. Методы, которые были реализованы:
- GET
- SET
- DEL
- KEYS
- HSET
- HGET
- LSET
- LGET

Запустить через Docker:
cd redis-cache
docker build -t redis-cache:latest .
docker run -p 8080:8080 redis-cache .

Внутри есть файл с Postman коллекцией со всеми запросами.
