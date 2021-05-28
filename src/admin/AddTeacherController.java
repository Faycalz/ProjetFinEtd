package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AddTeacherController implements Initializable {

	    @FXML
	    private JFXTextField nameT;

	    @FXML
	    private JFXTextField lastNameT;


	    @FXML
	    private JFXComboBox<String> Degree;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;
	    Connection con ;
		Statement st ;
		ResultSet rs = null;
		PreparedStatement preparedStatement;
		String query;
		private boolean update;
		int teacherId;
		int id;
		
		
		
			       
			    
			

	    private void getQuery() {

			if (update == false) {

				query = "INSERT INTO prof (`nom`,`prenom`,`grade`, `username`,`password`) VALUES (?,?,?,?,?)";


			}else{
				query = "UPDATE `prof` SET "
						+ "`nom`=?,"
						+ "`prenom`=?,"
						+ "`grade`=?,"		
						+ "`username`=?,"
						+ "`password`=?  WHERE id = '"+teacherId+"'";
						
			}

		}
	    @FXML
		private void clean() {
	    	nameT.setText(null);
	    	lastNameT.setText(null);
			Degree.setValue(null);
		}
	    void generatePass() {
	    	
	    	
	    	   String q1 = "SELECT id+1 AS idd FROM prof ORDER BY id DESC LIMIT 1";

	           try {
	               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

	               while (rs1.next()) {
	                   id = rs1.getInt("idd");
	                   
	                   
	               }
	           } catch (SQLException ex) {
	               System.out.println(ex);
	           }
	    }
	
	    
	    
	    @FXML
		public void save (ActionEvent event )  {
			con = DbConnection.createConnection();
			
			String name = nameT.getText();
			String lastname = lastNameT.getText();
			String degree = Degree.getValue();
			
			
			//String username = Insertname.getText()+Insertlastname.getText();

			if (name.isEmpty() || lastname.isEmpty() ||  degree.isEmpty()  ) {

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




				generatePass();

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, nameT.getText());
				preparedStatement.setString(2, lastNameT.getText());
				preparedStatement.setString(3, Degree.getValue());
				preparedStatement.setString(4, nameT.getText()+id);
				preparedStatement.setString(5, nameT.getText()+"sciences");
				
				
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
	    void setTextField(int id ,String name, String lastname,  String degree) {

	    	teacherId=id;
			nameT.setText(name);
			lastNameT.setText(lastname);
			Degree.setValue(degree);

			// InsertGroup.setValue(group);


		}
	    void setUpdate(boolean b) {
			this.update = b;

		}


	    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> list = FXCollections.observableArrayList("assistante","professor");
		Degree.setItems(list);
	}

}
