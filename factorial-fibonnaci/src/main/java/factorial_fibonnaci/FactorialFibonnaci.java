package factorial_fibonnaci;

import plugin_api.APIControl;
import plugin_api.Plugin;
import plugin_api.ScriptModifier;

public class FactorialFibonnaci implements Plugin {
    private ScriptModifier modifier;

    public FactorialFibonnaci() {
        modifier = new ScriptModifier() {
			@Override
			public String modifyScript(String script) {
                String modifiedScript = script;
                modifiedScript += "\n"
                + "def fibonnaci(n):\n"
                + "    if n <= 0: return 0\n"
                + "    elif n == 1: return 0\n"
                + "    elif n == 2: return 1\n"
                + "    else: return fibonnaci(n-1) + fibonnaci(n-2)\n"
                + "def factorial(n):\n"
                + "    result = 1\n"
                + "    for i in range(1, n + 1):\n"
                + "        result = result * i\n"
                + "    return result\n";

                return modifiedScript;
            }
        };
    }

    @Override
    public void start(APIControl control) {
        control.registerScriptModifier(modifier);
    }
}
