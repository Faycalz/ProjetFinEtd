package etudiant;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;

import admin.AddStudentController;
import admin.StudentManagememntController;
import application.Controller;
import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

public class TeamController implements Initializable {
	@FXML
	private Label list;

	@FXML
	private TableView<Team> tableGroup;

	@FXML
	private TableColumn<Team, Integer> teamc;

	@FXML
	private TableColumn<Team, String> stud1;

	@FXML
	private TableColumn<Team, String> stud11;

	@FXML
	private TableColumn<Team, String> stud2;

	@FXML
	private TableColumn<Team, String> stud22;

	@FXML
	private TableColumn<Team, String> subj;

	@FXML
	private TableColumn<Team, String> grpt;
    @FXML
    private TableColumn<Team, String> Edit;

	@FXML
	private JFXButton chooseStud;

	@FXML
	private JFXButton RefT;
	static String test = null;

	String query = null;
	String query2 = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Team team = null;
	static String	name;
	ObservableList<Team> TeamList = FXCollections.observableArrayList();


	public static String getGrp() throws ClassNotFoundException {

		try {
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();

			String requette="SELECT username  FROM `etudiant` WHERE code_binome IS NULL AND username = '" + Controller.typedID + "'";
			ResultSet rs=st.executeQuery(requette);
			while (rs.next()) {
				test="null";
			}
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
		System.out.println(test);
		return test;
	}

	@FXML
	public void getAddView(ActionEvent actionEvent) throws ClassNotFoundException {
		try {
			getGrp();
			if (test == "null" ) {
			Parent parent = FXMLLoader.load(getClass().getResource("ChooseTeam.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.show();
			}
			else {
				Notifications notif2 = Notifications.create();
				Image img2 = new Image("/images/protection.png");
				notif2.title("Team ");
				notif2.text("You already have a team");
				notif2.darkStyle();
				notif2.hideAfter(Duration.seconds(5));
				notif2.position(Pos.BOTTOM_RIGHT);
				notif2.graphic(new ImageView(img2) );
				notif2.show();
			}
			//refreshView();
		} catch (IOException ex) {
			Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static String getName() throws ClassNotFoundException {
    	
    	try {
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();
			
	    	String requette="SELECT  CONCAT(etudiant.nom ,'"+" "+ "', etudiant.prenom) AS name1 FROM `etudiant` WHERE `username`='"+Controller.typedID+"'";
			ResultSet rs=st.executeQuery(requette);
			while (rs.next()) {
				name=rs.getString(1);
			}
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
		return name;
    }
	
	@FXML
	public void refreshView() {
		try {
			TeamList.clear();
			query = "SELECT CONCAT(etudiant.nom ,'"+" "+ "', etudiant.prenom) AS name1,binome.code , binome.id_grp , binome.id_sujet FROM  etudiant , binome WHERE binome.code=etudiant.code_binome AND etudiant.username != '" + Controller.typedID + "'  AND etudiant.code_binome=(SELECT etudiant2.code_binome from  etudiant etudiant2   where etudiant2.code_binome=etudiant.code_binome AND etudiant2.username = '" + Controller.typedID + "'  ) ";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			

			try {
				getName();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (resultSet.next()) {
				
				TeamList.add(new Team(
						resultSet.getInt("code"),
						name,
						resultSet.getString("name1"),
						resultSet.getString("id_sujet"),
						resultSet.getString("id_grp")
						));
				tableGroup.setItems(TeamList);
			}


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
	private void loadInfo() {
		connection = DbConnection.createConnection();
		refreshView();
		teamc.setCellValueFactory(new PropertyValueFactory<>("code"));
		stud1.setCellValueFactory(new PropertyValueFactory<>("std1"));
		stud2.setCellValueFactory(new PropertyValueFactory<>("std2"));
		subj.setCellValueFactory(new PropertyValueFactory<>("sujet"));
		grpt.setCellValueFactory(new PropertyValueFactory<>("grp"));
	


		//add cell of button edit 
		Callback<TableColumn<Team, String>, TableCell<Team, String>> cellFoctory = (TableColumn<Team, String> param) -> {
			// make cell containing buttons
			final TableCell<Team, String> cell = new TableCell<Team, String>() {
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
								team = tableGroup.getSelectionModel().getSelectedItem();
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Delete Student");
								alert.setHeaderText("Are you sure want to delete your team?");
								Optional<ButtonType> option = alert.showAndWait();
								if (option.get() == ButtonType.OK) {
									team = tableGroup.getSelectionModel().getSelectedItem();
									query = "UPDATE `etudiant` SET `code_binome` = null WHERE `etudiant`.`code_binome` ="+team.getCode();
									connection = DbConnection.createConnection();
									preparedStatement = connection.prepareStatement(query);
									preparedStatement.execute();
									query2 = "DELETE FROM binome WHERE code ="+team.getCode();
									connection = DbConnection.createConnection();
									preparedStatement = connection.prepareStatement(query2);
									preparedStatement.execute();
									refreshView();}
								else if (option.get() == ButtonType.CANCEL) {
									System.out.println("nothing");
								} 
							} catch (SQLException ex) {
								Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						

						HBox managebtn = new HBox(deleteIcon);
						managebtn.setStyle("-fx-alignment:center");	
						setGraphic(managebtn);
						setText(null);
					}
				}

			};

			return cell;
		};
		Edit.setCellFactory(cellFoctory);
		tableGroup.setItems(TeamList);
	}
	
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadInfo();
		try {
			getGrp();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
