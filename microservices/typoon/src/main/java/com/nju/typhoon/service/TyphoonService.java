package com.nju.typhoon.service;

import com.google.gson.*;
import com.nju.typhoon.domain.Typhoon;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
@Service
@Log4j2
public class TyphoonService{
    @Autowired
    private @NonNull KafkaTemplate<String,Typhoon> kafkaTemplate;

    private @NonNull JsonArray typhoonData;
    private static int count=0;
    private static int getCount=0;

    public TyphoonService(){
        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            typhoonData= (JsonArray) parser.parse(new FileReader("./src/main/resources/rumbia.json"));
            //JsonObject jsons=(JsonObject) parser.parse(new FileReader("./src/main/resources/rumbia.json"));
            //typhoonData=(JsonArray) jsons.get("typhoon");
            log.info("typhoon_data"+typhoonData.size());
            System.out.println(typhoonData.size()+typhoonData.toString());
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*kafka推送消息*/
    public void produceTyphoon() {
        JsonObject typhoon=(JsonObject) typhoonData.get(count);
        count++;
        Gson gson = new Gson();
        Typhoon typhoon1=gson.fromJson(typhoon, Typhoon.class);
        typhoon1.setKind("typhoon");
        kafkaTemplate.send("typhoon",typhoon1);
        if(count==94)
            count=0;
        //kafkaTemplate.send("typhoon",typhoon2);
        log.info("send typhoon inf");
    }

    /*逐条获取消息*/
    public Typhoon getTyphoon(){
        JsonObject typhoon=(JsonObject) typhoonData.get(getCount);
        getCount++;
        Gson gson = new Gson();
        Typhoon typhoon1=gson.fromJson(typhoon, Typhoon.class);
        if(getCount==94)
            getCount=0;
        return typhoon1;
    }
}
