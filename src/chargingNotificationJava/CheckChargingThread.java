package chargingNotificationJava;

import javax.imageio.ImageIO;
import java.awt.*;

public class CheckChargingThread extends Thread {
    private Main main;
    private Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();

    public CheckChargingThread(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
//        super.run();
        try {
            while(true) {

            Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
            while (checkCharge(batteryStatus)) {
                Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
                main.trayIcon.displayMessage("Remove the charger!!",batteryStatus.getBatteryLifePercentLong() + "% charged...", TrayIcon.MessageType.INFO);
                Thread.currentThread().sleep(310000);
            }
            Thread.currentThread().sleep(30000);
        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



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
