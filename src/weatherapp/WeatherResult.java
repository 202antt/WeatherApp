package weatherapp;

public class WeatherResult {
    private double temperature;
    private String description;
    private String icon;

    public WeatherResult(double temperature, String description, String icon) {
        this.temperature = temperature;
        this.description = description;
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
