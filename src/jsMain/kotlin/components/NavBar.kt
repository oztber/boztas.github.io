package components

import react.*
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.span
import emotion.react.css
import web.cssom.*
import styles.Theme

external interface NavBarProps : Props {
    var currentPage: String
    var onNavigate: (String) -> Unit
}

val NavBar = FC<NavBarProps> { props ->
    nav {
        css {
            position = Position.fixed
            top = 0.px
            left = 0.px
            right = 0.px
            zIndex = integer(100)
            background = "${Theme.navBg}ee".unsafeCast<Background>()
            backdropFilter = blur(12.px)
            borderBottom = "1px solid #1a3a8f44".unsafeCast<Border>()
            padding = Padding(0.px, 48.px)
            height = 64.px
            display = Display.flex
            alignItems = AlignItems.center
            justifyContent = JustifyContent.spaceBetween
        }

        // Logo
        div {
            css {
                display = Display.flex
                alignItems = AlignItems.center
                gap = 10.px
                cursor = Cursor.pointer
            }
            onClick = { props.onNavigate("home") }

            div {
                css {
                    width = 32.px
                    height = 32.px
                    borderRadius = 8.px
                    background = Theme.gradientAccent
                    display = Display.flex
                    alignItems = AlignItems.center
                    justifyContent = JustifyContent.center
                }
                span {
                    css {
                        color = Theme.textPrimary
                        fontSize = 14.px
                        fontWeight = FontWeight.bold
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                    }
                    +"KS"
                }
            }
            span {
                css {
                    color = Theme.textPrimary
                    fontSize = 16.px
                    fontWeight = FontWeight.bold
                    fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                    letterSpacing = 1.px
                }
                +"KotlinSite"
            }
        }

        // Nav links
        div {
            css {
                display = Display.flex
                gap = 8.px
                alignItems = AlignItems.center
            }

            listOf("home", "buttons", "sliders","tasks").forEach { page ->
                val isActive = props.currentPage == page
                a {
                    css {
                        padding = Padding(8.px, 18.px)
                        borderRadius = 6.px
                        cursor = Cursor.pointer
                        fontSize = 14.px
                        fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                        letterSpacing = 0.5.px
                        textDecoration = None.none
                        transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)
                        if (isActive) {
                            background = Theme.accentGlow.unsafeCast<Background>()
                            color = Theme.accentBright
                            border = "1px solid #4a90d955".unsafeCast<Border>()
                        } else {
                            color = Theme.textSecondary
                            border = "1px solid transparent".unsafeCast<Border>()
                        }
                    }
                    onClick = { props.onNavigate(page) }
                    +page.replaceFirstChar { it.uppercase() }
                }
            }
        }
    }
}