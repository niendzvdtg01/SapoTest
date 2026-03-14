# Flash Sale Order System

## 1. Giới thiệu

Dự án xây dựng một **hệ thống đặt hàng Flash Sale đơn giản** sử dụng:

* **Backend:** Java Spring Boot (REST API)
* **Frontend:** React
* **Database:** MySQL

Hệ thống cho phép người dùng mua sản phẩm trong chương trình Flash Sale với các kiểm tra:

* Kiểm tra tồn kho sản phẩm
* Giới hạn số lượng mua mỗi người
* Tạo đơn hàng
* Trả kết quả thành công hoặc thất bại

---

# 2. Kiến trúc hệ thống

Hệ thống gồm 2 phần chính:

## Backend (Spring Boot)

Chức năng:

* Xử lý logic Flash Sale
* Kiểm tra tồn kho
* Kiểm tra giới hạn mua mỗi người
* Tạo đơn hàng
* Trả kết quả API cho frontend

## Frontend (React)

Chức năng:

* Hiển thị danh sách sản phẩm Flash Sale
* Cho phép người dùng bấm **Mua ngay**
* Gửi request API đến backend
* Hiển thị thông báo kết quả

---

# 3. API Flash Sale

## Endpoint

```
POST /api/flash-sale/order
```

## Request Body

```json
{
  "productId": 1,
  "userId": 2,
  "quantity": 1
}
```

### Ý nghĩa

| Field     | Mô tả             |
| --------- | ----------------- |
| productId | ID sản phẩm       |
| userId    | ID người dùng     |
| quantity  | số lượng muốn mua |

---

## Response

### Thành công

```json
{
  "message": "Đặt hàng thành công"
}
```

### Hết hàng

```json
{
  "message": "Sản phẩm đã hết hàng"
}
```

### Vượt giới hạn

```json
{
  "message": "Bạn đã vượt quá số lượng cho phép"
}
```

---

# 4. Logic xử lý Flash Sale

Luồng xử lý:

```
Client gửi request
        ↓
Backend nhận request
        ↓
Kiểm tra sản phẩm tồn tại
        ↓
Kiểm tra tồn kho
        ↓
Kiểm tra số lượng user đã mua
        ↓
Nếu hợp lệ:
   - Giảm stock
   - Tạo Order
   - Tạo OrderItem
        ↓
Trả response
```

---

# 5. Cấu trúc thư mục

```
flash-sale-test
│
├── backend
│   ├── controller
│   │   └── FlashSaleController.java
│   │
│   ├── service
│   │   └── FlashSaleService.java
│   │
│   ├── dto
│   │   └── FlashSaleRequest.java
│   │
│   ├── repository
│   │   ├── ProductRepository.java
│   │   ├── OrderRepository.java
│   │   └── OrderItemRepository.java
│   │
│   └── entity
│       ├── Product.java
│       ├── Order.java
│       └── OrderItem.java
│
├── database
│   └── schema.png
│
├── frontend
│   └── FlashSale.jsx
│
└── README.md
```

---

# 6. Thiết kế Database

Hệ thống sử dụng 3 bảng chính.

## Product

Lưu thông tin sản phẩm Flash Sale

```
id
name
original_price
sale_price
stock
created_at
```

## Order

Lưu thông tin đơn hàng

```
id
user_id
total_price
created_at
```

## OrderItem

Lưu chi tiết sản phẩm trong đơn hàng

```
id
order_id
product_id
quantity
price
```

Sơ đồ database:

```
database/schema.png
```

---

# 7. Frontend

Component React thực hiện:

* Hiển thị danh sách sản phẩm Flash Sale
* Hiển thị:

  * tên sản phẩm
  * giá gốc
  * giá sale
  * số lượng còn
* Nút **Mua ngay**
* Gửi request đến API
* Hiển thị thông báo kết quả

---

# 8. Công nghệ sử dụng

## Backend

* Java
* Spring Boot
* Spring Data JPA
* REST API

## Frontend

* React
* Axios

## Database

* MySQL

---

# 9. Hướng phát triển thêm

Trong hệ thống Flash Sale thực tế có thể cải tiến:

* Redis cache để quản lý stock
* Message Queue (RabbitMQ / Kafka)
* Cơ chế chống oversell
* Rate limit chống spam
* Authentication và Authorization

---

# 10. Kết luận

Dự án mô phỏng hệ thống Flash Sale cơ bản bao gồm:

* REST API đặt hàng
* Kiểm tra tồn kho
* Giới hạn mua mỗi người
* Tạo đơn hàng
* Kết nối frontend và backend
