package weatherapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WeatherApp extends Application {
    private String lastCity = "";
    private String lastUnit = "metric";
    private TextArea historyArea = new TextArea();

    @Override
    public void start(Stage stage) {
        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();
        Button fetchButton = new Button("Get Weather");

        Label tempLabel = new Label("Temperature:");
        Label descLabel = new Label("Description:");
        ImageView iconView = new ImageView();

        ToggleGroup unitGroup = new ToggleGroup();
        RadioButton celsius = new RadioButton("Celsius");
        RadioButton fahrenheit = new RadioButton("Fahrenheit");
        celsius.setToggleGroup(unitGroup);
        fahrenheit.setToggleGroup(unitGroup);
        celsius.setSelected(true);

        historyArea.setEditable(false);
        historyArea.setPrefRowCount(8);

        fetchButton.setOnAction(e -> {
            String city = cityField.getText().trim();
            String unit = celsius.isSelected() ? "metric" : "imperial";
            WeatherResult result = WeatherService.getWeatherData(city, unit);
            if (result != null) {
                tempLabel.setText("Temperature: " + result.getTemperature() + (unit.equals("metric") ? "°C" : "°F"));
                descLabel.setText("Description: " + result.getDescription());

                Image icon = new Image("https://openweathermap.org/img/wn/" + result.getIcon() + "@2x.png");
                iconView.setImage(icon);

                lastCity = city;
                lastUnit = unit;
                refreshHistory();
            }
        });

        VBox root = new VBox(10,
                new HBox(10, cityLabel, cityField, fetchButton),
                new HBox(10, celsius, fahrenheit),
                tempLabel, descLabel, iconView,
                new Label("Search History:"), historyArea);

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Weather Information App");
        stage.setScene(scene);
        stage.show();

        // Refresh weather every 60 seconds
        Timeline refresher = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
            if (!lastCity.isEmpty()) {
                WeatherResult result = WeatherService.getWeatherData(lastCity, lastUnit);
                if (result != null) {
                    tempLabel.setText("Temperature: " + result.getTemperature() + (lastUnit.equals("metric") ? "°C" : "°F"));
                    descLabel.setText("Description: " + result.getDescription());

                    Image icon = new Image("https://openweathermap.org/img/wn/" + result.getIcon() + "@2x.png");
                    iconView.setImage(icon);
                    refreshHistory();
                }
            }
        }));
        refresher.setCycleCount(Timeline.INDEFINITE);
        refresher.play();
    }

    private void refreshHistory() {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("weather_history.txt");
            String content = java.nio.file.Files.readString(path);
            historyArea.setText(content);
        } catch (Exception ex) {
            historyArea.setText("No history yet.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
