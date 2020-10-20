package test_plugin;

import java.util.*;

import plugin_api.APIControl;
import plugin_api.ScriptModifier;
import plugin_api.Plugin;
import plugin_api.YNotifier;
import plugin_api.YReceiver;

public class TestPlugin implements Plugin {
    private YNotifier notifier;
    private YReceiver receiver;
    private ScriptModifier modifier;
    private List<Long> yTimes;
    private List<Double> yValues;

    public TestPlugin() {
        yTimes = new ArrayList<>();
        yValues = new ArrayList<>();

        notifier = new YNotifier() {
            @Override
            public void notify(long timeAtLaunch, long currentTime, int yCount) {
                yTimes.add(currentTime);
            }
        };

        receiver = new YReceiver() {
            @Override
            public void collect(double y) {
                yValues.add(y);
            }
        };

        modifier = new ScriptModifier(){
            @Override
            public String modifyScript(String script) {
                return script
                 + "def foobar(num):\n"
                 + "    return 69";
            }
        };
    }

    @Override
    public void start(APIControl control) {
        control.registerYNotifier(notifier);
        control.registerYReceiver(receiver);
        control.registerScriptModifier(modifier);
    }
}