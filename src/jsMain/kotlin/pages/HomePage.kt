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

// Data class for our new Projects section
data class Project(val id: String, val title: String, val shortDesc: String, val fullDesc: String, val page: String)

val HomePage = FC<HomePageProps> { props ->
    // State to track which project's "Info" modal is currently open
    val (selectedProject, setSelectedProject) = useState<Project?>(null)

    div {
        css {
            minHeight = 100.vh
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

            // Subtle glowing orb
            div {
                css {
                    position = Position.absolute
                    width = 800.px
                    height = 800.px
                    borderRadius = 50.pct
                    background = "radial-gradient(circle, #4a90d915 0%, transparent 60%)".unsafeCast<Background>()
                    top = 50.pct
                    left = 50.pct
                    transform = "translate(-50%, -50%)".unsafeCast<Transform>()
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

                // Animated Hero Button
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

                        onHover {
                            transform = "translateY(-3px)".unsafeCast<Transform>()
                            boxShadow = "0px 8px 24px #4a90d955".unsafeCast<BoxShadow>()
                        }
                        onActive {
                            transform = "translateY(1px)".unsafeCast<Transform>()
                            boxShadow = "0px 2px 8px #4a90d933".unsafeCast<BoxShadow>()
                        }
                    }
                    onClick = { props.onNavigate("buttons") }
                    +"Explore Components →"
                }
            }
        }

        // Animated Feature Cards section
        div {
            css {
                display = Display.grid
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

                        onHover {
                            transform = "translateY(-6px)".unsafeCast<Transform>()
                            boxShadow = "0px 12px 32px rgba(0,0,0,0.4)".unsafeCast<BoxShadow>()
                            border = "1px solid #4a90d966".unsafeCast<Border>()
                        }
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

        // NEW: Projects List Section
        div {
            css {
                maxWidth = 1100.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 24.px, 96.px, 24.px)
            }

            h2 {
                css { color = Theme.textPrimary; fontSize = 32.px; marginBottom = 48.px; textAlign = TextAlign.center }
                +"Featured Projects"
            }

            div {
                css {
                    display = Display.grid
                    gridTemplateColumns = "repeat(auto-fit, minmax(320px, 1fr))".unsafeCast<GridTemplateColumns>()
                    gap = 32.px
                }

                val projectList = listOf(
                    Project("proj1", "E-Commerce Dashboard", "A mock dashboard for tracking sales.", "This project features complex charting using external JS libraries, managing global application state, and dynamic routing based on user authentication.", "ecommerce"),
                    Project("proj2", "Weather App API", "Real-time weather data fetching.", "Showcasing Coroutines in Kotlin/JS, dealing with asynchronous network requests, JSON serialization, and error boundary handling in React.", "weather"),
                    Project("proj3", "WebAudio Synthesizer", "Generating sound in the browser.", "A deep dive into the Web Audio API, using sliders and toggles to control oscillator frequencies and envelope generators in real-time.", "synth")
                )

                projectList.forEach { project ->
                    div {
                        css {
                            background = "rgba(15, 22, 41, 0.6)".unsafeCast<Background>()
                            border = "1px solid #1a3a8f55".unsafeCast<Border>()
                            borderRadius = 16.px
                            padding = 32.px
                            display = Display.flex
                            flexDirection = FlexDirection.column
                        }

                        h3 {
                            css { color = Theme.textPrimary; fontSize = 22.px; margin = 0.px; marginBottom = 12.px }
                            +project.title
                        }
                        p {
                            css { color = Theme.textSecondary; fontSize = 15.px; lineHeight = 1.6.em; flex = number(1.0); marginBottom = 32.px; fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>() }
                            +project.shortDesc
                        }

                        // Project Buttons Container
                        div {
                            css { display = Display.flex; gap = 12.px; alignItems = AlignItems.center }

                            // 3D "Info" Button
                            button {
                                css {
                                    padding = Padding(10.px, 20.px)
                                    background = Theme.gradientAccent
                                    color = Theme.textPrimary
                                    border = "none".unsafeCast<Border>()
                                    borderRadius = 6.px
                                    fontSize = 13.px
                                    fontWeight = FontWeight.bold
                                    cursor = Cursor.pointer
                                    boxShadow = "0px 4px 0px #0a2463".unsafeCast<BoxShadow>()
                                    transition = Transition(TransitionProperty.all, 0.1.s, TransitionTimingFunction.ease)

                                    onHover { filter = "brightness(1.1)".unsafeCast<Filter>() }
                                    onActive {
                                        transform = "translateY(4px)".unsafeCast<Transform>()
                                        boxShadow = "0px 0px 0px #0a2463".unsafeCast<BoxShadow>()
                                    }
                                }
                                onClick = { setSelectedProject(project) }
                                +"Info"
                            }

                            // Outline "Read More" Button
                            button {
                                css {
                                    padding = Padding(10.px, 20.px)
                                    background = "transparent".unsafeCast<Background>()
                                    color = Theme.accentBright
                                    border = "1px solid #4a90d966".unsafeCast<Border>()
                                    borderRadius = 6.px
                                    fontSize = 13.px
                                    fontWeight = FontWeight.bold
                                    cursor = Cursor.pointer
                                    transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

                                    onHover { background = "#4a90d911".unsafeCast<Background>() }
                                    onActive { transform = "scale(0.95)".unsafeCast<Transform>() }
                                }
                                onClick = { props.onNavigate(project.page) }
                                +"Read More →"
                            }
                        }
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

        // --- POPUP MODAL ---
        if (selectedProject != null) {
            div {
                css {
                    position = Position.fixed
                    top = 0.px; left = 0.px; right = 0.px; bottom = 0.px
                    background = "rgba(0, 0, 0, 0.7)".unsafeCast<Background>()
                    backdropFilter = blur(8.px)
                    zIndex = integer(1000)
                    display = Display.flex
                    alignItems = AlignItems.center
                    justifyContent = JustifyContent.center
                    padding = Padding(
                        top = 24.px,
                        right = 14.px,
                        bottom = 14.px,
                        left = 14.px
                    )
                }
                // Click outside to close
                onClick = { setSelectedProject(null) }

                div {
                    css {
                        background = Theme.bgCard
                        border = "1px solid #4a90d966".unsafeCast<Border>()
                        borderRadius = 16.px
                        padding = 40.px
                        maxWidth = 500.px
                        width = 100.pct
                        boxShadow = "0px 24px 48px rgba(0,0,0,0.5)".unsafeCast<BoxShadow>()
                        position = Position.relative
                    }
                    // Prevent clicks inside the modal from closing it
                    onClick = { it.stopPropagation() }

                    h2 {
                        css { color = Theme.textPrimary; margin = 0.px; marginBottom = 16.px; fontSize = 28.px }
                        +selectedProject.title
                    }
                    p {
                        css { color = Theme.textSecondary; lineHeight = 1.7.em; fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>(); marginBottom = 32.px }
                        +selectedProject.fullDesc
                    }

                    button {
                        css {
                            width = 100.pct
                            padding = Padding(
                                14.px,
                                right = 14.px,
                                bottom = 14.px,
                                left = 14.px
                            )
                            background = Theme.gradientAccent
                            color = Theme.textPrimary
                            border = "none".unsafeCast<Border>()
                            borderRadius = 8.px
                            fontWeight = FontWeight.bold
                            cursor = Cursor.pointer
                            fontSize = 15.px
                            transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)

                            onHover { filter = "brightness(1.1)".unsafeCast<Filter>() }
                            onActive { transform = "scale(0.98)".unsafeCast<Transform>() }
                        }
                        onClick = { setSelectedProject(null) }
                        +"Close"
                    }
                }
            }
        }
    }
}

// --- CSS Helpers ---
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