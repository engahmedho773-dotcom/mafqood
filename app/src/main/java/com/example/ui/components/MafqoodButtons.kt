package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Teal600
import com.example.ui.theme.Teal700

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 56.dp,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(if (height >= 56.dp) 16.dp else 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Teal600,
            contentColor = Color.White,
            disabledContainerColor = BorderStrong,
            disabledContentColor = Color(0xFF7C8BA3)
        ),
        modifier = modifier.height(height)
    ) {
        Text(
            text = text,
            fontSize = if (height >= 56.dp) 16.sp else 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(if (height >= 56.dp) 16.dp else 12.dp),
        border = BorderStroke(1.5.dp, BorderStrong),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Navy900,
            containerColor = Color.White
        ),
        modifier = modifier.height(height)
    ) {
        Text(
            text = text,
            fontSize = if (height >= 56.dp) 16.sp else 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun AccentDarkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(if (height >= 56.dp) 16.dp else 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Signal500,
            contentColor = Navy900
        ),
        modifier = modifier.height(height)
    ) {
        Text(
            text = text,
            fontSize = if (height >= 56.dp) 16.sp else 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun InverseDarkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 56.dp
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(if (height >= 56.dp) 16.dp else 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Navy900
        ),
        modifier = modifier.height(height)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
