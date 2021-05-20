package system;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class groupController {

	@FXML
    private TableView<Group> tableGroup;

    @FXML
    private TableColumn<Group, String> nameGrp;

    @FXML
    private TableColumn<Group, Integer> CapGrp;

    @FXML
    private TableColumn<Group, Integer> TechGrp;

    @FXML
    private TableColumn<Group, String> Update;

    @FXML
    private TableColumn<Group, String> Delete;

    @FXML
    private JFXButton AddGrp;

    @FXML
    private JFXButton RefGrp;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Group group = null;
    
    ObservableList<Group> GroupList = FXCollections.observableArrayList();
    
    @FXML
    public void getAddView(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddGroup.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
            refreshView();
        } catch (IOException ex) {
            Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   
  
    
    

    @FXML
    public void refreshView() {
        try {
            GroupList.clear();
            query = "SELECT * FROM groups";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	GroupList.add(new Group(
                        resultSet.getString("nom_grp"),
                        resultSet.getInt("Capacité"),
                        resultSet.getInt("id_prof")  
            			 ));
            	tableGroup.setItems(GroupList);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void loadInfo() {
        System.out.println("Loading info");
        connection = DbConnection.createConnection();
        refreshView();
        nameGrp.setCellValueFactory(new PropertyValueFactory<>("nom"));
       CapGrp.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        TechGrp.setCellValueFactory(new PropertyValueFactory<>("prof"));

      



    }


    @FXML
    void initialize() {
        loadInfo();

    }
    
    
   
}
