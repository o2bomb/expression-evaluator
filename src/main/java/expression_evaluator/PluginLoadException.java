package expression_evaluator;

/**
 * Describes an Exception that is thrown when PluginManager fails
 * to load in a plugin
 * 
 * @see PluginManager
 */
public class PluginLoadException extends Exception {
    public PluginLoadException(String message) {
        super(message);
    }
}
