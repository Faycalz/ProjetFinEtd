package system;

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

public class AddGroupController implements Initializable {


    @FXML
    private JFXTextField InsertID;

    @FXML
    private JFXTextField InsertName;

    @FXML
    private JFXComboBox<String > InsertTeacher;

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
    String GroupName;
    public  String typedTeacher = null;
    int id ;
    
    public static ObservableList<String> loadComboGroup() throws ClassNotFoundException {

		ObservableList<String> List = FXCollections.observableArrayList();
		try {
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();
			String qe="SELECT username  FROM `prof`";
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
    	
    	typedTeacher = InsertTeacher.getValue();
    	   String q1 = "SELECT * FROM prof  WHERE username = '" + typedTeacher + "'";

           try {
               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

               while (rs1.next()) {
                   id = rs1.getInt("id");
                   
               }
           } catch (SQLException ex) {
               System.out.println(ex);
           }
    }
    
    


	void setTextField(String name, String capacity,String teacher) {

		
		GroupName=name;
		InsertID.setText(name) ;
		InsertID.setEditable(false); 
		InsertName.setText(capacity);
		InsertTeacher.setValue(teacher);

		// InsertGroup.setValue(group);


	}
	void setUpdate(boolean b) {
		this.update = b;

	}
	
	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO groups (`nom_grp`,`capacité`,`id_prof`) VALUES (?,?,?)";


		}else{
			query = "UPDATE `groups` SET "
					+ "`nom_grp`=?,"
					+ "`capacité`=?,"
					
					+ "`id_prof`= ? WHERE  nom_grp= '"+GroupName+"'";
		}

	}
	
	@FXML
	public void save (ActionEvent event )  {
		con = DbConnection.createConnection();
		
		String teacher = InsertTeacher.getValue();
		String name = InsertID.getText();
		
		String cap = InsertName.getText();
		
		
		//String username = Insertname.getText()+Insertlastname.getText();

		if (name.isEmpty() || cap.isEmpty()   ) {

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
			preparedStatement.setString(1, InsertID.getText());
			preparedStatement.setString(2, InsertName.getText());
			preparedStatement.setInt(3, id);
			
			
			preparedStatement.execute();


			//clean();
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
			InsertTeacher.setItems(loadComboGroup());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	
	
	
	
	
}
	
	