package com.junfu.ep.po;

import lombok.Data;

@Data
public class Weather {
    private String tip;
    private String temp_day_c;
    private String temp_night_c;
    private String publishTime;
    private String city;
}
