package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TimeTableController implements Initializable {
	
    private CalendarView calendar;

    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         loadCalendar();
    }

    private void loadCalendar() {
       calendar = new CalendarView();

        Calendar CM = new Calendar("CM");
        Calendar TD = new Calendar("TD");
        Calendar TP = new Calendar("TP");
        Calendar Exam = new Calendar("Exam");
        CM.setStyle(Calendar.Style.STYLE7);
        TD.setStyle(Calendar.Style.STYLE2);
        TP.setStyle(Calendar.Style.STYLE3);
        Exam.setStyle(Calendar.Style.STYLE5);
        
        
        Entry entry = new Entry("Soutenance Achraf Berrajaa Projet");
        Interval interval = new Interval( );
        entry.setInterval(interval);
    //  entry.setRecurrenceRule("RRULE:FREQ=DAILY;");
        
        CM.addEntry(entry);
        //
       
      
        //
        CalendarSource myCalendarSource = new CalendarSource("Emploi de temps"); // set title 
        myCalendarSource.getCalendars().addAll(CM, TD,TP,Exam);

        calendar.getCalendarSources().addAll(myCalendarSource);

        calendar.setRequestedTime(LocalTime.now());


        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendar.setToday(LocalDate.now());
                        calendar.setTime(LocalTime.now());
                    });

                    try {
                        // update every 5 seconds
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        calendar.showWeekPage();
        grid.getChildren().add(calendar);
    }

	
    public void  Logout() throws IOException {
		
		Main logout = new Main();
		logout.changeScene("Login.fxml");

		
	}

   
  
}
