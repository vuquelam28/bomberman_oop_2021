module com.example.bomberman_oop_2021 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bomberman_oop_2021 to javafx.fxml;
    exports com.example.bomberman_oop_2021;
}