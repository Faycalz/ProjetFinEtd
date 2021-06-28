package system;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Controller;
import database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AddEvaluationController implements Initializable {

 

    @FXML
    private JFXTextField InsertMark;

    @FXML
    private JFXTextField InsertRemark;

    @FXML
    private JFXTextField InsertRapp;

    @FXML
    private JFXTextField InsertApp;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton reset;
    @FXML
    private JFXComboBox<String> InsertDate1;

    @FXML
    private TableView<Evaluation> tableSub;

    @FXML
    private TableColumn<Evaluation, Integer> teamcode;

    @FXML
    private TableColumn<Evaluation, String> DateEV;

    @FXML
    private TableColumn<Evaluation, String> mark;

    @FXML
    private TableColumn<Evaluation, String> ReppP;

    @FXML
    private TableColumn<Evaluation, String> AppP;

    @FXML
    private TableColumn<Evaluation, String> remark;

    @FXML
    private TableColumn<Evaluation, String> Edit;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    TeamController teame ;
    String seanceid;
    int ids;
    int id ;
    ObservableList<Evaluation> TeamsList = FXCollections.observableArrayList();
    @FXML
    public void refreshView() {
        try {
        	TeamsList.clear();
            query = "SELECT evaluation.note AS notee  , evaluation.ev_rapport AS evrap , evaluation.ev_app AS evapp , evaluation.id AS evid , séance.date AS dat , evaluation.remarque AS rem FROM evaluation , séance WHERE evaluation.code_binome = '"+ TeamController.idteam+"' AND evaluation.id_seance=séance.id ";                   
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { 
            	TeamsList.add(new Evaluation(
                        resultSet.getInt("evid"),
                        resultSet.getDate("dat"),
                        resultSet.getDouble("notee"),
                        resultSet.getString("evrap"),
                        resultSet.getString("evapp"),
                        resultSet.getString("rem")
                        
                       
                         
                       
                ));
            	tableSub.setItems(TeamsList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
    void getTextFieldd() {
    	
    	seanceid = InsertDate1.getValue().toString();
    	   String q1 = "SELECT * FROM séance  WHERE  séance.date = '"+seanceid+"'";

           try {
               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

               while (rs1.next()) {
                   ids = rs1.getInt("id");
                   
               }
           } catch (SQLException ex) {
               System.out.println(ex);
           }
    }
    
    void getTextField() {
    	
    	   String q1 = "SELECT * FROM prof  WHERE username = '"+ Controller.typedID+ "'";

           try {
               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

               while (rs1.next()) {
                   id = rs1.getInt("id");
                   
               }
           } catch (SQLException ex) {
               System.out.println(ex);
           }
    }
    
    
    private void loadInfo() {
		connection = DbConnection.createConnection();
		refreshView();
		teamcode.setCellValueFactory(new PropertyValueFactory<>("code"));
		DateEV.setCellValueFactory(new PropertyValueFactory<>("date"));
		mark.setCellValueFactory(new PropertyValueFactory<>("note"));
		ReppP.setCellValueFactory(new PropertyValueFactory<>("ev_rapport"));
		AppP.setCellValueFactory(new PropertyValueFactory<>("ev_app"));
		remark.setCellValueFactory(new PropertyValueFactory<>("remarque"));
    }
    
    
    
    public static ObservableList<String> loadComboGroup() throws ClassNotFoundException {

		ObservableList<String> List = FXCollections.observableArrayList();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        	Calendar cal = Calendar.getInstance();
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();
			String qe="SELECT  séance.date FROM `séance` WHERE séance.date >='"+dateFormat.format(cal.getTime())+"' AND séance.id_grp='"+ TeamController.grpteam+"'";
			ResultSet rs=st.executeQuery(qe);
			while (rs.next()) {
				List.add(rs.getString(1));
			}
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
		return List;
	}

    
    
    private void getQuery() {

    

			query = "INSERT INTO evaluation (`note`,`remarque`,`ev_app`,`ev_rapport`, `code_binome`,`id_prof`,`id_seance`) VALUES (?,?,?,?,?,?,?)";
    	
    	}
    	
    

	@FXML
	private void clean() {
		InsertDate1.setValue(null);
		InsertMark.setText(null);
		InsertRemark.setText(null);
		InsertRapp.setText(null);
		InsertApp.setText(null);
		
	}
	
	
	
	@FXML
	public void save (ActionEvent event )  {
		connection = DbConnection.createConnection();
		
		String date = InsertDate1.getValue().toString();
		String mark = InsertMark.getText();
		String remark = InsertRemark.getText();
		String rapp = InsertRapp.getText();
		String app = InsertApp.getText();
		
		//String username = Insertname.getText()+Insertlastname.getText();

		
		if (date.isEmpty()  || mark.isEmpty() ||  remark.isEmpty() || rapp.isEmpty() || app.isEmpty() ) {

			Notifications notif2 = Notifications.create();
			Image img2 = new Image("/images/notification.png");

			notif2.title("Try Again");
			notif2.text("Not entered value");
			notif2.darkStyle();
			notif2.hideAfter(Duration.seconds(5));
			notif2.position(Pos.BOTTOM_RIGHT);
			notif2.graphic(new ImageView(img2) );
			notif2.show();

		}
		
		/*else if (InsertMark.getText().matches("[0-9_] ") == false)
		{
			Notifications notif2 = Notifications.create();
			Image img2 = new Image("/images/keyboard.png");

			notif2.title("Try Again");
			notif2.text("Number in note");
			notif2.darkStyle();
			notif2.hideAfter(Duration.seconds(5));
			notif2.position(Pos.BOTTOM_RIGHT);
			notif2.graphic(new ImageView(img2) );
			notif2.show();
		}*/
		else

		getTextFieldd();
	   getTextField();
	   getQuery();
		//insert();
		
		try {






			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, Double.valueOf(InsertMark.getText()));
			preparedStatement.setString(2, InsertRemark.getText());
			preparedStatement.setString(3, InsertApp.getText());
			preparedStatement.setString(4, InsertRapp.getText());
			preparedStatement.setInt(5, TeamController.idteam);
			preparedStatement.setInt(6, id);
			preparedStatement.setInt(7, ids);
			
			
			preparedStatement.execute();


			clean();
			Notifications.create()
			.title("About Us")
			.text("success")
			.position(Pos.CENTER)
			.showInformation();

			refreshView();

		} catch (Exception ex) {
			System.out.println(ex);
		}

		
	


	}
    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			loadInfo();
			try {
				InsertDate1.setItems(loadComboGroup());
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

}
