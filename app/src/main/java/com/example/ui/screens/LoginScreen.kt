package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.PinCheckMark
import com.example.ui.components.PrimaryButton
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun LoginScreen(
    onSignInSuccess: () -> Unit,
    onHowItWorksClick: () -> Unit
) {
    val lang = LocalAppLanguage.current

    var email by remember { mutableStateOf("reem.alqahtani@ub.edu.sa") }
    var password by remember { mutableStateOf("password123") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Brand Icon
        PinCheckMark(
            size = 48.dp,
            pinColor = Navy900,
            checkColor = Teal600
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = Strings.get("welcome_title", lang),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Navy900
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = Strings.get("welcome_subtitle", lang),
            fontSize = 15.sp,
            color = TextMuted
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(Strings.get("email_label", lang)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Teal600,
                unfocusedBorderColor = BorderStrong,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(Strings.get("password_label", lang)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = TextMuted
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Teal600,
                unfocusedBorderColor = BorderStrong,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {}) {
                Text(
                    text = Strings.get("forgot_password", lang),
                    color = Teal600,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = Strings.get("sign_in", lang),
            onClick = onSignInSuccess,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Privacy Reassurance Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier
                    .size(18.dp)
                    .padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = Strings.get("privacy_note_login", lang),
                fontSize = 13.sp,
                color = TextMuted,
                lineHeight = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = onHowItWorksClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 36.dp)
        ) {
            Text(
                text = Strings.get("how_it_works", lang),
                color = Teal600,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }
}
