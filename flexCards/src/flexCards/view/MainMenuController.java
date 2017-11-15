package flexCards.view;

import java.io.IOException;
import java.net.URL;
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
	public void addSubjectsText(ArrayList<String> subjects) {
		for(int i = 0; i < subjects.size(); i++) {
			subjectSelector.getItems().add(subjects.get(i));
		}
	}
	
	
	
	public void goSubject() throws IOException {
		System.out.println("Clicked OK");
		//Calls showSubjectView with currently Selected item from subjectSelector
		main.showSubjectView(subjectSelector.getSelectionModel().getSelectedItem().toString());
	}
}
