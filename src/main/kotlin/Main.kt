import java.io.File

private const val resultFilePath = "result.txt"

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide the path to the Markdown file as an argument.")
        return
    }

    val filePath = args[0]
    val markdownText = try {
        File(filePath).readText()
    } catch (e: Exception) {
        println("Error reading file: ${e.message}")
        return
    }

    val converter = LinkedInMarkdownConverter()
    val linkedInPost = converter.convert(markdownText)

    File(resultFilePath).writeText(linkedInPost)
}