package system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import database.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AddSubjectController implements Initializable {
	

    @FXML
    private JFXTextArea title;

    @FXML
    private JFXTextArea field;

    @FXML
    private JFXTextArea descrip;

    @FXML
    private JFXTextArea keyw;

    @FXML
    private JFXTextArea devtool;

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
	int subjectId;
    
    
    
	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO `sujet` (`titre`,`description`,`domaine`,`mots_clés`, `outilsdev`) VALUES (?,?,?,?,?)";


		}else{
			query = "UPDATE `sujet` SET "
					+ "`titre`=?,"
					+ "`description`=?,"
					+ "`domaine`=?,"
					+ "`mots_clés`=?,"
					+ "`outilsdev`= ? WHERE id = '"+subjectId+"'";
		}

	}
    
	@FXML
	private void clean() {
		title.setText(null);
		field.setText(null);
		descrip.setText(null);
		keyw.setText(null);
		devtool.setText(null);

	}
    
    
	@FXML
	public void save (ActionEvent event )  {
		con = DbConnection.createConnection();
		
		String title1 = title.getText(); 
		String field1 = field.getText();
		String descrip1 = 	descrip.getText();
		String keyw1 = 	keyw.getText();
		String devtool1 = 	devtool.getText();
		
		
		//String username = Insertname.getText()+Insertlastname.getText();

		if (title1.isEmpty() || field1.isEmpty() ||  descrip1.isEmpty() || keyw1.isEmpty() || devtool1.isEmpty() ) {

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






			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, title.getText());
			preparedStatement.setString(2, descrip.getText());
			preparedStatement.setString(3, field.getText());
			preparedStatement.setString(4, keyw.getText());
			preparedStatement.setString(5, devtool.getText());
		
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

	
    
    
	void setTextField(int id ,String titlee, String fieldd , String desc, String keyww , String devtooll) {

		subjectId=id;
		title.setText(titlee);
		descrip.setText(desc);
		field.setText(fieldd);
		keyw.setText(keyww);
		devtool.setText(devtooll);
		


	}

	void setUpdate(boolean b) {
		this.update = b;

	}
    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
    
    

}
