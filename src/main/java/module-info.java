module moe.hayden.votebox {
    requires javafx.controls;
    requires javafx.fxml;


    opens moe.hayden.votebox to javafx.fxml;
    exports moe.hayden.votebox;
    exports moe.hayden.votebox.controllers;
    opens moe.hayden.votebox.controllers to javafx.fxml;
}