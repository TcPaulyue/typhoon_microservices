package com.nju.wind.domain;

import lombok.Data;

@Data
public class Weather {
    private String time;
    private String location;
    private String kind;
    private String temp;
    private String wind;
    private String precipitation;
    private String pressure;
}
