package plugin_api;

/**
 * This interface is implemented by the main application. It specifies methods
 * used for retrieving the four input values for evaluating an expression, and
 * also methods to register callback methods implemented by 3rd-party plugins
 */
public interface APIControl {
    /**
     * Registers a YNotifier in the main application
     * 
     * @param notifier A YNotifier containing the callback method
     * @see YNotifier
     */
    void registerYNotifier(YNotifier notifier);

    /**
     * Registers a YReceiver in the main application
     * 
     * @param receiver A YReceiver containing the callback method
     * @see YReceiver
     */
    void registerYReceiver(YReceiver receiver);

    /**
     * Retrieves the expression to be evaluated
     * 
     * @return The expression to be evaluated
     */
    String getExpression();

    /**
     * Retrieves the minimum value of x
     * 
     * @return The minimum value of x
     */
    int getMinX();

    /**
     * Retrieves the maximum value of x
     * 
     * @return The maximum value of x
     */
    int getMaxX();

    /**
     * Retrieves the increment value
     * 
     * @return The increment value
     */
    int getIncX();
}
