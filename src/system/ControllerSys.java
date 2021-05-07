package system;



import javafx.application.Platform;
import javafx.fxml.FXML;

public class ControllerSys {

	@FXML
	public void onClose() {
		Platform.exit();
	}
	

}
