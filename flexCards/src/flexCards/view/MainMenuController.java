package flexCards.view;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Logic.Subject;
import flexCards.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class MainMenuController implements Initializable{
	
	private Main main;
	
	
	@FXML
	private ChoiceBox subjectSelector;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	//Initializes the subjectSelector TextBox with all subjects from Subjects.txt
	public void addSubjectsText() {
		ArrayList<String> subjects = Main.txtToStringArrayList("Data/Subjects.txt");
		subjectSelector.getItems().clear();
		for(int i = 0; i < subjects.size(); i++) {
			subjectSelector.getItems().add(subjects.get(i));
		}
	}
	
	
	
	public void goSubject() throws IOException {
		//Calls showSubjectView with currently Selected item from subjectSelector
		if (Files.isDirectory(Paths.get("Data/" + subjectSelector.getSelectionModel().getSelectedItem().toString()))) {
			main.showSubjectView(subjectSelector.getSelectionModel().getSelectedItem().toString()); 
		} 
		//main.showSubjectView(subjectSelector.getSelectionModel().getSelectedItem().toString());
	}
	
	
	@FXML
	public void goNewSubject() throws IOException {
		main.showAddSubject(this);
	}
}
