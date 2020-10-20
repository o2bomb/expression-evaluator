# Expression Evaluator
A Java program that evaluates simple mathematical equations

To run the program using a standard gradle terminal, use `./gradlew run`.

To run the programing using a clean terminal use `./gradlew run --console=plain` (**this is the recommended method**)

## Plugins
To load in a plugin, select `[1] Manage plugins` => `[2] Load new plugin`. When prompted, type in the name of a plugin to load in. Names must include the plugin's package, along with the class of the plugin (e.g. test_plugin.TestPlugin). I have included a few example plugins in this project:
- progress_indicator.ProgressIndicator 
- factorial_fibonnaci.FactorialFibonnaci

To include your own plugin in this gradle project, you must complete the following:
- the plugin must be built and compiled
- `settings.gradle` must include your plugin as a sub project
- `build.gradle` must specify your plugin as a dependency