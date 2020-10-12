package expression_evaluator;

import plugin_api.PrinterOuter;

public class Printer implements PrinterOuter {

    @Override
    public void printSomething() {

        System.out.println("Why hello therer biatch");
    }
    
}
