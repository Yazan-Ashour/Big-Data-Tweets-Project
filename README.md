# Real-Time Tweet Stream Processing and Visualization

# Project Overview:

This project involves designing and implementing a complete data pipeline to handle streaming tweets, process them, and visualize the results in a web application. The pipeline includes real-time tweet ingestion, sentiment analysis, hashtag extraction, storage, and visualization of tweets on a map with trend analysis. The goal is to provide a user-friendly interface to interact with the tweet data and gain insights from various aspects, including tweet sentiment, hashtags, and temporal trends.

# Key Features:

1- Real-time Streaming: Continuous tweet stream ingestion from Twitter API or a simulated generator.

2- Data Storage: Processed tweets stored in Elasticsearch for easy querying and analysis.

3- Tweet Processing: Extraction of hashtags, sentiment analysis, and geolocation.

4- Visualization: A web application that allows users to search tweets by keyword, display them on a map, visualize trends over time, and analyze sentiment.


# Data Pipeline Diagram :

1- Tweet Stream Ingestion:

  - Streams tweets using the Twitter API or a simulated tweet generator.

  - Ingests the data into Kafka for buffering and scalable processing.

2- Tweet Processing:

  - Kafka consumer retrieves tweets from the Kafka topic.
    
  - Tweets are processed: hashtags are extracted, sentiment analysis is performed, and geolocation data is parsed.

3- Storage:

  -  Processed tweets are stored in Elasticsearch.
    
  - The data includes text, hashtags, sentiment, timestamp, and location.

4- Web Application:

 - The frontend allows users to interact with the processed tweet data.
  
 - Users can search tweets, view sentiment trends, and see tweet distribution on the map based on geolocation.
