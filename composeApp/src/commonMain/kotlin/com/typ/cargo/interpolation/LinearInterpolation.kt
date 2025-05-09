package com.typ.cargo.interpolation

class LinearInterpolation(val fraction: Float) : InterpolationAlgorithm<Float> {

    override fun interpolate(startValue: Float, endValue: Float): Float {
        return startValue + fraction * (endValue - startValue)
    }

}