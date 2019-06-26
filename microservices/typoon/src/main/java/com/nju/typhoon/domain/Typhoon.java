package com.nju.typhoon.domain;

import lombok.Data;

@Data
public class Typhoon {
    private String time;
    private String longitude;
    private String latitude;
    private String strong;
    private String power;
    private String speed;
    private String move_dir;
//    private String move_speed;
//    private String pressure;
//    private String radius7;
//    private String radius10;
//    private String radius12;
//    private String remark;
    private String kind;
}
