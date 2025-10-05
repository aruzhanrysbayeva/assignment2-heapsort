package cli;

import algorithms.HeapSort;
import metrics.PerformanceTracker;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        int[] sizes = {100,1000,10000,100000};
        Random rand = new Random();

        FileWriter csvWriter = new FileWriter("results.csv");
        csvWriter.append("Algorithm,InputSize,Time(ms),Comparisons,Swaps,ArrayAccesses\n");

        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = rand.nextInt(100000);

            PerformanceTracker tracker = new PerformanceTracker();
            long startTime = System.nanoTime();

            HeapSort.sort(arr, tracker);

            long endTime = System.nanoTime();

            double timeMs = (endTime - startTime) / 1_000_000.0;

            csvWriter.append(String.format(
                    "HeapSort,%d,%.3f,%d,%d,%d\n",
                    size, timeMs, tracker.getComparisons(), tracker.getSwaps(), tracker.getArrayAccesses()
            ));
        }

        csvWriter.close();
        System.out.println("Benchmark complete. Results saved to results.csv");
    }
}
