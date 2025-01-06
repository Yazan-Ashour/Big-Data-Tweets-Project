const updateTrends = (hashtags, trends) => {
    const updatedTrends = { ...trends };
    hashtags.forEach((tag) => {
      updatedTrends[tag] = (updatedTrends[tag] || 0) + 1;
    });
    return updatedTrends;
  };
  
  export default updateTrends;
  