package system;

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

public class subjectController {
	@FXML
    private TableView<Subject> tableGroup;

    @FXML
    private TableColumn<Subject, String> titleSub;

    @FXML
    private TableColumn<Subject, String> descrip;

    @FXML
    private TableColumn<Subject, String> field;

    @FXML
    private TableColumn<Subject, String> Keyword;

    @FXML
    private TableColumn<Subject, String> DevTools;

    @FXML
    private JFXButton AddSub;

    @FXML
    private JFXButton RefSub;
    
    

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Subject subject = null;
    
    ObservableList<Group> SubjectList = FXCollections.observableArrayList();
    
    @FXML
    public void getAddView(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddSubject.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
            //refreshView();
        } catch (IOException ex) {
            Logger.getLogger(subjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
