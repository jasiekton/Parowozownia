package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    int m=5;
    int trains_number=10;
    Train[] train = new Train [trains_number];

    TrainConsumerProducer tracks = new TrainConsumerProducer(2);
    TrainConsumerProducer signalBox = new TrainConsumerProducer(1);
    TrainConsumerProducer roundHouse = new TrainConsumerProducer(m);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stage.fxml"));
        Parent root = fxmlLoader.load();
        Controller.Instance = fxmlLoader.getController();

        for (int i=0; i<trains_number; i++)
        {
            train [i] = new Train(tracks, signalBox, roundHouse, i+1,'L');
        }

        for (int i=0; i<trains_number; i++)
        {
            train [i].start();
        }


        Scene scene = new Scene(root);
        stage.setTitle("Parowozownia");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}