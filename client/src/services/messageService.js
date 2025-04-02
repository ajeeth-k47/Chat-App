import axios from 'axios';

const API_URL = 'http://localhost:8080/api/messages';

const getMessages = async (topic,chatId) => {
  try {
    const encodedTopic = encodeURIComponent(topic);
    const response = await axios.get(`${API_URL}/${encodedTopic}/${chatId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching messages:', error);
    throw error;
  }
};

const sendMessage = async (topic,chatId, message) => {
  try {
    const encodedTopic = encodeURIComponent(topic);
    const response = await axios.post(`${API_URL}/${encodedTopic}/${chatId}`, message);
    return response.data;
  } catch (error) {
    console.error('Error sending message:', error);
    throw error;
  }
};

export default {
  getMessages,
  sendMessage
};
