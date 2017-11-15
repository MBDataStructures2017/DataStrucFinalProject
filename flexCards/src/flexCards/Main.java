package flexCards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Logic.Subject;
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
	///////////////End of Logic Methods////////////////
	
	
	//Think of this as your "Main Method"
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Employee App");
		
		//Load the txt file with all subject names.
		ArrayList<String> subjectNames = txtToStringArrayList("Data/Subjects.txt");
		System.out.println("Lets Hope this gets on gitHub!");
		
		showMainView();
		showMainItems(subjectNames);//Passes in the names of all subjects for the user to potentially select.
	}
	
	//Sets up main menu UI
	public static void showMainItems(ArrayList<String> subjectNames) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));//Path to fxml doc
		BorderPane mainItems = loader.load();
		MainMenuController controller = loader.getController();
		controller.addSubjectsText(subjectNames);
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
	

	public static void main(String[] args) {
		
		launch(args);
	}
}
