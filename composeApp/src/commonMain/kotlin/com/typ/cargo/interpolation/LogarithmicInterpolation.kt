package com.typ.cargo.interpolation

import kotlin.math.exp

class LogarithmicInterpolation(private val alpha: Float) : InterpolationAlgorithm<Float> {
    override fun interpolate(startValue: Float, endValue: Float): Float {
        return alpha * exp(startValue) + (1 - alpha) * exp(endValue)
    }
}