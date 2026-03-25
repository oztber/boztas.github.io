package pages

import react.*
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import emotion.react.css
import react.dom.html.ReactHTML.br
import web.cssom.*
import styles.Theme

external interface HomePageProps : Props {
    var onNavigate: (String) -> Unit
}

val HomePage = FC<HomePageProps> { props ->
    div {
        css {
            minHeight = 100.vh
            // Swapped to a dark, high-contrast gradient
            background = "linear-gradient(135deg, #05070a 0%, #0a0e1a 100%)".unsafeCast<Background>()
            paddingTop = 64.px
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
        }

        // Hero section
        div {
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
                alignItems = AlignItems.center
                justifyContent = JustifyContent.center
                minHeight = 90.vh
                padding = Padding(0.px, 24.px)
                textAlign = TextAlign.center
                position = Position.relative
                overflow = Overflow.hidden
            }

            // Subtle, darker glowing orb background effect
            div {
                css {
                    position = Position.absolute
                    width = 800.px
                    height = 800.px
                    borderRadius = 50.pct
                    background = "radial-gradient(circle, #4a90d915 0%, transparent 60%)".unsafeCast<Background>()
                    top = 50.pct
                    left = 50.pct
                    transform = translate((-50).pct, (-50).pct)
                    pointerEvents = "none".unsafeCast<PointerEvents>()
                }
            }

            span {
                css {
                    fontSize = 12.px
                    letterSpacing = 4.px
                    color = Theme.accent
                    textTransform = TextTransform.uppercase
                    fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                    marginBottom = 24.px
                    display = Display.block
                }
                +"Made with Kotlin / JS"
            }

            h1 {
                css {
                    // clamp() makes the font fluid: 42px on mobile, scales up to 72px on desktop
                    fontSize = "clamp(42px, 8vw, 72px)".unsafeCast<FontSize>()
                    fontWeight = FontWeight.bold
                    color = Theme.textPrimary
                    margin = 0.px
                    lineHeight = 1.1.em
                    marginBottom = 24.px
                    background = "linear-gradient(135deg, #ffffff 30%, #8a9bc0 100%)".unsafeCast<Background>()
                    asDynamic().WebkitBackgroundClip = "text"
                    asDynamic().WebkitTextFillColor = "transparent"
                }
                +"Software Engineer"
                br {}
                +"& UI Developer"
            }

            p {
                css {
                    fontSize = "clamp(16px, 4vw, 18px)".unsafeCast<FontSize>()
                    color = Theme.textSecondary
                    maxWidth = 600.px
                    lineHeight = 1.7.em
                    marginBottom = 48.px
                    fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>()
                }
                +"A playground for experimenting with Kotlin/JS, showcasing interactive components, state management, and responsive design. This is a work in progress portfolio."
            }

            div {
                css {
                    display = Display.flex
                    gap = 16.px
                    flexWrap = FlexWrap.wrap
                    justifyContent = JustifyContent.center
                }

                button {
                    css {
                        padding = Padding(14.px, 32.px)
                        background = Theme.gradientAccent
                        color = Theme.textPrimary
                        border = "none".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontSize = 15.px
                        fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                        fontWeight = FontWeight.bold
                        cursor = Cursor.pointer
                        letterSpacing = 0.5.px
                        transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)
                        boxShadow = "0px 0px 24px #4a90d933".unsafeCast<BoxShadow>()
                    }
                    onClick = { props.onNavigate("buttons") }
                    +"Explore Buttons →"
                }
            }
        }

        // Feature cards section
        div {
            css {
                display = Display.grid
                // auto-fit automatically handles mobile layout! Stacks on small screens, grid on large screens.
                gridTemplateColumns = "repeat(auto-fit, minmax(280px, 1fr))".unsafeCast<GridTemplateColumns>()
                gap = 24.px
                maxWidth = 1100.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 24.px, 96.px, 24.px)
            }

            data class Card(val icon: String, val title: String, val desc: String, val page: String)
            listOf(
                Card("◈", "Interactive Buttons", "Hover effects, animations, and component states.", "buttons"),
                Card("⊟", "Live Sliders", "Range inputs with real-time visual feedback and DOM manipulation.", "sliders"),
                Card("☑", "Task Management", "Array state handling, form submission, and CRUD operations.", "tasks")
            ).forEach { card ->
                div {
                    css {
                        background = Theme.gradientCard
                        border = "1px solid #1a3a8f33".unsafeCast<Border>()
                        borderRadius = 12.px
                        padding = 32.px
                        cursor = Cursor.pointer
                        transition = Transition(TransitionProperty.all, 0.25.s, TransitionTimingFunction.ease)
                    }
                    onClick = { props.onNavigate(card.page) }

                    div {
                        css { fontSize = 28.px; marginBottom = 16.px; color = Theme.accentBright }
                        +card.icon
                    }
                    h3 {
                        css { color = Theme.textPrimary; fontSize = 18.px; margin = 0.px; marginBottom = 10.px; fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>() }
                        +card.title
                    }
                    p {
                        css { color = Theme.textSecondary; fontSize = 14.px; lineHeight = 1.6.em; margin = 0.px; fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>() }
                        +card.desc
                    }
                }
            }
        }

        // Tech Stack Section
        div {
            css {
                maxWidth = 1100.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 24.px, 96.px, 24.px)
                textAlign = TextAlign.center
            }

            h2 {
                css { color = Theme.textPrimary; fontSize = 28.px; marginBottom = 32.px }
                +"Built With"
            }

            div {
                css {
                    display = Display.flex
                    gap = 16.px
                    justifyContent = JustifyContent.center
                    flexWrap = FlexWrap.wrap
                }

                listOf("Kotlin", "React", "Emotion CSS", "JavaScript").forEach { tech ->
                    span {
                        css {
                            background = "#1a3a8f22".unsafeCast<Background>()
                            color = Theme.accentBright
                            border = "1px solid #4a90d944".unsafeCast<Border>()
                            padding = Padding(8.px, 16.px)
                            borderRadius = 20.px
                            fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                            fontSize = 14.px
                        }
                        +tech
                    }
                }
            }
        }
    }
}