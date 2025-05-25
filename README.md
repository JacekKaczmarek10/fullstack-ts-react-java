# 📬 Message App – Fullstack Application

This project was created as part of a **recruitment task**.

It consists of a **Java Spring Boot** backend and a **React + TypeScript** frontend. The goal was to implement a simple fullstack application where the frontend communicates with the backend, which in turn interacts with a SQL database.

Both parts of the project are included as Git submodules:

- [`backend`](./backend) – Java, Spring Boot, SQL
- [`frontend`](./frontend) – React, TypeScript

## 🧩 Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web, Spring Data JPA
- SQL Database H2
- Maven

### Frontend
- React
- TypeScript
- Axios
- Vite

## 🚀 Getting Started

Each submodule (`backend` and `frontend`) contains its own `README.md` file with detailed instructions on how to run it locally.

### Prerequisites

- Node.js (for frontend)
- Java 21+ and Maven (for backend)
- Git


### Backend (Java + Spring Boot)

```bash
cd backend
# Follow instructions in backend/README.md or run:
./mvnw spring-boot:run
```

The backend should start on [http://localhost:8080](http://localhost:8080).

### Frontend (React + TypeScript)

```bash
cd frontend
# Follow instructions in frontend/README.md or run:
npm install
npm run dev
```

The frontend should be available at [http://localhost:5173](http://localhost:5173) by default.

## 📂 Project Structure

```bash
message-app/
  ├── backend/       # Java Spring Boot backend
  ├── frontend/      # React + TypeScript frontend
  └── .gitmodules    # Git submodules config
```

## ✅ Features

- Fullstack communication via REST API
- Clean architecture using modern frameworks
- Input validation
- H2 in-memory database support
- Ready-to-run setup with minimal configuration
- Git submodules to separate concerns

## 📄 License

This code was created as part of a **recruitment assignment**. It is not licensed for production use.

## 🙋 Author

**Jacek Kaczmarek**  
GitHub: [@JacekKaczmarek10](https://github.com/JacekKaczmarek10)
