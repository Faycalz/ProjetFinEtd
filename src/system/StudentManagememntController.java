package system;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.Student;
import system.ControllerSys;

public class StudentManagememntController {

	@FXML
	private TableView<Student> Table;
	
    @FXML
    private TableColumn<Student, Integer> Id;

    @FXML
    private TableColumn<Student, String> Name;

    @FXML
    private TableColumn<Student, String> LastName;

    @FXML
    private TableColumn<Student, String> Bday;

    @FXML
    private TableColumn<Student, String> Username;

    @FXML
    private TableColumn<Student, String> Password;

    @FXML
    private TableColumn<Student, Integer> Group;

    @FXML
    private TableColumn<Student, Integer> Teamid;
    
    @FXML
    private TableColumn<Student, String> Gender;
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Student student = null;

    ObservableList<Student> StudentList = FXCollections.observableArrayList();
    
    @FXML
    public void getAddView(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            refreshView();
        } catch (IOException ex) {
            Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    int index;
    @FXML
    void delete(ActionEvent event) {

        index = Table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        Integer from_table=Id.getCellData(index);
        Connection conn;
        PreparedStatement pst;
        conn = DbConnection.createConnection();
        String sql = "delete from etudiant where `num_insc` = ? " ;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, from_table);
            pst.execute();
            JOptionPane.showConfirmDialog(null, "Are you sure?");
            refreshView();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    public void refreshView() {
        try {
            StudentList.clear();
            query = "SELECT * FROM etudiant";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StudentList.add(new Student(
                        resultSet.getInt("num_insc"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("id_groupe"),
                        resultSet.getInt("code_binome"),  
                        resultSet.getString("gender")
                      
                       
                ));
                Table.setItems(StudentList);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void loadInfo() {
        System.out.println("Loading info");
        connection = DbConnection.createConnection();
        refreshView();
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        Bday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        Password.setCellValueFactory(new PropertyValueFactory<>("password"));
        Group.setCellValueFactory(new PropertyValueFactory<>("group"));
        Teamid.setCellValueFactory(new PropertyValueFactory<>("team"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      



    }


    @FXML
    void initialize() {
        loadInfo();

    }
    
    
    
    
    
    

}
