# RabbitMQ Spring Boot ve KEDA ile Dinamik Ölçeklendirme

Bu proje, Spring Boot ve RabbitMQ kullanarak oluşturulmuş bir üretici-tüketici (producer-consumer) mimarisini ve bu mimarinin Kubernetes üzerinde **KEDA** kullanılarak olay tabanlı (event-driven) olarak nasıl otomatik ölçeklendirildiğini göstermektedir.

## 🚀 Özellikler

- **Üretici Servis (Producer)**: Mesajları RabbitMQ kuyruğuna gönderen Spring Boot API.
- **Tüketici Servis (Consumer)**: Kuyruktaki mesajları işleyen Spring Boot uygulaması.
- **RabbitMQ**: Servisler arası asenkron iletişim için mesaj aracısı.
- **KEDA Entegrasyonu**: RabbitMQ kuyruğundaki bekleyen mesaj sayısına göre tüketici (consumer) pod'larını otomatik olarak ölçeklendirir.
- **Dockerize Edilmiş**: Yerel geliştirme için `Docker-compose` desteği.
- **Kubernetes Uyumlu**: Deployment manifestleri ve KEDA `ScaledObject` yapılandırması hazır.

## 🛠 Kullanılan Teknolojiler

- **Java 17+**
- **Spring Boot 3.x**
- **RabbitMQ**
- **Docker & Docker Compose**
- **Kubernetes**
- **KEDA (Kubernetes Event-driven Autoscaling)**

## 📂 Proje Yapısı

- `/producer`: Mesaj üreten Spring Boot uygulaması.
- `/consumer`: Mesajları tüketen Spring Boot uygulaması.
- `docker-compose.yaml`: RabbitMQ ve servisleri yerelde ayağa kaldırmak için kurulum.
- `consumer/deployment.yaml`: Consumer servisinin K8s üzerinde deployment dosyası.
- `consumer/scaledobject.yaml`: KEDA otomatik ölçeklendirme yapılandırması.

## ☸️ Kubernetes ve KEDA Yapılandırması

Tüketici servisi, KEDA kullanılarak kuyruk yoğunluğuna göre ölçeklenecek şekilde ayarlanmıştır.

### ScaledObject Yapılandırması
`ScaledObject`, `notification_queue` kuyruğunu izler:
- **minReplicaCount**: 1 (Her zaman en az 1 pod çalışır)
- **maxReplicaCount**: 10 (En fazla 10 pod'a kadar ölçeklenir)
- **Eşik Değer (Threshold)**: 10 mesaj (Kuyruktaki her 10 mesaj için yeni bir pod açılır).

### Ortam Değişkenleri
Consumer şunları bekler:
- `SPRING_RABBITMQ_HOST`: RabbitMQ broker adresi.
- `SPRING_RABBITMQ_PORT`: RabbitMQ portu (varsayılan 5672).

## 🏃 Nasıl Çalıştırılır?

### Yerel Ortam (Docker Compose)
```bash
docker-compose up -d
```

### Kubernetes
1. Cluster'ınızda KEDA'nın kurulu olduğundan emin olun.
2. Tüketici servisinin deployment'ını yapın:
   ```bash
   kubectl apply -f consumer/deployment.yaml
   ```
3. KEDA ölçeklendiriciyi aktif edin:
   ```bash
   kubectl apply -f consumer/scaledobject.yaml
   ```

## 📝 Lisans
Bu proje eğitim amaçlıdır.
