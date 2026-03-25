package styles

import web.cssom.*

object Theme {
    val bgBase = "#0a0e1a".unsafeCast<Color>()
    val bgCard = "#0f1629".unsafeCast<Color>()
    val bgCardHover = "#151d35".unsafeCast<Color>()
    val navBg = "#080b15".unsafeCast<Color>()

    val navy1 = "#0d1b4b".unsafeCast<Color>()
    val navy2 = "#0a2463".unsafeCast<Color>()
    val navy3 = "#1a3a8f".unsafeCast<Color>()
    val accent = "#4a90d9".unsafeCast<Color>()
    val accentBright = "#6baed6".unsafeCast<Color>()
    val accentGlow = "#4a90d940".unsafeCast<Color>()

    val textPrimary = "#e8edf5".unsafeCast<Color>()
    val textSecondary = "#8a9bc0".unsafeCast<Color>()
    val textMuted = "#4a5578".unsafeCast<Color>()

    val gradientMain = "linear-gradient(135deg, #0a0e1a 0%, #0d1b4b 50%, #0a0e1a 100%)".unsafeCast<Background>()
    val gradientCard = "linear-gradient(145deg, #0f1629 0%, #0d1b4b 100%)".unsafeCast<Background>()
    val gradientAccent = "linear-gradient(90deg, #0a2463 0%, #4a90d9 100%)".unsafeCast<Background>()
    val gradientHero = "linear-gradient(135deg, #080b15 0%, #0d1b4b 40%, #0a2463 70%, #080b15 100%)".unsafeCast<Background>()
}