package Logic;

import java.util.ArrayList;
import java.util.PriorityQueue;

import flexCards.Main;

public class StudySet {
	private String filePath;
	
	private Subject parentSubject;
	
	private PriorityQueue<LearningObjective> LOs;
	
	private ArrayList<FlexCard> cards;
	
	private String name;
	
	private String dataLine;
	
	private ArrayList<String> tempCardTitles;
	
	public StudySet(String name, String filePath, String dataLine, Subject parentSubject) {
		this.name = name;
		this.parentSubject = parentSubject;
		this.dataLine = dataLine;
		this.filePath = filePath;
		
		LOs = new PriorityQueue<LearningObjective>();
		
		this.filePath = filePath + name + "/";
		tempCardTitles = new ArrayList<String>();
		cards = new ArrayList<FlexCard>();
		
		ArrayList<String> tempCardLines = Main.txtToStringArrayList(filePath+"/flexCards.txt");
		//Creating new Flex Cards from each line of this study set's flexCards.txt file.
		for(int i = 1; i < tempCardLines.size(); i++) {
			String tempCardTitle = tempCardLines.get(i).substring(tempCardLines.get(i).indexOf("<f>")+3, tempCardLines.get(i).indexOf("</f>"));
			tempCardTitle = tempCardTitle.substring(0, tempCardTitle.indexOf("*"));
			tempCardTitles.add(tempCardTitle);
			cards.add( new FlexCard(dataLine, tempCardLines.get(i), this, i + 1));
		}
		
		//Initializing the LearningObjective priority queue.
		for(int l = 0; l < cards.size(); l++) {
			for(int g = 0; g <cards.get(l).getLearningObjectives().size(); g++) {
				LOs.offer(cards.get(l).getLearningObjectives().get(g));
			}
		}
		
		
		
		
		
	}
	
	public ArrayList<String> getTempCardTitles() {
		return tempCardTitles;
	}
	public String getName() {
		return name;
	}
	
	public String[] getFeildNames() {
		return dataLine.substring(dataLine.indexOf("<f>")+3, dataLine.indexOf("</f>")).split("\\*");
	}
	
	public String[] getFieldCombos() {
		return dataLine.substring(dataLine.indexOf("<KI>")+5, dataLine.indexOf("</KI>")).split("\\*");
	}
	
	public String getFilePath() {
		return parentSubject.getFilePath() + name;
	}
	
	public ArrayList<FlexCard> getCards() {
		return cards;
	}
	
	public Subject getParentSubject() {
		return parentSubject;
	}
	
	public String getDataLine() {
		return dataLine;
	}
	
	public PriorityQueue<LearningObjective> getLOQueue(){
		return LOs;
	}
}
