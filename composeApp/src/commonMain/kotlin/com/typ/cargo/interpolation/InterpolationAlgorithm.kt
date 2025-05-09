package com.typ.cargo.interpolation

interface InterpolationAlgorithm<O> {
    fun interpolate(startValue: Float, endValue: Float): O
}