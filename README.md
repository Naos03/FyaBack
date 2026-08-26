# FyaBack - API de Gestión de Créditos

Este es el backend del sistema de gestión de créditos para **Fya Social Capital**, construido con **Spring Boot**. Proporciona una API RESTful segura para el registro y consulta de solicitudes de crédito, incluyendo notificaciones por correo electrónico y persistencia en base de datos.

## 🚀 Tecnologías y Herramientas

- **Java 17+**
- **Spring Boot** (Web, Data JPA, Security, Mail, Validation)
- **Base de Datos:** H2 Database (En memoria)
- **Seguridad:** JWT (JSON Web Tokens) básico y Rate Limiting.
- **Notificaciones:** SMTP (Mailtrap / Gmail) de forma asincrónica.

## ⚙️ Características Principales

1. **Precarga de Datos:** Inicialización automática de la base de datos con 10 registros de prueba a través de `data.sql` asegurando el orden de ejecución con Hibernate.
2. **Validación Estricta:** Uso de `jakarta.validation` para asegurar la integridad de los datos entrantes (ej. montos mínimos, cédulas válidas).
3. **Seguridad Integrada:**
   - Protección contra inyecciones SQL nativa por JPA.
   - Endpoints protegidos mediante un token de autorización (Bearer Token).
   - Filtro de _Rate Limiting_ (Máx. 20 peticiones por minuto por IP) para prevenir ataques de fuerza bruta.
4. **Notificaciones Asincrónicas:** Envío de correos automáticos a `fyasocialcapital@gmail.com` al registrar un crédito utilizando `@Async` para no bloquear la respuesta HTTP.

## 🛠️ Instalación y Ejecución

1. Clona el repositorio.
2. Asegúrate de tener configuradas tus variables de entorno para Java y Maven.
3. Configura tus credenciales SMTP en `src/main/resources/application.properties`.
4. Ejecuta el proyecto desde la terminal:
   ```bash
   mvn spring-boot:run
   ```
5. La API estará disponible en http://localhost:9090/api/creditos
