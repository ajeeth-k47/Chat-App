import React, { useState, useEffect } from 'react';
import { getChats, createChat } from '../services/ChatService';
import ChatList from './ChatList';
import CreateChatForm from './CreateChatForm';

const HomePage = () => {
  const [chats, setChats] = useState([]);
  const [newChat, setNewChat] = useState('');

  useEffect(() => {
    // Fetch all chats when the component loads
    const fetchChats = async () => {
      const data = await getChats();
      console.log(data)
      setChats(data);
    };

    fetchChats();
  }, []);

  const handleCreateChat = async () => {
    if (newChat.trim()) {
      const createdChat = await createChat({ topic: newChat });
      setChats([...chats, createdChat]);
      setNewChat('');
    }
  };

  return (
    <div className="home-page">
      <ChatList chats={chats} />
      <CreateChatForm newChat={newChat} setNewChat={setNewChat} onCreateChat={handleCreateChat} />
    </div>
  );
};

export default HomePage;
