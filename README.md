# RabbitMQ Spring Boot with KEDA Scaling

This project demonstrates a producer-consumer architecture using Spring Boot and RabbitMQ, with event-driven autoscaling in Kubernetes powered by **KEDA**.

## 🚀 Features

- **Producer Service**: A Spring Boot API that sends messages to a RabbitMQ queue.
- **Consumer Service**: A Spring Boot application that processes messages from the queue.
- **RabbitMQ**: Message broker for asynchronous communication.
- **KEDA Integration**: Automatically scales the consumer pods based on the number of messages waiting in the RabbitMQ queue.
- **Dockerized**: Includes `Docker-compose` for local development.
- **Kubernetes Ready**: Deployment manifests and KEDA `ScaledObject` included.

## 🛠 Technologies

- **Java 17+**
- **Spring Boot 3.x**
- **RabbitMQ**
- **Docker & Docker Compose**
- **Kubernetes**
- **KEDA (Kubernetes Event-driven Autoscaling)**

## 📂 Project Structure

- `/producer`: Spring Boot app that generates messages.
- `/consumer`: Spring Boot app that consumes messages.
- `docker-compose.yaml`: Setup for RabbitMQ and services locally.
- `consumer/deployment.yaml`: K8s deployment for the consumer.
- `consumer/scaledobject.yaml`: KEDA scaling configuration.

## ☸️ Kubernetes & KEDA Setup

The consumer is configured to scale automatically using KEDA.

### ScaledObject Configuration
The `ScaledObject` monitors the `notification_queue`.
- **minReplicaCount**: 1
- **maxReplicaCount**: 10
- **Threshold**: 10 messages (Scales up every 10 messages in the queue).

### Environment Variables
The consumer expects:
- `SPRING_RABBITMQ_HOST`: RabbitMQ broker address.
- `SPRING_RABBITMQ_PORT`: RabbitMQ port (default 5672).

## 🏃 How to Run

### Local (Docker Compose)
```bash
docker-compose up -d
```

### Kubernetes
1. Ensure KEDA is installed in your cluster.
2. Apply the consumer deployment:
   ```bash
   kubectl apply -f consumer/deployment.yaml
   ```
3. Apply the KEDA scaler:
   ```bash
   kubectl apply -f consumer/scaledobject.yaml
   ```

## 📝 License
This project is for educational purposes.
