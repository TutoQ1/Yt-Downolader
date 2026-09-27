package yt.downloader.ytdownloader.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import yt.downloader.ytdownloader.repository.Repo;
import yt.downloader.ytdownloader.service.Downloader;

import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    TextField urlField;
    @FXML
    Label error;
    @FXML
    TextField dirField;
    @FXML
    Label saved;

    Repo repo = new Repo();
    Downloader dwnload = new Downloader();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        urlField.setPromptText("URL VIDEO");
        error.setVisible(false);
        saved.setVisible(false);
        try {
            String savedDir = repo.LoadConfigDir();
            if (savedDir != null) {
                dirField.setText(savedDir);
            } else {
                dirField.setPromptText("INPUT OUTPUT DIRECTORY");
            }
        } catch (Exception e) {
            errorBanish(e.getMessage());
        }
    }

    private void saved()
    {
        saved.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1.3));
        pauseTransition.setOnFinished(event -> saved.setVisible(false));
        pauseTransition.play();
    }

    private void errorBanish(String e)
    {
        error.setText(e);
        error.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.3));
        pause.setOnFinished(event -> error.setVisible(false));

        pause.play();
    }

    public void download()
    {
        try {
            String URL = urlField.getText();
            dwnload.execute(URL);
        }catch (Exception e)
        {
            errorBanish(e.getMessage());
        }
    }

    public void setDir()
    {
        try {
            String dir = dirField.getText();
            repo.setConfig(dir);
            saved();
        } catch (Exception e)
        {
            errorBanish(e.getMessage());
        }
    }

    public void dependencies()
    {
        dwnload.installDependencies();
    }
}
