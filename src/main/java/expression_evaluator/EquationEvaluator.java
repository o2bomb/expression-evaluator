package expression_evaluator;

import java.util.LinkedList;
import java.util.List;

import org.python.util.PythonInterpreter;

import plugin_api.APIControl;
import plugin_api.ScriptModifier;
import plugin_api.YNotifier;
import plugin_api.YReceiver;

public class EquationEvaluator implements APIControl {
    private List<YNotifier> notifiers;
    private List<YReceiver> receivers;
    private List<ScriptModifier> modifiers;

    private String expression;
    private int minX;
    private int maxX;
    private int incX;
    private long timeAtLaunch;
    private int yCount;

    public static EquationEvaluator instance = null;

    public static EquationEvaluator getInstance() {
        if (instance == null) {
            instance = new EquationEvaluator();
        }
        return instance;
    }

    private EquationEvaluator() {
        notifiers = new LinkedList<>();
        receivers = new LinkedList<>();
        modifiers = new LinkedList<>();

        expression = "";
        minX = 0;
        maxX = 0;
        incX = 0;
        timeAtLaunch = 0;
        yCount = 0;
    }

    public void runEvaluator(String expression, int minX, int maxX, int incX) {
        this.expression = expression;
        this.minX = minX;
        this.maxX = maxX;
        this.incX = incX;
        this.timeAtLaunch = System.currentTimeMillis();

        try (PythonInterpreter pyInter = new PythonInterpreter()) {
            App.clearTerminal();
            System.out.println("---------------------------------------------");
            System.out.println(String.format("Evaluating expression '%s'", expression));
            System.out.println(String.format("MIN-X: %d", minX));
            System.out.println(String.format("MAX-X: %d", maxX));
            System.out.println(String.format("INCREMENT: %d", incX));
            // Initialise script
            String script = initialiseScript();
            for (int i = minX; i <= maxX; i += incX) {
                // Replace x in expression with actual number and wrap it in float()
                String finalExpression = "\ntry:\n"
                        + String.format("    y = float(%s)\n", expression.replace("x", Integer.toString(i)))
                        + "except NameError, err:\n" + "    raise Exception(err)";
                // Append expression to script
                script += finalExpression;
                script += "\nstoreY(y)";
                // Execute the script
                try {
                    pyInter.exec(script);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Perhaps you forgot to load in a plugin?");
                    break;
                }
            }
        }
    }

    /**
     * Initializes the script and loads in all script modifications and additional
     * mathematical functions defined by 3rd-party plugins
     * 
     * @return The initialized script
     */
    private String initialiseScript() {
        String script = "from expression_evaluator.ScriptBridger import storeY\n";
        for (ScriptModifier m : modifiers) {
            script = m.modifyScript(script);
        }

        return script;
    }

    /**
     * This method is called whenever a y value has been calculated. All 3rd-party
     * plugins that listen for this event will be notified, and those that require y
     * values will receive it
     */
    public void storeCalculatedY(double y) {
        for (YReceiver r : receivers) {
            r.collect(y);
        }

        yCount++;
        for (YNotifier n : notifiers) {
            n.notify(timeAtLaunch, System.currentTimeMillis(), yCount);
        }
    }

    /**
     * Resets the evaluator's state
     */
    public void reset() {
        expression = "";
        minX = 0;
        maxX = 0;
        incX = 0;
        timeAtLaunch = 0;
        yCount = 0;
    }

    @Override
    public void registerYNotifier(YNotifier notifier) {
        notifiers.add(notifier);
    }

    @Override
    public void registerYReceiver(YReceiver receiver) {
        receivers.add(receiver);
    }

    @Override
    public void registerScriptModifier(ScriptModifier modifier) {
        modifiers.add(modifier);
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public int getMinX() {
        return minX;
    }

    @Override
    public int getMaxX() {
        return maxX;
    }

    @Override
    public int getIncX() {
        return incX;
    }
}
