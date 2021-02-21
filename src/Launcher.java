import database.clauses.Where;
import database.criterias.Comparison;
import database.criterias.ComparisonOperand;
import managers.BaseManager;
import models.BaseModel;
import models.Identity;

import java.util.List;

public class Launcher {

    public static void main(String[] args) throws Exception {
        //Application.launch(OakTwister.class);
        Identity i = new Identity();
        BaseManager manager = i.getManager();
        List<BaseModel> identities = manager.selectAll();
        System.out.println(identities);
        Where criteria = new Where(new Comparison("id", ComparisonOperand.EQUALS, "1"));
        BaseModel i2 = manager.select(criteria);
        System.out.println(i2);
    }

}
