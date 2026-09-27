package yt.downloader.ytdownloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yt.downloader.ytdownloader.repository.Repo;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage)  throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),400,500);
        stage.setScene(scene);
        stage.setTitle("YT-DOWNLOADER");
        stage.show();
    }

}
