package system;


import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class TeamController {

    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXButton AddEv;

    @FXML
    private JFXButton RefEv;

    @FXML
    private TableView<Team> tableSub;

    @FXML
    private TableColumn<Team, Integer> teamcode;

    @FXML
    private TableColumn<Team, String> stud1;

    @FXML
    private TableColumn<Team, String> stud2;

    @FXML
    private TableColumn<Team, String> group;

    @FXML
    private TableColumn<Team, Integer> subject;
    

    @FXML
    private TableColumn<Team, String> Edit;
    
    String query = null;
	String query2 = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	  public static int idteam ;
	  public static String grpteam ;
	Team team ;
	ControllerSys cont ;
    ObservableList<Team> TeamsList = FXCollections.observableArrayList();
    
    
    @FXML
    public void getAddView(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddEvaluation.fxml"));
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
    
    
    
    
    @FXML
    public void refreshView() {
        try {
        	TeamsList.clear();
            query = "SELECT DISTINCT CONCAT(etudiant1.nom ,'"+" "+ "', etudiant1.prenom) AS name1 ,CONCAT(etudiant2.nom ,'"+" "+"', etudiant2.prenom) AS name2 , etudiant1.code_binome AS code_bin , binome.id_sujet AS suj , binome.id_grp AS grp FROM etudiant etudiant1 ,etudiant etudiant2 , binome WHERE etudiant1.id_grp IN (SELECT DISTINCT nom_grp FROM groups WHERE groups.id_prof='"+2+"' ) AND etudiant2.code_binome = etudiant1.code_binome AND etudiant2.num_insc != etudiant1.num_insc AND etudiant1.code_binome=  binome.code GROUP BY etudiant1.code_binome ";                   
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { 
            	TeamsList.add(new Team(
                        resultSet.getInt("code_bin"),
                        resultSet.getString("name1"),
                        resultSet.getString("name2"),
                        resultSet.getString("grp"),
                        resultSet.getInt("suj")
                       
                         
                       
                ));
            	tableSub.setItems(TeamsList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
    
    
    private void loadInfo() {
        System.out.println("Loading info");
        connection = DbConnection.createConnection();
        refreshView();
        teamcode.setCellValueFactory(new PropertyValueFactory<>("teamcode"));
        stud1.setCellValueFactory(new PropertyValueFactory<>("std1"));
        stud2.setCellValueFactory(new PropertyValueFactory<>("std2"));
        group.setCellValueFactory(new PropertyValueFactory<>("grp"));
        subject.setCellValueFactory(new PropertyValueFactory<>("sujet"));
    
      

      //add cell of button edit 
        Callback<TableColumn<Team, String>, TableCell<Team, String>> cellFoctory = (TableColumn<Team, String> param) -> {
           // make cell containing buttons
           final TableCell<Team, String> cell = new TableCell<Team, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {

                      // FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                       FontAwesomeIconView editIconn = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                       
              
                  
                       editIconn.setId("edit_iconn");
                       editIconn.setGlyphSize(23);
                       editIconn.setGlyphStyle( "-fx-fill:#FFC764");                       
                       editIconn.setStyleClass("StyleSys.css");
                
                       editIconn.setOnMouseClicked((MouseEvent event) -> {
                           
                         //  team = tableSub.getSelectionModel().getSelectedItem();
                    
                          
                          
                          
                          		
                         
                              try {
                            	  team = tableSub.getSelectionModel().getSelectedItem();
                            	  idteam = team.getTeamcode();
                            	  grpteam = team.getGrp();
                                  Parent parent = FXMLLoader.load(getClass().getResource("AddEvaluation.fxml"));
                                  Scene scene = new Scene(parent);
                                  Stage stage = new Stage();
                                  stage.setScene(scene);
                                  stage.initStyle(StageStyle.UTILITY);
                                  stage.show();
                                  //refreshView();
                              } catch (IOException ex) {
                                  Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              
                             
                    	  /* team = tables.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("AddEvaluation.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
                           }*/

                          
                         
                         
                           
                          
                           

                          

                       });

                       HBox managebtn = new HBox(editIconn);
                       managebtn.setStyle("-fx-alignment:center");
                      // HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIconn, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
        Edit.setCellFactory(cellFoctory);
        tableSub.setItems(TeamsList);


    }
    
    @FXML
    void initialize() {
        loadInfo();
    }

	

}
