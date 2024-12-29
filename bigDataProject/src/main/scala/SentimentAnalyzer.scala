import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import java.util.Properties

object SentimentAnalyzer {
  private val propsNLP = new Properties()
  propsNLP.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment")
  private val pipeline = new StanfordCoreNLP(propsNLP)

  def analyzeSentiment(text: String): String = {
    val annotation = new Annotation(text)
    pipeline.annotate(annotation)
    val sentences = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])

    if (sentences != null && sentences.size() > 0) {
      val sentence = sentences.get(0)
      val sentiment = sentence.get(classOf[SentimentCoreAnnotations.SentimentClass])
      sentiment
    } else {
      "Unknown"
    }
  }
}