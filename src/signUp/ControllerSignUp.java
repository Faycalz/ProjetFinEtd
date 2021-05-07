package signUp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControllerSignUp implements Initializable {

	
	@FXML
	private VBox vbox;
	private Parent fxml;
	
	@FXML
	private Label label_timeone;
	
	@FXML
	private Label lbl_timetwo;
	    

	
	@FXML
	private void open_signupetd(ActionEvent event) {
		
		TranslateTransition t1 = new TranslateTransition(Duration.seconds(1), vbox);
		t1.setToX(vbox.getLayoutX()*20);
		t1.play();
		t1.setOnFinished((e)-> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignUpEtd.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {
				// TODO: handle exception
			}
		});
		
	}
	
	
	@FXML
	private void open_signupprof(ActionEvent event) {
		
		TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), vbox);
		t2.setToX(0);
		t2.play();
		t2.setOnFinished((e)-> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignUpProf.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {

			}
		});
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		/*Timeline clock=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            label_timeone.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
            new KeyFrame(Duration.seconds(1))    
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        
        Timeline clocktwo=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lbl_timetwo.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
            new KeyFrame(Duration.seconds(1))    
        );
        clocktwo.setCycleCount(Animation.INDEFINITE);
        clocktwo.play();*/
		
		
		
		TranslateTransition t3 = new TranslateTransition(Duration.seconds(1), vbox);
		t3.setToX(vbox.getLayoutX()*20);
		t3.play();
		t3.setOnFinished((e)-> {
			try {
				fxml= FXMLLoader.load(getClass().getResource("SignUpEtd.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {
				// TODO: handle exception
				
			}
		});
	}
	

	
	
	
	
	
	
}
