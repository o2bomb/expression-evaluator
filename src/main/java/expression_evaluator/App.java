/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package expression_evaluator;

import java.util.Scanner;

public class App {
    private static EquationEvaluator evaluator = EquationEvaluator.getInstance();
    private static PluginManager pluginManager = new PluginManager(evaluator);

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Expression Evaluator.");

        inputLoop: while (true) {
            try {
                System.out.println("---------------------------------------------");
                System.out.println("[1] Manage plugins");
                System.out.println("[2] Run equation evaluator");
                System.out.println("[0] Exit program");
                System.out.print("Please enter a choice: ");
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                System.out.println(String.format("You have selected [%d]", choice));

                switch (choice) {
                    case 0:
                        // Exit input loop/program
                        break inputLoop;
                    case 1:
                        clearTerminal();
                        // Manage plugins
                        runPluginManager();
                        break;
                    case 2:
                        clearTerminal();
                        // Evaluate equation
                        runEquationEvaluator();
                        break;
                    default:
                        clearTerminal();
                        // Choice is invalid; continue loop
                        System.out.println("Invalid input, enter a number from 0 to 2");
                        break;
                }
            } catch (NumberFormatException e) {
                clearTerminal();
                // Choice is invalid; continue loop
                System.out.println("Invalid input, enter a number from 0 to 2");
                continue;
            }
        }
        sc.close();
    }

    /**
     * Runs the plugin manager submenu
     */
    private static void runPluginManager() {
        inputLoop: while (true) {
            try {
                // Get user input for loading in a plugin or showing all loaded plugins
                System.out.println("---------------------------------------------");
                System.out.println("[1] Show all plugins");
                System.out.println("[2] Load new plugin");
                System.out.println("[0] Back");
                System.out.print("Please enter a choice: ");
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                System.out.println(String.format("You have selected [%d]", choice));

                switch (choice) {
                    case 0:
                        // Exit current input loop
                        clearTerminal();
                        break inputLoop;
                    case 1:
                        clearTerminal();
                        // Show all plugins
                        if (pluginManager.viewPlugins().size() > 0) {
                            System.out.println("Currently loaded plugins:");
                            for (Class<?> p : pluginManager.viewPlugins()) {
                                System.out.println(p.getName());
                            }
                        } else {
                            System.out.println("No plugins loaded. Try loading one in and try again");
                        }
                        break;
                    case 2:
                        clearTerminal();
                        // Load in new plugin
                        loadPlugin();
                        break;
                    default:
                        clearTerminal();
                        // Choice is invalid; continue loop
                        System.out.println("Invalid input, enter a number from 0 to 2");
                        break;
                }
            } catch (NumberFormatException e) {
                clearTerminal();
                System.out.println("Invalid input, enter a number from 0 to 2");
                continue;
            }
        }
    }

    /**
     * Runs the equation evaluator program
     */
    private static void runEquationEvaluator() {
        // Get user input for expression, minX, maxX and incX
        System.out.print("Enter a valid expression with x as the variable: ");
        String expression = sc.nextLine();
        int minX, maxX, incX;
        while (true) {
            try {
                System.out.print("Enter a minX: ");
                minX = Integer.parseInt(sc.nextLine());
                System.out.print("Enter a maxX (must be larger than minX): ");
                maxX = Integer.parseInt(sc.nextLine());
                if (minX > maxX) {
                    throw new IllegalArgumentException("maxX must be smaller than minX");
                }
                System.out.print("Enter an incX (must be a positive integer): ");
                incX = Integer.parseInt(sc.nextLine());
                if (incX <= 0) {
                    throw new IllegalArgumentException("incX must be a positive integer");
                }
                break;
            } catch (NumberFormatException e) {
                clearTerminal();
                System.out.println("minX, maxX and incX must be valid integers");
                System.out.println("Please try again.");
                continue;
            } catch (IllegalArgumentException e) {
                clearTerminal();
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
                continue;
            }
        }

        // Execute python script to fetch values of y
        evaluator.runEvaluator(expression, minX, maxX, incX);
        evaluator.reset();
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void loadPlugin() {
        System.out.print("Enter the plugins full name (including package): ");
        String pluginName = sc.nextLine();
        try {
            // Attempt to load plugin in
            pluginManager.loadPlugin(pluginName);
        } catch (PluginLoadException e) {
            System.out.println(e.getMessage());
        }
    }
}
