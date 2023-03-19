package com.diatop.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ResourceController {

    //  @CrossOrigin(origins = "*", maxAge = 3600,
//          allowedHeaders={"X-Auth-Token", "x-requested-with", "x-xsrf-token"})
    @RequestMapping("/test")
    public Message home() {
        return new Message("Hello World");
    }

    class Message {
        private String id = UUID.randomUUID().toString();
        private String content;

        public Message(String content) {
            this.content = content;
        }

        public Message(String id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
