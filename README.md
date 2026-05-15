# Food Delivery Saga Choreography Pattern

A distributed Food Delivery System implementing the **Saga Choreography Design Pattern** using Spring Boot microservices, RabbitMQ, and PostgreSQL.

---

# 🚀 Tech Stack

* Java 21
* Spring Boot
* RabbitMQ
* PostgreSQL
* Maven

---

# 📦 Microservices

This project contains the following microservices:

## 1. Order Service

Responsible for:

* Creating food orders
* Managing order status
* Handling order compensation (CANCELLED)

---

## 2. Payment Service

Responsible for:

* Processing payments
* Publishing payment events
* Handling payment compensation (REFUNDED)

---

## 3. Restaurant Service

Responsible for:

* Accepting/rejecting food orders
* Publishing restaurant events

---

# 🐇 RabbitMQ Configuration

## Important Ports

| Port  | Purpose                       |
| ----- | ----------------------------- |
| 5672  | RabbitMQ Messaging Port       |
| 15672 | RabbitMQ Management Dashboard |

---

## RabbitMQ Dashboard

```text
http://localhost:15672
```

### Default Login

```text
username : guest
password : guest
```

---

# 🖥️ RabbitMQ Commands (Windows 11)

## Start RabbitMQ Server

```cmd
net start RabbitMQ
```

---

## Stop RabbitMQ Server

```cmd
net stop RabbitMQ
```

---

## Check RabbitMQ Status

```cmd
rabbitmqctl status
```

---

## Enable RabbitMQ Dashboard

```cmd
rabbitmq-plugins enable rabbitmq_management
```

---

# 🏗️ Architecture

This project follows the **Saga Choreography Pattern**.

In choreography:

* There is NO central orchestrator
* Services communicate using events
* Each service reacts independently

---

# 🔄 Success Flow

```text
Order Created
    ↓
Payment Success
    ↓
Restaurant Accepted
```

---

# ❌ Failure / Compensation Flow

```text
Order Created
    ↓
Payment Success
    ↓
Restaurant Rejected
    ↓
Payment Refunded
    ↓
Order Cancelled
```

---

# 📡 RabbitMQ Components Used

| Component                    | Type      | Purpose                       |
| ---------------------------- | --------- | ----------------------------- |
| RabbitTemplate               | Class     | Producer to send messages     |
| Queue                        | Class     | Message storage               |
| TopicExchange                | Class     | Routes messages               |
| Binding                      | Class     | Connects queue + exchange     |
| BindingBuilder               | Class     | Creates routing rules         |
| MessageConverter             | Interface | Converts messages             |
| Jackson2JsonMessageConverter | Class     | Java Object ↔ JSON conversion |

---

# 🗂️ Project Structure

```text
food-delivery-saga-choreography/
│
├── order-service/
├── payment-service/
├── restaurant-service/
│
├── README.md
└── .gitignore
```

---

# ⚙️ Setup Instructions

## 1. Install Required Software

* Java 21
* PostgreSQL
* RabbitMQ Server
* Erlang OTP
* Maven

---

## 2. Create PostgreSQL Databases

```sql
CREATE DATABASE order_db;
CREATE DATABASE payment_db;
CREATE DATABASE restaurant_db;
```

---

## 3. Start RabbitMQ Server

```cmd
net start RabbitMQ
```

---

## 4. Run Services

Run each microservice separately:

```bash
mvn spring-boot:run
```

---

# 🌐 Service Ports

| Service            | Port |
| ------------------ | ---- |
| order-service      | 8081 |
| payment-service    | 8082 |
| restaurant-service | 8083 |

---

# 📚 Concepts Learned

* Microservices Architecture
* Event-Driven Communication
* Saga Choreography Pattern
* Compensation Transactions
* RabbitMQ Producer/Consumer
* Distributed Transactions
* Eventual Consistency

---

---
