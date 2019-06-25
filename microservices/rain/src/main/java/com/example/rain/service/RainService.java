package com.example.rain.service;

import com.example.rain.domain.Rain;
import com.google.gson.*;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
@Log4j2
public class RainService {

    @Autowired
    private @NonNull KafkaTemplate<String,Rain> kafkaTemplate;

    private @NonNull JsonArray rain_data;

    private static  int count=0;

    private static int getCount=0;

    public RainService(){
        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonArray类型
            JsonObject jsons = (JsonObject) parser.parse(new FileReader("./src/main/resources/rain.json"));
            rain_data = (JsonArray) jsons.get("rain");
            log.info("rain_data"+rain_data.size());
            //System.out.println(rain_data.size()+rain_data.toString());
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getPrec(int count){
        String s= this.rain_data.get(count).toString();
        int place=s.lastIndexOf(',')+1;
        return Double.valueOf(s.substring(place,s.length()-1));
    }
    public String getTime(int count){
        String s= this.rain_data.get(count).toString();
        int place=s.lastIndexOf('"');
        return s.substring(2,place);
    }
    public String getLevel(int count){
        String s= this.rain_data.get(count).toString();
        int begin=s.indexOf(',')+1;
        int end=s.lastIndexOf(',');
        return s.substring(begin,end);
    }

    //用kafkatemplate不断产生雨情
    public void produceRain(){
        Rain rain=new Rain();
        rain.setLevel(this.getLevel(count));
        rain.setPrec(this.getPrec(count));
        rain.setTime(this.getTime(count));
        rain.setLocation("shanghai");
        rain.setKind("rain");
        try {
            kafkaTemplate.send("rain", rain);
            count++;
            if(count==180)
                count=0;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
        log.info("producerRecord:    "+rain.getLevel()+"    " +rain.getPrec()+"   "+rain.getTime()+"  "+rain.getLocation());
        }
    }
    public Rain getRain(){
        Rain rain=new Rain();
        rain.setLevel(this.getLevel(getCount));
        rain.setPrec(this.getPrec(getCount));
        rain.setTime(this.getTime(getCount));
        rain.setLocation("shanghai");
        rain.setKind("rain");
        getCount++;
        if(getCount==180)
            getCount=0;
        return rain;
    }
}
