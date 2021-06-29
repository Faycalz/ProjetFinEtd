package etudiant;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Controller;
import database.DbConnection;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class dashcontroller implements Initializable {
	String name = null ;
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = null ;
    String query2 = null;
    String query3 = null;
    String query4= null ;
    String query5= null ;
    String query6= null ;
    String query7 = null;
    String query8 = null;
    String query9 = null;
    String query10 = null;
    String query11 = null;
    String query12 = null;
    String query13 = null;
    
    int count4,count5,count6,count7;
   
    int theme1 ,theme2,theme3,theme4;
    int avg1 ,avg2 ,avg3 , avg4, avg5 ,avg6;
	@FXML
	private PieChart pieChart;
	
    @FXML
    private Label Name;
    
    @FXML
    private javafx.scene.chart.BarChart<String, Number> BarChart;
    
    @FXML
    private Label teacherNum;

    @FXML
    private Label StudentNum;

    @FXML
    private Label GroupNum;

	
	public void iniPieChart() {
		updateData();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				
				
				new PieChart.Data("Theme 1",theme1),
				new PieChart.Data("Theme 2", theme2),
				new PieChart.Data("Theme 3",theme3),
				new PieChart.Data("Theme 4", theme4)
				
				
				
				);
		
		
	    
        

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                
                                
                        		   data.getName() ,": " , data.pieValueProperty().intValue(), " student" 
                        )
                )
        );
        pieChart.setData(pieChartData);
	
	}
	
	
	
	
    
public void updateData() {
       
    	System.out.println(Controller.typedID);
        query = "SELECT * FROM etudiant WHERE username = '" + Controller.typedID + "'";

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
        //----------------- count number of subject for subjects statistics -----------------
        query4 = "SELECT COUNT(*) FROM binome WHERE id_sujet = '" +1+ "'";
        try {
            ResultSet rs = DbConnection.executeQuery(query4, DbConnection.createConnection());
            while (rs.next()) {
  
               theme1 = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        query5 = "SELECT COUNT(*) FROM binome WHERE id_sujet = '" +2+ "'";
        try {
            ResultSet rs = DbConnection.executeQuery(query5, DbConnection.createConnection());
            while (rs.next()) {
       
                theme2 = rs.getInt(1);
  
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        query6 ="SELECT COUNT(*) FROM binome WHERE id_sujet = '" +3+ "'";
        try {
            ResultSet rs = DbConnection.executeQuery(query6, DbConnection.createConnection());
            while (rs.next()) {
                
               
               
              theme3= rs.getInt(1);
             
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        query7 = "SELECT COUNT(*) FROM binome WHERE id_sujet = '" +4+ "'";
        try {
            ResultSet rs = DbConnection.executeQuery(query7, DbConnection.createConnection());
            while (rs.next()) {
              
              theme4 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
          query8 = "SELECT AVG(evaluation.note) AS notee  FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp01"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query8, DbConnection.createConnection());
            while (rs.next()) {
              
              avg1 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        query9 = "SELECT AVG(evaluation.note) AS notee  FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp02"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query9, DbConnection.createConnection());
            while (rs.next()) {
              
              avg2 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        query10 = "SELECT AVG(evaluation.note) AS notee  FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp03"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query10, DbConnection.createConnection());
            while (rs.next()) {
              
              avg3 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        query11 = "SELECT AVG(evaluation.note) AS notee FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp04"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query11, DbConnection.createConnection());
            while (rs.next()) {
              
              avg4 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
       
        query12 = "SELECT AVG(evaluation.note) AS notee FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp05"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query12, DbConnection.createConnection());
            while (rs.next()) {
              
              avg5 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        query13 = "SELECT AVG(evaluation.note) AS notee FROM evaluation , groups , séance WHERE evaluation.id_seance=séance.id AND séance.id_grp IN (SELECT nom_grp FROM groups WHERE groups.nom_grp ='"+"grp06"+"' )  ";
        try {
            ResultSet rs = DbConnection.executeQuery(query13, DbConnection.createConnection());
            while (rs.next()) {
              
              avg6 = rs.getInt(1);
               
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniPieChart();
		updateData();
		
		XYChart.Series<String, Number> serie = new XYChart.Series<>();
		serie.getData().add(new XYChart.Data<>("grp01",avg1));
		serie.getData().add(new XYChart.Data<>("grp02",avg2));
		serie.getData().add(new XYChart.Data<>("grp03",avg3));
		serie.getData().add(new XYChart.Data<>("grp04",avg4));
		serie.getData().add(new XYChart.Data<>("grp05",avg5));
		serie.getData().add(new XYChart.Data<>("grp06",avg6));
		BarChart.getData().add(serie);
		
		
	}
	
	
	

}
