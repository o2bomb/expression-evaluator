package progress_indicator;

import plugin_api.APIControl;
import plugin_api.Plugin;
import plugin_api.YNotifier;

/**
 * A plugin that provides running progress indicator for the main program
 */
public class ProgressIndicator implements Plugin {
    private APIControl control;

    private YNotifier notifier;

    public ProgressIndicator() {
        notifier = new YNotifier() {
            @Override
            public void notify(long timeAtLaunch, long currentTime, int yCount) {
                double minX = control.getMinX();
                double maxX = control.getMaxX();
                double incX = control.getIncX();
                double percentCompleted = Math.min(100.0 * ((double) yCount / ((maxX - minX) / incX)), 100.0);
                System.out.println(
                        String.format("[ProgressIndicator] %.2f%% | Time elapsed: %dms", percentCompleted, currentTime - timeAtLaunch));
            }
        };
    }

    @Override
    public void start(APIControl control) {
        this.control = control;
        control.registerYNotifier(notifier);
    }
}
