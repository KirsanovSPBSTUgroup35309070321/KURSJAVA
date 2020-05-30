package sample.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField LoginWrite;
    @FXML
    private PasswordField PasswordWrite;
    @FXML
    private Button SignUp;
    @FXML
    private Label ErrorWarning;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    public void ButtonClick(ActionEvent actionEvent) throws IOException {



        if(LoginWrite.getText().equals("admin") && PasswordWrite.getText().equals("admin"))
        {
            Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        }
        else{ ErrorWarning.setText("Неверный логин/пароль"); }
    }
}
