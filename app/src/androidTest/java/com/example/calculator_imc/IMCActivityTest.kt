package com.example.calculator_imc

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class IMCActivityTest {

    private lateinit var scenario: ActivityScenario<IMCActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(IMCActivity::class.java)
    }

    @Test
    fun testCalculateIMC() {
        scenario.onActivity { activity ->
            // Encuentra el RangeSlider y establece sus valores mínimo y máximo
            val rangeSlider = activity.findViewById<RangeSlider>(R.id.rsHeight)
            rangeSlider.values = listOf(rangeSlider.valueFrom, 170f) // Simula altura de 170 cm
            // Simular cambios en las vistas
            activity.findViewById<FloatingActionButton>(R.id.btnPlusWeight).performClick()
            activity.findViewById<FloatingActionButton>(R.id.btnPlusAge).performClick()

            // Invocar el método de cálculo del IMC
            val result = activity.calculateIMC()
            assertEquals(24.22, result, 0.01) // Ajusta el valor esperado según la fórmula IMC
        }
    }

    // Agrega más pruebas según sea necesario para otros métodos y funcionalidades de la actividad

    @After
    fun tearDown() {
        scenario.close()
    }
}