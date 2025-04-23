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
	
	private static Stage stg;
	@Override
	public void start(Stage primaryStage) throws IOException{
			stg = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("SelectLogin.fxml"));
			primaryStage.setTitle("Shopdee");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(img);
			primaryStage.show();
		}
	
	public void changeSceneToLogin(String fxml)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getIcons().add(img);
		stg.setTitle("Shopdee");
		stg.setScene(new Scene(pane));
		stg.setResizable(false);
		stg.show();
	}
	
	public void changeSceneToSystem(String fxml)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getIcons().add(img);
		stg.setTitle("Shopdee");
		stg.setScene(new Scene(pane));
		stg.setResizable(false);
		stg.setX(200);
		stg.setY(50);
		stg.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
