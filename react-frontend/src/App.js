import React,{ useState } from 'react';
import './App.css';
import ImageGenerator from './components/ImageGenerator';
import ChatComponent from './components/ChatComponent';
import RecipeGenerator from './components/RecipeGenerator';

function App() {
  const [activeTab, setActiveTab] = useState('image-generator');

  const handletabChange = (tab) => {
    setActiveTab(tab);
  }

  return (
    <div className="App">
      <div className="tabs">
        <button className={activeTab === 'image-generator' ? 'active' : ''} onClick={() => handletabChange('image-generator')}>Image Generator</button>
        <button className={activeTab === 'chat' ? 'active' : ''} onClick={() => handletabChange('chat')}>Text Generator</button>
        <button className={activeTab === 'recipe-generator' ? 'active' : ''} onClick={() => handletabChange('recipe-generator')}>Recipe Generator</button>
      </div>
      <div className="content">
        {activeTab === 'image-generator' && <ImageGenerator />}
        {activeTab === 'chat' && <ChatComponent/>}
        {activeTab === 'recipe-generator' && <RecipeGenerator/>}
      </div>

    </div>
      
  );
}

export default App;
