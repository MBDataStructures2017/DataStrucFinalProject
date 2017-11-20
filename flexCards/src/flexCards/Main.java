package flexCards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Logic.LearningObjective;
import Logic.StudySet;
import Logic.Subject;
import flexCards.AddFlexCard.AddFlexCardViewController;
import flexCards.AddStudySet.AddStudySetViewController;
import flexCards.Study.StudyViewController;
import flexCards.StudySet.StudySetViewController;
import flexCards.Subject.SubjectViewController;
import flexCards.view.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private static SubjectViewController subjectViewController;
	
	///////////////Logic Methods//////////////////
	/**Creates an arraylist of strings from a target text file. 
	 * 
	 * @param targetFile - The name of the file that you wish to use a your source for your new arraylist of strings. Include file extensions.
	 * @return An arraylist of strings created from the target file. Each item matches a line of text from the target file.
	 */
	public static ArrayList<String> txtToStringArrayList(String targetFile){
		//Initialize inputAream and ArrayList of strings that will hold the files contents.
		ArrayList<String> convertedFile = new ArrayList<String>();
		Scanner inputStream = null;
		
		//Finding the file and setting inputStream equal to it
		try {
			inputStream = new Scanner(new FileInputStream(targetFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Adding lines of the file to the ArrayList of strings
		while(inputStream.hasNextLine()) {
			convertedFile.add(inputStream.nextLine());
		}
		inputStream.close();
		
		//Return arraylist of strings which now contains all lines of the target file.
		return convertedFile;
	}
	
	public static void append(String s, File f) throws IOException {
		s = "\n" + s;
		FileWriter fw = new FileWriter(f, true);
		fw.write(s);
		fw.close();
	}
	///////////////End of Logic Methods////////////////
	
	
	//Think of this as your "Main Method"
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("flexCards");
		
		
		
		//Load the txt file with all subject names.
		//ArrayList<String> subjectNames = txtToStringArrayList("Data/Subjects.txt");
		
		showMainView();
		showMainItems();//Passes in the names of all subjects for the user to potentially select.
	}
	
	//Sets up main menu UI
	public static void showMainItems() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));//Path to fxml doc
		BorderPane mainItems = loader.load();
		MainMenuController controller = loader.getController();
		controller.addSubjectsText();
		mainLayout.setCenter(mainItems);
	}
	
	//Sets up primary window
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainPanel.fxml"));//Path to fxml doc
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showAddSubject() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddSubject/AddSubjectView.fxml"));
		BorderPane addSubject = loader.load();
		
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New Subject");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addSubject);
		
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	
	public static void showAddStudySet(Subject parentSubject) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddStudySet/AddStudySetView.fxml"));
		BorderPane addStudySet = loader.load();
		
		AddStudySetViewController controller = loader.getController();
		controller.setSubject(parentSubject);
		
		
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New Study Set");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addStudySet);
		
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	
	public static void showAddFlexCard(StudySet parentStudySet) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddFlexCard/AddFlexCardView.fxml"));
		BorderPane addFlexCard = loader.load();
		
		AddFlexCardViewController controller = loader.getController();
		controller.setStudySet(parentStudySet);
		controller.initializeFeildLabels();
		
		
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New flexCard");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addFlexCard);
		
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	
	//Sets up Subject View
	public static void showSubjectView(String subjectName) throws IOException {
		System.out.println("ShowSubjectView()");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Subject/SubjectView.fxml"));//Path to fxml doc
		BorderPane subjectView = loader.load();
		SubjectViewController controller = loader.getController();//Passing Data into the controller
		
		controller.setTitle(subjectName);//Passing Data into the controller!
		controller.subjectInit(subjectName);//Initializes the Subject object within SubjectView
		mainLayout.setCenter(subjectView);
	}
	
	//Sets up Study Set View
	public static void showStudySetView(Subject subject, String studySetName) throws IOException {
		System.out.println("ShowStudySetView()");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("StudySet/StudySetView.fxml"));//Path to fxml doc
		BorderPane subjectView = loader.load();
		StudySetViewController controller = loader.getController();//Passing Data into the controller
		controller.setTitle(studySetName);//Passing Data into the controller!
		controller.initStudySet(subject, studySetName);//Initializes StudySet object in StudySet View
		mainLayout.setCenter(subjectView);
	}
	
	public static void showStudyView(String queueName, PriorityQueue<LearningObjective> queueContents, StudySet parentSet) throws IOException {
		System.out.println("ShowStudySetView()");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Study/StudyView.fxml"));//Path to fxml doc
		BorderPane subjectView = loader.load();
		StudyViewController controller = loader.getController();//Passing Data into the controller
		controller.setStudySet(parentSet);
		controller.setTitle(queueName);//Passing Data into the controller!
		controller.setLearningObjectives(queueContents);//Initializes StudySet object in StudySet View
		mainLayout.setCenter(subjectView);
		
		
	}
	

	public static void main(String[] args) {
		
		launch(args);
	}
}
