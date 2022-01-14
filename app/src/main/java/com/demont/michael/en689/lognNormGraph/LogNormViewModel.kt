package com.demont.michael.en689.lognNormGraph

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class LogNormViewModel : ViewModel() {

    var vNumber: Int
    var VPK: MutableList<Double> = ArrayList()
    var mu: Double
    var STDV: Double

    init {
        vNumber = -1
        mu = -1.00
        STDV = -1.00
    }

    fun fillPk() {
        var iterator = 1
        while (iterator <= vNumber) {
            var value: Double = (iterator - 3.00 / 8.00) / (vNumber + 1.00 / 4.00) * 100
            VPK.add(value)
            iterator++
        }
    }

    fun calculateMU() {
        val n: Int = VPK.size
        var sum: Double = 0.00

        for (element in VPK) {
            sum += element
        }
        mu = sum / n
    }

    fun calculateSTDV() {
        var kwad: Double = 0.00
        val n: Int = VPK.size
        for (element in VPK) {
            kwad += (element - mu) * (element - mu)
        }
        STDV = sqrt(kwad / n)
    }

    //Tientallen keren geprobeerd met LiveData maar steeds problemen
    fun fillZVal():ArrayList<Double> {
        var temp = ArrayList<Double>()
        for (element in VPK) {
            temp.add((element - mu) / STDV + 5)
        }
        return temp
    }

}
