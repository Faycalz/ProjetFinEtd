package etudiant;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Controller;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ChooseTeamController implements Initializable {
	
	  @FXML
	    private ComboBox<String> innsertname;

	    @FXML
	    private ComboBox<String> innsertname1;
	    
	    @FXML
	    private JFXTextField codeSTU;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;
	    String espacee = " ";
	    static String grp = null;
	    Connection con ;
	    Statement st ;
		ResultSet rs = null;
		int id;
		int id_binome;
		PreparedStatement preparedStatement;
	    String query=null;
	    String query2=null;
	    String query3=null;
	    String secondId= null ;
	    public static String getGrp() throws ClassNotFoundException {
	    	
	    	try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				
		    	String requette="SELECT  `id_grp`FROM `etudiant` WHERE `username`='"+Controller.typedID+"'";
				ResultSet rs=st.executeQuery(requette);
				while (rs.next()) {
					grp=rs.getString(1);
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			return grp;
	    }
	    
	    public static ObservableList<String> loadComboGroup() throws ClassNotFoundException {

			ObservableList<String> List = FXCollections.observableArrayList();
			try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				getGrp();
				String qe="SELECT username  FROM `etudiant` WHERE code_binome IS NULL AND username != '" + Controller.typedID + "' AND id_grp='"+grp+"' ";
				//CONCAT(etudiant.nom ,'" + " "+ "', etudiant.prenom)
				ResultSet rs=st.executeQuery(qe);
				while (rs.next()) {
					List.add(rs.getString(1));
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			
			return List;
			
		}
	    
	    void getTextField() {
	    	
	    	secondId = innsertname.getValue();
	    	   String q1 = "SELECT * FROM etudiant  WHERE username = '" + secondId + "'";

	           try {
	               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

	               while (rs1.next()) {
	                   id = rs1.getInt("num_insc");
	                   
	               }
	           } catch (SQLException ex) {
	               System.out.println(ex);
	           }
	    }
	    
	    void getTeamId() {
	    	
	    	
	    	   String q1 = "SELECT code AS idd FROM binome ORDER BY code DESC LIMIT 1";

	           try {
	               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

	               while (rs1.next()) {
	                   id_binome = rs1.getInt("idd");
	                   
	                   
	               }
	           } catch (SQLException ex) {
	               System.out.println(ex);
	           }
	    }
	    
		 private void getQuery() {

				

					query = "INSERT INTO binome (id_grp) VALUES (?)";


				
			}
		 private void getQuery2() {

				

				query2 = "UPDATE etudiant SET"
						+"`code_binome`=? WHERE username = '"+Controller.typedID+"'";


			
		}
		 private void getQueryy() {

				

				query3 = "UPDATE etudiant SET"
						+ "`code_binome`=? WHERE num_insc= '"+id+"'";


			
		}
		 
		 @FXML
			public void save (ActionEvent event )  {
				con = DbConnection.createConnection();
				
				
				
							
				
				//String username = Insertname.getText()+Insertlastname.getText();

				

			

			   getQuery();
			  
			   
				try {

					preparedStatement = con.prepareStatement(query);
					
					preparedStatement.setString(1, grp);	
					preparedStatement.execute();
	
					Notifications.create()
					.title("About Us")
					.text("success")
					.position(Pos.CENTER)
					.showInformation();



				} catch (Exception ex) {
					System.out.println(ex);
				}
				getTeamId();
				getTextField();
				   getQuery2();
					  
				   
					try {

						preparedStatement = con.prepareStatement(query2);
						preparedStatement.setInt(1,id_binome);
							
						preparedStatement.execute();
		
						Notifications.create()
						.title("About Us")
						.text("success")
						.position(Pos.CENTER)
						.showInformation();
					} catch (Exception ex) {
						System.out.println(ex);
					}

					 getQueryy();
					  
					   
						try {

							preparedStatement = con.prepareStatement(query3);
							preparedStatement.setInt(1,id_binome);
								
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
		 
		 

	
		 
	    
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			try {
				innsertname.setItems(loadComboGroup());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	

}
