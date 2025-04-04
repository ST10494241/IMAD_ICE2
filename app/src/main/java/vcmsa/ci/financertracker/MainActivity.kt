package vcmsa.ci.financertracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var incomeInput: EditText
    private lateinit var expenseFood: EditText
    private lateinit var expenseEntertainment: EditText
    private lateinit var expenseUtility: EditText
    private lateinit var expenseMiscellaneous: EditText
    private lateinit var calculateButton: Button
    private lateinit var totalIncomeText: TextView
    private lateinit var totalExpensesText: TextView
    private lateinit var balanceText: TextView
    private lateinit var expenseMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        incomeInput = findViewById(R.id.incomeInput)
        expenseFood = findViewById(R.id.expenseFood)
        expenseEntertainment = findViewById(R.id.expenseEntertainment)
        expenseUtility = findViewById(R.id.expenseUtility)
        expenseMiscellaneous = findViewById(R.id.expenseMiscellaneous)
        calculateButton = findViewById(R.id.calculateButton)
        totalIncomeText = findViewById(R.id.totalIncomeText)
        totalExpensesText = findViewById(R.id.totalExpensesText)
        balanceText = findViewById(R.id.balanceText)
        expenseMessage = findViewById(R.id.expenseMessage)

        // Set button click listener
        calculateButton.setOnClickListener { calculateBalance() }
    }

    private fun calculateBalance() {
        // Get income and expenses
        val incomeString = incomeInput.text.toString()
        val foodString = expenseFood.text.toString()
        val entertainmentString = expenseEntertainment.text.toString()
        val utilityString = expenseUtility.text.toString()
        val miscellaneousString = expenseMiscellaneous.text.toString()

        // Check for empty or invalid input
        if (incomeString.isEmpty() || !isNumeric(incomeString) ||
            foodString.isEmpty() || !isNumeric(foodString) ||
            entertainmentString.isEmpty() || !isNumeric(entertainmentString) ||
            utilityString.isEmpty() || !isNumeric(utilityString) ||
            miscellaneousString.isEmpty() || !isNumeric(miscellaneousString)) {
            Toast.makeText(this, "Please enter valid income and expense values.", Toast.LENGTH_SHORT).show()
            return
        }

        // Parse the input values
        val income = incomeString.toDouble()
        val food = foodString.toDouble()
        val entertainment = entertainmentString.toDouble()
        val utility = utilityString.toDouble()
        val miscellaneous = miscellaneousString.toDouble()

        // Calculate total expenses and balance
        val totalExpenses = food + entertainment + utility + miscellaneous
        val balance = income - totalExpenses

        // Display results
        totalIncomeText.text = "Total Income: $$income"
        totalExpensesText.text = "Total Expenses: $$totalExpenses"
        balanceText.text = "Balance: $$balance"

        // Decision logic based on balance
        if (balance >= 0) {
            expenseMessage.text = "You are saving money!"
            expenseMessage.setTextColor(Color.GREEN)
        } else {
            expenseMessage.text = "You are overspending!"
            expenseMessage.setTextColor(Color.RED)
        }

        // Expense percentage logic
        analyzeExpenseCategory(food, "Food", income)
        analyzeExpenseCategory(entertainment, "Entertainment", income)
        analyzeExpenseCategory(utility, "Utility", income)
        analyzeExpenseCategory(miscellaneous, "Miscellaneous", income)
    }

    private fun analyzeExpenseCategory(expense: Double, category: String, income: Double) {
        val expensePercentage = (expense / income) * 100
        val expenseCategoryText = TextView(this)
        expenseCategoryText.text = "$category Expense: $$expense"

        if (expensePercentage > 30) {
            expenseCategoryText.setTextColor(Color.RED)
            expenseMessage.append("\nWarning: $category is too high! Consider cutting back.")
        } else if (expensePercentage < 5) {
            expenseCategoryText.setTextColor(Color.GREEN)
            expenseMessage.append("\nGreat! You're keeping $category expenses low.")
        } else {
            expenseCategoryText.setTextColor(Color.BLACK)
            expenseMessage.append("\n$category expenses are within a reasonable range.")
        }
    }

    // Helper method to check if a string is numeric
    private fun isNumeric(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}
