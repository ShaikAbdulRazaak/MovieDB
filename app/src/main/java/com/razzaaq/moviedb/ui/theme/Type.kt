package com.razzaaq.moviedb.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.razzaaq.moviedb.R

// Set of Material typography styles to start with

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

val MontserratFont = GoogleFont(name = "Montserrat")

val josefinSansFont = GoogleFont(name = "Josefin Sans")

val ubuntu = GoogleFont(name = "Ubuntu")

val didactGothic = GoogleFont(name = "Didact Gothic")

val MontserratFontFamily = FontFamily(
    Font(googleFont = MontserratFont, fontProvider = provider),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.SemiBold),
)

val ubuntuFontFamily = FontFamily(
    Font(googleFont = ubuntu, fontProvider = provider),
    Font(googleFont = ubuntu, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = ubuntu, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = ubuntu, fontProvider = provider, weight = FontWeight.SemiBold)
)

val didactGothicFontFamily = FontFamily(Font(googleFont = didactGothic, fontProvider = provider),    Font(googleFont = ubuntu, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = didactGothic, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = didactGothic, fontProvider = provider, weight = FontWeight.SemiBold))

val JosefinSansFontFamily = FontFamily(
    Font(googleFont = josefinSansFont, fontProvider = provider),
    Font(googleFont = josefinSansFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = josefinSansFont, fontProvider = provider, weight = FontWeight.Light),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MontserratFontFamily, // Changed from Default
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)