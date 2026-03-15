## Requirements

| Tool | Version |
| Java | 21+      |
| MySQL | 8.0+    |

## Setup & Configuration
### 1. Clone the repository
```bash
git clone https://github.com/chhipajitender2512-dev/Product-Management-Sarvika.git
```

### 2. Create Mysql Database
```sql
CREATE DATABASE product_management;
```

### 3. Update application properties file
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build the project
```bash
./gradlew clean build
```

### 5. Run the application
```bash
./gradlew bootRun
```

The server starts on **http://localhost:8080**

## Authentication

The API uses **JWT Bearer Token** authentication.

### Default Users (seeded on startup)

| Username | Password
| `admin` | `admin123`

### Login to get Token
http://localhost:8080/api/auth/login
```bash
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "admin",
    "password": "admin@123"
}'
```


## Api Endpoints
Required Token to access Product APIs.

### Base URL: `http://localhost:8080/api`

#### `GET /products`
Returns a list of Products.
**Example:**
```bash
curl --location 'http://localhost:8080/api/products' \
--header 'Authorization: Bearer <token>'
```

### `POST /products`
Creates a Product.
**Example:**
```bash
curl --location 'http://localhost:8080/api/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <token>' \
--data '{
"name":"Soap",
"price": 25,
"description": "Soap product"
}'
```

### `GET /products/{id}`
Get a Product by ID.
**Example:**
```bash
curl --location 'http://localhost:8080/api/products/4' \
--header 'Authorization: Bearer <token>'
```

### `PUT /products/{id}`
Update a Product by ID.
**Example:**
```bash
curl --location --request PUT 'http://localhost:8080/api/products/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <token>' \
--data '{
    "name" : "fan",
    "description" : "product fan",
    "price" : 1000
}'
```

### ``DELETE /products/{id}``
**Example**
```bash
curl --location --request DELETE 'http://localhost:8080/api/products/10' \
--header 'Authorization: Bearer <token>'
```

## Access Swagger
### URL: http://localhost:8080/swagger-ui/index.html