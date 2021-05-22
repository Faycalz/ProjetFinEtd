package admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SessionController {
	
	  @FXML
	    private TableView<Session> tableGroup;

	    @FXML
	    private TableColumn<Session, Time> hour;

	    @FXML
	    private TableColumn<Session, Date> date;

	    @FXML
	    private TableColumn<Session, String> room;

	    @FXML
	    private TableColumn<Session, String> groupSe;

	    @FXML
	    private TableColumn<Session, String> Edit;
	    
	    @FXML
	    private JFXButton AddSess;

	    @FXML
	    private JFXButton RefSess;
	    
	    
	    @FXML
	    public void getAddView(ActionEvent actionEvent) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("AddSession.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.setResizable(false);
	            stage.show();
	            //refreshView();
	        } catch (IOException ex) {
	            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }
}
