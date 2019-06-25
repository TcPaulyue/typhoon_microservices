package com.example.rain.domain;

import lombok.Data;

@Data
public class Rain {
    private  String level;//水位
    private  double prec;//降水量
    private  String time;//降水时间
    private String kind;
    private String id;
    private String location;
}
