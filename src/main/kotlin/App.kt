import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.button

val App = FC<Props> {
    var count by useState(0)

    div {
        h1 { +"Hello from Kotlin/JS 🎉" }
        button {
            onClick = { count++ }
            +"Clicked $count times"
        }
    }
}
