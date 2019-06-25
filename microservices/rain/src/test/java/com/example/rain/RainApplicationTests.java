package com.example.rain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RainApplicationTests {

    private JsonArray rain_data;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testLoadInf() throws IOException {
        JsonParser parser = new JsonParser();
        // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonArray类型
        JsonObject jsons = (JsonObject) parser.parse(new FileReader("./src/main/resources/rain.json"));
        rain_data = (JsonArray) jsons.get("rain");
        System.out.println(rain_data.size()+rain_data.toString());
    }
}
