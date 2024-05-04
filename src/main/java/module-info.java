module com.example.clickergameattempt2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.clickergameattempt2 to javafx.fxml;
    exports com.example.clickergameattempt2;
}