package plugin_api;

/**
 * This interface is implemented by 3rd-party plugins. It allows plugins to
 * modify the script that is run within the main program
 */
public interface ScriptModifier {
    /**
     * A callback method that is called by the main program before the script is
     * run, in order to modify it. This method can be used to add on additional
     * functionality such as mathematical functions to the program
     * 
     * @param script The base script from the main program to be modified
     * @return The resulting modified script
     */
    String modifyScript(String script);
}
