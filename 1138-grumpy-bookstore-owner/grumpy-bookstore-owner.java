class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int totalGrumpyCustomers = 0; // This will hold the total number of customers affected by grumpiness
        int totalCustomersSatisfied = 0; // Sum of all customers that are satisfied
        int numCustomers = customers.length; // Total number of customers
      
        // Calculate the initial total number of customers that are satisfied and the total affected by grumpiness
        for (int i = 0; i < numCustomers; ++i) {
            if (grumpy[i] == 1) {
                // If the owner is grumpy during customer i's visit, add to totalGrumpyCustomers
                totalGrumpyCustomers += customers[i];
            }
            // Add to the total number of satisfied customers regardless of grumpiness
            totalCustomersSatisfied += customers[i];
        }
      
        int temporaryGrumpyCustomers = 0; // Temporary variable to keep track of grumpy customers during the 'minutes' window
        int maxGrumpyCustomersConverted = 0; // Maximum number of grumpy customers that can be converted to satisfied customers
        for (int i = 0; i < numCustomers; ++i) {
            // If the owner is grumpy, temporarily add this customer to the count
            if (grumpy[i] == 1) {
                temporaryGrumpyCustomers += customers[i];
            }
            // If we are beyond the 'minutes' window, subtract the customers at the start of the window
            if (i >= minutes) {
                if (grumpy[i - minutes] == 1) {
                    temporaryGrumpyCustomers -= customers[i - minutes];
                }
            }
          
            // Store the higher value between the current and previous maximum number of convertible grumpy customers
            maxGrumpyCustomersConverted = Math.max(maxGrumpyCustomersConverted, temporaryGrumpyCustomers);
        }
      
        // Calculate final answer: Subtract the initial grumpy customers from the total customers satisfied and add the convertible grumpy customers
        return totalCustomersSatisfied - totalGrumpyCustomers + maxGrumpyCustomersConverted;
    }
}