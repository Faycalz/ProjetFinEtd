package etudiant;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Controller;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

public class ChooseSubjectController {
	  @FXML
	    private JFXTextField Insertname;

	    @FXML
	    private JFXTextField Insertlastname;

	    @FXML
	    private JFXComboBox<Integer> InsertSubject;

	    @FXML
	    private JFXComboBox<String> InsertTeam;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;
	    Connection con ;
	    Statement st ;
		ResultSet rs = null;
		int id;
		int id_binome;
		Team team;
		static int code;
		static String test ;
		SubjectController sub ;
		PreparedStatement preparedStatement;
	    String query=null;
	    String file_name = "C:\\Users\\faycal\\eclipse-workspace\\Gestion des etudiants\\src\\images\\theme.pdf";
	    
	    public void pdf() {
	    	
	    	File file = new File(file_name);
       	 try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    }
	    public static ObservableList<Integer> loadComboGroup() throws ClassNotFoundException {

			ObservableList<Integer> List = FXCollections.observableArrayList();
			try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				
				String qe="SELECT id FROM sujet order by id ";
				
				ResultSet rs=st.executeQuery(qe);
				while (rs.next()) {
					List.add(rs.getInt(1));
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			
			return List;
			
		}
	    private static int getCode() throws ClassNotFoundException {
	    	try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				
		    	String requette="SELECT  code_binome FROM `etudiant` WHERE `username`='"+Controller.typedID+"'";
				ResultSet rs=st.executeQuery(requette);
				while (rs.next()) {
					code=rs.getInt(1);
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			return code;
	    }
	    
	    public static String getSujet() throws ClassNotFoundException {
	    	getCode();
	    	
			try {
				
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();

				String requette="SELECT code  FROM `binome` WHERE id_sujet IS NULL AND code = '" +code+ "'";
				ResultSet rs=st.executeQuery(requette);
				while (rs.next()) {
					test="null";
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			System.out.println(test);
			return test;
		}
	    
	    
	    
	    private void getQuery()  {
	    	try {
				getCode();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	query = "UPDATE binome SET"
					+ "`id_sujet`=? WHERE code= '"+code+"'";


		
	}
	    
	    @FXML
		public void save (ActionEvent event )  {
			con = DbConnection.createConnection();
			
		
		   
			getQuery();
		
		  
		   
			try {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, InsertSubject.getValue());	
				preparedStatement.execute();

				Notifications.create()
				.title("About Us")
				.text("success")
				.position(Pos.CENTER)
				.showInformation();



			} catch (Exception ex) {
				System.out.println(ex);
			}
		
				  
			   
				

				 
		}
	 
	    
	    
	    
	    
	    
	    
	    public void initialize() {
			// TODO Auto-generated method stub
	    	
			try {
				
				InsertSubject.setItems(loadComboGroup());
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	    
}
