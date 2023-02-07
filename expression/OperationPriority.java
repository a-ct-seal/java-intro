package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationPriority {
    public static final int UnaryOperation = 0;
    public static final int Operand = 0;
    public static final int Add = 2;
    public static final int Subtract = 2;
    public static final int Multiply = 1;
    public static final int Divide = 1;
    public static final int Gcd = 3;
    public static final int Lcm = 3;
    public static final int GreatestPriority;
    public static final Map<Integer, List<String>> priorities;

    static {
        GreatestPriority = max(UnaryOperation, Operand,
                Add, Subtract, Multiply, Divide, Gcd, Lcm);
        priorities = new HashMap<>();
        for (int i = 0; i < OperationPriority.GreatestPriority; i++) {
            priorities.put(i + 1, new ArrayList<>());
        }
        priorities.get(Gcd).add(OperationStrings.Gcd);
        priorities.get(Lcm).add(OperationStrings.Lcm);
        priorities.get(Add).add(OperationStrings.Add);
        priorities.get(Subtract).add(OperationStrings.Subtract);
        priorities.get(Multiply).add(OperationStrings.Multiply);
        priorities.get(Divide).add(OperationStrings.Divide);
    }

    private static int max(Integer... values) {
        int res = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > res) {
                res = values[i];
            }
        }
        return res;
    }
}
