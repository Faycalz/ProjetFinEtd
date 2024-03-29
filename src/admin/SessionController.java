package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import system.Session;

public class SessionController {

	@FXML
	private TableView<Session> Table;
	@FXML
	private TableColumn<Session, Integer> id;

	@FXML
	private TableColumn<Session, Time> hour;

	@FXML
	private TableColumn<Session, Date> date;

	@FXML
	private TableColumn<Session, String> room;

	@FXML
	private TableColumn<Session, String> groupSe;

	@FXML
	private TableColumn<Session, String> Edit;

	@FXML
	private JFXButton AddSess;

	@FXML
	private JFXButton RefSess;

	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Session session = null;


	ObservableList<Session> SessionList = FXCollections.observableArrayList();
	@FXML
	public void getAddView(ActionEvent actionEvent) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddSession.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.show();
			//refreshView();
		} catch (IOException ex) {
			Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	@FXML
	public void refreshView() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        	Calendar cal = Calendar.getInstance();
        	
        	SessionList.clear();
            query = "SELECT * FROM s�ance LEFT JOIN salle ON s�ance.id_salle=salle.id WHERE s�ance.date >='"+dateFormat.format(cal.getTime())+"'  ";
          
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				SessionList.add(new Session(
						resultSet.getInt("id"),
						resultSet.getTime("heur"),
						resultSet.getDate("date"),
						resultSet.getString("num"),
						resultSet.getString("id_grp")

						));
				Table.setItems(SessionList);
			}


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}


	private void loadInfo() {
		System.out.println("Loading info");
		connection = DbConnection.createConnection();
		refreshView();
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		room.setCellValueFactory(new PropertyValueFactory<>("room"));
		groupSe.setCellValueFactory(new PropertyValueFactory<>("group"));



		//add cell of button edit 
		Callback<TableColumn<Session, String>, TableCell<Session, String>> cellFoctory = (TableColumn<Session, String> param) -> {
			// make cell containing buttons
			final TableCell<Session, String> cell = new TableCell<Session, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					//that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
						FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);


						deleteIcon.setId("my_icon");
						deleteIcon.setStyleClass("StyleSys.css");
						editIcon.setId("edit_icon");
						editIcon.setStyleClass("StyleSys.css");
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {

							try {
								session = Table.getSelectionModel().getSelectedItem();
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Delete Session");

								alert.setHeaderText("Are you sure want to delete this session ? ");
								Optional<ButtonType> option = alert.showAndWait();
								if (option.get() == ButtonType.OK) {
									session = Table.getSelectionModel().getSelectedItem();
									query = "DELETE FROM s�ance WHERE id  ="+session.getId();
									connection = DbConnection.createConnection();
									preparedStatement = connection.prepareStatement(query);
									preparedStatement.execute();
									refreshView();}
								else if (option.get() == ButtonType.CANCEL) {
									System.out.println("nothing");
								} 


							} catch (SQLException ex) {
								Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
							}





						});
						editIcon.setOnMouseClicked((MouseEvent event) -> {

							session = Table.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader ();
							loader.setLocation(getClass().getResource("AddSession.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
							}

							AddSessionController addSessionController = loader.getController();
							addSessionController.setUpdate(true);
							addSessionController.setTextField(session.getId(),session.getHour().toLocalTime(), session.getDate().toLocalDate(),session.getRoom() , session.getGroup());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();




						});

						HBox managebtn = new HBox(editIcon,deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};
		Edit.setCellFactory(cellFoctory);
		Table.setItems(SessionList);


	}


	@FXML
	void initialize() {
		loadInfo();

	}
}
