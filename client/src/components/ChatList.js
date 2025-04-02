import React from 'react';
import { Link } from 'react-router-dom';
//    "start": "react-scripts start",
const ChatList = ({ chats }) => {
  return (
    <div className="chat-list">
      <h2>Existing Chats</h2>
      <ul>
        {chats.map((chat) => (
          <li key={chat.id}>
            <Link to={`/chat/${chat.topic}/${chat.id}`}>{chat.topic}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChatList;
