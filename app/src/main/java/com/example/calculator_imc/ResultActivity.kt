package com.example.calculator_imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator_imc.IMCActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {

    lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculator: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initUi(result)
        initListeners()
    }

    private fun initComponent() {
        tvResult= findViewById(R.id.tvResult)
        tvIMC= findViewById(R.id.tvIMC)
        tvDescription= findViewById(R.id.tvDescription)
        btnReCalculator= findViewById((R.id.btnReCalculator))
    }

    fun initUi(result: Double) {
        tvIMC.text=result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.under_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.under_weight))
                tvDescription.text = getString(R.string.description_under_weight)
            }
            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvDescription.text = getString(R.string.description_normal_weight)
            }
            in 25.00..29.99 -> { //Exceso de peso
                tvResult.text= getString(R.string.over_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.over_weight))
                tvDescription.text = getString(R.string.description_over_weight)
            }
            in 30.00..99.00 -> { //Obesidad
                tvResult.text= getString(R.string.obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.description_obesity)
            }
            else->{  //Error
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvIMC.text = getString(R.string.error)
                tvIMC.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.error)
                tvDescription.setTextColor(ContextCompat.getColor(this, R.color.obesity))
            }
        }
    }
    private fun initListeners() {
        btnReCalculator.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}