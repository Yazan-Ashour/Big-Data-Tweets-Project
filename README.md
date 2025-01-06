# Real-Time Tweet Stream Processing and Visualization

# Project Overview:

This project involves designing and implementing a complete data pipeline to handle streaming tweets, process them, and visualize the results in a web application. The pipeline includes real-time tweet ingestion, sentiment analysis, hashtag extraction, storage, and visualization of tweets on a map with trend analysis. The goal is to provide a user-friendly interface to interact with the tweet data and gain insights from various aspects, including tweet sentiment, hashtags, and temporal trends.

# Key Features:

1- Real-time Streaming: Continuous tweet stream ingestion from Twitter API or a simulated generator.

2- Data Storage: Processed tweets stored in Elasticsearch for easy querying and analysis.

3- Tweet Processing: Extraction of hashtags, sentiment analysis, and geolocation.

4- Visualization: A web application that allows users to search tweets by keyword, display them on a map, visualize trends over time, and analyze sentiment.


# Data Pipeline :

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

### 1. Install Backend Dependencies:

Create a virtual environment and install the required Python packages:
       
       python -m venv env
       pip install fastapi elasticsearch requests
       pip install stanfordnlp


Start Kafka: Download and start Kafka on your machine:

       # The place where you will work and run
       cd C:\kafka_2.12-3.8.1\bin\windows 
       # Start Zookeeper 
       bin/zookeeper-server-start.sh config/zookeeper.properties
       # Start Kafka
       bin/kafka-server-start.sh config/server.properties


Elasticsearch: If using Elasticsearch, ensure it is running:

       bin/elasticsearch

### 2. Install Frontend Dependencies:

Navigate to the frontend directory:

       cd frontend


Install React dependencies:
       npm install


### 3. Running the Project:

Backend: Run the FastAPI server:

       python -m uvicorn main:app --host 127.0.0.1 --port 8000 --reloadBackend: Run


Frontend: Start the React application:

       npm start


### 4. Access the Web Application:

Once the backend and frontend are running, open your browser and navigate to http://localhost:3000. You can input a keyword to search tweets, view them on a map,
see the sentiment gauge, and view trends.


# Conclusion :

This project showcases a full end-to-end data pipeline for processing real-time tweet streams, performing sentiment analysis, and visualizing the data in a web interface. By leveraging Kafka for stream handling, Elasticsearch for storage, and React for visualization, this pipeline offers a scalable and interactive solution for tweet analysis and exploration.


# Data Pipeline Diagram :

```mermaid
graph LR
    A[Data Ingestion] --> B[Kafka Topic]
    B --> C[Data Processing]
    C --> D[Sentiment Analysis ]
    D --> E[Elasticsearch Storage]
    E --> F[Visualization in frontend ]






       


