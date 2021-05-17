package system;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AddStudentController implements Initializable{

	 
	    
	   
	 
	
	
	 @FXML
	    private JFXTextField Insertname;

	    @FXML
	    private JFXTextField Insertlastname;

	    @FXML
	    private JFXDatePicker Insertbirthday;

	    @FXML
	    private JFXRadioButton male;

	    @FXML
	    private ToggleGroup gendergroup;

	    @FXML
	    private JFXRadioButton female;

	    @FXML
	    private JFXComboBox<String> InsertGroup;
	    
	   

	   
	    Connection con ;
	    Statement st ;
	    ResultSet rs = null;
	    PreparedStatement preparedStatement;
	    String query;
	   
	  
	  
	  
	    
	    public static ObservableList<String> loadComboGroup() throws ClassNotFoundException {

	        ObservableList<String> List = FXCollections.observableArrayList();
	        try {
	            DbConnection.Setconnection();
	            Statement st=(Statement) DbConnection.con.createStatement();
	            String qe="SELECT `id` FROM `group`";
	            ResultSet rs=st.executeQuery(qe);
	            while (rs.next()) {
	                List.add(rs.getString(1));
	            }
	        } catch (SQLException ex) {

	            System.err.println(ex.getMessage());
	        }
	        return List;
	    }
	
	   
	    
	    


		@Override
		public void initialize(java.net.URL location, ResourceBundle resources) {
			
			try {
				InsertGroup.setItems(loadComboGroup());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
						
			
	
		}
		//RadioButton selectedRadioButton = (RadioButton) gendergroup.getSelectedToggle();
		
		private void getQuery() {
		       query = "INSERT INTO etudiant (`nom`,`prenom`,`date_naissance`,`id_groupe`, `username`,`password`) VALUES (?,?,?,?,?,?)";

		    }
			
		private void insert() {
		
	        try {

	            preparedStatement = con.prepareStatement(query);
	            preparedStatement.setString(1, Insertname.getText());
	            preparedStatement.setString(2, Insertlastname.getText());
	            preparedStatement.setString(3, Insertbirthday.getValue().toString());
	            
	            preparedStatement.setString(4, InsertGroup.getValue().toString());
	            preparedStatement.setString(5, Insertname.getText());
	            preparedStatement.setString(6, Insertlastname.getText());
	           // preparedStatement.setString(7, selectedRadioButton.getText());
	            preparedStatement.execute();

	        } catch (Exception ex) {
	            System.out.println(ex);
	        }

	    }
		
		
		
		
		
		@FXML
		public void save (ActionEvent event )  {
			con = DbConnection.createConnection();
		
			String name = Insertname.getText();
			String lastname = Insertlastname.getText();
			String bday = Insertbirthday.getValue().toString();
			//String gender = selectedRadioButton.getText();
			String grpname = InsertGroup.getValue().toString();
			//String username = Insertname.getText()+Insertlastname.getText();
			
			if (name.isEmpty() || lastname.isEmpty() ||  bday.isEmpty() || grpname.isEmpty()) {
				
				Notifications notif2 = Notifications.create();
        		Image img2 = new Image("/images/no-credit-card.png");
        		
        		notif2.title("Try Again");
        		notif2.text("Not entered value");
        		notif2.darkStyle();
        		notif2.hideAfter(Duration.seconds(5));
        		notif2.position(Pos.BOTTOM_RIGHT);
        		notif2.graphic(new ImageView(img2) );
        		notif2.show();
				
			}else {
				
				
				 getQuery();
				 insert();
				 
				 Notifications.create()
                 .title("About Us")
                 .text("success")
                 .position(Pos.CENTER)
                 .showInformation();
				
				
			}
		
			
		}
		
		
		
		
		
		 



	    
	    
	    
	    
	    
	   
	   
	    
}
