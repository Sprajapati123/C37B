package com.example.c37b

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CalculationTest {

    @Test
    fun add_test(){
        val calculation = Calculation()
        val result = calculation.add(5,5)
        assertEquals(9,result)
    }

    @Test
    fun add_testUsingMockito(){
        val calculation = mock(Calculation::class.java)

        `when`(calculation.add(5,5)).thenReturn(10)

        val result = calculation.add(5,5)
        assertEquals(10,result)



    }
}