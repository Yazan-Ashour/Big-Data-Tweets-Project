import React from "react";
import "./InputField.css";

const InputField = ({ keyword, setKeyword }) => {
  const handleInputChange = (e) => {
    setKeyword(e.target.value);
  };

  const handleClearAndRefresh = () => {
    setKeyword(""); 
    window.location.reload(); 
  };

  return (
    <div className="input-container">
      <input
        type="text"
        placeholder="Search by keyword..."
        value={keyword}
        onChange={handleInputChange}
        className="input-field with-icon"
        aria-label="Search field"
      />
      <button
        className="clear-button"
        onClick={handleClearAndRefresh}
        aria-label="Clear search"
        title="Clear"
        disabled={!keyword.trim()} 
      >
        ✖
      </button>
    </div>
  );
};

export default InputField;
