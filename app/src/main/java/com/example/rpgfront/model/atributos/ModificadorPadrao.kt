package com.example.rpgfront.model.atributos

class ModificadorPadrao : ModificadorStrategy {
   override fun calcular(valor : Int) : Int
   {
       return when (valor) {
           3 -> -3
           4, 5 -> -2
           6, 7, 8 -> -1
           9, 10, 11, 12 -> 0
           13, 14, 15 -> +1
           16, 17 -> +2
           18 -> +3
           else -> 0
       }
   }

}