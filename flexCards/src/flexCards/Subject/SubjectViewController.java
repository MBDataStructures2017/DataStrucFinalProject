package flexCards.Subject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Subject;
import flexCards.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SubjectViewController implements Initializable{
	
	private Main main;
	private Subject subject;
	
	@FXML
	private ListView studySets;
	
	
	@FXML
	private Label subjectTitle;
	
	@FXML
	private Label testLabel;
	
	
	
	//Launches Selected Study Set
	public void goStudySet() throws IOException {
		main.showStudySetView(subject, studySets.getSelectionModel().getSelectedItem().toString());
	}
	
	@FXML
	public void printSelection() {
		System.out.println(studySets.getSelectionModel().getSelectedItem().toString());
	}
	
	@FXML
	public void setTitle(String subjectName) {
		subjectTitle.setText(subjectName);
	}
	
	@FXML void goAddStudySet() throws IOException {
		main.showAddStudySet(subject, this);
	}
	
	//Initializes and populates subject (population is still as WIP)
	//Displays all of subjects studySets in the studySets ListView
	public void subjectInit(String subjectName) {
		subject = new Subject(subjectName);
		for(int i = 0; i < subject.getStudySets().size(); i++) {
			studySets.getItems().add(subject.getStudySets().get(i).getName());
		}
	}
	
	@FXML
	public void startStudy() throws IOException {
		main.showMainStudyView(subjectTitle.getText(), subject.getMainQueue(), subject);
	}
	
	@FXML
	public void goBack() throws IOException {
		main.showMainItems();
	}
	
	@FXML
	public void refreshStudySets() {
		studySets.getItems().clear();
		subjectInit(subject.getName());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
}
