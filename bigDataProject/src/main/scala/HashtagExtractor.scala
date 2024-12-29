import java.util.regex.Pattern

object HashtagExtractor {
  def extractHashtags(text: String): List[String] = {
    val hashtagPattern = Pattern.compile("#\\w+")
    val matcher = hashtagPattern.matcher(text)
    var hashtags = List[String]()
    while (matcher.find()) {
      hashtags = hashtags :+ matcher.group()
    }
    hashtags
  }
}
