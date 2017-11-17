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
	private String toField;
	private FlexCard card;
	
	public LearningObjective(String fromField, String toField, double knowledgeIndex) {
		this.toField = toField;
		this.fromField = fromField;
		this.knowledgeIndex = knowledgeIndex;
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
		return fromField +" --> " + toField;
	}


	
	
	
}
