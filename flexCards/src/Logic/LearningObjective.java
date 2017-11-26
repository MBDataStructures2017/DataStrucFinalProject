package Logic;

import java.io.IOException;
import java.util.ArrayList;

import flexCards.Main;

/**Most Basic Unit of this application. 
 * Represents a single ("Get this field from this field") EX:
 * Get the year of an event from it's description.
 * 
 * @author Ben Kizaric and Megan Choy
 *
 */
public class LearningObjective implements Comparable{
	
	private String filePath;
	
	private double knowledgeIndex;
	private String fromField;
	private int fromFieldIndex;
	private String toField;
	private int toFieldIndex;
	private FlexCard parentCard;
	private int fieldComboIndex;
	
	public LearningObjective(String fromField, int fromFieldIndex, String toField, int toFieldIndex, double knowledgeIndex, int fieldComboIndex, FlexCard parentCard) {
		this.fromField = fromField;
		this.fromFieldIndex = fromFieldIndex;
		this.toField = toField;
		this.toFieldIndex = toFieldIndex;
		this.knowledgeIndex = knowledgeIndex;
		this.parentCard = parentCard;
		this.fieldComboIndex = fieldComboIndex;
	}
	
	public String getFromFieldName() {
		return parentCard.getFieldNames()[fromFieldIndex];
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void evaluate1() throws IOException {//It works!!!!!
		Main.replaceLineInFile(parentCard.getParentStudySet().getFilePath()+"/flexCards.txt", parentCard.getDataLineIndex(), generalEvaluate(0.5));
		
	}
	public void evaluate2() throws IOException {
		Main.replaceLineInFile(parentCard.getParentStudySet().getFilePath()+"/flexCards.txt", parentCard.getDataLineIndex(), generalEvaluate(0.75));
		
	}
	public void evaluate3() throws IOException {
		Main.replaceLineInFile(parentCard.getParentStudySet().getFilePath()+"/flexCards.txt", parentCard.getDataLineIndex(), generalEvaluate(0.9));
		
	}
	
	public void evaluate4() throws IOException {
		Main.replaceLineInFile(parentCard.getParentStudySet().getFilePath()+"/flexCards.txt", parentCard.getDataLineIndex(), generalEvaluate(1.25));
		
	}
	public void evaluate5() throws IOException {
		Main.replaceLineInFile(parentCard.getParentStudySet().getFilePath()+"/flexCards.txt", parentCard.getDataLineIndex(), generalEvaluate(1.5));
		
	}
	
	/**
	 * Changes the object data for both this Learning Objective and its parent card.
	 * Alters the Knowledge index of this Learning objective bu multiplying it by 
	 * the parameter multiplier. Updates the parent card's Knowledge Index to reflect this.
	 * 
	 * Also returns a string representing the updated version of this cards data.
	 * 
	 * @param multiplyer
	 * @return a string representing the updated version of this cards data.
	 */
	private String generalEvaluate(double multiplier) {	
		String header = "";
		String fields = "<f>";
		String footer = "";
		
		//Adjusts the value of this Learning Index to a new value as determined by multiplier
		knowledgeIndex *= multiplier;
		System.out.println(knowledgeIndex);
		
		//Populate the fields with the same data as the Cards field area.
		for(int i = 0; i < parentCard.getFields().length; i++) {
			fields += parentCard.getFields()[i] + "*";
		}
		fields += "</f>";
		
		double runningTotal = 0;//Used to calculate Knowledge index of all FieldCombos
		
		System.out.println(parentCard.getFieldComboKnowledgeIndexes().length);
		
		//Loops through the knowledge index of each FieldCombo in the parentCard
		for(int k = 0; k < parentCard.getFieldComboKnowledgeIndexes().length; k++) {
			//If it comes accross the FieldCombo that this LearningObjective is representing
			if(k == fieldComboIndex) {
				//Set that FieldCombo Knowledge Index to the newly adjusted value, as determined by multiplier.
				parentCard.getFieldComboKnowledgeIndexes()[k] = knowledgeIndex;
				footer += parentCard.getFieldComboKnowledgeIndexes()[k] + "*";
			}
			else {
				footer += parentCard.getFieldComboKnowledgeIndexes()[k] + "*";
			}
			runningTotal += parentCard.getFieldComboKnowledgeIndexes()[k];
		}
		
		//Set the cards overall Knowlege Index to the average of all FieldCombo Knowledge indexex
		parentCard.setKnowledgeIndex(runningTotal/(parentCard.getFieldComboKnowledgeIndexes().length - 1));
		header = parentCard.getKnowledgeIndex() + "*"+"0";
		
		System.out.println(header + fields + footer);
		
		return header + fields + footer;
	}
	
	public String getToFieldName() {
		return parentCard.getFieldNames()[toFieldIndex];
	}

	public double getKnowledgeIndex() {
		return knowledgeIndex;
	}

	public void setKnowledgeIndex(double knowledgeIndex) {
		this.knowledgeIndex = knowledgeIndex;
	}

	public String getFromField() {
		return fromField;
	}

	public void setFromField(String fromField) {
		this.fromField = fromField;
	}

	public String getToField() {
		return toField;
	}

	public void setToField(String toField) {
		this.toField = toField;
	}
	
	public FlexCard getParentFlexCard() {
		return this.parentCard;
	}

	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		return (int) (knowledgeIndex - ((LearningObjective) other).getKnowledgeIndex());
	}
	
	public String toString() {
		return "("+getFromFieldName()+")"+ fromField +" --> " + "("+getToFieldName()+")"+toField + " " + knowledgeIndex;
	}
}
