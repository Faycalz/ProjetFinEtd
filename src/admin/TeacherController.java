package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherController {
	
	    @FXML
	    private TableView<Teacher> tableTeacher;

	    @FXML
	    private TableColumn<Teacher, String> nameTeach;

	    @FXML
	    private TableColumn<Teacher, String> LastNTeach;

	    @FXML
	    private TableColumn<Teacher, String> degreeTech;

	    @FXML
	    private JFXButton RefTeach;

	    @FXML
	    private JFXButton AddTeach;
	    
	    
	    String query = null;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Teacher teacher = null;
	    
	    
	    ObservableList<Group> TeacherList = FXCollections.observableArrayList();
	    
	    @FXML
	    public void getAddView(ActionEvent actionEvent) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("AddTeacher.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.setResizable(false);
	            stage.show();
	            //refreshView();
	        } catch (IOException ex) {
	            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }
	    


}
