🏨 Hotel Booking Web Application
A full-stack Hotel Booking Web Application developed using React.js for the frontend and Spring Boot for the backend.
This application allows users to search hotels, view room details, make bookings, and manage reservations, while providing admin functionalities for hotel and booking management.

📌 Features
👤 User Features
User registration & login (JWT authentication)

Search hotels by location, date & price

View hotel details and room availability

Book hotel rooms online

View and cancel bookings

Secure payment simulation (optional)

🛠 Admin Features
Admin login

Add, update & delete hotels

Manage room details and pricing

View all bookings and users

Booking status management

🧑‍💻 Tech Stack
Frontend
React.js

JavaScript (ES6)

HTML5 & CSS3

Axios (API calls)

React Router

Bootstrap / Material UI

Backend
Spring Boot

Spring Security (JWT)

Spring Data JPA

RESTful APIs

Hibernate

Database
MySQL / MongoDB

Tools
Git & GitHub

Postman

VS Code / IntelliJ IDEA

🏗 Project Architecture
Hotel-Booking-App
│
├── frontend (React)
│   ├── components
│   ├── pages
│   ├── services
│   ├── App.js
│   └── index.js
│
├── backend (Spring Boot)
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   └── config
│
└── database
🔐 Authentication & Security
JWT-based authentication

Role-based access (USER / ADMIN)

Password encryption using BCrypt

