package application;
	


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;




public class Main extends Application {
	 


	
	
	private static Stage stg;


	 /*
     * This will be used as a splash screen for the application It will display some important data to the user and then disappear
     */
	  
    SplashScreenController splashScreen = new SplashScreenController();

 

	
	@Override
	public void start(Stage primaryStage)  {
		try {

		//	Thread.sleep(1000); // synchro attente audio et gif


			//START SPLASHER AUDIO 
			String uriString = new File("src/application/IMAGES/Op. GX Entry sound.wav").toURI().toString();
			MediaPlayer player = new MediaPlayer( new Media(uriString));
			player.play();
			//
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene1 = new Scene (root, 1280, 720); // scene principale
			stg = primaryStage;

			primaryStage.setTitle("MyINSA by T.MESBAHI & S.LAYACHI");
			primaryStage.getIcons().add(new Image("/IMAGES/logo my insa transparent.png"));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene1); 



			//Show the SplashScreen
			splashScreen.showWindow();

			//I am using the code below so the Primary Stage of the application 
			//doesn't appear for 6 seconds , so the splash screen is displayed
			PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(6));
			splashScreenDelay.setOnFinished(f -> {

				primaryStage.show();
				splashScreen.hideWindow();
			});
			splashScreenDelay.playFromStart();


	/*
			
	//START music
		File file = new File("src/application/IMAGES/Opera-GX-background-music-Evolve.wav"); // path to includE later on IMAGES ( WHICH IS NORMALLY A RESSOURCE PATH BUT YOU
		//	DONT WANT TO RENAME IT TO AVOID ISSUES.. . 
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); // RECOMMENCE L'AUDIO EN CONTINU OTIMAL POUR UNE INSTALLATION DE TAILLE REDUITE
			// END MUSIC 
			 
		*/

		} 

		catch(Exception e) {
			e.printStackTrace();}
	}




	// Methode qui switch les scenes automatiquement en passant le fxml suivant en argument
	// est ce quon peut ajouter unne animation fade in fade out??/
	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);

	}



	public static void main(String[] args) {
		launch(args);
	}
}
