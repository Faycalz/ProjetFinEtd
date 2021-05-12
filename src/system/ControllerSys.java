package system;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import system.FxmlLoaderr;

public class ControllerSys implements Initializable{


	@FXML
	private PieChart pieChart;

    @FXML
    private BorderPane mainPane;
    
	@FXML
	
	/*void initialize() {
		 FxmlLoaderr object = new FxmlLoaderr();
	     Pane view = object.getPage("Dashboard");
	     mainPane.setCenter(view);
		
	     
		}*/
	public void iniPieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Theme 1", 50),
				new PieChart.Data("Theme 2", 25),
				new PieChart.Data("Theme 3", 50),
				new PieChart.Data("Theme 4", 30)
				);
		pieChart.setData(pieChartData);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
		 FxmlLoaderr object = new FxmlLoaderr();
	     Pane view = object.getPage("Dashboard");
	     mainPane.setCenter(view);
	     
	     
	    
	     
	}
	
	}
	


