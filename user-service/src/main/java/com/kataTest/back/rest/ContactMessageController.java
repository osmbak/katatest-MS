package com.kataTest.back.rest;


import com.kataTest.back.enteties.ContactMessage;
import com.kataTest.back.service.ContactMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactMessageController {

        private final ContactMessageService contactService;

        public ContactMessageController(ContactMessageService contactService) {
            this.contactService = contactService;
        }

        @PostMapping
        public String receiveMessage(@RequestBody ContactMessage message) {
            return contactService.saveMessage(message);
        }

}
