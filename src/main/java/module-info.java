module com.example.clickergameattempt2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.clickergameattempt2 to javafx.fxml;
    exports com.example.clickergameattempt2;
    exports com.example.clickergameattempt2.ui;
    opens com.example.clickergameattempt2.ui to javafx.fxml;
    exports com.example.clickergameattempt2.controller;
    opens com.example.clickergameattempt2.controller to javafx.fxml;
    exports com.example.clickergameattempt2.model;
    opens com.example.clickergameattempt2.model to javafx.fxml;
    exports com.example.clickergameattempt2.util;
    opens com.example.clickergameattempt2.util to javafx.fxml;
}