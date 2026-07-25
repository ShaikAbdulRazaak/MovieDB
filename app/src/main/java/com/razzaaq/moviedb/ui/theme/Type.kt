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
    headlineLarge = TextStyle(
        fontFamily = ubuntuFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = ubuntuFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = ubuntuFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = ubuntuFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = JosefinSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = didactGothicFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = didactGothicFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = didactGothicFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = didactGothicFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)