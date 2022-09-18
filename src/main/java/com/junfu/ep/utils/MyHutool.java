package com.junfu.ep.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.junfu.ep.po.Weather;

public class MyHutool {
    /**
     * 获取天气信息
     * @param cityNumber
     * @return
     */
    public static Weather getWeather(String cityNumber) {

        Weather weather = new Weather();

        String res = HttpUtil.get("https://aider.meizu.com/app/weather/listWeather?cityIds="+cityNumber);

        JSONObject dataObject = new JSONObject(res);
        Object value = dataObject.getJSONArray("value").get(0);
        JSONObject valueObject = new JSONObject(value);
        System.out.println(valueObject);

        Object indexes = valueObject.getJSONArray("indexes").get(0);

        Object weathersInfo = valueObject.getJSONArray("weathers").get(0);
        JSONObject weathersInfoObject = new JSONObject(weathersInfo);
        JSONObject indexsObject = new JSONObject(indexes);
        String tip = (String)indexsObject.get("content");
        String city = (String)valueObject.get("city");
        String publishTime = (String)weathersInfoObject.get("date");
        String temp_night_c = (String)weathersInfoObject.get("temp_night_c");
        String temp_day_c = (String)weathersInfoObject.get("temp_day_c");


        weather.setCity(city);
        weather.setPublishTime(publishTime);
        weather.setTip(tip);
        weather.setTemp_day_c(temp_day_c);
        weather.setTemp_night_c(temp_night_c);

        System.out.println(weather);
        return weather;
    }
}
