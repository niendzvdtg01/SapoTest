Sapo Flash Sale Backend

Đây là một hệ thống Flash Sale Backend đơn giản được xây dựng bằng Spring Boot nhằm mô phỏng cách một hệ thống thương mại điện tử xử lý đơn hàng trong thời điểm khuyến mãi với lượng truy cập cao.

Hệ thống hỗ trợ các chức năng chính:

Xác thực người dùng bằng JWT

Quản lý người dùng

Quản lý sản phẩm

Mua sản phẩm trong Flash Sale

Tạo đơn hàng

Quản lý chi tiết đơn hàng

Hệ thống được xây dựng theo mô hình kiến trúc nhiều tầng (Layered Architecture) thường dùng trong các ứng dụng backend hiện đại.

Công nghệ sử dụng

Backend

Java 17

Spring Boot

Spring Data JPA

Spring Security

Security

JWT Authentication

Database

MySQL

Build Tool

Maven

Cấu trúc thư mục project
Backend
│
├── Config
│   └── SecurityConfig.java
│
├── Controller
│   ├── AuthController.java
│   ├── FlashSaleController.java
│   ├── ProductController.java
│   └── UserController.java
│
├── Dto
│   ├── FlashSaleRequest.java
│   ├── LoginRequests.java
│   └── UserCreation.java
│
├── Entity
│   ├── OrderItem.java
│   ├── Product.java
│   └── Users.java
│
├── JpaRepository
│   ├── OrderItemRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UsersRepository.java
│
├── Security
│   ├── AuthFilter.java
│   └── jwtUtils.java
│
├── Service
│   ├── AuthService.java
│   ├── FlashSaleConsumer.java
│   ├── FlashSaleService.java
│   ├── OrderService.java
│   ├── ProductService.java
│   └── UsersService.java
│
├── SapoApplication.java
└── ServletInitializer.java
Mô tả các package
Config

Chứa các file cấu hình của hệ thống.

Ví dụ:

SecurityConfig.java

Chức năng:

cấu hình Spring Security

cấu hình filter xác thực

bảo vệ các API

Controller Layer

Controller chịu trách nhiệm nhận request từ client.

AuthController

Xử lý các API liên quan đến xác thực:

đăng nhập

tạo token JWT

FlashSaleController

Xử lý các request mua hàng trong chương trình Flash Sale.

ProductController

Xử lý các API liên quan đến sản phẩm:

tạo sản phẩm

xem danh sách sản phẩm

UserController

Xử lý các API liên quan đến người dùng:

tạo tài khoản

quản lý thông tin người dùng

DTO Layer

DTO được sử dụng để nhận dữ liệu request từ client.

FlashSaleRequest

Request khi người dùng mua sản phẩm trong Flash Sale.

userId
productId
quantity
LoginRequests

Request khi người dùng đăng nhập.

email
password
UserCreation

Request khi tạo tài khoản người dùng mới.

name
email
password
Entity Layer

Entity đại diện cho các bảng trong database.

Users

Lưu thông tin người dùng.

id
name
email
password
Product

Lưu thông tin sản phẩm.

id
name
price
salePrice
stock
Order

Đại diện cho một đơn hàng.

id
user_id
total_amount
status
created_at
OrderItem

Lưu thông tin sản phẩm trong đơn hàng.

id
order_id
product_id
quantity
price
Repository Layer

Repository dùng để truy cập database thông qua Spring Data JPA.

Các repository trong hệ thống:

UsersRepository

ProductRepository

OrderRepository

OrderItemRepository

Tất cả repository đều kế thừa từ:

JpaRepository
Service Layer

Service chứa business logic của hệ thống.

AuthService

Xử lý các chức năng xác thực:

đăng nhập

tạo JWT token

UsersService

Xử lý các chức năng liên quan đến người dùng:

tạo người dùng

lấy thông tin người dùng

ProductService

Xử lý các chức năng liên quan đến sản phẩm:

tạo sản phẩm

lấy danh sách sản phẩm

FlashSaleService

Xử lý logic mua hàng trong Flash Sale:

kiểm tra sản phẩm

kiểm tra người dùng

xử lý request mua hàng

OrderService

Xử lý việc tạo đơn hàng:

tính tổng tiền

tạo order

tạo order item

FlashSaleConsumer

Mô phỏng consumer xử lý đơn hàng bất đồng bộ.

Trong hệ thống thực tế, thành phần này thường sẽ:

nhận message từ Message Queue

xử lý tạo đơn hàng

ghi vào database

Security

Hệ thống sử dụng JWT (JSON Web Token) để xác thực.

Các thành phần chính:

AuthFilter

Filter kiểm tra JWT trong request.

Chức năng:

đọc token từ header

xác thực token

set thông tin user vào security context

jwtUtils

Chứa các hàm xử lý JWT:

tạo token

kiểm tra token

lấy thông tin user từ token

Quan hệ database
Users
  │
  │ 1
  ▼
Orders
  │
  │ 1
  ▼
OrderItems
  │
  │
  ▼
Products

Ý nghĩa:

Một User có thể tạo nhiều Order

Một Order có thể chứa nhiều OrderItem

Mỗi OrderItem liên kết với một Product

Luồng xử lý Flash Sale

Request từ client:

POST /flashsale

Ví dụ request:

{
  "userId": 1,
  "productId": 5,
  "quantity": 2
}

Luồng xử lý:

Client
   ↓
FlashSaleController
   ↓
FlashSaleService
   ↓
Kiểm tra User
Kiểm tra Product
   ↓
Tạo Order
   ↓
Tạo OrderItem
   ↓
Lưu vào Database
Cách chạy project

Clone repository:

git clone https://github.com/niendzvdtg01/SapoTest.git

Di chuyển vào thư mục project:

cd SapoTest

Chạy project:

mvn spring-boot:run

Ứng dụng sẽ chạy tại:

http://localhost:8080
Hướng phát triển trong tương lai

Để hệ thống Flash Sale có thể xử lý lượng truy cập lớn, có thể bổ sung:

Redis

Sử dụng Redis để:

quản lý stock

tránh overselling

Message Queue

Sử dụng:

Kafka

RabbitMQ

để xử lý đơn hàng bất đồng bộ.

Rate Limiting

Giới hạn số request từ một user.

Distributed Lock

Tránh nhiều user mua cùng một sản phẩm khi stock thấp.
