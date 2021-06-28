package system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import admin.StudentManagememntController;
import application.Controller;
import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import etudiant.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class EvaluationController implements Initializable {
	
	   @FXML
	    private JFXTextField filterField;

	    @FXML
	    private JFXButton AddEv;

	    @FXML
	    private JFXButton RefEv;

	    @FXML
	    private TableView<Evaluation> tableSub;

	    @FXML
	    private TableColumn<Evaluation,String> teamcode;

	    

	    @FXML
	    private TableColumn<Evaluation, Date> DateEV;

	    @FXML
	    private TableColumn<Evaluation, Integer> mark;

	    @FXML
	    private TableColumn<Evaluation, String> ReppP;

	    @FXML
	    private TableColumn<Evaluation, String> AppP;

	    @FXML
	    private TableColumn<Evaluation, String> remark;

	    @FXML
	    private TableColumn<Evaluation, String> Edit;
	    
	    String query = null;
		String query2 = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	    ObservableList<Evaluation> EvaluationList = FXCollections.observableArrayList();
	    
	    
	    
	    
	    @FXML
	    public void getAddView(ActionEvent actionEvent) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("AddEvaluation.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.setResizable(false);
	            stage.show();
	            //refreshView();
	        } catch (IOException ex) {
	            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }
	    
	   /* select binom.Code_b, Etudiant.nom_et, Etudiant.prenom_et,seance.date, evaluation.note, evaluation.remarque, evaluation.evapp, evaluation.evrapport
	    from seance left join evaluation on (evaluation.id_seance=seance.id_seance)
	   left join binom on (evaluation.Code_b=binom.Code_b) left join Etudiant on (Etudiant.Code_b=binom.Code_b)*/
	    
	    /*SELECT binome.code , CONCAT(etudiant.nom ,'"+" "+ "', etudiant.prenom) AS name1 , séance.date , evaluation.note ,evaluation.remarque , evaluation.ev_app , evaluation.ev_rapport "
	            		+ "FROM evaluation LEFT JOIN séance ON (evaluation.id_seance = séance.id ) LEFT JOIN binome ON (evaluation.code_binome = binome.code) LEFT JOIN etudiant ON (etudiant.code_binome = binome.code) " ;
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();*/
	    @FXML
		public void refreshView() {
		/*EvaluationList.clear();
	            query = "SELECT binome.code , CONCAT(etudiant.nom ,'"+" "+ "', etudiant.prenom) AS name1 , séance.date , evaluation.note ,evaluation.remarque , evaluation.ev_app , evaluation.ev_rapport "
	            		+ "FROM evaluation LEFT JOIN séance ON (evaluation.id_seance = séance.id ) LEFT JOIN binome ON (evaluation.code_binome = binome.code) LEFT JOIN etudiant ON (etudiant.code_binome = binome.code) " ;
	            preparedStatemen*/
			
	    	/*SELECT Result.Code_b, LEFT(Result.Students,Len(Result.Students)-1) As "Students",seance.date, evaluation.note, evaluation.remarque, evaluation.evapp, evaluation.evrapport
	    	FROM(SELECT DISTINCT Etudiant2.Code_b,(SELECT UPPER(Etudiant1.nom_et)+" "+LOWER(Etudiant1.prenom_et) + ', ' AS [text()]FROM Etudiant Etudiant1WHERE Etudiant1.Code_b= Etudiant2.Code_bORDER BY Etudiant1.Code_b) [Students]FROM dbo.Students ST2) [Result] right join evaluation on (evaluation.Code_b=Result.Code_b) right join seance on (evaluation.id_seance=seance.id_seance)*/
			
	    	/*SELECT Result.Code_b, LEFT(Result.Students,Len(Result.Students)-1) As Students,seance.date, evaluation.note, evaluation.remarque, evaluation.evapp, evaluation.evrapport\r\n" + 
    		"FROM\r\n" + 
    		"    (\r\n" + 
    		"        SELECT DISTINCT etudiant2.Code_binome ,\r\n" + 
    		"            (\r\n" + 
    		"                SELECT CONCAT(etudiant.nom ,'"+" "+"', etudiant.prenom) AS name1  \r\n" + 
    		"                FROM Etudiant etudiant1\r\n" + 
    		"                WHERE etudiant1.Code_binome= etudiant2.Code_binome\r\n" + 
    		"                ORDER BY etudiant1.Code_binome \r\n" + 
    		"\r\n" + 
    		"            ) Students\r\n" + 
    		"        FROM Students etudiant2\r\n" + 
    		"    ) Result right join evaluation on (evaluation.Code_binome=Result.Code_binome) right join séance on (evaluation.id_seance=séance.id)" ;*/
	    	try {
				EvaluationList.clear();
	            query = "SELECT binome.code , CONCAT(etudiant.nom ,'"+" "+ "', etudiant.prenom) AS name1 , séance.date , evaluation.note ,evaluation.remarque , evaluation.ev_app , evaluation.ev_rapport \"\r\n" + 
	            		"	            		+ \"FROM evaluation LEFT JOIN séance ON (evaluation.id_seance = séance.id ) LEFT JOIN binome ON (evaluation.code_binome = binome.code) LEFT JOIN etudiant ON (etudiant.code_binome = binome.code) ";
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) { 
	            	EvaluationList.add(new Evaluation(
	                        resultSet.getInt("code"),             
	                        resultSet.getDate("date"),
	                        resultSet.getDouble("note"),
	                        resultSet.getString("remarque"),
	                        resultSet.getString("ev_app"),
	                        resultSet.getString("ev_rapport")
	                        
	                      
	                       
	                ));
	            	tableSub.setItems(EvaluationList);
	            }

	            

	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }

		}
	    int code_binome;
		String stdName;
		Date date ;
		double note;
		String ev_rapport;
		String ev_app;
		String remarque;
	    
	    private void loadInfo() {
			connection = DbConnection.createConnection();
			refreshView();
			teamcode.setCellValueFactory(new PropertyValueFactory<>("code"));
			
			DateEV.setCellValueFactory(new PropertyValueFactory<>("date"));
			mark.setCellValueFactory(new PropertyValueFactory<>("note"));
			ReppP.setCellValueFactory(new PropertyValueFactory<>("ev_rapport"));
			AppP.setCellValueFactory(new PropertyValueFactory<>("ev_app"));
			remark.setCellValueFactory(new PropertyValueFactory<>("remarque"));
		


			//add cell of button edit 
		/*	Callback<TableColumn<Team, String>, TableCell<Team, String>> cellFoctory = (TableColumn<Team, String> param) -> {
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
							FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
							FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
							deleteIcon.setId("my_icon");
							deleteIcon.setStyleClass("StyleSys.css");
							editIcon.setId("edit_icon");
							editIcon.setStyleClass("StyleSys.css");
							deleteIcon.setOnMouseClicked((MouseEvent event) -> {
								try {
									team = tableGroup.getSelectionModel().getSelectedItem();
									Alert alert = new Alert(AlertType.CONFIRMATION);
									alert.setTitle("Delete Student");
									alert.setHeaderText("Are you sure want to delete your team?");
									Optional<ButtonType> option = alert.showAndWait();
									if (option.get() == ButtonType.OK) {
										team = tableGroup.getSelectionModel().getSelectedItem();
										query = "UPDATE `etudiant` SET `code_binome` = null WHERE `etudiant`.`code_binome` ="+team.getCode();
										connection = DbConnection.createConnection();
										preparedStatement = connection.prepareStatement(query);
										preparedStatement.execute();
										query2 = "DELETE FROM binome WHERE code ="+team.getCode();
										connection = DbConnection.createConnection();
										preparedStatement = connection.prepareStatement(query2);
										preparedStatement.execute();
										refreshView();}
									else if (option.get() == ButtonType.CANCEL) {
										System.out.println("nothing");
									} 
								} catch (SQLException ex) {
									Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
								}

							});
							

							HBox managebtn = new HBox(deleteIcon);
							managebtn.setStyle("-fx-alignment:center");	
							setGraphic(managebtn);
							setText(null);
						}
					}

				};

				return cell;
			};
			Edit.setCellFactory(cellFoctory);
			tableGroup.setItems(TeamList);*/
		}
		
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			loadInfo();
			
			
		}

}
