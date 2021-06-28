package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

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

public class AddSessionController implements Initializable {

	public  String typedLocation = null;
	int id ;
	 Connection con ;
		Statement st ;
		ResultSet rs = null;
		PreparedStatement preparedStatement;
		String query;
		int sessionId;
		private boolean update;
	  @FXML
	    private JFXTimePicker time;

	    @FXML
	    private JFXDatePicker dateS;

	    @FXML
	    private ComboBox<String> salle;

	    @FXML
	    private ComboBox<String> grpSess;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;

	

		public static ObservableList<String> loadComboGroup() throws ClassNotFoundException {

			ObservableList<String> List = FXCollections.observableArrayList();
			try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				String qe="SELECT `nom_grp` FROM `groups`  ";
				ResultSet rs=st.executeQuery(qe);
				while (rs.next()) {
					List.add(rs.getString(1));
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			return List;
		}
		public static ObservableList<String> loadComboGroupp() throws ClassNotFoundException {

			ObservableList<String> List = FXCollections.observableArrayList();
			try {
				DbConnection.Setconnection();
				Statement st=(Statement) DbConnection.con.createStatement();
				String q="SELECT `num` FROM `salle`";
				ResultSet rs=st.executeQuery(q);
				while (rs.next()) {
					List.add(rs.getString(1));
				}
			} catch (SQLException ex) {

				System.err.println(ex.getMessage());
			}
			return List;
		}
	    
		 void getTextField() {
		    	
		    	typedLocation = salle.getValue();
		    	   String q1 = "SELECT * FROM salle  WHERE num = '" + typedLocation + "'";

		           try {
		               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

		               while (rs1.next()) {
		                   id = rs1.getInt("id");
		                   
		               }
		           } catch (SQLException ex) {
		               System.out.println(ex);
		           }
		    }
		 
		 
		 private void getQuery() {

				if (update == false) {

					query = "INSERT INTO séance (`heur`,`date`,`id_salle`, `id_grp`) VALUES (?,?,?,?)";


				}else{
					query = "UPDATE `séance` SET "
							+ "`heur`=?,"
							+ "`date`=?,"
							+ "`id_salle`=?,"		
							+ "`id_grp`=? WHERE id = '"+sessionId+"'";
							
							
				}

			}
		 
		 @FXML
			private void clean() {
		    	time.setValue(null);
				dateS.setValue(null);
				salle.setValue(null);
				grpSess.setValue(null);
			}
		 
		 @FXML
			public void save (ActionEvent event )  {
				con = DbConnection.createConnection();
				
				
				
				String grp = grpSess.getValue();
				String sallee = salle.getValue();				
				
				//String username = Insertname.getText()+Insertlastname.getText();

				if (  grp.isEmpty() ||  sallee.isEmpty()  ) {

					Notifications notif2 = Notifications.create();
					Image img2 = new Image("/images/no-credit-card.png");

					notif2.title("Try Again");
					notif2.text("Not entered value");
					notif2.darkStyle();
					notif2.hideAfter(Duration.seconds(5));
					notif2.position(Pos.BOTTOM_RIGHT);
					notif2.graphic(new ImageView(img2) );
					notif2.show();

				}else 


			   getQuery();
				//insert();
				
				try {


					getTextField();
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setString(1, String.valueOf(time.getValue())+" ");
					preparedStatement.setString(2, String.valueOf(dateS.getValue()));
					preparedStatement.setInt(3, id);
					preparedStatement.setString(4, grpSess.getValue());
					preparedStatement.execute();


					clean();
					
					Notifications.create()
					.title("About Us")
					.text("success")
					.position(Pos.CENTER)
					.showInformation();



				} catch (Exception ex) {
					System.out.println(ex);
				}

			}
		 
		 
		void setTextField(int id ,LocalTime time1, LocalDate date,  String salle1,  String grp1 ) {

		    	sessionId=id;
				time.setValue(time1);
				dateS.setValue(date);
				salle.setValue(salle1);
				grpSess.setValue(grp1);

				// InsertGroup.setValue(group);
			}
		    void setUpdate(boolean b) {
				this.update = b;

			}
		 
		 
		 
		 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			grpSess.setItems(loadComboGroup());
			grpSess.setVisibleRowCount(4);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			salle.setItems(loadComboGroupp());
			salle.setVisibleRowCount(4);
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	

}
