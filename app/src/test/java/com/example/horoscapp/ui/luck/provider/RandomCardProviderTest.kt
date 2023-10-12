package com.example.horoscapp.ui.luck.provider

import com.example.horoscapp.ui.luck.model.LuckyModel
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RandomCardProviderTest{

    @Test
    fun `RandomCard should return a random card and prediction not null`(){
        val randomCard = RandomCardProvider()

        val luckPrediction: LuckyModel? = randomCard.getLucky()

        assertNotNull(luckPrediction)
    }

}