module yt.downloader.ytdownloader {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;


    opens yt.downloader.ytdownloader.controller to javafx.fxml;
    exports yt.downloader.ytdownloader;
}