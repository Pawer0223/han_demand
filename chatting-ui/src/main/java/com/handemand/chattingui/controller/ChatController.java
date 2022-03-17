package com.handemand.chattingui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    private String chatPage() {
        return "chat/chatRoom";
    }
}
