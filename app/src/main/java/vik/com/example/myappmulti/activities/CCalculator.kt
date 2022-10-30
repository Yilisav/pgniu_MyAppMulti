package vik.com.example.myappmulti.activities


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import vik.com.example.myappmulti.R


class CCalculator : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")

    // перменные для отображения на экранах ввода и ввода
    private var outScreen = ""
    private var inScreen = ""

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
            val anyStringChange = if (anyString.count()<= 22) {
                anyString + char
            } else {
                Toast.makeText(applicationContext, getString(R.string.mes_longExpression), Toast.LENGTH_SHORT).show()
                anyString
            }
            return anyStringChange
        }

        // обяъявление переменных
        // поле ввода
        val editTextInput: TextView = findViewById(R.id.editTextInput)
        // поле вывода
        val textView: TextView = findViewById(R.id.resultView)
        outScreen = getString(R.string.calculator)
        textView.text = outScreen
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
            setResult(RESULT_OK)
            finish()
            }
        // очистка
        buttonClear.setOnClickListener {
            outScreen = getString(R.string.calculator)
            inScreen = ""
            textView.text = outScreen
            editTextInput.text = inScreen
        }
        // ввод  с клвиатуры и ввыдод на экран калькулятора
        buttonOne.setOnClickListener {
            inScreen = outToScreenCalculator("1", inScreen)
            editTextInput.text = inScreen}
        buttonTwo.setOnClickListener {
            inScreen = outToScreenCalculator("2", inScreen)
            editTextInput.text = inScreen}
        buttonThree.setOnClickListener {
            inScreen = outToScreenCalculator("3", inScreen)
            editTextInput.text = inScreen}
        buttonFour.setOnClickListener {
            inScreen = outToScreenCalculator("4", inScreen)
            editTextInput.text = inScreen}
        buttonFive.setOnClickListener {
            inScreen = outToScreenCalculator("5", inScreen)
            editTextInput.text = inScreen}
        buttonSix.setOnClickListener {
            inScreen = outToScreenCalculator("6", inScreen)
            editTextInput.text = inScreen}
        buttonSeven.setOnClickListener {
            inScreen = outToScreenCalculator("7", inScreen)
            editTextInput.text = inScreen}
        buttonEight.setOnClickListener {
            inScreen = outToScreenCalculator("8", inScreen)
            editTextInput.text = inScreen}
        buttonNine.setOnClickListener {
            inScreen = outToScreenCalculator("9", inScreen)
            editTextInput.text = inScreen}
        buttonZero.setOnClickListener {
            inScreen = outToScreenCalculator("0", inScreen)
            editTextInput.text = inScreen}
        buttonDot.setOnClickListener {
            inScreen = outToScreenCalculator(".", inScreen)
            editTextInput.text = inScreen}
        buttonMultiplication.setOnClickListener {
            inScreen = outToScreenCalculator("*", inScreen)
            editTextInput.text = inScreen}
        buttonDivision.setOnClickListener {
            inScreen = outToScreenCalculator("/", inScreen)
            editTextInput.text = inScreen}
        buttonSumma.setOnClickListener {
            inScreen = outToScreenCalculator("+", inScreen)
            editTextInput.text = inScreen}
        buttonDifference.setOnClickListener {
            inScreen = outToScreenCalculator("-", inScreen)
            editTextInput.text = inScreen}
        // удаление последнего введенного знака
        buttonBackSpace.setOnClickListener {
            inScreen = removeLastChar(inScreen).toString()
            editTextInput.text = inScreen
        }
        // вывод  результат операции
        buttonResult.setOnClickListener {
            outScreen = editTextInput.text.toString()
            if (outScreen == "")
            { Toast.makeText(applicationContext, getString(R.string.mes_empty), Toast.LENGTH_SHORT).show()}
            else {
                val expression = ExpressionBuilder(outScreen).build()
                val resultStr = expression.evaluate()
                val longResult = resultStr.toLong()
                if (resultStr == longResult.toDouble()) {
                    outScreen = longResult.toString()
                    textView.text = outScreen
                } else {
                    outScreen =resultStr.toString()
                    textView.text = outScreen
                }
            }
        }


        }
    // Сохраняем состояние экранов
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("INPUT",inScreen)
        outState.putString("OUTPUT",outScreen)
    }
    // Восстанавливаем состояние экранов
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inScreen = savedInstanceState.getString("INPUT", "")
        outScreen = savedInstanceState.getString("OUTPUT", "")
        val editTextInput: TextView = findViewById(R.id.editTextInput)
        val textView: TextView = findViewById(R.id.resultView)
        textView.text = outScreen
        editTextInput.text = inScreen
    }
}

