package vik.com.example.myappmulti


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder


class Calculator : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")

    // перменные для отображения на экранах ввода и ввода
    var outScreen = "КАЛЬКУЛЯТОР"
    var inScreen = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        // удаление последнего символав строке
        fun removeLastChar(str: String?): String? {
            return str?.replaceFirst(".$".toRegex(), "")
        }

        //добавление одного символа в строку
        @SuppressLint("ShowToast")
        fun outToScreenCalculator (char: String = "", anyString: String = ""): String {
            var anyStringChange: String = ""
            if (anyString.count()<= 24) {anyStringChange = anyString + char
            } else {
                Toast.makeText(applicationContext, "слишком длинное выражение", Toast.LENGTH_SHORT).show()
                anyStringChange = anyString
            }
            return anyStringChange
        }

        // обяъявление переменных
        // поле ввода
        val editTextInput: TextView = findViewById<TextView?>(R.id.editTextInput)
        // поле вывода
        val textView: TextView = findViewById(R.id.resultView)
        // кнопки
        val buttonClose : Button = findViewById(R.id.close)
        val buttonOne : Button = findViewById(R.id.one)
        val buttonTwo : Button = findViewById(R.id.two)
        val buttonThree : Button = findViewById(R.id.three)
        val buttonFour : Button = findViewById(R.id.four)
        val buttonFive : Button = findViewById(R.id.five)
        val buttonSix : Button = findViewById(R.id.six)
        val buttonSeven : Button = findViewById(R.id.seven)
        val buttonEight : Button = findViewById(R.id.eight)
        val buttonNine : Button = findViewById(R.id.nine)
        val buttonZero : Button = findViewById(R.id.zero)
        val buttonMultiplication : Button = findViewById(R.id.multiplication)
        val buttonDifference : Button = findViewById(R.id.difference)
        val buttonSumma : Button = findViewById(R.id.summa)
        val buttonDivision : Button = findViewById(R.id.division)
        val buttonDot : Button = findViewById(R.id.dot)
        val buttonBackSpace : Button = findViewById(R.id.backSpace)
        val buttonClear : Button = findViewById(R.id.clear)
        val buttonResult : Button = findViewById(R.id.result)

        // выход из ативности
        buttonClose.setOnClickListener{
            finish()
            }
        // очистка
        buttonClear.setOnClickListener {
            outScreen = "КАЛЬКУЛЯТОР"
            inScreen = ""
            textView.setText(outScreen)
            editTextInput.setText(inScreen)
        }
        // ввод  с клвиатуры и ввыдод на экран калькулятора
        buttonOne.setOnClickListener {
            inScreen = outToScreenCalculator("1", inScreen)
            editTextInput.setText(inScreen)}
        buttonTwo.setOnClickListener {
            inScreen = outToScreenCalculator("2", inScreen)
            editTextInput.setText(inScreen)}
        buttonThree.setOnClickListener {
            inScreen = outToScreenCalculator("3", inScreen)
            editTextInput.setText(inScreen)}
        buttonFour.setOnClickListener {
            inScreen = outToScreenCalculator("4", inScreen)
            editTextInput.setText(inScreen)}
        buttonFive.setOnClickListener {
            inScreen = outToScreenCalculator("5", inScreen)
            editTextInput.setText(inScreen)}
        buttonSix.setOnClickListener {
            inScreen = outToScreenCalculator("6", inScreen)
            editTextInput.setText(inScreen)}
        buttonSeven.setOnClickListener {
            inScreen = outToScreenCalculator("7", inScreen)
            editTextInput.setText(inScreen)}
        buttonEight.setOnClickListener {
            inScreen = outToScreenCalculator("8", inScreen)
            editTextInput.setText(inScreen)}
        buttonNine.setOnClickListener {
            inScreen = outToScreenCalculator("9", inScreen)
            editTextInput.setText(inScreen)}
        buttonZero.setOnClickListener {
            inScreen = outToScreenCalculator("0", inScreen)
            editTextInput.setText(inScreen)}
        buttonDot.setOnClickListener {
            inScreen = outToScreenCalculator(".", inScreen)
            editTextInput.setText(inScreen)}
        buttonMultiplication.setOnClickListener {
            inScreen = outToScreenCalculator("*", inScreen)
            editTextInput.setText(inScreen)}
        buttonDivision.setOnClickListener {
            inScreen = outToScreenCalculator("/", inScreen)
            editTextInput.setText(inScreen)}
        buttonSumma.setOnClickListener {
            inScreen = outToScreenCalculator("+", inScreen)
            editTextInput.setText(inScreen)}
        buttonDifference.setOnClickListener {
            inScreen = outToScreenCalculator("-", inScreen)
            editTextInput.setText(inScreen)}
        // удаление последнего введенного знака
        buttonBackSpace.setOnClickListener {
            inScreen = removeLastChar(inScreen).toString()
            editTextInput.setText(inScreen)
        }
        // вывод  результат операции
        buttonResult.setOnClickListener {
            outScreen = editTextInput.text.toString()
            if (outScreen == "")
            { android.widget.Toast.makeText(applicationContext, "в строке ввода пусто", android.widget.Toast.LENGTH_SHORT).show()}
            else {
                val expression = ExpressionBuilder(outScreen).build()
                val resultStr = expression.evaluate()
                val longResult = resultStr.toLong()
                if (resultStr == longResult.toDouble()) {
                    outScreen = longResult.toString()
                    textView.setText(outScreen)
                } else {
                    outScreen =resultStr.toString()
                    textView.setText(outScreen)
                }
            }
        }


        }
    // Сохраняем состояние экранов
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input",inScreen)
        outState.putString("output",outScreen)
    }
    // Восстанавливаем состояние экранов
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inScreen = savedInstanceState.getString("input", "")
        outScreen = savedInstanceState.getString("output", "")
        val editTextInput: TextView = findViewById(R.id.editTextInput)
        val textView: TextView = findViewById(R.id.resultView)
        textView.setText(outScreen)
        editTextInput.setText(inScreen)
    }
}

