package expression_evaluator;

/**
 * Contains methods that the script will call in order to interact
 * with the main program
 */
public class ScriptBridger {
    private static EquationEvaluator evaluator = EquationEvaluator.getInstance();

    public static void storeY(double y) {
        evaluator.storeCalculatedY(y);
    }
}
