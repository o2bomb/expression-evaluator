package plugin_api;

/**
 * This interface is implemented by 3rd-party plugins. It allows plugins to keep
 * track of when each individual y value has been calculated since the main
 * program started
 */
public interface YNotifier {
    /**
     * A callback method that is called by the main program every time a y value has
     * been calculated, specifying the time elapsed since the start of the
     * calculation of the first y value, and also the number of y values that have
     * been calculated
     * 
     * @param timeAtLaunch    The time (in milliseconds) of the start of calculation
     * @param currentTime     The time (in milliseconds) that has elapsed since the
     *                        start of the first calculation
     * @param calculatedCount The number y values that have been calculated
     */
    void notify(long timeAtLaunch, long currentTime, int yCount);
}
