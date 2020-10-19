package plugin_api;

/**
 * <p>
 * This interface <b>must</b> be implemented by all 3rd-party plugins. It
 * contains a <code>start()</code> method that acts as the entry point to the
 * plugin's code.
 * </p>
 * 
 * <p>
 * Plugins may also add methods that act as mathematical functions that are used
 * in expressions. To implement one, the method <b>must</b>:
 * <ul>
 * <li>Be a static method</li>
 * <li>The name of the method must be the same as the name of the mathematical
 * function that is contained within the expression that has been invoked</li>
 * </ul>
 * </p>
 */
public interface Plugin {
    /**
     * A method that acts as the entry point for the plugin's code. This method
     * receives an <b>APIControl</b> object that can be used to register various
     * callback methods, and retrieve data about the expression to be evaluated
     * 
     * @param control An object that allows the plugin to interact with the main
     *                program
     */
    void start(APIControl control);
}
