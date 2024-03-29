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
import com.jfoenix.controls.JFXTextField;

import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
    private TableColumn<Group, String> Delete;

    @FXML
    private JFXButton AddGrp;

    @FXML
    private JFXTextField filtergrp;

    @FXML
    private JFXButton RefGrp;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Group group = null;
    String espace = " ";
    
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
            query = "SELECT groups.nom_grp,groups.Capacit�,groups.id_prof, CONCAT(prof.nom ,'" + espace + "', prof.prenom)  AS nom_prenom  FROM groups LEFT JOIN prof ON groups.id_prof=prof.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	GroupList.add(new Group(
            			 
                        resultSet.getString("nom_grp"),
                        resultSet.getString("Capacit�"),
                        resultSet.getString("nom_prenom" )  
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

      //add cell of button edit 
        Callback<TableColumn<Group, String>, TableCell<Group, String>> cellFoctory = (TableColumn<Group, String> param) -> {
           // make cell containing buttons
           final TableCell<Group, String> cell = new TableCell<Group, String>() {
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
                              group = tableGroup.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM groups WHERE nom_grp ="+group.getNom();
                               connection = DbConnection.createConnection();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               refreshView();
                               
                           } catch (SQLException ex) {
                               Logger.getLogger(groupController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                          

                         

                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           group = tableGroup.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("AddGroup.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(groupController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AddGroupController addGroupController  = loader.getController();
                           addGroupController.setUpdate(true);
                           addGroupController.setTextField(group.getNom(),group.getCapacity(),group.getProf());
                           Parent parent = loader.getRoot();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           

                          

                       });

                       HBox managebtn = new HBox(editIcon);
                       managebtn.setStyle("-fx-alignment:center");
                      // HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
        Delete.setCellFactory(cellFoctory);
        tableGroup.setItems(GroupList);




    }


    @FXML
    void initialize() {
        loadInfo();
     // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Group> filteredData = new FilteredList<>(GroupList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filtergrp.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(group -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (group.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (group.getProf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Group> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableGroup.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableGroup.setItems(sortedData);
    }
    
    
   
}
