package database.criterias;

public class Logical implements Condition {

    private final Condition leftCondition;
    private final LogicalOperand operand;
    private final Condition rightCondition;

    public Condition getLeftCondition() {
        return leftCondition;
    }

    public LogicalOperand getOperand() {
        return operand;
    }

    public Condition getRightCondition() {
        return rightCondition;
    }

    public Logical(Condition leftCondition, LogicalOperand operand, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.operand = operand;
        this.rightCondition = rightCondition;
    }

    @Override
    public String toString() {
        return leftCondition.toString() + " " + operand.toString() + " " + rightCondition.toString();
    }

}
