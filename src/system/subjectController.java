package system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

public class subjectController {
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
    private JFXButton AddSub;

    @FXML
    private JFXButton RefSub;
    
    

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Subject subject = null;
    String file_name = "C:\\Users\\faycal\\eclipse-workspace\\Gestion des etudiants\\src\\images\\theme.pdf";
    Document document = new Document();
    
    
    
    ObservableList<Subject> SubjectList = FXCollections.observableArrayList();
    
    @FXML
    public void getAddView(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddSubject.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
            //refreshView();
        } catch (IOException ex) {
            Logger.getLogger(subjectController.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Loading info");
        connection = DbConnection.createConnection();
        refreshView();
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleSub.setCellValueFactory(new PropertyValueFactory<>("title"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        Keyword.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        DevTools.setCellValueFactory(new PropertyValueFactory<>("devTools"));
        
      //add cell of button edit 
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

                       FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                       FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                       
              
                       deleteIcon.setId("my_icon");
                       deleteIcon.setStyleClass("StyleSys.css");
                       editIcon.setId("edit_icon");
                       editIcon.setStyleClass("StyleSys.css");
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           try {
                        	   Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Delete Student");
								alert.setHeaderText("Are you sure want to delete this subject?");
								Optional<ButtonType> option = alert.showAndWait();
								if (option.get() == ButtonType.OK) {
                        	   subject = tableSub.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM sujet WHERE id  ="+subject.getId();
                               connection = DbConnection.createConnection();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               refreshView(); }
								else if (option.get() == ButtonType.CANCEL) {
									System.out.println("nothing");;
								} 
                           } catch (SQLException ex) {
                               Logger.getLogger(StudentManagememntController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                          

                         

                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                    	   subject = tableSub.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("AddSubject.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(subjectController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AddSubjectController addSubjectController = loader.getController();
                           addSubjectController.setUpdate(true);
                           addSubjectController.setTextField( subject.getId(),subject.getTitle(),subject.getDescription(), subject.getField(),subject.getKeywords(),subject.getDevTools());
                           Parent parent = loader.getRoot();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           

                          

                       });

                       HBox managebtn = new HBox(editIcon , deleteIcon);
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
        tableSub.setItems(SubjectList);


    }
    
    
    
    
    
    public void generatePdf() throws SQLException {
    	
    	try {
    		PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();
	    	
	    	
	    	connection = DbConnection.createConnection();
	    	query = "SELECT * FROM sujet";
	    	preparedStatement = connection.prepareStatement(query);
	    	resultSet = preparedStatement.executeQuery();
	    	Rectangle small = new Rectangle(290,100);
	        Font smallfont = new Font(FontFamily.HELVETICA, 10);
	    	int i=0;
	    	while (resultSet.next()) {
	    		
	    		i++;
	    		PdfPTable table = new PdfPTable(2);
	    		// Creating an Area Break    
	    		Paragraph para = new Paragraph ("Thème 0"+i); 
	    		document.add(para); 
	    		Paragraph para2 = new Paragraph ("    ");
	    		document.add(para2);
	            table.setTotalWidth(new float[]{ 160, 400 });
	            table.setLockedWidth(true);
	            PdfContentByte cb = writer.getDirectContent();
	            // first row
	            PdfPCell cell = new PdfPCell();
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell = new PdfPCell(new Phrase("Titre"));
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(resultSet.getString("titre"), smallfont));
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            table.addCell(cell);
	            
	            cell.setFixedHeight(45);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell = new PdfPCell(new Phrase("description"));
	            table.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Phrase(resultSet.getString("description"), smallfont));
	            cell.setFixedHeight(45);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            table.addCell(cell);
	            
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell = new PdfPCell(new Phrase("domaine"));
	            table.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Phrase(resultSet.getString("domaine"), smallfont));
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            table.addCell(cell);
	            
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell = new PdfPCell(new Phrase("mots clés"));
	            table.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Phrase(resultSet.getString("mots_clés"), smallfont));
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            table.addCell(cell);
	            
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell = new PdfPCell(new Phrase("Outils de devlepment"));
	            table.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Phrase(resultSet.getString("outilsdev"), smallfont));
	            cell.setFixedHeight(30);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            table.addCell(cell);
	            document.setMarginMirroringTopBottom(true);
	            /*cell = new PdfPCell(new Phrase("and something else here", smallfont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            table.addCell(cell);*/
	            document.add(table);
	    		
	    		
	    		
	    	}
	    	
	    	
	    	
	    	document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    public void initialize() 
    {
    	 loadInfo();
    	try {
			generatePdf();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
