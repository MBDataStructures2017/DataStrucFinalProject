package Logic;

import java.util.ArrayList;

public class FlexCard {
	
	private String filePath;
	
	private double knowledgeIndex;
	private String primaryField;
	private ArrayList<String> fields;
	private ArrayList<LearningObjective> learningObjectives;
	private StudySet parentSet;
	private String[] fieldNames;
	//private String[] fieldCombos;
	
	public FlexCard(String studySetLine, String line, StudySet parentSet) {
		learningObjectives = new ArrayList<LearningObjective>();
		fields = new ArrayList<String>();
		this.parentSet = parentSet;
		this.fieldNames = studySetLine.substring(studySetLine.indexOf("<f>")+3, studySetLine.indexOf("</f>")).split("\\*");
		
		
		String[] fieldArr = line.substring(line.indexOf("<f>")+3, line.indexOf("</f>")).split("\\*");
		System.out.println("B:" + fieldArr.length);
		
		
		String[] fieldComboArr = parentSet.getFieldCombos();
		System.out.println("A:" + fieldComboArr.length);
		//this.fieldCombos = fieldComboArr;
		
		
		String allFieldComboKI = line.substring(line.indexOf("</f>")+4);
		String[] allFieldComboKIStrArr = allFieldComboKI.split("\\*");
		double[] fieldComboKnowledgeIndexes = new double[fieldComboArr.length];
		
		
		
		System.out.println(fieldArr[0]+"__"+fieldComboKnowledgeIndexes.length +"____"+allFieldComboKIStrArr.length);
		for(int k = 1; k < allFieldComboKIStrArr.length; k++) {
			//System.out.println(fieldArr[0] +"_"+fieldComboKnowledgeIndexes[k] + "_"/*allFieldComboKIStrArr[k]*/);
			fieldComboKnowledgeIndexes[k] = Double.parseDouble(allFieldComboKIStrArr[k]);
		}
		
		for(int h = 1; h < fieldComboArr.length; h++) {
			int fromIndex = Integer.parseInt(fieldComboArr[h].substring(1, 2));
			fromIndex--;
			int toIndex = Integer.parseInt(fieldComboArr[h].substring(fieldComboArr[h].length() -1, fieldComboArr[h].length()));
			toIndex--;
			learningObjectives.add(new LearningObjective(fieldArr[fromIndex],fromIndex, fieldArr[toIndex],toIndex, fieldComboKnowledgeIndexes[h], this));
		}
		
		this.primaryField = fieldArr[0];
		for(int j = 0; j < fieldArr.length; j++) {
			fields.add(fieldArr[j]);
		}
	}
	
	public void printCard() {
		System.out.println("--------------Fields:---------------");
		for(int k = 0; k < fields.size(); k++) {
			System.out.println("[" + fields.get(k) + "]");
		}
		System.out.println("--------Learning Objectives:--------");
		for(LearningObjective lo : learningObjectives) {
			System.out.println(lo.toString());
		}
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

	public ArrayList<String> getFields() {
		return fields;
	}

	public void setFields(ArrayList<String> fields) {
		this.fields = fields;
	}

	public ArrayList<LearningObjective> getLearningObjectives() {
		return learningObjectives;
	}

	public void setLearningObjectives(ArrayList<LearningObjective> learningObjectives) {
		this.learningObjectives = learningObjectives;
	}
	
	
	
}
