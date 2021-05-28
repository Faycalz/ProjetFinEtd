package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import system.Student;

public class TeacherController {
	
    @FXML
    private TableView<Teacher> tableTeacher;

	  @FXML
	    private TableColumn<Teacher, Integer> id;

	    @FXML
	    private TableColumn<Teacher, String> nameT;

	    @FXML
	    private TableColumn<Teacher, String>lastNameT;

	    @FXML
	    private TableColumn<Teacher, String> username;

	    @FXML
	    private TableColumn<Teacher, String> password;

	    @FXML
	    private TableColumn<Teacher, String> Degree;

	    @FXML
	    private TableColumn<Teacher, String> Edit;
	    @FXML
	    private JFXButton RefTeach;

	    @FXML
	    private JFXButton AddTeach;
	    
	    
	    String query = null;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Teacher teacher = null;
	    
	    
	    ObservableList<Teacher> TeacherList = FXCollections.observableArrayList();
	    
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
	    
	    @FXML
	    public void refreshView() {
	        try {
	        	TeacherList.clear();
	            query = "SELECT * FROM prof";
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	TeacherList.add(new Teacher(
	                        resultSet.getInt("id"),
	                        resultSet.getString("username"),
	                        resultSet.getString("password"),
	                        resultSet.getString("nom"),
	                        resultSet.getString("prenom"),
	                       
	                        resultSet.getString("grade")
	                ));
	            	tableTeacher.setItems(TeacherList);
	            }


	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }

	    }
	    
	    
	    
	    
	    
	    private void loadInfo() {
	        System.out.println("Loading info");
	        connection = DbConnection.createConnection();
	        refreshView();
	        id.setCellValueFactory(new PropertyValueFactory<>("id"));
	        nameT.setCellValueFactory(new PropertyValueFactory<>("name"));
	        lastNameT.setCellValueFactory(new PropertyValueFactory<>("lastname"));
	        username.setCellValueFactory(new PropertyValueFactory<>("username"));
	        password.setCellValueFactory(new PropertyValueFactory<>("password"));
	        Degree.setCellValueFactory(new PropertyValueFactory<>("degree"));
	     
	       
	      

	      //add cell of button edit 
	        Callback<TableColumn<Teacher, String>, TableCell<Teacher, String>> cellFoctory = (TableColumn<Teacher, String> param) -> {
	           // make cell containing buttons
	           final TableCell<Teacher, String> cell = new TableCell<Teacher, String>() {
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
	                        	   teacher = tableTeacher.getSelectionModel().getSelectedItem();
	                        	   Alert alert = new Alert(AlertType.CONFIRMATION);
	                        	   alert.setTitle("Delete Teacher");
	                        	   
	                        	   alert.setHeaderText("Are you sure want to delete "+teacher.getName()+" "+teacher.getLastname()+"?");
	                        	   Optional<ButtonType> option = alert.showAndWait();
	                        	   if (option.get() == ButtonType.OK) {
	                        		   teacher = tableTeacher.getSelectionModel().getSelectedItem();
	                               query = "DELETE FROM prof WHERE id  ="+teacher.getId();
	                               connection = DbConnection.createConnection();
	                               preparedStatement = connection.prepareStatement(query);
	                               preparedStatement.execute();
	                               refreshView();}
	                        	   else if (option.get() == ButtonType.CANCEL) {
	                        	     System.out.println("nothing");;
	                        	      } 
	                               
	                               
	                           } catch (SQLException ex) {
	                               Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
	                           }
	                           
	                          

	                         

	                       });
	                       editIcon.setOnMouseClicked((MouseEvent event) -> {
	                           
	                    	   teacher =tableTeacher.getSelectionModel().getSelectedItem();
	                           FXMLLoader loader = new FXMLLoader ();
	                           loader.setLocation(getClass().getResource("AddTeacher.fxml"));
	                           try {
	                               loader.load();
	                           } catch (IOException ex) {
	                               Logger.getLogger(AddTeacherController.class.getName()).log(Level.SEVERE, null, ex);
	                           }
	                           
	                           AddTeacherController addTeacherController  = loader.getController();
	                           addTeacherController.setUpdate(true);
	                           addTeacherController.setTextField(teacher.getId(),teacher.getName(), teacher.getLastname(), teacher.getDegree());
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
	        tableTeacher.setItems(TeacherList);


	    }
	    
	    @FXML
	    void initialize() {
	        loadInfo();

	    }
	    
	    
	    

}
