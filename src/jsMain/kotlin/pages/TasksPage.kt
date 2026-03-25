package pages

import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.button
import emotion.react.css
import react.dom.html.ReactHTML.span
import web.cssom.*
import styles.Theme
import web.html.InputType
import web.html.text

data class Task(val id: Int, val text: String, val isCompleted: Boolean)

val TasksPage = FC<Props> {
    val (tasks, setTasks) = useState<List<Task>>(listOf(
        Task(1, "Configure Kotlin/JS wrappers", true),
        Task(2, "Build interactive portfolio components", false),
        Task(3, "Deploy to GitHub Pages", false)
    ))
    val (inputValue, setInputValue) = useState("")

    val handleAddTask = {
        if (inputValue.isNotBlank()) {
            val newTask = Task(id = tasks.size + 1, text = inputValue, isCompleted = false)
            setTasks(tasks + newTask)
            setInputValue("")
        }
    }

    val toggleTask = { id: Int ->
        setTasks(tasks.map { if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it })
    }

    val deleteTask = { id: Int ->
        setTasks(tasks.filter { it.id != id })
    }

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
                maxWidth = 600.px
                margin = Margin(0.px, Auto.auto)
                padding = Padding(0.px, 24.px)
            }

            h1 {
                css { color = Theme.textPrimary; fontSize = "clamp(32px, 5vw, 42px)".unsafeCast<FontSize>(); marginBottom = 8.px }
                +"Task Manager"
            }
            p {
                css { color = Theme.textSecondary; fontSize = 16.px; marginBottom = 48.px; fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>() }
                +"Showcasing array state management and dynamic list rendering."
            }

            // Input section
            div {
                css {
                    display = Display.flex
                    gap = 12.px
                    marginBottom = 32.px
                }

                input {
                    type = InputType.text
                    value = inputValue
                    placeholder = "Add a new task..."
                    css {
                        flex = number(1.0)
                        padding = Padding(12.px, 16.px)
                        background = Theme.bgCard
                        color = Theme.textPrimary
                        border = "1px solid #1a3a8f55".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>()
                        fontSize = 16.px
                    }
                    onChange = { event -> setInputValue(event.asDynamic().target.value as String) }
                    onKeyDown = { event -> if (event.key == "Enter") handleAddTask() }
                }

                button {
                    css {
                        padding = Padding(12.px, 24.px)
                        background = Theme.gradientAccent
                        color = Theme.textPrimary
                        border = "none".unsafeCast<Border>()
                        borderRadius = 8.px
                        fontWeight = FontWeight.bold
                        cursor = Cursor.pointer
                    }
                    onClick = { handleAddTask() }
                    +"Add"
                }
            }

            // Task List
            div {
                css { display = Display.flex; flexDirection = FlexDirection.column; gap = 12.px }

                tasks.forEach { task ->
                    div {
                        key = task.id.toString().unsafeCast<Key>()
                        css {
                            display = Display.flex
                            alignItems = AlignItems.center
                            justifyContent = JustifyContent.spaceBetween
                            background = Theme.gradientCard
                            padding = Padding(
                                16.px,
                                right = 16.px,
                                bottom = 16.px,
                                left = 16.px
                            )
                            borderRadius = 8.px
                            border = "1px solid #1a3a8f33".unsafeCast<Border>()
                            transition = Transition(TransitionProperty.all, 0.2.s, TransitionTimingFunction.ease)
                            opacity = if (task.isCompleted) number(0.6) else number(1.0)
                        }

                        div {
                            css { display = Display.flex; alignItems = AlignItems.center; gap = 16.px; cursor = Cursor.pointer }
                            onClick = { toggleTask(task.id) }

                            // Custom Checkbox
                            div {
                                css {
                                    width = 20.px; height = 20.px
                                    borderRadius = 4.px
                                    border = (if (task.isCompleted) "1px solid #4a90d9" else "1px solid #4a5578").unsafeCast<Border>()
                                    background = if (task.isCompleted) Theme.accent.unsafeCast<Background>() else "transparent".unsafeCast<Background>()
                                }
                            }

                            span {
                                css {
                                    color = Theme.textPrimary
                                    fontFamily = "'DM Sans', sans-serif".unsafeCast<FontFamily>()
                                    textDecoration = if (task.isCompleted) "line-through".unsafeCast<TextDecoration>() else "none".unsafeCast<TextDecoration>()
                                }
                                +task.text
                            }
                        }

                        button {
                            css {
                                background = "transparent".unsafeCast<Background>()
                                border = "none".unsafeCast<Border>()
                                color = Theme.textMuted
                                cursor = Cursor.pointer
                                fontSize = 18.px
                            }
                            onClick = { deleteTask(task.id) }
                            +"×"
                        }
                    }
                }

                if (tasks.isEmpty()) {
                    p {
                        css { textAlign = TextAlign.center; color = Theme.textMuted; marginTop = 24.px }
                        +"All tasks completed! Grab a coffee."
                    }
                }
            }
        }
    }
}