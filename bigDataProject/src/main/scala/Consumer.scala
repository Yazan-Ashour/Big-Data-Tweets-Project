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
    val topic = "tweets_topic"

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

          // Extract fields based on our schema
          val createdAt = (json \ "created_at").extractOpt[String]
          val id = (json \ "id").extractOpt[String]
          val text = (json \ "text").extractOpt[String]
          val location: Option[(Double, Double)] = for {
            coordinates <- (json \ "coordinates" \ "coordinates").extractOpt[List[Double]]
            if coordinates.size == 2
          } yield (coordinates(0), coordinates(1))

          val hashtags = HashtagExtractor.extractHashtags(tweetText)
          val sentiment = SentimentAnalyzer.analyzeSentiment(tweetText)

          // Create updated JSON with sentiment and hashtags
          val schemaJson = JObject(
            "created_at" -> createdAt.map(JString).getOrElse(JNull),
            "hashtags" -> JArray(hashtags.map(JString)),
            "id" -> id.map(JString).getOrElse(JNull),
            "location" -> location.map {
              case (lon, lat) => JObject("lon" -> JDouble(lon), "lat" -> JDouble(lat))
            }.getOrElse(JNull),
            "sentiment" -> JString(sentiment),
            "text" -> text.map(JString).getOrElse(JNull)
          )
          val jsonString = compact(render(schemaJson))
          println(s"Processed Tweet: $jsonString")
          val response = sendToElasticSearch.sendToElasticSearch(jsonString)
        }
      }
    } catch {
      case e: Exception => println(s"Error: ${e.getMessage}")
    } finally {
      consumer.close()
    }
  }
}