# 💸 Projeto de Agendamento de Transferências

Aplicação simples para agendar transferências bancárias.

## 🧱 Estrutura

backend/ → API (Spring Boot, Java 11, H2) </br>
frontend/transfer-ui → Interface (Vue 3 + Vite)


---

## ▶️ Como rodar

### 1. Rodar o backend e o frontend com o docker

```bash
cd backend
mvn clean package -DskipTests
cd ..
docker-compose build --no-cache
docker-compose up -d
```

A API ficará disponível em http://localhost:8080/api/transfers </br>
O frontend estará disponível em http://localhost:5173
