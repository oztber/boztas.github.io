package pages

import react.*
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import emotion.react.css
import web.cssom.*
import styles.Theme
import kotlinx.browser.window

val ButtonsPage = FC<Props> {
    val (clicked, setClicked) = useState("")
    val (toggleA, setToggleA) = useState(false)
    val (toggleB, setToggleB) = useState(false)
    val (count, setCount) = useState(0)
    val (isLoading, setIsLoading) = useState(false)

    // Simulate a loading network request
    val handleLoadClick = {
        if (!isLoading) {
            setIsLoading(true)
            setClicked("Loading Data...")
            window.setTimeout({
                setIsLoading(false)
                setClicked("Data Loaded Successfully!")
            }, 2000)
        }
    }

    div {
        css {
            minHeight = 100.vh
            background = "linear-gradient(135deg, #050812 0%, #0a1128 100%)".unsafeCast<Background>()
            paddingTop = 96.px
            paddingBottom = 96.px
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
        }

        div {
            css {
                maxWidth = 900.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 48.px)
            }

            h1 {
                css {
                    color = Theme.textPrimary
                    fontSize = 42.px
                    marginBottom = 8.px
                }
                +"Button Showcase"
            }
            p {
                css {
                    color = Theme.textSecondary
                    fontSize = 16.px
                    marginBottom = 64.px
                    fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>()
                }
                +"Different button styles, animations, states and interactions."
            }

            // Section: Primary styles
            sectionTitle("Primary Styles")
            div {
                css { display = Display.flex; gap = 16.px; flexWrap = FlexWrap.wrap; marginBottom = 48.px }

                styledButton("Solid", Theme.gradientAccent.unsafeCast<String>(), Theme.textPrimary, "none") { setClicked("Solid") }
                styledButton("Outline", "transparent", Theme.accentBright, "1px solid #4a90d966") { setClicked("Outline") }
                styledButton("Ghost", "transparent", Theme.textSecondary, "none") { setClicked("Ghost") }
                styledButton("Danger", "linear-gradient(90deg, #6b0a0a, #c0392b)", Theme.textPrimary, "none") { setClicked("Danger") }
                styledButton("Success", "linear-gradient(90deg, #0a4b1e, #27ae60)", Theme.textPrimary, "none") { setClicked("Success") }
            }

            // Section: Advanced Interactions
            sectionTitle("Advanced Interactions")
            div {
                css { display = Display.flex; gap = 16.px; flexWrap = FlexWrap.wrap; marginBottom = 48.px; alignItems = AlignItems.center }

                // 3D Push Button
                button {
                    css {
                        padding = Padding(12.px, 28.px)
                        background = Theme.gradientAccent
                        color = Theme.textPrimary
                        border = "none".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontSize = 14.px
                        fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                        fontWeight = FontWeight.bold
                        cursor = Cursor.pointer
                        boxShadow = "0px 6px 0px #0a2463".unsafeCast<BoxShadow>()
                        transition = Transition(TransitionProperty.all, 0.1.s, TransitionTimingFunction.ease)

                        onHover { filter = "brightness(1.1)".unsafeCast<Filter>() }
                        onActive {
                            transform = "translateY(6px)".unsafeCast<Transform>()
                            boxShadow = "0px 0px 0px #0a2463".unsafeCast<BoxShadow>()
                        }
                    }
                    onClick = { setClicked("3D Push Button") }
                    +"3D Press Me"
                }

                // Glassmorphism Button
                button {
                    css {
                        padding = Padding(12.px, 28.px)
                        background = "rgba(255, 255, 255, 0.05)".unsafeCast<Background>()
                        color = Theme.textPrimary
                        border = "1px solid rgba(255, 255, 255, 0.1)".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontSize = 14.px
                        fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                        backdropFilter = blur(10.px)
                        cursor = Cursor.pointer
                        transition = Transition(TransitionProperty.all, 0.3.s, TransitionTimingFunction.ease)

                        onHover {
                            background = "rgba(255, 255, 255, 0.1)".unsafeCast<Background>()
                            transform = "translateY(-3px)".unsafeCast<Transform>()
                            boxShadow = "0px 8px 32px rgba(0, 0, 0, 0.3)".unsafeCast<BoxShadow>()
                        }
                        onActive { transform = "translateY(0px)".unsafeCast<Transform>() }
                    }
                    onClick = { setClicked("Glass Effect") }
                    +"Glassmorphism"
                }

                // Loading State Button
                button {
                    css {
                        padding = Padding(12.px, 28.px)
                        background = if (isLoading) "transparent".unsafeCast<Background>() else Theme.gradientAccent
                        color = if (isLoading) Theme.textSecondary else Theme.textPrimary
                        border = if (isLoading) "1px solid #4a5578".unsafeCast<Border>() else "none".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontSize = 14.px
                        fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
                        fontWeight = FontWeight.bold
                        cursor = if (isLoading) Cursor.notAllowed else Cursor.pointer
                        transition = Transition(TransitionProperty.all, 0.3.s, TransitionTimingFunction.ease)
                        minWidth = 140.px

                        if (!isLoading) {
                            onHover { transform = "translateY(-2px)".unsafeCast<Transform>(); boxShadow = "0px 8px 16px #4a90d944".unsafeCast<BoxShadow>() }
                            onActive { transform = "translateY(1px)".unsafeCast<Transform>(); boxShadow = "0px 2px 4px #4a90d944".unsafeCast<BoxShadow>() }
                        }
                    }
                    onClick = { handleLoadClick() }
                    if (isLoading) +"⏳ Loading..." else +"Fetch Data"
                }
            }

            // Section: Counter
            sectionTitle("Stateful Counter")
            div {
                css {
                    display = Display.flex
                    alignItems = AlignItems.center
                    gap = 24.px
                    marginBottom = 48.px
                    background = Theme.gradientCard
                    border = "1px solid #1a3a8f55".unsafeCast<Border>()
                    borderRadius = 12.px
                    padding = 32.px
                }

                button {
                    css { counterBtn() }
                    onClick = { if (count > 0) setCount(count - 1) }
                    +"−"
                }
                span {
                    css {
                        fontSize = 48.px
                        fontWeight = FontWeight.bold
                        color = Theme.accentBright
                        minWidth = 80.px
                        textAlign = TextAlign.center
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                    }
                    +"$count"
                }
                button {
                    css { counterBtn() }
                    onClick = { setCount(count + 1) }
                    +"+"
                }
                button {
                    css {
                        padding = Padding(10.px, 20.px)
                        background = "transparent".unsafeCast<Background>()
                        color = Theme.textMuted
                        border = "1px solid #4a557833".unsafeCast<Border>()
                        borderRadius = 6.px
                        cursor = Cursor.pointer
                        fontSize = 13.px
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                        transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

                        onHover { background = "#4a557822".unsafeCast<Background>(); color = Theme.textSecondary }
                        onActive { transform = "scale(0.95)".unsafeCast<Transform>() }
                    }
                    onClick = { setCount(0) }
                    +"reset"
                }
            }

            // Section: Toggles
            sectionTitle("Toggle Buttons")
            div {
                css { display = Display.flex; gap = 16.px; marginBottom = 48.px; flexWrap = FlexWrap.wrap }

                toggleButton("Dark Mode", toggleA) { setToggleA(!toggleA) }
                toggleButton("Notifications", toggleB) { setToggleB(!toggleB) }
            }

            // Feedback
            if (clicked.isNotEmpty()) {
                div {
                    css {
                        marginTop = 16.px
                        padding = Padding(12.px, 20.px)
                        background = Theme.accentGlow.unsafeCast<Background>()
                        border = "1px solid #4a90d944".unsafeCast<Border>()
                        borderRadius = 8.px
                        color = Theme.accentBright
                        fontSize = 14.px
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                    }
                    +"→ Last action: $clicked"
                }
            }
        }
    }
}

// --- CSS Helpers & Components ---

private fun CSSProperties.counterBtn() {
    width = 48.px
    height = 48.px
    borderRadius = 50.pct
    background = "#1a3a8f44".unsafeCast<Background>()
    color = Theme.accentBright
    border = "1px solid #4a90d955".unsafeCast<Border>()
    cursor = Cursor.pointer
    fontSize = 24.px
    display = Display.flex
    alignItems = AlignItems.center
    justifyContent = JustifyContent.center
    fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
    transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

    onHover {
        background = "#1a3a8f66".unsafeCast<Background>()
        transform = "scale(1.1)".unsafeCast<Transform>()
    }
    onActive {
        transform = "scale(0.9)".unsafeCast<Transform>()
    }
}

private fun ChildrenBuilder.sectionTitle(title: String) {
    react.dom.html.ReactHTML.h2 {
        css {
            color = Theme.textSecondary
            fontSize = 12.px
            letterSpacing = 3.px
            textTransform = TextTransform.uppercase
            fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
            marginBottom = 20.px
        }
        +title
    }
}

private fun ChildrenBuilder.styledButton(
    label: String,
    bg: String,
    color: Color,
    border: String,
    onClick: () -> Unit
) {
    button {
        css {
            padding = Padding(12.px, 28.px)
            background = bg.unsafeCast<Background>()
            this.color = color
            this.border = if (border == "none") "none".unsafeCast<Border>() else border.unsafeCast<Border>()
            borderRadius = 8.px
            fontSize = 14.px
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
            fontWeight = FontWeight.bold
            cursor = Cursor.pointer
            letterSpacing = 0.5.px
            transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

            onHover {
                transform = "translateY(-3px)".unsafeCast<Transform>()
                boxShadow = "0px 8px 16px rgba(0, 0, 0, 0.2)".unsafeCast<BoxShadow>()
                filter = "brightness(1.1)".unsafeCast<Filter>()
            }
            onActive {
                transform = "translateY(1px)".unsafeCast<Transform>()
                boxShadow = "0px 2px 4px rgba(0, 0, 0, 0.2)".unsafeCast<BoxShadow>()
                filter = "brightness(0.9)".unsafeCast<Filter>()
            }
        }
        this.onClick = { onClick() }
        +label
    }
}

private fun ChildrenBuilder.toggleButton(label: String, active: Boolean, onClick: () -> Unit) {
    button {
        css {
            padding = Padding(12.px, 28.px)
            background = if (active) Theme.accentGlow.unsafeCast<Background>() else "transparent".unsafeCast<Background>()
            color = if (active) Theme.accentBright else Theme.textSecondary
            border = if (active) "1px solid #4a90d966".unsafeCast<Border>() else "1px solid #4a557844".unsafeCast<Border>()
            borderRadius = 8.px
            fontSize = 14.px
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
            cursor = Cursor.pointer
            transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

            onHover {
                background = if (active) Theme.accentGlow.unsafeCast<Background>() else "#4a557822".unsafeCast<Background>()
                transform = "scale(1.02)".unsafeCast<Transform>()
            }
            onActive {
                transform = "scale(0.95)".unsafeCast<Transform>()
            }
        }
        this.onClick = { onClick() }
        +(if (active) "✓ $label" else label)
    }
}

private fun CSSProperties.onHover(block: CSSProperties.() -> Unit) {
    val nested = js("{}")
    block(nested.unsafeCast<CSSProperties>())
    asDynamic()["&:hover"] = nested
}

private fun CSSProperties.onActive(block: CSSProperties.() -> Unit) {
    val nested = js("{}")
    block(nested.unsafeCast<CSSProperties>())
    asDynamic()["&:active"] = nested
}