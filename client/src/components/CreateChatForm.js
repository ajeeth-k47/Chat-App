import React from 'react';

const CreateChatForm = ({ newChat, setNewChat, onCreateChat }) => {
  return (
    <div className="create-chat-form">
      <input
        type="text"
        placeholder="Enter chat topic"
        value={newChat}
        onChange={(e) => setNewChat(e.target.value)}
      />
      <button onClick={onCreateChat}>Create Chat</button>
    </div>
  );
};

export default CreateChatForm;
