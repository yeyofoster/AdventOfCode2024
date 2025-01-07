package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HistorianHysteria {
    public static final String INPUT_FILE = "Day1\\input.txt";

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        initializeLists(list1, list2);

        // Part 1 - TotalDistance
        // int totalDistance = getTotalDistance(list1, list2);
        // System.out.println(totalDistance);

        // Part 2 - SimilarityScore
        int similarity = getSimilarityScore(list1, list2);
        System.out.println(similarity);
    }

    /*
     * Function that receive 2 lists and calculate the similarity score between
     * their elements.
     */
    public static int getSimilarityScore(List<Integer> list1, List<Integer> list2) {
        int similarityScore = 0;
        HashMap<Integer, Integer> frequenciesMap = new HashMap<Integer, Integer>();

        // Getting frequencies of each number in list2.
        for (Integer number : list2) {
            frequenciesMap.put(number, frequenciesMap.getOrDefault(number, 0) + 1);
        }

        // Calculating similarityScore using the numbers in list1 and the frequenciesMap.
        for (Integer number : list1) {
            similarityScore += number * frequenciesMap.getOrDefault(number, 0);
        }

        return similarityScore;
    }

    /*
     * Function that receive 2 lists and calculate the distance between their
     * elements.
     */
    public static int getTotalDistance(List<Integer> list1, List<Integer> list2) {
        int totalDistance = 0;

        // Sort both array to match the corresponding ID between both list.
        Collections.sort(list1);
        Collections.sort(list2);

        // Calculating the total distance.
        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }

        return totalDistance;
    }

    /*
     * Function that initialize both list using the input.txt file.
     */
    public static void initializeLists(List<Integer> list1, List<Integer> list2) {
        Path filePath = Paths.get(INPUT_FILE);

        // Start reading input file line by line.
        try {
            Files.lines(filePath).forEach(line -> {
                // Getting the numbers from the line and adding them to their corresponding list.
                String[] numbers = line.split("\\D+");

                list1.add(Integer.parseInt(numbers[0]));
                list2.add(Integer.parseInt(numbers[1]));
            });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}