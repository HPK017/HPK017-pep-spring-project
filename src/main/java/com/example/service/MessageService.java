package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer message_id){
        return messageRepository.findById(message_id);
    }

    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteMessage(Integer message_id){
         messageRepository.deleteById(message_id);
    }

    public Message updateMessage(Integer message_id, Message message){
        message.setMessage_id(message_id);
        return messageRepository.save(message);
    }

}
