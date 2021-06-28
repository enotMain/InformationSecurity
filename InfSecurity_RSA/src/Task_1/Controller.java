package Task_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

// Контроллер для графика
public class Controller extends Application {

    /**
     * Запуск
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Метод для построения графика зависимости между временем и битовостью больших чисел - контроллер
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("javaFX.fxml"));
        primaryStage.setTitle("Test of algorithms");

        // Координатные оси
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        // Сам график
        LineChart<Number, Number> numberLineChart = new LineChart<>(x,y);
        // Оси
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Fermat'small theorem");
        series1.setName("The extended Euclidean algorithm");
        // Контейнеры для хранения данных
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data2 = FXCollections.observableArrayList();
        Help.main();

        // Занесение данных с соблюдением зависимости между счетчиком и битовостью
        int m;
        for(int i = 0; i < 50; i++){
            m = (i + 1) * 27;
            data.add(new XYChart.Data(m, Help.timeEuc.get(i)));
            data2.add(new XYChart.Data(m, Help.timeFer.get(i)));
        }

        // Внесение данных на оси
        series1.setData(data);
        series2.setData(data2);

        // Внесение данных на график
        Scene scene = new Scene(numberLineChart, 600,600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}