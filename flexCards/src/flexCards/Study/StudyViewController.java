package flexCards.Study;

import java.io.IOException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import Logic.LearningObjective;
import Logic.StudySet;
import flexCards.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StudyViewController implements Initializable{
	
	private PriorityQueue<LearningObjective> LOQueue;
	private boolean hasFlipped;
	private LearningObjective currentLO;
	private StudySet studySet;
	
	
	@FXML
	private Label titleLabel;
	
	@FXML
	private Label fromLabel;
	
	@FXML
	private Label fromLabelName;
	
	@FXML
	private Label toLabel;
	
	@FXML
	private Label toLabelName;
	
	@FXML
	private VBox guessBox;
	
	
	public void setTitle(String title) {
		titleLabel.setText(title);
	}
	
	public void setLearningObjectives(PriorityQueue<LearningObjective> queue) {
		LOQueue = queue;
		fromLabel.setText(queue.peek().getFromField());
		fromLabelName.setText(queue.peek().getFromFieldName());
		toLabelName.setText(queue.peek().getToFieldName());
		
		System.out.println(LOQueue.peek().getFromField());
	}
	
	@FXML
	public void evaluate1() throws IOException{
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.evaluate1();
		
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabelName.setText(LOQueue.peek().getFromFieldName());
		toLabelName.setText(LOQueue.peek().getToFieldName());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
		
		
	}
	
	@FXML
	public void evaluate2() throws IOException{
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.evaluate2();
		
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabelName.setText(LOQueue.peek().getFromFieldName());
		toLabelName.setText(LOQueue.peek().getToFieldName());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate3() throws IOException{
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.evaluate3();
		
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabelName.setText(LOQueue.peek().getFromFieldName());
		toLabelName.setText(LOQueue.peek().getToFieldName());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate4() throws IOException{
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.evaluate4();
		
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabelName.setText(LOQueue.peek().getFromFieldName());
		toLabelName.setText(LOQueue.peek().getToFieldName());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate5() throws IOException{
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.evaluate5();
		
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabelName.setText(LOQueue.peek().getFromFieldName());
		toLabelName.setText(LOQueue.peek().getToFieldName());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void flip() {
		toLabel.setText(LOQueue.peek().getToField());
		toLabel.setVisible(true);
		guessBox.setVisible(true);
	}
	
	public void setStudySet(StudySet studySet) {
		this.studySet = studySet;
	}
	

	
	@FXML
	public void goBack() throws IOException {
		Main.showStudySetView(studySet.getParentSubject(), studySet.getName());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//fromLabel.setText(LOQueue.peek().getFromField());
		//fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
		//currentLO = LOQueue.peek();
		
	}
}
