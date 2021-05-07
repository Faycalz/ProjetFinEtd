package application;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;

import database.DbConnection;
import impl.org.controlsfx.i18n.Translation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Controller {

	@FXML
	private Label label1;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	

	private void onLogin() {
		
		
		if(usernameField.getText().matches("[a-zA-Z0-9_] {4,}")) {
			return;
		}
		if(passwordField.getText().isEmpty()) {
			return;
		}
		
		int status = DbConnection.checkLogin(usernameField.getText().trim().toLowerCase(), passwordField.getText());
		
		switch (status) {
		case 0 : {
			Stage stage = (Stage) usernameField.getScene().getWindow();		
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/system/FxmlSys.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.setScene(new Scene(root));
		}break;
		case -1: Notifications notif = Notifications.create();
		Image img = new Image("/images/server (2).png");
		
		notif.title("Connection Failed");
		notif.text("You must connect to your database");
		notif.darkStyle();
		notif.hideAfter(Duration.seconds(5));
		notif.position(Pos.BOTTOM_RIGHT);
		notif.graphic(new ImageView(img) );
		
		notif.show();
		
		break;
		
		
		
		case 1: Notifications notif2 = Notifications.create();
		Image img2 = new Image("/images/account.png");
		
		notif2.title("Try Again");
		notif2.text("The username or password you entered is incorrect.");
		notif2.darkStyle();
		notif2.hideAfter(Duration.seconds(5));
		notif2.position(Pos.BOTTOM_RIGHT);
		notif2.graphic(new ImageView(img2) );
		notif2.show();
		
		label1.setText("Username or Password incorrect");
		label1.setStyle("-fx-text-fill: red ; -fx-font-size : 14  ");
		label1.setVisible(true);
		 break;
		
		
		}
		
		
		
	}
	

	@FXML
	private void onSignUp(ActionEvent event) throws IOException {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/signUp/FxmlSignUp.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/signUp/application.css").toExternalForm());
		scene.setFill(Color.TRANSPARENT);
		//String css = this.getClass().getResource("/signUp/SignUpCss.css").toExternalForm();
		//scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Graduate Service");
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		primaryStage.show();
		
	}
	
	
	
	
	
	
	
		
	
	

 
}

