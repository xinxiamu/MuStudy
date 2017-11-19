package com.j8.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用正则，从文本中提取文本。
 */
public class ExtractFromText {
    public static void main(String[] args) {
        List<Weather> weathers = new ArrayList<Weather>();

        String txt = "Location: New York, Temperature: 10"
                + "\nLocation: Nevada, Temperature: 22"
                + "\nLocation: London, Temperature: 12";

        String pattern = "(Location:)(\\s*.+)(,)(.*Temperature:\\s+)(\\d+)";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);

        while (m.find()) {

            System.out.println("found Location >> " + m.group(2));
            String location = m.group(2).trim();

            System.out.println("found Temperature >> " + m.group(5));
            String temperature = m.group(5);

            weathers.add(new Weather(location, Integer.parseInt(temperature)));
        }

        System.out.println(weathers);
    }
}

class Weather {
    private String location;
    private int temperature;

    public Weather(String location, int temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather [location=" + location + ", temperature=" + temperature + "]";
    }
}
