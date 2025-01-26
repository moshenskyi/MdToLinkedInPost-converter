import org.commonmark.node.BulletList
import org.commonmark.node.Document
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.Heading
import org.commonmark.node.Link
import org.commonmark.node.Node
import org.commonmark.node.Paragraph
import org.commonmark.node.StrongEmphasis
import org.commonmark.node.Text
import org.commonmark.parser.Parser

class LinkedInMarkdownConverter {
    private val parser = Parser.builder().build()

    private val italicUnicodeConverter by lazy { ItalicUnicodeSurrogateConverter() }
    private val boldUnicodeConverter by lazy { BoldUnicodeSurrogateConverter() }

    fun convert(markdown: String): String {
        val document = parser.parse(markdown)
        val result = StringBuilder()
        processNode(document, result)
        return result.toString().trim()
    }

    private fun processNode(node: Node, result: StringBuilder) {
        when (node) {
            is Document -> processChildren(node, result)
            is Heading -> processHeading(node, result)
            is Paragraph -> processParagraph(node, result)
            is BulletList -> processBulletList(node, result)
            is Link -> processLink(node, result)
            is StrongEmphasis -> processStrongEmphasis(node, result)
            is FencedCodeBlock -> processCode(node, result)
            is Text -> processText(node, result)
        }
    }

    private fun processText(node: Text, result: StringBuilder) {
        when (node.parent) {
            is Heading -> {
                val data = boldUnicodeConverter.convert(node.literal)
                result.append(data)
                result.appendLine().appendLine()
            }

            is StrongEmphasis -> {
                val data = boldUnicodeConverter.convert(node.literal)
                result.append(data)
            }

            else -> result.append(node.literal)
        }
    }

    private fun processChildren(node: Node, result: StringBuilder) {
        var child = node.firstChild

        while (child != null) {
            processNode(child, result)
            child = child.next
        }
    }

    private fun processHeading(node: Heading, result: StringBuilder) {
        processChildren(node, result)
    }

    private fun processParagraph(node: Paragraph, result: StringBuilder) {
        processChildren(node, result)
        result.append("\n\n")
    }

    private fun processBulletList(node: BulletList, result: StringBuilder) {
        var child = node.firstChild
        while (child != null) {
            result.append("- ")
            processChildren(child, result)
            result.appendLine()
            child = child.next
        }
        result.appendLine()
    }

    private fun processLink(node: Link, result: StringBuilder) {
        processChildren(node, result)
        result.append(" (${node.destination})")
    }

    private fun processStrongEmphasis(node: StrongEmphasis, result: StringBuilder) {
        processChildren(node, result)
    }

    private fun processCode(node: FencedCodeBlock, result: StringBuilder) {
        val data = italicUnicodeConverter.convert(node.literal)
        result.appendLine(data)
    }
}
