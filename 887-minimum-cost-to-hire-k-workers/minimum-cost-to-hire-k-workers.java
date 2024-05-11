import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
  
    // This class represents workers with their quality and wage ratio
    class Worker {
        double wageQualityRatio;
        int quality;
      
        Worker(int quality, int wage) {
            this.quality = quality;
            this.wageQualityRatio = (double) wage / quality;
        }
    }
  
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int numWorkers = quality.length;
        Worker[] workers = new Worker[numWorkers];
      
        // Create Worker instances combining both quality and wage.
        for (int i = 0; i < numWorkers; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
      
        // Sort the workers based on their wage-to-quality ratio.
        Arrays.sort(workers, (a, b) -> Double.compare(a.wageQualityRatio, b.wageQualityRatio));
      
        // Use a max heap to keep track of the highest quality workers.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        double minCost = Double.MAX_VALUE; // Initialize minimum cost to a large number.
        int totalQuality = 0; // Tracks the total quality of hired workers.
      
        // Iterate through the sorted workers by increasing wage-to-quality ratio.
        for (Worker worker : workers) {
            // Add the current worker's quality to the total.
            totalQuality += worker.quality;
            // Add the current worker's quality to the max heap.
            maxHeap.offer(worker.quality);
          
            // If we have enough workers (exactly k), we can try to form an answer.
            if (maxHeap.size() == k) {
                // Calculate the cost based on current worker's wage-to-quality ratio.
                minCost = Math.min(minCost, totalQuality * worker.wageQualityRatio);
                // Remove the worker with the highest quality (to maintain only k workers).
                totalQuality -= maxHeap.poll();
            }
        }
      
        return minCost; // Return the minimum cost found.
    }
}