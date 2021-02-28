import javafx.application.Application;
import utilities.ImportDatabase;

public class Launcher {

    public static void main(String[] args) {
        //Application.launch(OakTwister.class);
        ImportDatabase cmd = new ImportDatabase();
        cmd.execute();
    }

}
