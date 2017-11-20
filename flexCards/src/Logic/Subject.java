package Logic;

import java.util.ArrayList;

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
