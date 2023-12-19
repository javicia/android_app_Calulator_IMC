package com.example.calculator_imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class IMCActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 30
    private var currentHeight: Int = 120

    //Constantes
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSustractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSustractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculator: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)
        initComponents()
        initListeners()
        InitUI()
    }


    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSustractWeight = findViewById(R.id.btnSustractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSustractAge = findViewById(R.id.btnSustractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculator = findViewById(R.id.btnCalculator)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            setGenderColor()
            changeGender()
        }
        viewFemale.setOnClickListener {
            setGenderColor()
            changeGender()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
            btnPlusWeight.setOnClickListener {
                currentWeight += 1
                setWeight()
            }
            btnSustractWeight.setOnClickListener {
                currentWeight -= 1
                setWeight()
            }
            btnPlusAge.setOnClickListener {
                currentAge += 1
                setAge()
            }
            btnSustractAge.setOnClickListener {
                currentAge -= 1
                setAge()
            }
            btnCalculator.setOnClickListener {
                val result = calculateIMC()
                navigateToResult(result)
            }
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    //Fórmula de IMC. Peso por altura al cuadrado
    fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()

    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        getCardBackground(isMaleSelected)
        getCardBackground(isFemaleSelected)
        viewMale.setCardBackgroundColor(getCardBackground(isMaleSelected))
        viewMale.setCardBackgroundColor(getCardBackground(isFemaleSelected))
    }

    private fun getCardBackground(isSelectedComponent: Boolean): Int {


        var colorReferences = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReferences)
    }


    private fun InitUI() {
        setGenderColor()
        setWeight()
        setAge()
    }


}