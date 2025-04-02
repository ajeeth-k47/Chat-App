


import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import axios from 'axios';
import { Link } from 'react-router-dom';

const ChatRoom = () => {
    const { chatId, topic } = useParams(); // Extract chatId and topic from the URL
    const encodedTopic = encodeURI(topic);
    const [messages, setMessages] = useState([]);
    const [sender, setSender] = useState('');
    const [content, setContent] = useState('');
    const [stompClient, setStompClient] = useState(null);
    const [subscribed, setSubscribed] = useState(false); // Track subscription

    useEffect(() => {
        fetchMessages(); // Fetch messages when the component loads
        connectWebSocket(); // Establish WebSocket connection

        return () => {
            if (stompClient) {
                stompClient.disconnect(); // Cleanup WebSocket connection on unmount
                console.log('Disconnected from WebSocket');
            }
        };
    }, []); // Only runs once when the component mounts

    // Function to fetch chat messages
    const fetchMessages = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/messages/${encodedTopic}/${chatId}`);
            setMessages(response.data);
        } catch (error) {
            console.error('Error fetching messages:', error);
        }
    };

    // Function to connect to WebSocket
    const connectWebSocket = () => {
        const socket = new SockJS('http://localhost:8080/chat'); // Connect to WebSocket
        const stomp = over(socket);

        stomp.connect({}, () => {
            console.log('Connected to WebSocket');
            setStompClient(stomp);

            if (!subscribed) { // Ensure subscription happens only once
                stomp.subscribe(`/topic/${chatId}`, (message) => {
                    const newMessage = JSON.parse(message.body);

                    setMessages((prevMessages) => {
                        // Prevent duplicate messages
                        if (!prevMessages.some(msg => msg.id === newMessage.id)) {
                            return [...prevMessages, newMessage];
                        }
                        return prevMessages;
                    });
                });

                setSubscribed(true); // Mark as subscribed
            }
        }, (error) => {
            console.error('WebSocket connection error:', error);
        });
    };

    // Function to send a message
    const sendMessage = () => {
        if (stompClient && sender.trim() && content.trim()) {
            const newMessage = { sender, content };

            stompClient.send(`/app/sendMessage/${chatId}`, {}, JSON.stringify(newMessage)); // Send message via WebSocket
            setSender('');
            setContent(''); // Clear message input, but keep sender name
        }
    };

    return (
        <div>
            <h1>{topic}</h1>
           <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
               <thead style={{ backgroundColor: "#f4f4f4", color: "#333" }}>
                   <tr>
                       <th style={{ padding: "12px", border: "1px solid #ddd", textAlign: "left" }}>Sender</th>
                       <th style={{ padding: "12px", border: "1px solid #ddd", textAlign: "left" }}>Content</th>
                   </tr>
               </thead>
               <tbody>
                   {messages.map((msg, index) => (
                       <tr key={index} style={{ backgroundColor: index % 2 === 0 ? "#f9f9f9" : "#fff" }}>
                           <td style={{ padding: "10px", border: "1px solid #ddd" }}>{msg.sender}</td>
                           <td style={{ padding: "10px", border: "1px solid #ddd" }}>{msg.content}</td>
                       </tr>
                   ))}
               </tbody>
           </table>


            <div>
                <label>Sender:</label>
                <input
                    type="text"
                    value={sender}
                    onChange={(e) => setSender(e.target.value)}
                    placeholder="Enter your name"
                />
            </div>

            <div style={{ marginBottom: '20px' }}>
                <label style={{ fontSize: '16px', fontWeight: 'bold', color: '#333' }}>Content:</label>
                <textarea
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    placeholder="Type a message..."
                    style={{
                        width: '100%',
                        height: '150px',
                        padding: '10px',
                        border: '1px solid #ccc',
                        borderRadius: '5px',
                        fontSize: '14px',
                        color: '#333',
                        outline: 'none',
                        resize: 'none', // Prevent resizing the textarea
                    }}
                />
            </div>
             <br />
             <br />

            <button onClick={sendMessage}>Send</button>
            <br />
            <br />
             <Link to={`/`}>← Back to home :)</Link>
        </div>
    );
};

export default ChatRoom;
