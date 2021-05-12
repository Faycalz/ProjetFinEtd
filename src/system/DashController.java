package system;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class DashController  implements Initializable{

	@FXML
	private PieChart pieChart;
	
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
	public void initialize(URL location, ResourceBundle resources) {
		iniPieChart();
		
	}

	

}
