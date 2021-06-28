package etudiant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Controller;
import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import system.AddSessionController;
import etudiant.Session;
import etudiant.SessionController;
//import etudiant.StudentManagememntController;

public class SessionController {
	
	 @FXML
	    private JFXTextField filtersession;
		
		    @FXML
		    private TableColumn<Session, Integer> id;

		    @FXML
		    private TableView<Session> tableGroup;

		    @FXML
		    private TableColumn<Session, Time> hour;

		    @FXML
		    private TableColumn<Session, Date> date;

		    @FXML
		    private TableColumn<Session, String> room;

		    @FXML
		    private TableColumn<Session, String> groupSe;

		    @FXML
		    private TableColumn<Session, String> Edit;
		    
		    @FXML
		    private JFXButton RefSess;
		    @FXML
		    private TableColumn<Session, String> TeacherCol;
		    String query = null;
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    String idd ;
		    String espace = " ";
		    Session session ;
		    
		    
		   /* private static int getDate() throws ClassNotFoundException {
		    	try {
					DbConnection.Setconnection();
					Statement st=(Statement) DbConnection.con.createStatement();
					
			    	String requette="SELECT  date FROM `s` WHERE `username`='"+Controller.typedID+"'";
					ResultSet rs=st.executeQuery(requette);
					while (rs.next()) {
						code=rs.getInt(1);
					}
				} catch (SQLException ex) {

					System.err.println(ex.getMessage());
				}
				return code;
		    }*/
		    void getgrp() {
		    	
		    	   String q1 = "SELECT * FROM etudiant  WHERE username = '"+ Controller.typedID+ "'";

		           try {
		               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

		               while (rs1.next()) {
		                   idd = rs1.getString("id_grp");
		                   
		               }
		           } catch (SQLException ex) {
		               System.out.println(ex);
		           }
		    }
		    
		    ObservableList<Session> SessionList = FXCollections.observableArrayList();
		    @FXML
		    public void refreshView() {
		        try {
		        	getgrp();
		        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		        	Calendar cal = Calendar.getInstance();
		        	
		        	SessionList.clear();
		            query = "SELECT * FROM séance LEFT JOIN salle ON séance.id_salle=salle.id WHERE séance.date >='"+dateFormat.format(cal.getTime())+"' AND séance.id_grp ='"+idd+"'  ";
		            //séance.date>='"+ +"'
		            preparedStatement = connection.prepareStatement(query);
		            resultSet = preparedStatement.executeQuery();

		            while (resultSet.next()) {
		            	SessionList.add(new Session(
		            			 
		                        resultSet.getInt("id"),
		                        resultSet.getTime("heur"),
		                        resultSet.getDate("date" ),
		                        resultSet.getString("num" ),
		                        resultSet.getString("id_grp")
		                        
		            			 ));
		            	tableGroup.setItems(SessionList);
		            }


		        } catch (SQLException throwables) {
		            throwables.printStackTrace();
		        }

		        
		    }
		    private void loadInfo() {
			       
		        connection = DbConnection.createConnection();
		        refreshView();
		        id.setCellValueFactory(new PropertyValueFactory<>("id"));
				hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				room.setCellValueFactory(new PropertyValueFactory<>("room"));
				groupSe.setCellValueFactory(new PropertyValueFactory<>("group"));

		           
		       }
	

		    @FXML
		    void initialize() {
		        loadInfo();
		     // Wrap the ObservableList in a FilteredList (initially display all data).
		        FilteredList<Session> filteredData = new FilteredList<>(SessionList, b -> true);
				
				// 2. Set the filter Predicate whenever the filter changes.
		        filtersession.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(session -> {
						// If filter text is empty, display all persons.
										
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						
						// Compare first name and last name of every person with filter text.
						String lowerCaseFilter = newValue.toLowerCase();
						
						if (session.getGroup().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
							return true; // Filter matches first name.
						} else if (session.getRoom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches last name.
						}
						else  if (session.getDate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches last name.
						}
						else
						    	 return false; // Does not match.
					});
				});
				
				// 3. Wrap the FilteredList in a SortedList. 
				SortedList<Session> sortedData = new SortedList<>(filteredData);
				
				// 4. Bind the SortedList comparator to the TableView comparator.
				// 	  Otherwise, sorting the TableView would have no effect.
				sortedData.comparatorProperty().bind(tableGroup.comparatorProperty());
				
				// 5. Add sorted (and filtered) data to the table.
				tableGroup.setItems(sortedData);
		    }

}
