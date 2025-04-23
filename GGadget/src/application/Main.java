package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	Image img = new Image("/picture/Logo.png");
	
	public static Stage stg;
	@Override
	public void start(Stage primaryStage) throws IOException{
			stg = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
			primaryStage.setTitle("GGadget");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(img);
			primaryStage.show();
		}
	
	public void changeScene(String fxml)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getIcons().add(img);
		stg.setTitle("GGadget");
		stg.setScene(new Scene(pane));
		stg.setResizable(false);
		stg.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
