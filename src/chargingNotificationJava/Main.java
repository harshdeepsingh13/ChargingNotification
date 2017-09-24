package chargingNotificationJava;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Main extends Application {
    public TrayIcon trayIcon;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Platform.setImplicitExit(false);

        CheckChargingThread checkChargingThread = new CheckChargingThread(this);

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chargingNotificationfxml/notification.fxml"));
        primaryStage.setTitle("About Us!!");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.getIcons().clear();
        primaryStage.getIcons().add(new Image("images/chargeLightGrey.png"));

        SystemTray systemTray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(ImageIO.read(getClass().getResource("/images/chargeLightGrey.png")),"Charging Notifications");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Charging Notifications");
        systemTray.add(trayIcon);

        trayIcon.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           System.out.println("hey");
                                           Platform.runLater(() ->
                                           {
                                               System.out.println("Later");
                                               primaryStage.show();
                                           });
                                       }
                                   }
                                    );
        checkChargingThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
