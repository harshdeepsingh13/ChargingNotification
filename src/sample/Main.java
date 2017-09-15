package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("notification.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

//        checkCharge();

        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        while(true) {
            System.out.println("False");

            Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
            while (checkCharge(batteryStatus)) {
                Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Remove Please!!");
                alert.setHeaderText("Remove the charger.");
                alert.setContentText(batteryStatus.getBatteryLifePercentLong() + "% charged...");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getIcons().add(new Image("images/chargeLightGrey.png"));
                stage.requestFocus();
//                stage.initStyle(StageStyle.UNDECORATED);
//                stage.setFullScreen(true);
                stage.setOnCloseRequest(event ->
                {
//                    ((Node)(event.getSource())).getScene().getWindow().hide();

                });
                alert.showAndWait();
//                alert.show();

                Thread.currentThread().sleep(310000);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public boolean checkCharge(Kernel32.SYSTEM_POWER_STATUS batteryStatus)
    {


//        System.out.println(batteryStatus);
        if(batteryStatus.getACLineStatusString().equalsIgnoreCase("online"))
        {
            System.out.println(batteryStatus.getBatteryLifePercentLong());
            if(batteryStatus.getBatteryLifePercentLong() >= 96)
                return true;
            else
                return false;
        }
        else return false;
    }
}
