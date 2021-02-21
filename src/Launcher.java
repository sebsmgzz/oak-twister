package app;

import app.managers.BaseManager;
import app.models.BaseModel;
import app.models.Identity;

import java.util.List;

public class Launcher {

    public static void main(String[] args) throws Exception {
        //Application.launch(app.OakTwister.class);
        Identity i = new Identity();
        BaseManager manager = i.getManager();
        List<BaseModel> identities = manager.selectAll();
        System.out.println(identities);
    }

}
