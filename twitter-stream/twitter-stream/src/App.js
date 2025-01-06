import React, { useEffect, useState } from "react";
import InputField from "./components/InputField/InputField";
import EmotionBar from "./components/EmotionBar/EmotionBar";
import TweetList from "./components/TweetList/TweetList";
import TrendDiagram from './components/TrendDiagram/TrendDiagram'; 
import MapSection from "./components/MapSection/MapSection";

const App = () => {
  const [tweets, setTweets] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [trends, setTrends] = useState({});
  const [sentimentCounts, setSentimentCounts] = useState({
    Positive: 0,
    Neutral: 0,
    Negative: 0,
  });

  const fetchTweets = async (keyword) => {
    try {
      const response = await fetch(
        `http://127.0.0.1:8000/search_tweets?keyword=${encodeURIComponent(keyword)}`
      );
      const data = await response.json();
      if (data.tweets) {
        setTweets(data.tweets);
        updateTrends(data.tweets);
        calculateSentimentCounts(data.tweets);
      }
    } catch (error) {
      console.error("Error fetching tweets:", error);
    }
  };

  const updateTrends = (tweetList) => {
    const updatedTrends = {};
    tweetList.forEach((tweet) => {
      tweet.hashtags.forEach((tag) => {
        updatedTrends[tag] = (updatedTrends[tag] || 0) + 1;
      });
    });
    setTrends(updatedTrends);
  };

  const calculateSentimentCounts = (tweetList) => {
    const counts = { Positive: 0, Neutral: 0, Negative: 0 };
    tweetList.forEach((tweet) => {
      if (tweet.sentiment && counts[tweet.sentiment] !== undefined) {
        counts[tweet.sentiment]++;
      }
    });
    setSentimentCounts(counts);
  };

  useEffect(() => {
    if (keyword.trim()) {
      fetchTweets(keyword);
    }
  }, [keyword]);

  const filteredTweets = tweets.filter((tweet) =>
    tweet.text.toLowerCase().includes(keyword.toLowerCase())
  );

  return (
    <div className="app-container">
      <InputField keyword={keyword} setKeyword={setKeyword} />
      <EmotionBar sentimentCounts={sentimentCounts} />
      <TweetList tweets={filteredTweets} />
      <MapSection filteredTweets={filteredTweets} />
      <TrendDiagram trends={trends} />
    </div>
  );
};

export default App;
