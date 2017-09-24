package chargingNotificationJava;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private FileReader fileReader;
    @FXML private Label infoLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controller");
        try {
//            fileReader = new FileReader(new File(String.valueOf(getClass().getResourceAsStream("/src/content/README.md"))));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/content/README.md")));

            StringBuffer stringBuffer = new StringBuffer();
            String s;
            while((s=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(s);
            }
            s = stringBuffer.substring(stringBuffer.indexOf("# ChargingNotification") + new String("# ChargingNotification").length(),stringBuffer.lastIndexOf("life"));
            infoLabel.setText(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
