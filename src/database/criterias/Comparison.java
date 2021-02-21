package database.criterias;

public class Comparison implements Condition {

    private final String leftValue;
    private final ComparisonOperand operand;
    private final String rightValue;

    public String getLeftValue() {
        return leftValue;
    }

    public ComparisonOperand getOperand() {
        return operand;
    }

    public String getRightValue() {
        return rightValue;
    }

    public Comparison(String leftValue, ComparisonOperand operand, String rightValue) {
        this.leftValue = leftValue;
        this.operand = operand;
        this.rightValue = rightValue;
    }

    public String getOperandValue() {
        switch (operand) {
            case EQUALS:
                return "==";
            case NOT_EQUALS:
                return "!=";
            case GREATER:
                return ">";
            case LESS:
                return "<";
            case GREATER_OR_EQUALS:
                return ">=";
            case LESS_OR_EQUALS:
                return "<=";
            default:
                return operand.toString();
        }
    }

    @Override
    public String toString() {
        return  "(" + leftValue.toString() + " " + getOperandValue() + " " + rightValue.toString() + ")";
    }

}
