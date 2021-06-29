package etudiant;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import admin.FxmlLoaderr;
import application.Controller;
import database.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FinalMarkController  implements Initializable{
	
	
	  @FXML
	    private Label N;

	    @FXML
	    private Label name;

	    @FXML
	    private Label LN;

	    @FXML
	    private Label lastname;

	    @FXML
	    private Label FM;

	    @FXML
	    private Label fmark;

	    @FXML
	    private Label listF;
	    public int id ;
	    
	    void getTextField() {
	    	
	    	   String q1 = "SELECT * FROM etudiant  WHERE username = '"+ Controller.typedID+ "'";

	           try {
	               ResultSet rs1 = DbConnection.executeQuery(q1, DbConnection.createConnection());

	               while (rs1.next()) {
	                   id = rs1.getInt("code_binome");
	                   
	               }
	           } catch (SQLException ex) {
	               System.out.println(ex);
	           }
	    }
	    
	    public void updateData() {
	    	
	    	System.out.println(Controller.typedID); 
	    	String query = "SELECT * FROM etudiant WHERE username = '" + Controller.typedID + "'";
	    	 getTextField();
	        try {
	            ResultSet rs = DbConnection.executeQuery(query, DbConnection.createConnection());
	            while (rs.next()) {
	                
	               String namee = rs.getString("nom");
	              
	                name.setText(namee);
	                name.setVisible(true);
	    
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        String query2 = "SELECT * FROM etudiant WHERE username = '" + Controller.typedID + "'";

	        try {
	            ResultSet rs = DbConnection.executeQuery(query2, DbConnection.createConnection());
	            while (rs.next()) {
	                
	               String namee2 = rs.getString("prenom");
	              
	               lastname.setText(namee2);
	               lastname.setVisible(true);
	    
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        String query3 = "SELECT AVG(evaluation.note) AS notee FROM evaluation   WHERE evaluation.code_binome ='"+id+"'";              

	         try {
	            ResultSet rs = DbConnection.executeQuery(query3, DbConnection.createConnection());
	            while (rs.next()) {
	                
	               int name3 = rs.getInt("notee");
	              
	                fmark.setText(name3+"");
	                fmark.setVisible(true);
	    
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        
	         
	         
	       
	    }
	    @Override
     	public void initialize(URL arg0, ResourceBundle arg1) {
     		// TODO Auto-generated method stub
     		

        	 updateData();
     	}


}
