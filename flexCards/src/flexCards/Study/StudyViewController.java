package flexCards.Study;

import java.net.URL;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import Logic.LearningObjective;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StudyViewController implements Initializable{
	
	private PriorityQueue<LearningObjective> LOQueue;
	private boolean hasFlipped;
	private LearningObjective currentLO;
	
	
	@FXML
	private Label titleLabel;
	
	@FXML
	private Label fromLabel;
	
	@FXML
	private Label toLabel;
	
	@FXML
	private VBox guessBox;
	
	
	public void setTitle(String title) {
		titleLabel.setText(title);
	}
	
	public void setLearningObjectives(PriorityQueue<LearningObjective> queue) {
		LOQueue = queue;
	}
	
	@FXML
	public void evaluate1(){
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.setKnowledgeIndex(justRemoved.getKnowledgeIndex() -10);//Oversimplification of Algorithm
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
		
		
	}
	
	@FXML
	public void evaluate2(){
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.setKnowledgeIndex(justRemoved.getKnowledgeIndex() -3);//Oversimplification of Algorithm
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate3(){
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.setKnowledgeIndex(justRemoved.getKnowledgeIndex());//Oversimplification of Algorithm
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate4(){
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.setKnowledgeIndex(justRemoved.getKnowledgeIndex()+3);//Oversimplification of Algorithm
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void evaluate5(){
		LearningObjective justRemoved = LOQueue.poll();
		justRemoved.setKnowledgeIndex(justRemoved.getKnowledgeIndex()+10);//Oversimplification of Algorithm
		LOQueue.add(justRemoved);
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
	}
	
	@FXML
	public void flip() {
		toLabel.setVisible(true);
		guessBox.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fromLabel.setText(LOQueue.peek().getFromField());
		fromLabel.setText(LOQueue.peek().getToField());
		guessBox.setVisible(false);
		toLabel.setVisible(false);
		//currentLO = LOQueue.peek();
		
	}
}
