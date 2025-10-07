module system.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;

    opens system.login to javafx.fxml;
    opens system.login.controller to javafx.fxml;
    opens system.login.model to com.google.gson;
    opens system.login.service to javafx.fxml;
    opens system.login.utils to javafx.fxml;
    
    exports system.login;
    exports system.login.controller;
    exports system.login.model;
    exports system.login.service;
    exports system.login.utils;
}
