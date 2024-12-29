import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.json4s.native.JsonMethods._
import org.json4s._
import java.util.Properties
import java.time.Duration
import scala.collection.JavaConverters._

object consumer {
  def main(args: Array[String]): Unit = {
    // Kafka Configuration
    val broker = "localhost:9092"
    val topic = "tweets_.topic"

    // Kafka Consumer Properties
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "tweet-consumer-group")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    // Initialize Kafka Consumer
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(List(topic).asJava)

    // Define implicit formats for json4s
    implicit val formats: DefaultFormats.type = DefaultFormats

    try {
      while (true) {
        val records = consumer.poll(Duration.ofMillis(1000))
        for (record <- records.asScala) {
          val json = parse(record.value())
          val tweetText = (json \ "text").extract[String]

          val hashtags = HashtagExtractor.extractHashtags(tweetText)
          val sentiment = SentimentAnalyzer.analyzeSentiment(tweetText)

          // Create updated JSON with sentiment and hashtags
          val updatedJson = JObject(
            json.asInstanceOf[JObject].obj ++ List(
              "sentiment" -> JString(sentiment),
              "hashtags" -> JArray(hashtags.map(JString))
            )
          )
          val jsonString = compact(render(updatedJson))
          println(s"Processed Tweet: $jsonString")
        }
      }
    } catch {
      case e: Exception => println(s"Error: ${e.getMessage}")
    } finally {
      consumer.close()
    }
  }
}
