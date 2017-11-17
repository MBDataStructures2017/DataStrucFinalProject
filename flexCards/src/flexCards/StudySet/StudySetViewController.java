package flexCards.StudySet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.StudySet;
import Logic.Subject;
import flexCards.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StudySetViewController implements Initializable{
	
	private Main main;
	private StudySet studySet;
	
	@FXML
	private ListView cards;
	
	@FXML
	private Label title;
	
	
	
	
	@FXML
	public void printSelection() {
		System.out.println(cards.getSelectionModel().getSelectedItem().toString());
	}
	
	@FXML
	public void printCardData() {
		int cardIndex = cards.getSelectionModel().getSelectedIndex();
		studySet.getCards().get(cardIndex).printCard();
	}
	
	@FXML
	public void setTitle(String inputTitle) {
		title.setText(inputTitle);
	}
	
	public void initStudySet(Subject s, String studySetName) {
		//Loops through Current Subject, finds the target studySet
		for(int i = 0; i < s.getStudySets().size(); i++) {
			if(s.getStudySets().get(i).getName().equals(studySetName)) {
				studySet = s.getStudySets().get(i);
			}
		}
		//Adds all cards from the studyset to the cards ListView
		for(int j = 0; j < studySet.getTempCardTitles().size(); j++) {
			cards.getItems().add(studySet.getTempCardTitles().get(j));
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
	}
}
