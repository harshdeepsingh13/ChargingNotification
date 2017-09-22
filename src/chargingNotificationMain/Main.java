package chargingNotificationMain;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {
    private AudioClip audioClip = new AudioClip(getClass().getResource("/sounds/alertTone.wav").toString());

    @Override
    public void start(Stage primaryStage) throws Exception{

        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();

        SystemTray systemTray = SystemTray.getSystemTray();
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("src/images/chargeLightGrey.png"),"Charging Notifications");
//        trayIcon.setImage(Toolkit.getDefaultToolkit().createImage("/src/images/chargeLightGrey.png"));
        trayIcon.setImageAutoSize(true);
//        systemTray.add(trayIcon);
        trayIcon.setToolTip("Charging Notifications");
        while(true) {
//            System.out.println("False");

            Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
            while (checkCharge(batteryStatus)) {
                Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
                systemTray.add(trayIcon);
                trayIcon.displayMessage("Remove the charger!!",batteryStatus.getBatteryLifePercentLong() + "% charged...", TrayIcon.MessageType.INFO);
                systemTray.remove(trayIcon);
                Thread.currentThread().sleep(310000);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean checkCharge(Kernel32.SYSTEM_POWER_STATUS batteryStatus)
    {
        System.out.println(batteryStatus);
        if(batteryStatus.getACLineStatusString().equalsIgnoreCase("online"))
        {
//            System.out.println(batteryStatus.getBatteryLifePercentLong());
            if(batteryStatus.getBatteryLifePercentLong() >= 97)
                return true;
            else
                return false;
        }
        else return false;
//        return true;
    }
}
