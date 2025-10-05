module system.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens system.login to javafx.fxml;
    opens system.login.controller to javafx.fxml;
    opens system.login.utils to javafx.fxml;
    
    exports system.login;
    exports system.login.controller;
}
