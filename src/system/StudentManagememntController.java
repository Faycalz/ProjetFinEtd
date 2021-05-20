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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import system.Student;
import system.ControllerSys;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

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
    @FXML
    private TableColumn<Student, String> Edit;
    
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
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("id_grp"),
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
      

      //add cell of button edit 
        Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFoctory = (TableColumn<Student, String> param) -> {
           // make cell containing buttons
           final TableCell<Student, String> cell = new TableCell<Student, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {

                       FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                       FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                       
              
                       deleteIcon.setId("my_icon");
                       deleteIcon.setStyleClass("StyleSys.css");
                       editIcon.setId("edit_icon");
                       editIcon.setStyleClass("StyleSys.css");
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           try {
                               student = Table.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM etudiant WHERE num_insc  ="+student.getId();
                               connection = DbConnection.createConnection();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               refreshView();
                               
                           } catch (SQLException ex) {
                               Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                          

                         

                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           student = Table.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("AddStudent.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AddStudentController addStudentController = loader.getController();
                           addStudentController.setUpdate(true);
                           addStudentController.setTextField(student.getId(),student.getName(), student.getLastname(),student.getBirthday().toLocalDate() , student.getGroup());
                           Parent parent = loader.getRoot();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           

                          

                       });

                       HBox managebtn = new HBox(editIcon,deleteIcon);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
        Edit.setCellFactory(cellFoctory);
        Table.setItems(StudentList);


    }


    @FXML
    void initialize() {
        loadInfo();

    }
    
    
    
    
    
    

}
