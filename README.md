# Real-Time Tweet Stream Processing and Visualization

# Project Overview:

This project involves designing and implementing a complete data pipeline to handle streaming tweets, process them, and visualize the results in a web application. The pipeline includes real-time tweet ingestion, sentiment analysis, hashtag extraction, storage, and visualization of tweets on a map with trend analysis. The goal is to provide a user-friendly interface to interact with the tweet data and gain insights from various aspects, including tweet sentiment, hashtags, and temporal trends.

# Key Features:

1- Real-time Streaming: Continuous tweet stream ingestion from Twitter API or a simulated generator.

2- Data Storage: Processed tweets stored in Elasticsearch for easy querying and analysis.

3- Tweet Processing: Extraction of hashtags, sentiment analysis, and geolocation.

4- Visualization: A web application that allows users to search tweets by keyword, display them on a map, visualize trends over time, and analyze sentiment.


# Data Pipeline Diagram :

```mermaid
graph LR
    A[Real-time Tweet Stream Ingestion] --> B[Kafka Topic]
    B --> C[Tweet Processing]
    C --> D[Sentiment Analysis & Geolocation Parsing]
    D --> E[Elasticsearch Storage]
    E --> F[Web Application]


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


# Requirements :

# System Requirements:

1- Java (for Kafka): Java 8 or higher

2- Apache Kafka: Version 2.8.0 or higher

3- Elasticsearch: Version 7.x or higher (for Elasticsearch integration)

4- Scala: Version 2.12.18 (for backend services)

5- Python: Version 3.6 or higher (for sentiment analysis)

# Dependencies:

## Backend (FastAPI & Kafka):

1- FastAPI: A modern, fast web framework for building APIs.

2- Kafka: For stream processing and message queuing.

3- Elasticsearch/MongoDB: For data storage and querying.

4- Stanford CoreNLP: For sentiment analysis.

## Frontend (React):

1- React: A JavaScript library for building user interfaces.

2- Leaflet: For rendering the map visualization.

3- D3.js: For rendering trend diagrams.


# Setup and Installation :


