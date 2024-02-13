package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional; 
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
@RequestMapping("/social")
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId){
         Optional<Account> account= accountService.getAccountById(accountId);
         return  account.map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
       Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer accountId, @RequestBody Account account){
        try{
          Account updatedAccount =  accountService.updateAccount(accountId, account);
            return ResponseEntity.ok(updatedAccount);
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Optional<Message> message = messageService.getMessageById(messageId);
        return message.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable Integer messageId, @RequestBody Message message) {
        try {
            Message updatedMessage = messageService.updateMessage(messageId, message);
            return ResponseEntity.ok(updatedMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
