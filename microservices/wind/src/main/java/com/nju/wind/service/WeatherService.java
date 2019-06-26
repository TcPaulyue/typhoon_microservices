package com.nju.wind.service;

import com.google.gson.*;
import com.nju.wind.domain.Weather;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
@Service
@Log4j2
public class WeatherService {
    @Autowired
    private @NonNull KafkaTemplate<String,Weather> kafkaTemplate;

    private @NonNull JsonArray weatherData;
    private static int count=0;
    private static int getCount=0;

    public WeatherService(){
        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            weatherData= (JsonArray) parser.parse(new FileReader("./src/main/resources/weather.json"));
            //JsonObject jsons=(JsonObject) parser.parse(new FileReader("./src/main/resources/rumbia.json"));
            //weatherData=(JsonArray) jsons.get("typhoon");
            log.info("weather_data"+weatherData.size());
            //System.out.println(weatherData.size()+weatherData.toString());
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void produceWeather() {
        JsonObject weather=(JsonObject) weatherData.get(count);
        count++;
        Gson gson = new Gson();
        Weather weather1=gson.fromJson(weather, Weather.class);
        weather1.setKind("wind");
        kafkaTemplate.send("wind",weather1);
        if(count==140)
            count=0;
        //kafkaTemplate.send("typhoon",typhoon2);
        log.info("send typhoon inf");
    }
    public Weather getWeather(){
        JsonObject weather=(JsonObject) weatherData.get(getCount);
        System.out.println(weather.toString());
        getCount++;
        if(getCount==140)
            getCount=0;
        Gson gson = new Gson();
        Weather weather1=gson.fromJson(weather, Weather.class);
        weather1.setKind("wind");
        weather1.setLocation("Nanjing");
        return weather1;
    }
}
