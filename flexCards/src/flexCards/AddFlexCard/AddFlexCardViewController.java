package flexCards.AddFlexCard;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Logic.StudySet;
import flexCards.Main;
import flexCards.StudySet.StudySetViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddFlexCardViewController {
	private Main main;
	
	private StudySetViewController parentController;
	
	private StudySet studySet;
	
	private String pathToStudySetFlexcardFile;
	
	@FXML
	private GridPane myGrid;
	
	@FXML
	private Label Field1Label;
	@FXML
	private TextArea Feild1Input;
	
	@FXML
	private Label Field2Label;
	@FXML
	private TextArea Feild2Input;
	
	@FXML
	private Label Field3Label;
	@FXML
	private TextArea Feild3Input;
	
	@FXML
	private Label Field4Label;
	@FXML
	private TextArea Feild4Input;
	
	@FXML
	private Label Field5Label;
	@FXML
	private TextArea Feild5Input;
	
	@FXML
	private Label Field6Label;
	@FXML
	private TextArea Feild6Input;
	
	@FXML
	private Label Field7Label;
	@FXML
	private TextArea Feild7Input;
	
	@FXML
	private Label Field8Label;
	@FXML
	private TextArea Feild8Input;
	
	@FXML
	private Label Field9Label;
	@FXML
	private TextArea Feild9Input;
	
	public void initializeFeildLabels() {
		int index = 0;
		
		ArrayList<String> fieldNames = new ArrayList<String>();
		for(int i = 0; i < studySet.getFeildNames().length; i++) {
			fieldNames.add(studySet.getFeildNames()[i]);
		}
		while(fieldNames.size() != 9) {
			fieldNames.add("");
		}
	
		Field1Label.setText(fieldNames.get(0));
		Field2Label.setText(fieldNames.get(1));
		Field3Label.setText(fieldNames.get(2));
		Field4Label.setText(fieldNames.get(3));
		Field5Label.setText(fieldNames.get(4));
		Field6Label.setText(fieldNames.get(5));
		Field7Label.setText(fieldNames.get(6));
		Field8Label.setText(fieldNames.get(7));
		Field9Label.setText(fieldNames.get(8));
		
		
	}
	
	@FXML
	public void setParentController(StudySetViewController parentController) {
		this.parentController = parentController;
	}
	
	@FXML
	public void addNewFlexCard() throws IOException {
		String[] fieldCombos = studySet.getFieldCombos();
		String heading = "100*0*";
		
		String fields = "<f>";
		if(Feild1Input.getText().equals("") == false)
			fields += Feild1Input.getText() + "*";
		if(Feild2Input.getText().equals("") == false)
			fields += Feild2Input.getText() + "*";
		if(Feild3Input.getText().equals("") == false)
			fields += Feild3Input.getText() + "*";
		if(Feild4Input.getText().equals("") == false)
			fields += Feild4Input.getText() + "*";
		if(Feild5Input.getText().equals("") == false)
			fields += Feild5Input.getText() + "*";
		if(Feild6Input.getText().equals("") == false)
			fields += Feild6Input.getText() + "*";
		if(Feild7Input.getText().equals("") == false)
			fields += Feild7Input.getText() + "*";
		if(Feild8Input.getText().equals("") == false)
			fields += Feild8Input.getText() + "*";
		if(Feild9Input.getText().equals("") == false)
			fields += Feild9Input.getText() + "*";
		
		fields +="</f>";
		
		String footer = "";
		for(int k = 0; k < studySet.getFieldCombos().length; k++) {
			footer +="100*";
		}
		
		String outputLine = heading + fields + footer;
		System.out.println(outputLine);
			
		System.out.println(studySet.getFilePath());
		
		File targetFlexCardsFile = new File(studySet.getFilePath()+"/flexCards.txt");
		Main.append(outputLine, targetFlexCardsFile, false);
		
		parentController.refreshCards();
		
		close();
		
		
	}
	
	@FXML
	public void close() {
		Stage stage = (Stage) Field1Label.getScene().getWindow();
	    stage.close();
	}
	
	public void setStudySet(StudySet studySet) {
		this.studySet = studySet;
	}
}
