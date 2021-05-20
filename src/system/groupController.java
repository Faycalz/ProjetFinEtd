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
    private JFXButton RefGrp;

    @FXML
    private JFXButton DelGrp;

    @FXML
    private JFXButton AddGrp;

    @FXML
    private JFXButton UpGrp;
    
    @FXML
    private TableView<Group> Table;
    
    @FXML
    private TableColumn<Group, Integer> IdG;
    
    @FXML
    private TableColumn<Group, String> NameG;
    
    @FXML
    private TableColumn<Group, Integer> TeachG;
    

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
          //refreshView();
        } catch (IOException ex) {
            Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   /* 
    int index;
    @FXML
    void delete(ActionEvent event) {

        index = TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        Integer from_table=Id.getCellData(index);
        Connection conn;
        PreparedStatement pst;
        conn = DbConnection.createConnection();
        String sql = "delete from group where `id` = ? " ;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, from_table);
            pst.execute();
            JOptionPane.showConfirmDialog(null, "Are you sure?");
            //refreshView();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    

    @FXML
    public void refreshView() {
        try {
            GroupList.clear();
            query = "SELECT * FROM etudiant";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	GroupList.add(new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("id_prof")  
            			 ));
                Table.setItems(GroupList);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
    
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*  
    private void loadInfo() {
        System.out.println("Loading info");
        connection = DbConnection.createConnection();
        //refreshView();
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));

      



    }


    @FXML
    void initialize() {
        loadInfo();

    }
    
    */
   
}
