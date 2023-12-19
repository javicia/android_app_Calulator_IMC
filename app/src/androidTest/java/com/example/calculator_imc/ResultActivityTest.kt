package com.example.calculator_imc

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ResultActivityTest {

    private lateinit var scenario: ActivityScenario<ResultActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(ResultActivity::class.java)
    }

    @Test
    fun testInitUi_withUnderWeight() {
        scenario.onActivity { activity ->
            activity.initUi(16.5) // Simula un IMC correspondiente a bajo peso
            assertEquals(activity.getString(R.string.under_weight), activity.tvResult.text)
        }
            scenario.onActivity { activity ->
                activity.initUi(21.5) // Simula un IMC correspondiente peso normal
                assertEquals(activity.getString(R.string.normal_weight), activity.tvResult.text)
            }
                scenario.onActivity { activity ->
                    activity.initUi(28.5) // Simula un IMC correspondiente a exceso de peso
                    assertEquals(activity.getString(R.string.over_weight), activity.tvResult.text)
                }
                    scenario.onActivity { activity ->
                        activity.initUi(40.3) // Simula un IMC correspondiente a obesidad
                        assertEquals(activity.getString(R.string.obesity), activity.tvResult.text)
            }
        }

        // Agrega más pruebas según sea necesario para otras condiciones (peso normal, exceso de peso, obesidad, error)

        @After
        fun tearDown() {
            scenario.close()
        }
    }
