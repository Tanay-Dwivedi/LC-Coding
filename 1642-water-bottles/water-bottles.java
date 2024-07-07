class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int counter = numBottles;
        
        while (numBottles >= numExchange) {
            
            int remain = numBottles % numExchange;
            numBottles /= numExchange;
            counter += numBottles;
            numBottles += remain;
            
        }
        
        return counter;
    }
}