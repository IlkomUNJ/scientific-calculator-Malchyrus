package com.example.basiccode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import org.mariuszgromada.math.mxparser.Expression
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Alignment

@Composable
fun ScientificCalculatorScreen() {
    var secondaryText by remember { mutableStateOf("") }
    var primaryText by remember { mutableStateOf("0") }
    var isInverse by remember { mutableStateOf(false) }
    var sci by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.weight(5f))
            Text(
                text = secondaryText,
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Text(
                text = primaryText,
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Switchcalc(status = sci) {
                        Column {
                            if (isInverse) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    SciButton("sin⁻¹", { secondaryText += "asin(" })
                                    SciButton("cos⁻¹", { secondaryText += "acos(" })
                                    SciButton("tan⁻¹", { secondaryText += "atan(" })
                                    SciButton("x!", { secondaryText += "!" })
                                    }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    SciButton("ln", { secondaryText += "ln(" })
                                    SciButton("log", { secondaryText += "log10(" })
                                    SciButton("√", { secondaryText += "√(" })
                                    SciButton("inv", { isInverse = !isInverse })
                                }
                            } else {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    SciButton("sin", { secondaryText += "sin(" })
                                    SciButton("cos", { secondaryText += "cos(" })
                                    SciButton("tan", { secondaryText += "tan(" })
                                    SciButton("π", { secondaryText += "π" })
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    SciButton("ln", { secondaryText += "ln(" })
                                    SciButton("log", { secondaryText += "log10(" })
                                    SciButton("^", { secondaryText += "^" })
                                    SciButton("inv", { isInverse = !isInverse })
                                }
                            }
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SciButton(
                            "C",
                            { secondaryText = ""; primaryText = "0" },
                            backgroundColor = Color(0xFFE53935)
                        )
                        SciButton("()", { secondaryText = addBracket(secondaryText) })
                        SciButton("⌫", {
                            if (secondaryText.isNotEmpty()) {
                                secondaryText = secondaryText.dropLast(1)
                            }
                        },
                            backgroundColor = Color(0xFFFF9800))
                        SciButton(
                            "÷",
                            { secondaryText += "÷" },
                            backgroundColor = Color(0xFFFF9800)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SciButton("7", { secondaryText += "7" })
                        SciButton("8", { secondaryText += "8" })
                        SciButton("9", { secondaryText += "9" })
                        SciButton(
                            "×",
                            { secondaryText += "×" },
                            backgroundColor = Color(0xFFFF9800)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SciButton("4", { secondaryText += "4" })
                        SciButton("5", { secondaryText += "5" })
                        SciButton("6", { secondaryText += "6" })
                        SciButton(
                            "-",
                            { secondaryText += "-" },
                            backgroundColor = Color(0xFFFF9800)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SciButton("1", { secondaryText += "1" })
                        SciButton("2", { secondaryText += "2" })
                        SciButton("3", { secondaryText += "3" })
                        SciButton(
                            "+",
                            { secondaryText += "+" },
                            backgroundColor = Color(0xFFFF9800)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SciButton("Sc", { sci = !sci })
                        SciButton("0", { secondaryText += "0" })
                        SciButton(".", { secondaryText += "." })
                        SciButton("=", {
                            val expressionString = secondaryText
                                .replace("×", "*")
                                .replace("÷", "/")
                                .replace("√", "sqrt")
                                .replace("π", "pi")

                            val expression = Expression(expressionString)
                            val eval = expression.calculate()

                            primaryText = if (eval.isNaN()) "Error" else eval.toString()
                        }, backgroundColor = Color(0xFF4CAF50))
                    }
                }
            }
        }
    }
}


@Composable
fun SciButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF333333),
    textColor: Color = Color.White,
    size: Dp = 80.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(3.dp)
            .size(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

fun addBracket(currentText: String): String {
    val openBracket = currentText.count { it == '(' }
    val closeBracket = currentText.count { it == ')' }
    return if (openBracket > closeBracket) {
        "$currentText)"
    } else {
        "$currentText("
    }
}

@Composable
fun Switchcalc(
    status: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = status,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SciCalculatorUIPreview() {
    ScientificCalculatorScreen()
}
