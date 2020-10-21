package plugin_api;

/**
 * This interface is implemented by 3rd-party plugins. It allows plugins to
 * collect each calculated value of y from the main application
 */
public interface YReceiver {
    /**
     * A callback method that is called by the main program every time a y value has
     * been calculated, specifying the y value that has been calculated, and its
     * corresponding x value
     * 
     * @param x The corresponding x value
     * @param y The calculated y value
     */
    void collect(double x, double y);
}
