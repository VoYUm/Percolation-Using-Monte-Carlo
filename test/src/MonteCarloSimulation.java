import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MonteCarloSimulation {

    public MonteCarloSimulation(Perc p) {
        simulationModel = p;
    }

    public Map<Integer, Integer> simulation(int numOfSimulations) {
        Map<Integer, Integer> pointsOpenToTimesPercolated = new HashMap<>();
        int n = simulationModel.getSideLength();
        for (int i = n; i < n * (n - 1) + 1; i++) {
            for (int j = 0; j < numOfSimulations; j++) {
                Perc p = new Perc(n, i);
                if (p.percolates()) {
                    if (pointsOpenToTimesPercolated.containsKey(i)) {
                        int currentValue = pointsOpenToTimesPercolated.get(i);
                        pointsOpenToTimesPercolated.put(i, currentValue + 1);
                    } else {
                        pointsOpenToTimesPercolated.put(i, 1);
                    }
                }
            }
        }
        Map<Integer, Integer> to_return = new TreeMap<>(pointsOpenToTimesPercolated);
        return to_return;
    }


//    public static void plotHistogram(Map<Integer, Integer> data, int maxValue) {
//        for (int i = 0; i <= maxValue; i++) {
//            StringBuilder row = new StringBuilder();
//            for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
//                int key = entry.getKey();
//                int value = entry.getValue();
//                if (value >= (maxValue - i + 1)) {
//                    Pattern pattern = Pattern.compile("o");
//                    Matcher matcher = pattern.matcher(row);
//                    if (!matcher.find()) {
//                        row.append("o");
//                    }
//                } else {
//                    row.append(" ");
//                }
//            }
//            System.out.println(row);
//        }
//
//        // Print x-axis labels
////        StringBuilder xAxis = new StringBuilder();
////        for (int key : data.keySet()) {
////            xAxis.append("-");
////        }
////        System.out.println(xAxis);
////        System.out.println("X-axis labels: " + data.keySet());
////        System.out.println("Y-axis represents frequency");
//    }

    public static double percolationThreshold(Map<Integer, Integer> map, int bondThreshold) {
        double closestKey = 0;
        int closestDiff = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            int diff = Math.abs(value - bondThreshold);

            if (diff < closestDiff) {
                closestDiff = diff;
                closestKey = entry.getKey();
            }
        }

        return closestKey;
    }

    public static void main(String[] args) {
        int sideLength = 50;
        int numOfSimulations = 100;

        Perc p = new Perc(sideLength);
        MonteCarloSimulation m = new MonteCarloSimulation(p);
        Map<Integer, Integer> pointsOpenToTimesPercolated = m.simulation(numOfSimulations);

        for (Map.Entry<Integer, Integer> entry : pointsOpenToTimesPercolated.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("number of open sites: " + key + ", percolation probability: " + (double) value * 100 / (double) numOfSimulations + "%");
        }

        System.out.println("Percolation threshold:" + percolationThreshold(pointsOpenToTimesPercolated, numOfSimulations / 2) * 100 / (double) (sideLength * sideLength));


//        int maxValue = pointsOpenToTimesPercolated.values().stream().max(Integer::compareTo).orElse(0);
//
//        // Plot the histogram
//        plotHistogram(pointsOpenToTimesPercolated, maxValue);

    }

    private final Perc simulationModel;

}


