import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import scala.io.Source
import java.util.Properties
import org.json4s.native.JsonMethods._

object producer {
  def main(args: Array[String]): Unit = {
    // Kafka Configuration
    val broker = "localhost:9092"
    val topic = "tweets_.topic"

    // Kafka Producer Properties
    val props = new Properties()
    props.put("bootstrap.servers", broker)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    // Initialize Kafka Producer
    val producer = new KafkaProducer[String, String](props)

    // Path to the data file
    val dataFilePath = "src/main/boulder_flood_geolocated_tweets.json"

    try {
      // Read the file line by line and send to Kafka
      for (line <-Source.fromFile(dataFilePath).getLines()) {
        // Parse each line as JSON
        val parsedJson = parse(line)
        val jsonString = compact(render(parsedJson)) // Reconvert JSON to string

        // Send the JSON string to Kafka
        producer.send(new ProducerRecord[String, String](topic, jsonString))
        println(s"Sent: $jsonString")

        Thread.sleep(5000) // Simulate real-time streaming
      }
    } catch {
      case e: Exception => println(s"Error: ${e.getMessage}")
    } finally {
      producer.close()
    }
  }
}
