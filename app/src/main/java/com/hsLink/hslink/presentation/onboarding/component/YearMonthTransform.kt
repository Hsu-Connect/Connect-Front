package com.hsLink.hslink.presentation.onboarding.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 날짜 입력을 yyyy-MM 형식으로 자동 포맷팅하는 VisualTransformation
 * 예: "202401" -> "2024-01"
 */
class YearMonthVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(6)

        val formatted = buildString {
            trimmed.forEachIndexed { index, char ->
                append(char)
                if (index == 3 && trimmed.length > 4) {
                    append("-")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    else -> offset + 1
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    else -> offset - 1
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

/**
 * 입력값을 숫자만 허용하고 최대 6자리로 제한
 */
fun String.toYearMonthFormat(): String {
    val digits = this.filter { it.isDigit() }.take(6)
    return when {
        digits.length <= 4 -> digits
        else -> "${digits.substring(0, 4)}-${digits.substring(4)}"
    }
}

/**
 * yyyy-MM 또는 yyyy.MM 형식 검증
 */
fun String.isValidYearMonth(): Boolean {
    val regex = """^\d{4}[-./]\d{2}$""".toRegex()
    if (!regex.matches(this)) return false

    val parts = this.split("""[-./]""".toRegex())
    if (parts.size != 2) return false

    val year = parts[0].toIntOrNull() ?: return false
    val month = parts[1].toIntOrNull() ?: return false

    return year in 1900..2100 && month in 1..12
}