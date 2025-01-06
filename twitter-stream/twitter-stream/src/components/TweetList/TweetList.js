import React from "react";
import "./TweetList.css";

const TweetList = ({ tweets }) => {
  return (
    <div className="tweet-list">
      <h2 className="tweet-list-title">Live Tweets</h2>
      <ul className="tweet-list-items">
        {tweets.map((tweet, index) => (
          <li key={index} className="tweet-item">
            <span className="tweet-user">{tweet.user}:</span> {tweet.text}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TweetList;
