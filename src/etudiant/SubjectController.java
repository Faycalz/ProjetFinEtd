package etudiant;

import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;

import application.Controller;
import database.DbConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import system.AddSubjectController;
import system.Student;
import system.StudentManagememntController;
import system.Subject;
import system.subjectController;

public class SubjectController {
	@FXML
    private TableView<Subject> tableSub;

    @FXML
    private TableColumn<Subject, Integer> Id;
    @FXML
    private TableColumn<Subject, String> titleSub;

    @FXML
    private TableColumn<Subject, String> descrip;

    @FXML
    private TableColumn<Subject, String> field;

    @FXML
    private TableColumn<Subject, String> Keyword;

    @FXML
    private TableColumn<Subject, String> DevTools;
    @FXML
    private TableColumn<Subject, String> Edit;

    @FXML
    private JFXButton RefSub;
    
    @FXML
    private JFXButton chooseSub;
    
    @FXML
    private Label list;
    static int code;
    static String test =null ;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Subject subject;
    String file_name = "C:\\Users\\faycal\\eclipse-workspace\\Gestion des etudiants\\src\\images\\theme.pdf";
    ObservableList<Subject> SubjectList = FXCollections.observableArrayList();
    
    
    private static int getCode() throws ClassNotFoundException {
    	try {
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();
			
	    	String requette="SELECT  code_binome FROM `etudiant` WHERE `username`='"+Controller.typedID+"'";
			ResultSet rs=st.executeQuery(requette);
			while (rs.next()) {
				code=rs.getInt(1);
			}
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}
		return code;
    }
    public static String getSujet() throws ClassNotFoundException {
    	getCode();
		try {
			
			DbConnection.Setconnection();
			Statement st=(Statement) DbConnection.con.createStatement();

			String requette2="SELECT code  FROM `binome` WHERE id_sujet IS NULL AND code = '" + code + "'";
			ResultSet rs=st.executeQuery(requette2);
			while (rs.next()) {
				test="null2";
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
        	getCode();
        	getSujet();
        	
			if (test == "null2" ) {
            Parent parent = FXMLLoader.load(getClass().getResource("ChooseSubject.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();}
			else if(test==null){
				Notifications notif2 = Notifications.create();
				Image img2 = new Image("/images/exam.png");
				notif2.title("Team ");
				notif2.text("You choose subject already");
				notif2.darkStyle();
				notif2.hideAfter(Duration.seconds(5));
				notif2.position(Pos.BOTTOM_RIGHT);
				notif2.graphic(new ImageView(img2) );
				notif2.show();
			}
           
        } catch (IOException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    

    }
    
    
    @FXML
    public void refreshView() {
        try {
        	SubjectList.clear();
            query = "SELECT * FROM sujet";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { 
            	SubjectList.add(new Subject(
            			resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("description"),
                        resultSet.getString("domaine"),               
                        resultSet.getString("mots_clés"),
                        resultSet.getString("outilsdev")
                      
                       
                ));
                tableSub.setItems(SubjectList);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    
    }
    private void loadInfo() {
	       
    	 connection = DbConnection.createConnection();
         refreshView();
         Id.setCellValueFactory(new PropertyValueFactory<>("id"));
         titleSub.setCellValueFactory(new PropertyValueFactory<>("title"));
         descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
         field.setCellValueFactory(new PropertyValueFactory<>("field"));
         Keyword.setCellValueFactory(new PropertyValueFactory<>("keywords"));
         DevTools.setCellValueFactory(new PropertyValueFactory<>("devTools"));
       
         Callback<TableColumn<Subject, String>, TableCell<Subject, String>> cellFoctory = (TableColumn<Subject, String> param) -> {
             // make cell containing buttons
             final TableCell<Subject, String> cell = new TableCell<Subject, String>() {
                 @Override
                 public void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);
                     //that cell created only on non-empty rows
                     if (empty) { 
                         setGraphic(null);
                         setText(null);

                     } else {

                         FontAwesomeIconView pdfIcon = new FontAwesomeIconView(FontAwesomeIcon.FILE_PDF_ALT);
                        
                         
                
                         pdfIcon.setId("my_icon2");
                         pdfIcon.setStyleClass("StyleSys.css");
                        
                         pdfIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                       
                        	 File file = new File(file_name);
                        	 try {
                                 Desktop.getDesktop().open(file);
                             } catch (IOException e) {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                             }
                         
                           

                         });
                        

                         HBox managebtn = new HBox( pdfIcon);
                         managebtn.setStyle("-fx-alignment:center");
                         HBox.setMargin(pdfIcon, new Insets(2, 2, 0, 3));
                        // HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                         setGraphic(managebtn);

                         setText(null);

                     }
                 }

             };

             return cell;
         };
          Edit.setCellFactory(cellFoctory);
          tableSub.setItems(SubjectList);
       }
    
    @FXML
    void initialize() {
        loadInfo();
        
   
    }

    
    
    
    
}


