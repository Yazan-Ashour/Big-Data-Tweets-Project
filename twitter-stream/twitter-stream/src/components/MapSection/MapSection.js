import React, { useRef } from "react";
import { MapContainer, TileLayer, Circle, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "./MapSection.css";

const MapSection = ({ filteredTweets }) => {
  const mapRef = useRef(); // Reference to the Map instance
  const tweets = filteredTweets || [];

  // Function to zoom in
  const zoomIn = () => {
    const map = mapRef.current;
    if (map) {
      map.setZoom(map.getZoom() + 1); // Increase zoom level
    }
  };

  // Function to zoom out
  const zoomOut = () => {
    const map = mapRef.current;
    if (map) {
      map.setZoom(map.getZoom() - 1); // Decrease zoom level
    }
  };

  return (
    <div className="map-box">
      <MapContainer
        center={[31.5, 34.5]} // Default center point
        zoom={6}
        className="leaflet-map"
        scrollWheelZoom={false}
        ref={mapRef} // Attach ref to the MapContainer
      >
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

        {tweets.map((tweet, index) => {
          const location = tweet.location;

          if (location && !isNaN(location.lat) && !isNaN(location.lon)) {
            return (
              <Circle
                key={index}
                center={[location.lat, location.lon]} // Map location
                radius={5000}
                pathOptions={{
                  color: "rgb(255, 0, 0)",
                  fillColor: "rgba(207, 197, 197, 0.5)",
                  fillOpacity: 0.7,
                }}
              >
                <Popup>
                  <div className="popup-content">
                    <h4 className="popup-user">{tweet.user}</h4>
                    <p className="popup-text">{tweet.text}</p>
                    <span className="popup-emotion">
                      {tweet.sentiment || "No Sentiment Data"}
                    </span>
                  </div>
                </Popup>
              </Circle>
            );
          } else {
            console.warn(
              `Invalid location data for tweet at index ${index}:`,
              location
            );            
            return null;
          }
        })}
      </MapContainer>

      <div className="zoom-buttons">
        <button className="zoom-button" onClick={zoomIn}>
          +
        </button>
        <button className="zoom-button" onClick={zoomOut}>
          -
        </button>
      </div>
    </div>
  );
};

export default MapSection;