package flexCards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Logic.LearningObjective;
import Logic.StudySet;
import Logic.Subject;
import flexCards.AddFlexCard.AddFlexCardViewController;
import flexCards.AddStudySet.AddStudySetViewController;
import flexCards.AddSubject.AddSubjectViewController;
import flexCards.Study.MainStudyViewController;
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
	/**
	 * Clears the text file whose relative path is specified by FilePath.
	 * @param filePath The reletive path to the target file
	 * @throws FileNotFoundException
	 */
	public static void clearFile(String filePath) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(filePath);
		pw.close();
	}
	
	/**
	 * Appends the string s onto the end of a text file s.
	 * @param s -The string to be appended
	 * @param f -The file that is being accessed
	 * @param isEmptyFile - Whether or not the file is empty
	 * @throws IOException
	 */
	public static void append(String s, File f, boolean isEmptyFile) throws IOException {
		if( !isEmptyFile) {
			s = "\n" + s;
		}
		FileWriter fw = new FileWriter(f, true);
		fw.write(s);
		fw.close();
	}
	
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
	
	/**
	 * Replaces the [lineIndex}th line in the file specified by[pathToFile] with [newLine]
	 * @param pathToFile
	 * @param lineIndex NOTE: File Indexes start at 1, not 0.
	 * @param newLine
	 * @throws IOException 
	 */
	public static void replaceLineInFile(String pathToFile, int lineIndex, String newLine) throws IOException {
		ArrayList<String> fileContents = txtToStringArrayList(pathToFile); // Creates a "Copy" of the file
		File file = new File(pathToFile); 
		boolean isEmpty = (fileContents.get(0).equals("") && fileContents.size() == 1);
		fileContents.set(lineIndex - 1, newLine);
		clearFile(pathToFile);
		append(fileContents.remove(0), file, true);
		for(String s : fileContents) {
			append(s, file, false);
		}
			
		
	}
	///////////////End of Logic Methods////////////////
	
	
	//Think of this as your "Main Method"
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("flexCards");
		
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
	
	/**
	 * Initialized and sets up a window for a subject view 
	 * @param subjectName The name of the subject. This is displayed and used to get data.
	 * @throws IOException
	 */
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
	
	/**
	 * Launches new window to create a new subject
	 * @param parentController The Mainmenu controller from whitch this method is called.
	 * @throws IOException
	 */
	public static void showAddSubject(MainMenuController parentController) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddSubject/AddSubjectView.fxml"));
		BorderPane addSubject = loader.load();
		
		AddSubjectViewController controller = loader.getController();
		//Passing a referance to the SubjectView Handler from which it launched so that it can update its list of subjects upon close;
		controller.setParentController(parentController);
		
		
		//UI Boilerplate
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New Subject");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		Scene scene = new Scene(addSubject);
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	
	/**
	 * Shows the view for a specific study set
	 * @param subject The subject that is the parent of the currently examined studyset
	 * @param studySetName The name of the study set being displayed in this window. Used for title and file accesing
	 * @throws IOException
	 */
	public static void showStudySetView(Subject subject, String studySetName) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("StudySet/StudySetView.fxml"));//Path to fxml doc
		BorderPane subjectView = loader.load();
		
		StudySetViewController controller = loader.getController();//Passing Data into the controller
		controller.setTitle(studySetName);//Passing Data into the controller!
		controller.initStudySet(subject, studySetName);//Initializes StudySet object in StudySet View
		mainLayout.setCenter(subjectView);
	}
	
	/**
	 * Launches new window where once can enter data about a new subject
	 * @param parentSubject The subject that the new study set will belong to.
	 * @param parentController The SubjectViewController from where this method was called
	 * @throws IOException
	 */
	public static void showAddStudySet(Subject parentSubject, SubjectViewController parentController) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddStudySet/AddStudySetView.fxml"));
		BorderPane addStudySet = loader.load();
		
		//Passing data into this view so that it can edit both its current subject and controller from which it launched.
		AddStudySetViewController controller = loader.getController();
		controller.setSubject(parentSubject);
		controller.setParentController(parentController);
		
		
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New Study Set");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addStudySet);
		
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	
	
	/**
	 * Launches new window where one can enter data about a new flexCard within a studySet.
	 * @param parentStudySet Study set that this new flexCard will belong to
	 * @param parentController Study set controller from which this method is called from.
	 * @throws IOException
	 */
	public static void showAddFlexCard(StudySet parentStudySet, StudySetViewController parentController) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AddFlexCard/AddFlexCardView.fxml"));
		BorderPane addFlexCard = loader.load();
		
		AddFlexCardViewController controller = loader.getController();
		controller.setStudySet(parentStudySet);//Passing Data
		controller.setParentController(parentController);//Passing Data
		controller.initializeFeildLabels();
		
		
		Stage addDialogueStage = new Stage();
		addDialogueStage.setTitle("Add New flexCard");
		addDialogueStage.initModality(Modality.WINDOW_MODAL);
		addDialogueStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addFlexCard);
		
		addDialogueStage.setScene(scene);
		addDialogueStage.showAndWait();
	}
	

	/**
	 * Shows the study view for the items in a particular study set.
	 * @param queueName Name for label. Usually the study set.
	 * @param queueContents A priority queue that is used to determine what items need to be reviewed.
	 * @param parentSet Study set whose items are in this queue.
	 * @throws IOException
	 */
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
	
	/**
	 * Shows the study view for the items in a particular subject
	 * @param queueName Name for label. Usually the subject.
	 * @param queueContents A priority queue that is used to determine what items need to be reviewed.
	 * @param parentSubject Study set whose items are in this queue.
	 * @throws IOException
	 */
	public static void showMainStudyView(String queueName, PriorityQueue<LearningObjective> queueContents, Subject parentSubject) throws IOException {
		System.out.println("ShowStudySetView()");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Study/MainStudyView.fxml"));//Path to fxml doc
		BorderPane subjectView = loader.load();
		MainStudyViewController controller = loader.getController();//Passing Data into the controller
		controller.setSubject(parentSubject);
		controller.setTitle(queueName);//Passing Data into the controller!
		controller.setLearningObjectives(queueContents);//Initializes StudySet object in StudySet View
		mainLayout.setCenter(subjectView);
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
