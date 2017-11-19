package Logic;

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
	
	public LearningObjective(String fromField, int fromFieldIndex, String toField, int toFieldIndex, double knowledgeIndex, FlexCard parentCard) {
		this.fromField = fromField;
		this.fromFieldIndex = fromFieldIndex;
		this.toField = toField;
		this.toFieldIndex = toFieldIndex;
		this.knowledgeIndex = knowledgeIndex;
		this.parentCard = parentCard;
	}
	
	public String getFromFieldName() {
		return parentCard.getFieldNames()[fromFieldIndex];
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

	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		return (int) (knowledgeIndex - ((LearningObjective) other).getKnowledgeIndex());
	}
	
	public String toString() {
		return "("+getFromFieldName()+")"+ fromField +" --> " + "("+getToFieldName()+")"+toField;
	}


	
	
	
}
