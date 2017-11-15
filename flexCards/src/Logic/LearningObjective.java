package Logic;

/**Most Basic Unit of this application. 
 * Represents a single ("Get this field from this field") EX:
 * Get the year of an event from it's description.
 * 
 * @author Ben Kizaric and Megan Choy
 *
 */
public class LearningObjective {
	
	private String filePath;
	
	private double knowledgeIndex;
	private String fromFeild;
	private String toFeild;
	private FlexCard card;
	
	public LearningObjective(FlexCard card, String toFrom, int kI) {
		this.card = card;
		this.knowledgeIndex = kI;
	}
	
}
