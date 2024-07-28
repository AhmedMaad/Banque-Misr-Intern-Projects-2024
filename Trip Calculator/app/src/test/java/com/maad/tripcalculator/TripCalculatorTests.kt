package com.maad.tripcalculator

import org.junit.Assert
import org.junit.Test

class TripCalculatorTests {

    @Test
    fun calculateTripPrice_luckyTraffic() {
        val km = 10f
        val time = 20
        val expectedPrice = 103 //baseFare (8) + (km * 7.5) + time
        val actualPrice = calculatePrice(km, time, 0.0f)
        Assert.assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun calculateTripPrice_safeZoneTraffic() {
        val km = 10f
        val time = 20
        val expectedPrice = 108.15.toInt() //103 + (103 * 5 / 100)
        val actualPrice = calculatePrice(km, time, 0.05f)
        Assert.assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun calculateTripPrice_heavyTraffic() {
        val km = 10f
        val time = 20
        val expectedPrice = 113.3.toInt() //103 + (103 * 10 / 100)
        val actualPrice = calculatePrice(km, time, 0.1f)
        Assert.assertEquals(expectedPrice, actualPrice)
    }

}