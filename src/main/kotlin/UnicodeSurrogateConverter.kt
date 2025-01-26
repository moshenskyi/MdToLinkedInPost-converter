abstract class UnicodeSurrogateConverter {
    abstract val adjacencyList: Array<String>

    fun convert(text: String): String {
        return text.map { char ->
            when (char) {
                in 'A'..'Z' -> adjacencyList[char - 'A']
                in 'a'..'z' -> adjacencyList[char - 'a' + 26]
                else -> char
            }
        }.joinToString("")
    }
}

class BoldUnicodeSurrogateConverter : UnicodeSurrogateConverter() {
    override val adjacencyList: Array<String> = arrayOf(
        "𝗔", "𝗕", "𝗖", "𝗗", "𝗘", "𝗙", "𝗚", "𝗛", "𝗜", "𝗝", "𝗞", "𝗟",
        "𝗠", "𝗡", "𝗢", "𝗣", "𝗤", "𝗥", "𝗦", "𝗧", "𝗨", "𝗩", "𝗪", "𝗫",
        "𝗬", "𝗭", "𝗮", "𝗯", "𝗰", "𝗱", "𝗲", "𝗳", "𝗴", "𝗵", "𝗶", "𝗷",
        "𝗸", "𝗹", "𝗺", "𝗻", "𝗼", "𝗽", "𝗾", "𝗿", "𝘀", "𝘁", "𝘂", "𝘃",
        "𝘄", "𝘅", "𝘆", "𝘇"
    )
}

class ItalicUnicodeSurrogateConverter : UnicodeSurrogateConverter() {
    override val adjacencyList: Array<String> = arrayOf(
        "𝘈", "𝘉", "𝘊", "𝘋", "𝘌", "𝘍", "𝘎", "𝘏", "𝘐", "𝘑", "𝘒", "𝘓",
        "𝘔", "𝘕", "𝘖", "𝘗", "𝘘", "𝘙", "𝘚", "𝘛", "𝘜", "𝘝", "𝘞", "𝘟",
        "𝘠", "𝘡", "𝘢", "𝘣", "𝘤", "𝘥", "𝘦", "𝘧", "𝘨", "𝘩", "𝘪", "𝘫",
        "𝘬", "𝘭", "𝘮", "𝘯", "𝘰", "𝘱", "𝘲", "𝘳", "𝘴", "𝘵", "𝘶", "𝘷",
        "𝘸", "𝘹", "𝘺", "𝘻"
    )
}