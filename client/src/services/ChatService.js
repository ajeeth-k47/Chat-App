import axios from 'axios';

const API_URL = 'http://localhost:8080/api/chats';

export const getChats = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching chats:', error);
  }
};

export const createChat = async (chatData) => {
  try {
    const response = await axios.post(API_URL, chatData);
    return response.data;
  } catch (error) {
    console.error('Error creating chat:', error);
  }
};
