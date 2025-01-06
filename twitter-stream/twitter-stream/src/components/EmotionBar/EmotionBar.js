import React from "react";
import "./EmotionBar.css";

const EmotionBar = ({ sentimentCounts }) => {
  const totalTweets = Object.values(sentimentCounts).reduce((sum, count) => sum + count, 0);

  const dominantSentiment = Object.keys(sentimentCounts).reduce((a, b) =>
    sentimentCounts[a] > sentimentCounts[b] ? a : b
  );

  const dominantProportion = totalTweets > 0 ? (sentimentCounts[dominantSentiment] / totalTweets) * 100 : 0;

  const getColorForSentiment = (sentiment) => {
    switch (sentiment) {
      case "Positive":
        return "green";
      case "Neutral":
        return "gray";
      case "Negative":
        return "red";
      default:
        return "blue";
    }
  };

  return (
    <div className="emotion-bar">
      <div
        className="emotion-bar-indicator"
        style={{
          width: `${dominantProportion}%`,
          backgroundColor: getColorForSentiment(dominantSentiment),
        }}
        aria-label={`Dominant Sentiment: ${dominantSentiment}`}
      ></div>
      <div className="emotion-bar-text">
        {dominantSentiment} ({Math.round(dominantProportion)}%)
      </div>
    </div>
  );
};

export default EmotionBar;
