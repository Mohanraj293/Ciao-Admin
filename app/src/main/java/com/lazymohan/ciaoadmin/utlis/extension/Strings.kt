package com.lazymohan.ciaoadmin.utlis.extension

import android.content.Context
import com.lazymohan.ciaoadmin.R
import java.math.BigDecimal
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun String?.orNa(mContext: Context): String {
  return if (this.isNullOrEmpty()) {
    mContext.getString(R.string.not_available)
  } else {
    this
  }
}

fun String.isValidLong(): Boolean {
  return toLongOrNull() != null
}

fun String.isValidDouble(): Boolean {
  return toDoubleOrNull() != null
}

@OptIn(ExperimentalContracts::class)
fun CharSequence?.isNotNullAndNotEmpty(): Boolean {
  contract {
    returns(true) implies (this@isNotNullAndNotEmpty != null)
  }
  return !this.isNullOrEmpty()
}

fun String.isValidInt(): Boolean {
  return toIntOrNull() != null
}

// This extension is only for Calibration calculations.
fun String?.toValidDoubleOrZero(): Double {
  return if (this.isNotNullAndNotEmpty() && this.isNotBlank() && this != "null") this.toDouble() else 0.0
}

// It will be used when subtracting the values in MathUtils.
fun String?.toValidNumberOrZero(): String {
  return if (this.isNotNullAndNotEmpty() && this.isNotBlank() && this != "null") this else "0"
}

fun String?.toValidStringOrNull(): String? {
  return if (this.isNotNullAndNotEmpty() && this.isNotBlank() && this != "null") this else null
}

fun CharSequence?.toValidStringOrNull(): String? {
  return when {
    this == null -> null
    this.toString() == "null" -> null
    else -> this.toString()
  }
}

fun String?.hasValidDecimalValue(): Boolean {
  return (this.isNotNullAndNotEmpty() && this.isNotBlank() && this != "null")
}

fun String?.orZero(): String {
  return if (this.isNullOrEmpty()) {
    "0"
  } else {
    this
  }
}

fun String?.getInitials(): String {
  if (isNotNullAndNotEmpty()) {
    val words = this.split("\\s".toRegex())
    val initials = words.map { it.first().uppercase() }
    return initials.joinToString("")
  }
  return ""
}

fun String.toCamelCase(): String {
  val firstLetter = this.first().uppercaseChar()
  return firstLetter.plus(this.substring(1).lowercase())
}

fun String.containsDigits(): Boolean {
  val regex = Regex("[0-9]")
  return regex.containsMatchIn(this)
}

fun String.extractDigits(): Int {
  val regex = Regex("[^0-9]")
  return this.replace(regex, "").toInt()
}

fun String?.toValidDecimalOrNull(): Double? {
  return try {
    when {
      this.isNullOrEmpty() -> null
      this.isBlank() -> null
      this == "null" -> null
      this == "-" -> null
      this == "." -> null
      this == ".-" || this == "-." -> null
      this == "-0" -> "0".toDouble()
      this.endsWith(".") -> this.plus("0").toDouble()
      else -> {
        val tempValue = if (endsWith(".")) {
          this.plus("0")
        } else {
          this
        }
        // Filter trailing 0's. Ex: 12.40000 or 0.10000 will be filtered as 12.4 and 0.1
        val value = BigDecimal(tempValue).stripTrailingZeros().toPlainString()
        value.toDouble()
      }
    }
  } catch (exception: Exception) {
    // In case of Number Format Exception or any.
    exception.printStackTrace()
    return null
  }
}