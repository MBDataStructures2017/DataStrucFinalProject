package flexCards.AddStudySet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import Logic.Subject;
import flexCards.Main;
import flexCards.Subject.SubjectViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudySetViewController {
	private Main main;
	
	private Subject subject;
	
	private SubjectViewController parentController;
	
	@FXML
	private TextField studySetName;
	
	@FXML
	private TextField field1Name;
	@FXML
	private TextField field1LearningObjectives;
	@FXML
	private TextField field2Name;
	@FXML
	private TextField field2LearningObjectives;
	@FXML
	private TextField field3Name;
	@FXML
	private TextField field3LearningObjectives;
	@FXML
	private TextField field4Name;
	@FXML
	private TextField field4LearningObjectives;
	@FXML
	private TextField field5Name;
	@FXML
	private TextField field5LearningObjectives;
	@FXML
	private TextField field6Name;
	@FXML
	private TextField field6LearningObjectives;
	@FXML
	private TextField field7Name;
	@FXML
	private TextField field7LearningObjectives;
	@FXML
	private TextField field8Name;
	@FXML
	private TextField field8LearningObjectives;
	@FXML
	private TextField field9Name;
	@FXML
	private TextField field9LearningObjectives;
	
	@FXML
	private Button submitButton;
	
	private String pathToSubject;
	
	@FXML
	public void setSubject(Subject parentSubject) {
		this.subject = parentSubject;
		System.out.println(subject.getFilePath());
		System.out.println(subject.getFilePathToStudySetsFile());
	}
	
	public void setParentController(SubjectViewController controller) {
		this.parentController = controller;
	}
	
	
	
	@FXML
	public void addNewStudySet() throws IOException {
		//Check For invalid states
		if(studySetName.getText().equals("")) {
			return;//Fail State
		}
		if(field1Name.getText().equals("") || field2Name.getText().equals("")) {
			return;//Fail State
		}
		if(field1LearningObjectives.getText().equals("") && field2LearningObjectives.getText().equals("")) {
			return;//Fail state
		}
		
		/////////Build Line String////////
		String heading = studySetName.getText() + "*0*0*0*";
		String fields = "<f>";
		if(field1Name.getText().equals("") == false)
			fields += field1Name.getText()+"*";
		if(field2Name.getText().equals("") == false)
			fields += field2Name.getText()+"*";
		if(field3Name.getText().equals("") == false)
			fields += field3Name.getText()+"*";
		if(field4Name.getText().equals("") == false)
			fields += field4Name.getText()+"*";
		if(field5Name.getText().equals("") == false)
			fields += field5Name.getText()+"*";
		if(field6Name.getText().equals("") == false)
			fields += field6Name.getText()+"*";
		if(field7Name.getText().equals("") == false)
			fields += field7Name.getText()+"*";
		if(field8Name.getText().equals("") == false)
			fields += field8Name.getText()+"*";
		if(field9Name.getText().equals("") == false)
			fields += field9Name.getText()+"*";
		
		fields += "</f>";
		
		
		String KI = "<KI>*";
		if(field1LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F1", field1LearningObjectives.getText());
		if(field2LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F2", field2LearningObjectives.getText());
		if(field3LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F3", field3LearningObjectives.getText());
		if(field4LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F4", field4LearningObjectives.getText());
		if(field5LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F5", field5LearningObjectives.getText());
		if(field6LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F6", field6LearningObjectives.getText());
		if(field7LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F7", field7LearningObjectives.getText());
		if(field8LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F8", field8LearningObjectives.getText());
		if(field9LearningObjectives.getText().equals("") == false)
			KI += fieldComboFormater("F9", field9LearningObjectives.getText());
		
		KI +="</KI>";
		
		String outputLine = heading+fields+KI;
		
		System.out.println(outputLine);
		System.out.println(subject.getFilePathToStudySetsFile());
		
			
		File targetStudySetFile = new File(subject.getFilePathToStudySetsFile());
		Main.append(outputLine, targetStudySetFile, false);
		System.out.println(new File(subject.getFilePath()+"/"+studySetName.getText()).mkdir());
		File fileObject = new File(subject.getFilePath()+"/"+studySetName.getText()+"/flexCards.txt");
		
		//Creates empty file with specified name in current project directory.
		PrintWriter outputStream = null;
        try {
			outputStream = new PrintWriter(new FileOutputStream(subject.getFilePath()+"/"+studySetName.getText()+"/flexCards.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        parentController.refreshStudySets();
		
		
		close();
		
		
	}
	
	private static String fieldComboFormater(String thisFieldKey, String inputLine) {
		String output = "";
		String[] targetFields = inputLine.split("\\, ");
		for(int i = 0; i < targetFields.length; i++) {
			output += thisFieldKey+"->"+targetFields[i]+"*";
		}
		return output;
	}
	
	@FXML
	public void close() {
		Stage stage = (Stage) studySetName.getScene().getWindow();
	    stage.close();
	}
}
