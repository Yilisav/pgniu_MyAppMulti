package vik.com.example.myappmulti.activities


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import net.objecthunter.exp4j.ExpressionBuilder
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.CalculatorBinding


class CCalculator : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")

    // перменные для отображения на экранах ввода и ввода
    private var outScreen = ""
    private var inScreen = ""
    // перменные для работы с кнопакми и  экранами ввода и ввода на макетах калькулятора
    private lateinit var binding: CalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        outScreen = getString(R.string.calculator)
        binding.resultView.text = outScreen

        // выход из ативности
        binding.closeCalculatorBut.setOnClickListener{
            setResult(RESULT_OK)
            finish()
            }
        // очистка
        binding.clearCalculatorBut.setOnClickListener {
            outScreen = getString(R.string.calculator)
            inScreen = ""
            binding.resultView.text = outScreen
            binding.editTextInput.text = inScreen
        }
        // ввод  с клвиатуры и ввыдод на экран калькулятора
        binding.buttonOne.setOnClickListener {
            inScreen = outToScreenCalculator("1", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonTwo.setOnClickListener {
            inScreen = outToScreenCalculator("2", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonThree.setOnClickListener {
            inScreen = outToScreenCalculator("3", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonFour.setOnClickListener {
            inScreen = outToScreenCalculator("4", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonFive.setOnClickListener {
            inScreen = outToScreenCalculator("5", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonSix.setOnClickListener {
            inScreen = outToScreenCalculator("6", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonSeven.setOnClickListener {
            inScreen = outToScreenCalculator("7", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonEight.setOnClickListener {
            inScreen = outToScreenCalculator("8", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonNine.setOnClickListener {
            inScreen = outToScreenCalculator("9", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonZero.setOnClickListener {
            inScreen = outToScreenCalculator("0", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonDot.setOnClickListener {
            inScreen = outToScreenCalculator(".", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonMultiplication.setOnClickListener {
            inScreen = outToScreenCalculator("*", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonDivision.setOnClickListener {
            inScreen = outToScreenCalculator("/", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonSumma.setOnClickListener {
            inScreen = outToScreenCalculator("+", inScreen)
             binding.editTextInput.text = inScreen}
        binding.buttonDifference.setOnClickListener {
            inScreen = outToScreenCalculator("-", inScreen)
             binding.editTextInput.text = inScreen}
        // удаление последнего введенного знака
        binding.backSpaceBut.setOnClickListener {
            inScreen = removeLastChar(inScreen).toString()
             binding.editTextInput.text = inScreen
        }
        // вывод  результат операции
        binding.buttonResult.setOnClickListener {
            outScreen =  binding.editTextInput.text.toString()
            if (outScreen == "")
            { Toast.makeText(applicationContext, getString(R.string.mes_empty), Toast.LENGTH_SHORT).show()}
            else {
                val expression = ExpressionBuilder(outScreen).build()
                val resultStr = expression.evaluate()
                val longResult = resultStr.toLong()
                if (resultStr == longResult.toDouble()) {
                    outScreen = longResult.toString()
                    binding.resultView.text = outScreen
                } else {
                    outScreen =resultStr.toString()
                    binding.resultView.text = outScreen
                }
            }
        }
        // обработчик кнопки назад
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed()
                {
                    setResult(RESULT_OK)
                    finish()
                }
            }
        )
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
        binding.resultView.text = outScreen
        binding.editTextInput.text = inScreen
    }
}

