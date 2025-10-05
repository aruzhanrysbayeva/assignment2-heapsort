package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;

    public void reset() {
        comparisons = swaps = arrayAccesses = 0;
    }

    public void incrementComparisons() { comparisons++; }
    public void incrementSwaps() { swaps++; }
    public void incrementArrayAccesses() { arrayAccesses++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }

    public void exportToCSV(String filename, int inputSize, String algorithmName) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            java.io.File file = new java.io.File(filename);
            if (file.length() == 0) {
                writer.write("Algorithm,InputSize,Comparisons,Swaps,ArrayAccesses\n");
            }
            writer.write(String.format("%s,%d,%d,%d,%d\n",
                    algorithmName, inputSize, comparisons, swaps, arrayAccesses));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
