module moe.hayden.votebox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens moe.hayden.votebox to javafx.fxml;
    exports moe.hayden.votebox;
    exports moe.hayden.votebox.controllers;
    opens moe.hayden.votebox.controllers to javafx.fxml;
    exports moe.hayden.votebox.controllers.voter;
    opens moe.hayden.votebox.controllers.voter to javafx.fxml;
    exports moe.hayden.votebox.controllers.voter.ballots;
    opens moe.hayden.votebox.controllers.voter.ballots to javafx.fxml;
    exports moe.hayden.votebox.controllers.admin;
    opens moe.hayden.votebox.controllers.admin to javafx.fxml;
}
