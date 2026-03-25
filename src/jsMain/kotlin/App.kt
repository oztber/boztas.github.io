import components.NavBar
import pages.ButtonsPage
import pages.HomePage
import pages.SlidersPage
import react.*
import react.dom.html.ReactHTML.div
import emotion.react.css
import pages.TasksPage
import web.cssom.*

val App = FC<Props> {
    val (page, setPage) = useState("home")

    div {
        css {
            fontFamily = "'Syne', sans-serif".unsafeCast<FontFamily>()
            background = "#0a0e1a".unsafeCast<Background>()
            minHeight = 100.vh
        }

        NavBar {
            currentPage = page
            onNavigate = { setPage(it) }
        }

        when (page) {
            "home" -> HomePage { onNavigate = { setPage(it) } }
            "buttons" -> ButtonsPage {}
            "sliders" -> SlidersPage {}
            "tasks" -> TasksPage {}
        }
    }
}