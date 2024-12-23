# Big-Data-Tweets-Project

producer task describtion:
The producer reads tweets from a JSON file named boulder_flood_geolocated_tweets.json (the data file).
Each tweet is parsed and sent to a Kafka topic named tweets_.topic.
The Kafka producer adds a delay of 5 seconds between sending each tweet to simulate real-time streaming.

Code Explanation:
The producer reads the JSON file line by line.
Each line is parsed as JSON and then sent to the Kafka topic tweets_.topic.
The producer uses the Kafka ProducerRecord to send messages to the topic.
A 5-second delay (Thread.sleep(5000)) is added between messages (simulating real-time streaming) .
