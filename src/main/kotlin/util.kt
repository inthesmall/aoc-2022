import java.io.FileNotFoundException
import java.net.URL

fun getResource(name: String): URL {
    return object {}.javaClass.getResource(name) ?: throw FileNotFoundException(name)
}

fun getLines(name: String): List<String> {
    return getResource(name).readText().lines()
}