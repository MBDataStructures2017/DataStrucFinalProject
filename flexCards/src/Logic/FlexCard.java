package Logic;

import java.util.ArrayList;

public class FlexCard {
	
	private String filePath;
	
	private double knowledgeIndex;
	private String primaryField;
	private String[] fields;
	private ArrayList<LearningObjective> learningObjectives;
	private StudySet parentSet;
	private String[] fieldNames;
	private String dataLine;
	private int dataLineIndex;
	//private String[] fieldCombos;
	//Here is a comment.

	private double[] fieldComboKnowledgeIndexes;
	
	public FlexCard(String studySetLine, String line, StudySet parentSet, int dataLineIndex) {
		learningObjectives = new ArrayList<LearningObjective>();
		//fields = new ArrayList<String>();
		this.parentSet = parentSet;
		this.fieldNames = studySetLine.substring(studySetLine.indexOf("<f>")+3, studySetLine.indexOf("</f>")).split("\\*");
		this.dataLine = line;
		this.dataLineIndex = dataLineIndex;
		this.knowledgeIndex = Double.parseDouble(line.substring(0, line.indexOf("*")));
		
		//Gets the Strings for the data for each field ex: "Barack Obama*2008-2016*Democrat* ---> [Barack Obama] [2008-2016] [Democrat]
		fields = line.substring(line.indexOf("<f>")+3, line.indexOf("</f>")).split("\\*");
		
		//[F1->F2] [F1->F3]....
		String[] fieldComboArr = parentSet.getFieldCombos();
		
		//Coresponds
		//[F1->F2] [F1->F3]....
		//[100.00] [203.00]
		String[] allFieldComboKIStr = line.substring(line.indexOf("</f>")+4).split("\\*");

		this.fieldComboKnowledgeIndexes = new double[fieldComboArr.length];
		
		
		//Coverts allFieldComboKIStr to an actuall array of doubles
		for(int k = 0; k < allFieldComboKIStr.length; k++) {
			fieldComboKnowledgeIndexes[k] = Double.parseDouble(allFieldComboKIStr[k]);
		}
		
		//Creating new LearningObjectives
		for(int h = 0; h < fieldComboArr.length; h++) {
			//[F1->F3]: from index is 1
			int fromIndex = Integer.parseInt(fieldComboArr[h].substring(1, 2));
			fromIndex--;
			//[F1->F3]: to index is 3
			int toIndex = Integer.parseInt(fieldComboArr[h].substring(fieldComboArr[h].length() -1, fieldComboArr[h].length()));
			toIndex--;
			learningObjectives.add(new LearningObjective(fields[fromIndex],fromIndex, fields[toIndex],toIndex, fieldComboKnowledgeIndexes[h],h, this));
		}
		
		
		this.primaryField = fields[0];
		
		//printCard();
	}
	
	public double[] getFieldComboKnowledgeIndexes() {
		return fieldComboKnowledgeIndexes;
	}

	public void printCard() {
		System.out.println("Line in flecCards.txtFile:" + dataLineIndex + " " + dataLine);
		System.out.println("--------------Fields:---------------");
		for(int k = 0; k < fields.length; k++) {
			System.out.println("[" + fields[k] + "]");
		}
		System.out.println("--------Learning Objectives:--------");
		for(LearningObjective lo : learningObjectives) {
			System.out.println(lo.toString());
		}
	}
	
	public String getDataLine() {
		return dataLine;
	}
	
	public int getDataLineIndex() {
		return dataLineIndex;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}
	
	public double getKnowledgeIndex() {
		return knowledgeIndex;
	}

	public void setKnowledgeIndex(double knowledgeIndex) {
		this.knowledgeIndex = knowledgeIndex;
	}

	public String getPrimaryField() {
		return primaryField;
	}

	public void setPrimaryField(String primaryField) {
		this.primaryField = primaryField;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public ArrayList<LearningObjective> getLearningObjectives() {
		return learningObjectives;
	}

	public void setLearningObjectives(ArrayList<LearningObjective> learningObjectives) {
		this.learningObjectives = learningObjectives;
	}
	
	public StudySet getParentStudySet() {
		return this.parentSet;
	}
	
	
	
	
}
