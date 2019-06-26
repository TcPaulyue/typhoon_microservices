package com.nju.listener.controller;

import com.nju.listener.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Controller
public class webSocketController {
    @RequestMapping(value = "/websocket")
    public String websocket() {
        try{
           // System.out.println(userId+"   "+fileId);
            //model.addAttribute("userId",userId);
            //model.addAttribute("fileId",fileId);
           //webSocketServer.sendMessage("rain inf");
            return "websocket";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}


