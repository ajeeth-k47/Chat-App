# ChatApp – A Simple Client-Server Chat Application

A full-stack chat application using React, Spring Boot, PostgreSQL, and WebSockets — built to demonstrate understanding of client-server architecture.

---

## 📌 Project Overview

The application is divided into two main parts:

- **Frontend**: Developed using **React**, it allows users to:
  - Create and view chats
  - Enter individual chats
  - Send and view messages in real-time

- **Backend**: Built using **Spring Boot**, it:
  - Exposes RESTful APIs for frontend communication
  - Manages data persistence using **JPA** and **PostgreSQL**
  - Uses **WebSockets** to deliver real-time chat updates across clients

---

## 🧪 Lab Test Extension

As part of the lab test, the application was extended to:

- Allow users to specify a **chat creator** when creating a new chat
- Display the **creator** alongside each chat on the home page

These additions demonstrate the ability to modify an existing codebase quickly and effectively under test conditions.

---

## 🧠 Technologies Used

| Layer       | Technology       |
|-------------|------------------|
| Frontend    | React            |
| Backend     | Spring Boot      |
| Database    | PostgreSQL       |
| ORM         | Spring Data JPA  |
| Realtime    | WebSockets (STOMP) |



