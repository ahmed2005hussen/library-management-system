# Library Management System — Requirements

## User Roles

### Normal User
- Register a new account
- Browse and search books
- Add books to cart (buy or borrow)
- Checkout and pay for orders
- Return borrowed books
- View order history
- View borrowed books history

### Worker
- Add new books
- Edit existing books
- Delete books

### Admin
- Add new workers
- Remove workers
- Manage worker accounts

---

## Book

| Field | Type |
|-------|------|
| id | int |
| title | String |
| author | String |
| category | String |
| buy_price | double |
| borrow_price_per_day | double |
| can_buy | boolean |
| can_borrow | boolean |
| copies | int |
| available | boolean |

---

## Cart

- Add book to cart (type: BUY or BORROW)
- Remove item from cart
- View cart items
- Checkout cart → creates an Order
- Pay order value

---

## Orders & Borrowing

- Order contains one or more books
- Each order has a status: PENDING / CONFIRMED / SHIPPED / DELIVERED / CANCELLED
- Borrow record tracks: borrow date, due date, return date
- Borrow status: BORROWED / RETURNED / LATE
