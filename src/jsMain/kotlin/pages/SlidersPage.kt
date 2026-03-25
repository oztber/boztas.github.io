package pages

import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import web.html.HTMLInputElement
import emotion.react.css
import web.cssom.*
import web.html.InputType
import styles.Theme
import web.html.range

val SlidersPage = FC<Props> {
    val (brightness, setBrightness) = useState(70)
    val (speed, setSpeed) = useState(40)
    val (blueAmount, setBlueAmount) = useState(128)
    val (greenAmount, setGreenAmount) = useState(60)

    div {
        css {
            minHeight = 100.vh
            background = Theme.gradientMain
            paddingTop = 96.px
            paddingBottom = 96.px
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
        }

        div {
            css {
                maxWidth = 800.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 48.px)
            }

            h1 {
                css { color = Theme.textPrimary; fontSize = 42.px; marginBottom = 8.px }
                +"Slider Showcase"
            }
            p {
                css {
                    color = Theme.textSecondary
                    fontSize = 16.px
                    marginBottom = 64.px
                    fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>()
                }
                +"Range inputs with live visual feedback."
            }

            // Basic sliders
            sliderCard("Brightness", brightness, 0, 100, "%") { setBrightness(it) }
            sliderCard("Animation Speed", speed, 0, 100, "ms") { setSpeed(it) }

            // Color mixer
            div {
                css {
                    background = Theme.gradientCard
                    border = "1px solid #1a3a8f55".unsafeCast<Border>()
                    borderRadius = 12.px
                    padding = 32.px
                    marginBottom = 32.px
                }

                span {
                    css {
                        color = Theme.textSecondary
                        fontSize = 12.px
                        letterSpacing = 3.px
                        textTransform = TextTransform.uppercase
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                        display = Display.block
                        marginBottom = 24.px
                    }
                    +"Color Mixer"
                }

                // Color preview
                div {
                    css {
                        width = 100.pct
                        height = 80.px
                        borderRadius = 8.px
                        marginBottom = 24.px
                        border = "1px solid #1a3a8f55".unsafeCast<Border>()
                        background = "rgb(20, $greenAmount, $blueAmount)".unsafeCast<Background>()
                        transition = Transition(TransitionProperty.all, 0.1.s, TransitionTimingFunction.ease)
                    }
                }

                inlineSlider("Blue", blueAmount, 0, 255, "#4a90d9") { setBlueAmount(it) }
                inlineSlider("Green", greenAmount, 0, 255, "#27ae60") { setGreenAmount(it) }

                div {
                    css {
                        marginTop = 16.px
                        fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                        color = Theme.textMuted
                        fontSize = 13.px
                    }
                    +"rgb(20, $greenAmount, $blueAmount)"
                }
            }
        }
    }
}

// Notice the parameter is renamed to onValueChanged
private fun ChildrenBuilder.sliderCard(
    title: String,
    value: Int,
    min: Int,
    max: Int,
    unit: String,
    onValueChanged: (Int) -> Unit
) {
    div {
        css {
            background = Theme.gradientCard
            border = "1px solid #1a3a8f55".unsafeCast<Border>()
            borderRadius = 12.px
            padding = 32.px
            marginBottom = 24.px
        }

        div {
            css { display = Display.flex; justifyContent = JustifyContent.spaceBetween; alignItems = AlignItems.center; marginBottom = 16.px }
            span {
                css { color = Theme.textPrimary; fontSize = 16.px; fontWeight = FontWeight.bold }
                +title
            }
            span {
                css {
                    color = Theme.accentBright
                    fontSize = 20.px
                    fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                    fontWeight = FontWeight.bold
                    minWidth = 70.px
                    textAlign = TextAlign.end
                }
                +"$value$unit"
            }
        }

        // Progress bar background
        div {
            css {
                position = Position.relative
                height = 6.px
                background = "#1a3a8f44".unsafeCast<Background>()
                borderRadius = 3.px
                marginBottom = 8.px
            }
            div {
                css {
                    position = Position.absolute
                    top = 0.px; left = 0.px; bottom = 0.px
                    width = "${(value - min).toDouble() / (max - min) * 100}%".unsafeCast<Width>()
                    background = Theme.gradientAccent
                    borderRadius = 3.px
                    transition = Transition(TransitionProperty.all, 0.1.s, TransitionTimingFunction.ease)
                }
            }
        }

        input {
            type = InputType.range
            this.min = min.toString()
            this.max = max.toString()
            this.value = value.toString()
            css {
                width = 100.pct
                cursor = Cursor.pointer
                accentColor = Theme.accent
                height = 4.px
                opacity = number(0.0)
                position = Position.relative
                marginTop = (-10).px
            }
            // Safely bypass strict types with asDynamic()
            onChange = { event ->
                val newValue = event.asDynamic().target.value.toString().toInt()
                onValueChanged(newValue)
            }
        }
    }
}

// Notice the parameter is renamed to onValueChanged
private fun ChildrenBuilder.inlineSlider(
    label: String,
    value: Int,
    min: Int,
    max: Int,
    color: String,
    onValueChanged: (Int) -> Unit
) {
    div {
        css { display = Display.flex; alignItems = AlignItems.center; gap = 16.px; marginBottom = 16.px }
        span {
            css {
                this.color = Theme.textSecondary
                fontSize = 13.px
                fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                minWidth = 50.px
            }
            +label
        }
        input {
            type = InputType.range
            this.min = min.toString()
            this.max = max.toString()
            this.value = value.toString()
            css { flex = number(1.0); cursor = Cursor.pointer; accentColor = color.unsafeCast<Color>() }

            // Safely bypass strict types with asDynamic()
            onChange = { event ->
                val newValue = event.asDynamic().target.value.toString().toInt()
                onValueChanged(newValue)
            }
        }
        span {
            css {
                this.color = color.unsafeCast<Color>()
                fontSize = 14.px
                fontFamily = "'DM Mono', monospace".unsafeCast<FontFamily>()
                minWidth = 36.px
                textAlign = TextAlign.end
            }
            +"$value"
        }
    }
}