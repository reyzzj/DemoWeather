package sg.edu.rp.c346.id21028514.demoweather;

public class Weather {

    private String area;
    private String forecast;

    public Weather(String area, String forecast) {
    }

    public String toString() {
        return "Weather" +
                "\narea = " + getArea()+
                "\nforecast = " + getForecast();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
