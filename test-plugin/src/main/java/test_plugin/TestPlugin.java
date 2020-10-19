package test_plugin;

import java.util.*;

import plugin_api.APIControl;
import plugin_api.Plugin;
import plugin_api.YNotifier;
import plugin_api.YReceiver;

public class TestPlugin implements Plugin {
    private YNotifier notifier;
    private YReceiver receiver;
    private List<Long> yTimes;
    private List<Integer> yValues;

    public TestPlugin() {
        yTimes = new ArrayList<>();
        yValues = new ArrayList<>();

        notifier = new YNotifier() {
            @Override
            public void notify(long timeSinceStart) {
                yTimes.add(timeSinceStart);
            }
        };

        receiver = new YReceiver() {
            @Override
            public void collect(int y) {
                yValues.add(y);
            }
        };
    }

    @Override
    public void start(APIControl control) {
        control.registerYNotifier(notifier);
        control.registerYReceiver(receiver);
    }

    public static int foo(int val1, int val2) {
        return val1 + val2;
    }
}