package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {
    public static final String INPUT_FILE = "Day2\\input.txt";

    public static void main(String[] args) {
        int safeReports = 0;
        List<List<Integer>> reports = getReports();

        for (List<Integer> report : reports) {
            if (validateReport(report)) {
                safeReports++;
            }
        }
        System.out.println(safeReports);
    }

    /*
     * Function that validate 2 rules in the report:
     * 1. The levels are either all increasing or all decreasing.
     * 2. Any two adjacent levels differ by at least one and at most three.
     */
    public static boolean validateReport(List<Integer> report) {
        // Initial validation for null values or less than 2 levels in the report.
        if (report == null || report.size() < 2) {
            return false;
        }

        boolean hasIncrease = false;
        boolean hasDecrease = false;

        for (int i = 0; i < report.size() - 1; i++) {
            int current = report.get(i);
            int next = report.get(i + 1);
            int difference = Math.abs(current - next);

            // Validate the range of difference between levels.
            if (difference < 1 || difference > 3) {
                return false;
            }

            // Validate if levels are increasing or decreasing;
            if (current < next) {
                hasIncrease = true;
            } else {
                hasDecrease = true;
            }

            // If levels are increasing and decreasing, the report is not valid.
            if (hasDecrease && hasIncrease) {
                return false;
            }
        }
        return true;
    };

    /*
     * Function that reads the reports from the input file and return it as a List
     * of reports.
     */
    public static List<List<Integer>> getReports() {
        List<List<Integer>> reports = new ArrayList<>();
        Path path = Paths.get(INPUT_FILE);
        try {
            Files.readAllLines(path).forEach(line -> {
                String[] numbersString = line.split(" ");
                List<Integer> listNumbers = new ArrayList<>();
                for (String numString : numbersString) {
                    listNumbers.add(Integer.parseInt(numString));
                }
                reports.add(listNumbers);
            });
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return reports;
    };
}
