module LMSProject {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens com.main;
    opens com.login;
    opens com.book;
}