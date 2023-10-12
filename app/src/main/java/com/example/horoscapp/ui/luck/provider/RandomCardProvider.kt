package com.example.horoscapp.ui.luck.provider

import com.example.horoscapp.R.drawable.*
import com.example.horoscapp.R.string.*
import com.example.horoscapp.ui.luck.model.LuckyModel
import javax.inject.Inject
import kotlin.random.Random

class RandomCardProvider @Inject constructor() {

    fun getLucky():LuckyModel?{
        return when(Random.nextInt(0,  26)){
            0 -> LuckyModel(card_0, luck_0)
            1 -> LuckyModel(card_1, luck_1)
            2 -> LuckyModel(card_2, luck_2)
            3 -> LuckyModel(card_3, luck_3)
            4 -> LuckyModel(card_4, luck_4)
            5 -> LuckyModel(card_5, luck_5)
            6 -> LuckyModel(card_6, luck_6)
            7 -> LuckyModel(card_7, luck_7)
            8 -> LuckyModel(card_8, luck_8)
            9 -> LuckyModel(card_9, luck_9)
            10 -> LuckyModel(card_10, luck_10)
            11 -> LuckyModel(card_11, luck_11)
            12 -> LuckyModel(card_12, luck_12)
            13 -> LuckyModel(card_13, luck_13)
            14 -> LuckyModel(card_14, luck_14)
            15 -> LuckyModel(card_15, luck_15)
            16 -> LuckyModel(card_16, luck_16)
            17 -> LuckyModel(card_17, luck_17)
            18 -> LuckyModel(card_18, luck_18)
            19 -> LuckyModel(card_19, luck_19)
            20 -> LuckyModel(card_20, luck_20)
            21 -> LuckyModel(card_21, luck_21)
            22 -> LuckyModel(card_22, luck_22)
            23 -> LuckyModel(card_23, luck_23)
            24 -> LuckyModel(card_24, luck_24)
            25 -> LuckyModel(card_25, luck_25)
            else -> null
        }
    }

}