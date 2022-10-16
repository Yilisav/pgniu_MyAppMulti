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
            if (anyString.count()<= 40) {anyStringChange = anyString + char
            } else {
                Toast.makeText(applicationContext, "слишком длинное выражение", Toast.LENGTH_SHORT).show()
                anyStringChange = anyString
            }
            return anyStringChange
        }


//        обяъявление переменных
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

        // поле ввода
        val editTextInput: TextView = findViewById(R.id.editTextInput)

        // поле вывода
        val textView: TextView = findViewById(R.id.nameActivity)



        // выход из ативности
        buttonClose.setOnClickListener{
            finish()
            }
        // очистка
        buttonClear.setOnClickListener {
            textView.setText("КАЛЬКУЛЯТОР")
            editTextInput.setText("")
        }
        // ввод  с клвиатуры и ввыдод на экран калькулятора
        buttonOne.setOnClickListener {editTextInput.setText(outToScreenCalculator("1", editTextInput.text.toString()).toString())}
        buttonTwo.setOnClickListener {editTextInput.setText(outToScreenCalculator("2", editTextInput.text.toString()).toString()) }
        buttonThree.setOnClickListener {editTextInput.setText(outToScreenCalculator("3", editTextInput.text.toString()).toString()) }
        buttonFour.setOnClickListener {editTextInput.setText(outToScreenCalculator("4", editTextInput.text.toString()).toString()) }
        buttonFive.setOnClickListener {editTextInput.setText(outToScreenCalculator("5", editTextInput.text.toString()).toString()) }
        buttonSix.setOnClickListener {editTextInput.setText(outToScreenCalculator("6", editTextInput.text.toString()).toString()) }
        buttonSeven.setOnClickListener {editTextInput.setText(outToScreenCalculator("7", editTextInput.text.toString()).toString()) }
        buttonEight.setOnClickListener {editTextInput.setText(outToScreenCalculator("8", editTextInput.text.toString()).toString()) }
        buttonNine.setOnClickListener {editTextInput.setText(outToScreenCalculator("9", editTextInput.text.toString()).toString()) }
        buttonZero.setOnClickListener {editTextInput.setText(outToScreenCalculator("0", editTextInput.text.toString()).toString()) }
        buttonDot.setOnClickListener {editTextInput.setText(outToScreenCalculator(".", editTextInput.text.toString()).toString()) }
        buttonMultiplication.setOnClickListener {editTextInput.setText(outToScreenCalculator("*", editTextInput.text.toString()).toString()) }
        buttonDivision.setOnClickListener {editTextInput.setText(outToScreenCalculator("/", editTextInput.text.toString()).toString())}
        buttonSumma.setOnClickListener {editTextInput.setText(outToScreenCalculator("+", editTextInput.text.toString()).toString()) }
        buttonDifference.setOnClickListener {editTextInput.setText(outToScreenCalculator("-", editTextInput.text.toString()).toString())   }
        // удаление последнего введенного знака
        buttonBackSpace.setOnClickListener {
            val valuestr1 = removeLastChar(editTextInput.text.toString())
            println(valuestr1)
            editTextInput.setText(valuestr1)
        }
        buttonResult.setOnClickListener {
            val screen = editTextInput.text.toString()
            val expression = ExpressionBuilder(screen).build()
            val resultStr = expression.evaluate()
            val longResult = resultStr.toLong()
            if (resultStr == longResult.toDouble()) {
                textView.setText(longResult.toString())
            } else {
                textView.setText(resultStr.toString())
            }
        }


        }
    }

