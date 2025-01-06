import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  CartesianGrid,
  ResponsiveContainer,
} from "recharts";
import "./TrendDiagram.css";

const TrendDiagram = ({ trends }) => {
  // Convert trends object to an array of { name, count } objects and sort by count
  const trendData = Object.keys(trends)
    .map((key) => ({
      name: key,
      count: trends[key],
    }))
    .sort((a, b) => b.count - a.count) // Sort by count in descending order
    .slice(0, 20); // Get the top 20 hashtags

  return (
    <div className="trend-diagram">
      <h2 className="trend-title">Trends Diagram</h2>
      <ResponsiveContainer width={400} height={400}>
        <BarChart
          data={trendData}
          margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" tick={{ fontSize: 12 }} />
          <YAxis tick={{ fontSize: 12 }} />
          <Tooltip
            contentStyle={{
              backgroundColor: "#fff",
              borderRadius: "8px",
              boxShadow: "0 2px 6px rgba(0, 0, 0, 0.15)",
            }}
          />
          <Legend wrapperStyle={{ fontSize: 12 }} />
          <Bar dataKey="count" fill="#1E90FF" barSize={15} />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default TrendDiagram;