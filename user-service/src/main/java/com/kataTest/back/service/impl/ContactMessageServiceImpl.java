package com.kataTest.back.service.impl;

import com.kataTest.back.enteties.ContactMessage;
import com.kataTest.back.repositoy.ContactMessageRepository;
import com.kataTest.back.service.ContactMessageService;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactRepository;

    public ContactMessageServiceImpl(ContactMessageRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public String saveMessage(ContactMessage message) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(message.getName());
        contactMessage.setEmail(message.getEmail());
        contactMessage.setSubject(message.getSubject());
        contactMessage.setMessage(message.getMessage());

        contactRepository.save(message);
        return "Message enregistré avec succès !";
    }
}

