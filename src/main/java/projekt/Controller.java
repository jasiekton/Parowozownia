package projekt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {
    public static Controller Instance;

    @FXML
    private Canvas img;

    public void RenderChanges(
            TrainConsumerProducer tracks,
            TrainConsumerProducer signalBox,
            TrainConsumerProducer roundHouse){
        Platform.runLater(()->{
            GraphicsContext gc = img.getGraphicsContext2D();
            gc.clearRect(0, 0, img.getWidth(), img.getHeight());
            Image trainImg = new Image(getClass().getResourceAsStream("/pociag2.jpg"));
            Image trainImg2 = new Image(getClass().getResourceAsStream("/pociag.jpg"));
            Text text = new Text();
            for (int i = 0; i < tracks.buffer.Trains.length; i++) {
                if(tracks.buffer.Trains[i] != null)
                {text.setText(String.valueOf(tracks.buffer.Trains[i].id));
                    if(tracks.buffer.Trains[i].direction=='L')
                    gc.drawImage(trainImg,500,i*50 + 180,60,50);
                    else gc.drawImage(trainImg2,500,i*50 + 180,60,50);
                    gc.fillText(String.valueOf(tracks.buffer.Trains[i].id),500,i*50+180);

                }
            }

            for (int i = 0; i < signalBox.buffer.Trains.length; i++) {
                if(signalBox.buffer.Trains[i] != null)
                {
                    text.setText(String.valueOf(signalBox.buffer.Trains[i].id));
                    if(signalBox.buffer.Trains[i].direction=='L')
                        gc.drawImage(trainImg,375,i*50 + 190,60,50);
                    else gc.drawImage(trainImg2,375,i*50 + 190,60,50);

                    gc.fillText(String.valueOf(signalBox.buffer.Trains[i].id),375,i*50+190);
                }
            }

            for (int i = 0; i < roundHouse.buffer.Trains.length; i++) {
                if(roundHouse.buffer.Trains[i] != null)
                {
                    gc.drawImage(trainImg,250,i*100 + 120,60,50);

                    gc.fillText(String.valueOf(roundHouse.buffer.Trains[i].id),250,i*100+120);}
            }
        });
    }

}