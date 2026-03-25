import react.create
import react.dom.client.createRoot
import web.dom.ElementId
import web.dom.document

fun main() {
    val container = document.getElementById("root".unsafeCast<ElementId>()) ?: error("No root element found")
    createRoot(container).render(App.create())
}