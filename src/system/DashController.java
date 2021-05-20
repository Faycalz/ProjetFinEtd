package system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import application.Controller;
import database.DbConnection;

public class DashController  implements Initializable{

	String name = null ;
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = null;
    String query2 = null;
    String query3 = null;
    int tnum=6;
    
	@FXML
	private PieChart pieChart;
    @FXML
    private Label Name;
    @FXML
    private Label teacherNum;

    @FXML
    private Label StudentNum;

    @FXML
    private Label GroupNum;

	
	public void iniPieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Theme 1", 50),
				new PieChart.Data("Theme 2", 25),
				new PieChart.Data("Theme 3", 50),
				new PieChart.Data("Theme 4", 30)
				);
		pieChart.setData(pieChartData);
	}
	
	
	public void updateData() {
       
        System.out.println(Controller.typedID);
        query = "SELECT * FROM prof WHERE username = '" + Controller.typedID + "'";

        try {
            ResultSet rs = DbConnection.executeQuery(query, DbConnection.createConnection());
            while (rs.next()) {
                
               name = rs.getString("nom");
               String prenom =rs.getString("prenom");
                Name.setText(name+" "+prenom);
                Name.setVisible(true);
    
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }public void updateDataa() {
       
        
        query2 = "SELECT COUNT(*) FROM etudiant";

        try {
            ResultSet rs = DbConnection.executeQuery(query2, DbConnection.createConnection());
            while (rs.next()) {
                
               
                //tnum = tnum +1;
               int count2 = rs.getInt(1);
               StudentNum.setText(String.valueOf(count2));
               StudentNum.setVisible(true);
    
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        query = "SELECT COUNT(*) FROM prof";
        try {
            ResultSet rs = DbConnection.executeQuery(query, DbConnection.createConnection());
            while (rs.next()) {
                
               
                //tnum = tnum +1;
               int count = rs.getInt(1);
               teacherNum.setText(String.valueOf(count));
               teacherNum.setVisible(true);
    
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        query3 = "SELECT COUNT(*) FROM groups";
        try {
            ResultSet rs = DbConnection.executeQuery(query3, DbConnection.createConnection());
            while (rs.next()) {
                
               
                //tnum = tnum +1;
               int count3 = rs.getInt(1);
               GroupNum.setText(String.valueOf(count3));
               GroupNum.setVisible(true);
    
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateData();
		iniPieChart();
		updateDataa();
		
	}
	
	
	

}
