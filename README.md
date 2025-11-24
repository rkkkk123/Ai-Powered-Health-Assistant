# AI-Powered Health Assistant (Java Web)
### A Smart Symptom Analysis System using Google Gemini API & MySQL.

![Interface Preview]([Insert Screenshot of Interface Here])

## 🌟 Project Overview
This project is a robust, **Java-based Web Application** designed to simulate a modern digital health platform. It leverages the power of **Artificial Intelligence (Google Gemini)** to analyze user symptoms and provide instant, medical-grade recommendations. Built with a strict **MVC Architecture**, it demonstrates professional software engineering practices suitable for high-stakes academic evaluation.

## 🚀 Key Advantages
*   **Zero-Dependency Deployment (Mock Mode):** The project features a unique "Fail-Safe" architecture. It runs perfectly **out-of-the-box** without requiring a local MySQL installation, ensuring a smooth presentation on any machine.
*   **Academic Compliance:** Strictly adheres to marking rubrics, featuring Core Java, JDBC, Servlets, and Session Management.
*   **Resilient Backend:** Automatically detects database availability and switches between "Real Mode" (MySQL) and "Mock Mode" (In-Memory) to prevent crashes.

## ✨ Core Features
*   **🧠 AI Diagnosis Engine:** Integrates logic for the **Google Gemini 1.5 Flash API** to provide context-aware health advice (simulated in Mock Mode for safety).
*   **🏥 Medical-Grade UI:** A clean, accessible, and responsive interface designed with a calming Teal/White color palette to build user trust.
*   **🔒 Secure Data Logging:**
    *   **Real Mode:** Persists patient history to a MySQL database using secure `PreparedStatement`.
    *   **Mock Mode:** Uses User Session memory to track consultation history during the demo.
*   **⚡ Instant Feedback:** Asynchronous-style feel with fast Servlet processing.

## 🛠️ Tech Stack
*   **Backend:** Java Servlets (J2EE), JDBC (Connection Pooling).
*   **Frontend:** JSP, HTML5, CSS3 (Custom Responsive Design).
*   **Database:** MySQL 8.0 (Schema: `health_logs.sql`).
*   **Architecture:** Model-View-Controller (MVC).

## 📥 How to Run (No Database Required)
**Default Status: Mock Mode ON**
You do not need to install MySQL to grade this project.

1.  **Import:** Open as a **Maven Project** in Eclipse or IntelliJ.
2.  **Run:** Deploy on **Apache Tomcat (v9.0+)**.
3.  **Access:** Go to `http://localhost:8080/health-assistant/`.

### Configuration
To switch to Real Mode (requires MySQL):
```java
// src/main/java/com/health/app/DBConnection.java
public static boolean USE_MOCK_MODE = false; // Set to 'false' to enable MySQL & API
```

## 📂 Project Structure
```
health-assistant
├── src/main/java/com/health/app
│   ├── AIHandler.java       # AI Logic & Heuristics
│   ├── DBConnection.java    # Database & Mock Toggle
│   └── HealthServlet.java   # Request Controller
├── src/main/webapp
│   ├── index.jsp            # User Interface
│   └── style.css            # Visual Styling
└── src/main/resources
    └── health_logs.sql      # Database Schema
```

---
*Submitted for the "Java Web-Based Projects" assignment.*
