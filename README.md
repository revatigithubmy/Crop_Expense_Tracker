# 🌾 FarmersBuddy - Financial Dashboard API

A robust Spring Boot backend application designed to help farmers track their crop expenses, harvest revenues, and net profits with high precision. This project demonstrates industry-standard practices including JWT security, DTO mapping, and complex business logic aggregation.

---

## 🚀 Key Features
* **Automated Financial Calculations**: Real-time profit/loss tracking using a dedicated `ProfitCalculator` utility.
* **Granular Reporting**: Specific REST endpoints for individual crop stats and overall farmer-level investment summaries.
* **Secure Access**: Fully protected using **JWT (JSON Web Token)** authentication to ensure data privacy.
* **Enterprise Design Patterns**: Implements the **DTO (Data Transfer Object)** pattern to provide clean, structured API responses.



---

## 🛠️ Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Security:** Spring Security & JWT
* **Database:** Postgresql
* **ORM:** Spring Data JPA (Hibernate)
* **Utilities:** Lombok, Maven

---

## 📂 Project Structure
The project follows a clean N-Tier architecture:
* `controller/`: Handles REST requests and path mapping.
* `service/`: Contains core business logic and financial aggregation.
* `repository/`: Interfaces for database communication.
* `dto/response/`: Structured response objects (`DashboardResponseDTO`, `ProfitResponseDTO`).
* `entity/`: Database models representing Crop, Expense, Harvest, and Farmer.

---

## 📡 API Endpoints (Dashboard)

All endpoints require a **Bearer Token** in the Authorization header.

| Feature | Method | Endpoint |
| :--- | :--- | :--- |
| **Crop Total Expense** | `GET` | `/dashboard/crop/{cropId}/total-expense` |
| **Crop Net Profit** | `GET` | `/dashboard/crop/{cropId}/profit` |
| **Farmer Total Investment** | `GET` | `/dashboard/farmer/{farmerId}/total-investment` |
| **Farmer Total Net Profit** | `GET` | `/dashboard/farmer/{farmerId}/total-profit` |



---

## 🧪 How to Run
1. **Clone the Repo:** `git clone https://github.com/your-username/farmers-buddy.git`
2. **Database Config:** Update `src/main/resources/application.properties` with your postgresql username and password.
3. **Build Project:** Run `mvn clean install`.
4. **Start App:** Run the application on port `8081`.
5. **Test:** Use **Postman** to send requests. Ensure you log in first to receive your JWT token.

---

## 📊 Sample Response Format
The Dashboard APIs return a structured JSON object:
```json
{
    "farmerId": 8,
    "cropId": 1,
    "totalExpense": 8600.00,
    "totalRevenue": 504600.00,
    "totalProfit": 496000.00,
    "cropBreakdown": [...] 
}
