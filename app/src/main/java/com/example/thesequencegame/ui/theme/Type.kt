package com.example.thesequencegame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.thesequencegame.R

val googleFontProvider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
)

val karlaFontFamily = FontFamily(
        Font(googleFont = GoogleFont("Karla"), fontProvider = googleFontProvider)
)

val SequenceTypography = Typography(
        titleLarge = TextStyle(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        bodySmall = TextStyle(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.sp
        )
)