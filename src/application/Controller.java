package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXRadioButton;


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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import system.ControllerSys;


public class Controller {
	public AnchorPane loginpane;
	@FXML
	private Label label1;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private JFXRadioButton TeacherRbtn;

	@FXML
	private JFXRadioButton StudentRbtn;
	 public static String typedID = null;
	
    String id = null, pass = null;
	 public void onLogin(ActionEvent event) throws IOException  {
		
		 if (StudentRbtn.isSelected()== false && TeacherRbtn.isSelected()== false)   {
			 label1.setText("please choose an option ");
     		label1.setStyle("-fx-text-fill: red ; -fx-font-size : 14  ");
     		label1.setVisible(true);
       	 }
		if(usernameField.getText().matches("[a-zA-Z0-9_] {4,}")) {
			return;
		}
		if(passwordField.getText().isEmpty()) {
			
			return;
		}
		
		/*int status = DbConnection.checkLogin(usernameField.getText().trim().toLowerCase(), passwordField.getText());
		
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
		
		
		}*/
		
		typedID = usernameField.getText();
        String typedPassword = passwordField.getText();
        if (StudentRbtn.isSelected()) {
            String q1 = "SELECT * FROM etudiant  WHERE username = '" + typedID + "'";

            try {
                ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

                while (rs1.next()) {
                    id = rs1.getString("username");
                    pass = rs1.getString("password");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            if (typedID.equals(id) && typedPassword.equals(pass)) {
            	Stage stage = (Stage) usernameField.getScene().getWindow();		
    			Parent root = null;
    			try {
    				root = FXMLLoader.load(getClass().getResource("/etudiant/EtdFirstWindow.fxml"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			stage.setScene(new Scene(root));

            } else {
            	Notifications notif2 = Notifications.create();
        		Image img2 = new Image("/images/account.png");
        		
        		notif2.title("Try Again");
        		notif2.text("The username or password is incorrect.");
        		notif2.darkStyle();
        		notif2.hideAfter(Duration.seconds(5));
        		notif2.position(Pos.BOTTOM_RIGHT);
        		notif2.graphic(new ImageView(img2) );
        		notif2.show();
        		label1.setText("Username or Password incorrect !");
        		label1.setStyle("-fx-text-fill: red ; -fx-font-size : 14  ");
        		label1.setVisible(true);
            }
        } else if (TeacherRbtn.isSelected()) {
            String q1 = "SELECT * FROM prof  WHERE username = '" + typedID + "'";
            id = null;
            pass = null;
            try {
                ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

                while (rs1.next()) {
                    id = rs1.getString("username");
                    
                    pass = rs1.getString("password");
                    

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            if (typedID.equals(id) && typedPassword.equals(pass)) {
            	Stage stage = (Stage) usernameField.getScene().getWindow();		
    			Parent root = null;
    			try {
    				root = FXMLLoader.load(getClass().getResource("/system/FxmlSys.fxml"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			stage.setScene(new Scene(root));

            } else {
            	Notifications notif2 = Notifications.create();
        		Image img2 = new Image("/images/account.png");
        		
        		notif2.title("Try Again");
        		notif2.text("The username or password you entered is incorrect.");
        		notif2.darkStyle();
        		notif2.hideAfter(Duration.seconds(5));
        		notif2.position(Pos.BOTTOM_RIGHT);
        		notif2.graphic(new ImageView(img2) );
        		notif2.show();
        		label1.setText("Username or Password incorrect !");
        		label1.setStyle("-fx-text-fill: red ; -fx-font-size : 14  ");
        		label1.setVisible(true);
              
            }
            
        }
        
     
	}
	
	
	/*@FXML
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
		
	}*/
	
	
	
	 @FXML
	    void initialize() throws IOException {

		 

	    }
	
	
	
		
	
	

 
}

