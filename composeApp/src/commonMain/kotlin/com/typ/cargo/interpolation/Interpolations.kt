package com.typ.cargo.interpolation

import kotlin.math.log10
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun linearInterpolation(input: Float, minInput: Float, maxInput: Float, minOutput: Float, maxOutput: Float): Float {
    val clampedInput = max(min(input, maxInput), minInput)
    return minOutput + (maxOutput - minOutput) * ((clampedInput - minInput) / (maxInput - minInput))
}

fun logarithmicInterpolation(input: Float, minInput: Float, maxInput: Float, minOutput: Float, maxOutput: Float): Double {
    val clampedInput = max(min(input, maxInput), minInput)
    val normalizedInput = (clampedInput - minInput) / (maxInput - minInput)
    val logarithmicValue = log10(1 + 9 * normalizedInput) / log10(10.0)
    return minOutput + (maxOutput - minOutput) * logarithmicValue
}

fun dampedInterpolation(input: Float, dampingFactor: Float, maxOutput: Int = 400): Int {
    val clampedInput = max(min(input, 1.0f), 0.0f)
    val dampedValue = clampedInput.pow(dampingFactor)
    return (dampedValue * maxOutput).toInt()
}

fun hybridInterpolation(input: Float, previousValue: Int): Int {
    val linear = linearInterpolation(input, 0.0f, 1.0f, 0.0f, 400.0f).toInt()
    val damped = dampedInterpolation(input, 0.15f)
    return ((linear + damped + previousValue) / 3)
}