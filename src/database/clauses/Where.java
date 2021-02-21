package database.clauses;

import database.criterias.Condition;

public class Where {

    private final Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public Where(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WHERE " + condition.toString();
    }

}
