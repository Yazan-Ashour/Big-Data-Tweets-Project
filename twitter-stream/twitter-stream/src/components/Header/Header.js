import React from "react";
import "./Header.css";

const Header = ({ keyword, setKeyword }) => {
  return (
    <div className="header">
      <input
        type="text"
        placeholder="Enter keyword..."
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
      />
    </div>
  );
};

export default Header;
