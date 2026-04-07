import React, { useState } from "react";

function ImageGenerator() {
    const [prompt, setPrompt] = useState('');
    const [imageUrls, setImageUrls] = useState([]);

    const handleGenerateImage = async () => {
      try {
          const response = await fetch(
              `http://localhost:8080/generate-multiple-images?prompt=${encodeURIComponent(prompt)}&numberOfImages=1`
          );
          if (!response.ok) {
              throw new Error(`HTTP error! Status: ${response.status}`);
          }
          const data = await response.json();
          setImageUrls(data);

      } catch (error) {
          console.error("Error fetching images:", error);
      }
};

    return (
        <div className="tab-content">
            <h2>Generate Image</h2>
            <input
                type="text"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Enter prompt for image"
            />
            <button onClick={handleGenerateImage}>Generate Image</button>

            <div className="image-grid">
                {imageUrls.map((url, index) => (
                    <img key={index} src={url} alt={`Generated ${index}`} />
                ))}
                {[...Array(4 - imageUrls.length)].map((_, index) => (
                    <div key={index + imageUrls.length}
                        className="empty-image-slot"></div>
                    ))}
            </div>
        </div>
    );
}

export default ImageGenerator;



