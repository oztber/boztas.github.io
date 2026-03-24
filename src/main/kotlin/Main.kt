import react.dom.client.createRoot
import web.dom.document

fun main() {
    val root = createRoot(document.getElementById("root")!!)
    root.render(App.create())
}