package flexCards.AddSubject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import flexCards.Main;
import flexCards.view.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSubjectViewController {
	
	private Main main;
	
	private MainMenuController parentController; 
	
	@FXML
	private TextField inputLine;
	
	@FXML
	private Button submitButton;
	
	@FXML
	public void addNewSubject() throws IOException {
		if(inputLine.getText().equals("") == false) {
			File subjects = new File("Data/Subjects.txt");
			Main.append(inputLine.getText(), subjects, false);
			new File("Data/"+inputLine.getText()).mkdir();
			File fileObject = new File("Data/"+inputLine.getText()+"/StudySets.txt");
			
			//Creates empty file with specified name in current project directory.
			PrintWriter outputStream = null;
	        try {
				outputStream = new PrintWriter(new FileOutputStream("Data/"+inputLine.getText()+"/StudySets.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		parentController.addSubjectsText();
		close();
	}
	
	@FXML
	public void setParentController( MainMenuController controller) {
		this.parentController = controller;
	}
	
	@FXML
	public void close() {
		Stage stage = (Stage) inputLine.getScene().getWindow();
	    stage.close();
	}
}
