package com.example.tipapp.util

fun calculateTotalTip(
    totalBill: Double,
    tipPrecentage: Int
): Double {
    return if (totalBill > 1 &&
        totalBill.toString().isNotEmpty()
    )
        (totalBill * tipPrecentage) / 100 else 0.0
}

fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPerecentage: Int
): Double {
    val  bill = calculateTotalTip(totalBill = totalBill,
        tipPrecentage = tipPerecentage) + totalBill
    return (bill / splitBy)
}