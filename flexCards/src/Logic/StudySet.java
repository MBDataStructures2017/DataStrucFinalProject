package Logic;

import java.util.ArrayList;
import java.util.PriorityQueue;

import flexCards.Main;

public class StudySet {
	private String filePath;
	
	private PriorityQueue<LearningObjective> LOs;
	
	private ArrayList<FlexCard> cards;
	
	private String name;
	
	private ArrayList<String> tempCardTitles;// This is just for the Nov 17th deadline.
	
	public StudySet(String name, String filePath, String dataLine) {
		this.name = name;
		
		this.filePath = filePath + name + "/";
		tempCardTitles = new ArrayList<String>();
		cards = new ArrayList<FlexCard>();
		ArrayList<String> tempCardLines = Main.txtToStringArrayList(filePath+"/flexCards.txt");
		for(int i = 1; i < tempCardLines.size(); i++) {
			String tempCardTitle = tempCardLines.get(i).substring(tempCardLines.get(i).indexOf("<f>")+3, tempCardLines.get(i).indexOf("</f>"));
			tempCardTitle = tempCardTitle.substring(0, tempCardTitle.indexOf("*"));
			tempCardTitles.add(tempCardTitle);
			cards.add( new FlexCard(dataLine, tempCardLines.get(i)) );
		}
	}
	
	public ArrayList<String> getTempCardTitles() {
		return tempCardTitles;
	}
	public String getName() {
		return name;
	}
	
	public ArrayList<FlexCard> getCards() {
		return cards;
	}
}
