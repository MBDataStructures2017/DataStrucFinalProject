package Logic;

import java.util.ArrayList;
import java.util.PriorityQueue;

import flexCards.Main;

public class Subject {
	private String filePath;
	private String filePathToStudySetsFile;
	
	private String name;
	private ArrayList<StudySet> studySets;
	
	
	public Subject(String inString) {
		this.name = inString;
		this.filePath = "Data/" + name +"/";
		this.filePathToStudySetsFile = "Data/" + name +"/StudySets.txt";
		ArrayList<String> studySetLines = Main.txtToStringArrayList(filePathToStudySetsFile);
		studySets = new ArrayList<StudySet>();
		
		for(int i = 1; i < studySetLines.size(); i++) {
			String tempStudySetName = studySetLines.get(i).substring(0, studySetLines.get(i).indexOf("*"));
			studySets.add(new StudySet(tempStudySetName, filePath+tempStudySetName, studySetLines.get(i), this));
		}
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public PriorityQueue<LearningObjective> getMainQueue(){
		PriorityQueue<LearningObjective> queue = new PriorityQueue<LearningObjective>();
		for(StudySet set : studySets) {
			for(int i = 0; i < set.getCards().size(); i++) {
				for(int k = 0; k < set.getCards().get(i).getLearningObjectives().size(); k++) {
					queue.add(set.getCards().get(i).getLearningObjectives().get(k));
				}
			}
		}
		return queue;
	}

	public String getFilePathToStudySetsFile() {
		return filePathToStudySetsFile;
	}

	public ArrayList<StudySet> getStudySets(){
		return studySets;
	}
	public String getName() {
		return name;
	}
}
