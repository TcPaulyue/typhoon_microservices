package com.nju.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.nju.listener.server.WebSocketServer.webSocketServer;

@Component
@Log4j2
public class KafkaListener {

    //监听3个topic
    @org.springframework.kafka.annotation.KafkaListener(topics = {"rain","weather","typhoon"})
    public void listen(ConsumerRecord<?, ?> record) throws Exception{
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info(kafkaMessage.get().toString());
            JSONObject jsonObject= JSON.parseObject(kafkaMessage.get().toString());
            String kind=jsonObject.get("kind").toString();
            switch (kind){
                case "rain": {
                    String time="时间： "+jsonObject.getString("time");
                    String prec="降水量： "+jsonObject.getString("prec");
                    String level="水位： "+jsonObject.getString("level");
                    String location="地点： "+jsonObject.getString("location");
                    log.info("雨情：\n"+time+"\n"+prec+"\n"+level+"\n"+location);
                    if(webSocketServer!=null){
                        webSocketServer.sendMessage(jsonObject.toJSONString());
                    }
                    break;
                }
                case "typhoon":{
                    if(webSocketServer!=null){
                        webSocketServer.sendMessage(jsonObject.toJSONString());
                    }
                    break;
                }
                case "weather":{
                    if(webSocketServer!=null){
                        webSocketServer.sendMessage(jsonObject.toJSONString());
                    }
                    break;
                }
            }
        }
    }
}
