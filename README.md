# IMAD Ice Task 2 – Budget Tracker App

**Student:** ST10494241 – Albino Magagule Junior  

---

## 📱 App Overview

This Android app helps users track their income and expenses to determine whether they are saving or overspending. It calculates the total expenses and provides visual feedback based on spending patterns.

---

## 🧠 Logic Behind Error Handling & Expense Messaging

### ✅ Input Validation
Before performing any calculations, the app:
- Checks if **all input fields** are filled and numeric using a helper function `isNumeric()`.
- If any field is invalid or empty, a **toast message** is displayed
  
### 📊 Expense Analysis
A function called `analyzeExpenseCategory()` evaluates each expense category as a percentage of the total income:
Depending on the percentage:
- **> 30%** – Shows a **warning** message in **red** text.
- **< 5%** – Displays a **positive** message in **green** text.
- **Between 5% and 30%** – Provides a **neutral** message in **black** text.

---

## 📷 Screenshots

### Figure 1: Balanced Budget – Moderate Expenses
![Screen Shot 2025-04-04 at 10 33 05 PM](https://github.com/user-attachments/assets/18012099-99a5-477f-948d-322942e97f75)

### Figure 2: Overspending Warning
![Screen Shot 2025-04-04 at 10 35 16 PM](https://github.com/user-attachments/assets/917d8e85-727f-4459-9831-09391df8b1ab)

