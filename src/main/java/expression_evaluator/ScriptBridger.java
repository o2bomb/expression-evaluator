package expression_evaluator;

/**
 * Contains methods that the script will call in order to interact
 * with the main program
 */
public class ScriptBridger {
    

    public static void storeY(double y) {
        System.out.println("CALCULATED VALUE OF Y: " + y);
    }
}
