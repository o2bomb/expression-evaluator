package csv_output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import plugin_api.APIControl;
import plugin_api.Plugin;
import plugin_api.YReceiver;

/**
 * A plugin that writes all results to a csv file (output.csv) in the root
 * directory of the project
 */
public class CSVOutput implements Plugin {
    private YReceiver receiver;

    private List<ResultTuple> results;

    public CSVOutput() {
        results = new ArrayList<>();

        receiver = new YReceiver() {
            @Override
            public void collect(double x, double y) {
                results.add(new ResultTuple(x, y));
                writeToCsv(x, y);
            }
        };
    }

    @Override
    public void start(APIControl control) {
        control.registerYReceiver(receiver);
    }

    private void writeToCsv(double x, double y) {
        // TODO: This code needs to be optimized. It works, but runs in O(n^2) time,
        // where n is the number of calculations made by the main program
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("output.csv"), CSVFormat.DEFAULT.withHeader("x, y"))) {
            for (ResultTuple r : results) {
                printer.printRecord(r.x, r.y);
            }
        } catch (IOException e) {
            System.out.println("[CSVOutput] Could not write to file: " + e.getMessage());
        }
    }

    private class ResultTuple {
        public double x;
        public double y;

        public ResultTuple(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
