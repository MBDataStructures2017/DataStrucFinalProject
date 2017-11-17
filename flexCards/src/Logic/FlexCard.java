package Logic;

import java.util.ArrayList;

public class FlexCard {
	
	private String filePath;
	
	private double knowledgeIndex;
	private String primaryField;
	private ArrayList<String> fields;
	private ArrayList<LearningObjective> learningObjectives;
	
	public FlexCard(String studySetLine, String line) {
		learningObjectives = new ArrayList<LearningObjective>();
		fields = new ArrayList<String>();
		
		String allFields = line.substring(line.indexOf("<f>")+3, line.indexOf("</f>"));
		String[] fieldArr = allFields.split("\\*");//These lines are bitches;
		
		String allFieldCombos = studySetLine.substring(studySetLine.indexOf("<KI>")+4, studySetLine.indexOf("</KI>"));
		String[] fieldComboArr = allFieldCombos.split("\\*");
		
		String allFieldComboKI = line.substring(line.indexOf("</f>")+4);
		String[] allFieldComboKIStrArr = allFieldComboKI.split("\\*");
		double[] fieldComboKnowledgeIndexes = new double[fieldComboArr.length];
		
		for(int k = 1; k < allFieldComboKIStrArr.length; k++) {
			fieldComboKnowledgeIndexes[k] = Double.parseDouble(allFieldComboKIStrArr[k]);
		}
		
		for(int h = 1; h < fieldComboArr.length; h++) {
			int fromIndex = Integer.parseInt(fieldComboArr[h].substring(1, 2));
			fromIndex--;
			int toIndex = Integer.parseInt(fieldComboArr[h].substring(fieldComboArr[h].length() -1, fieldComboArr[h].length()));
			toIndex--;
			learningObjectives.add(new LearningObjective(fieldArr[fromIndex], fieldArr[toIndex], fieldComboKnowledgeIndexes[h]));
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
	
}
