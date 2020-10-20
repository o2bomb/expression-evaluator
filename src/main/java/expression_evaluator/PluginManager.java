package expression_evaluator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import plugin_api.APIControl;
import plugin_api.Plugin;

public class PluginManager {
    private APIControl control;
    private List<Class<?>> pluginClasses;

    public PluginManager(APIControl control) {
        this.control = control;
        pluginClasses = new LinkedList<>();
    }

    public void loadPlugin(String pluginName) throws PluginLoadException {
        try {
            Class<?> pluginClass = Class.forName(pluginName);
            Plugin pluginObj = (Plugin) pluginClass.getConstructor().newInstance();

            pluginObj.start(control);
            pluginClasses.add(pluginClass);
        } catch (ClassNotFoundException e) {
            throw new PluginLoadException("Plugin could not be located");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new PluginLoadException("Plugin could not be loaded in");
        }
    }

    public List<Class<?>> viewPlugins() {
        return Collections.unmodifiableList(pluginClasses);
    }
}
