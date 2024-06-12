class Solution {
  public void sortColors(int[] nums) {
    int l = 0;               // The next 0 should be placed in l.
    int r = nums.length - 1; // THe next 2 should be placed in r.

    for (int i = 0; i <= r;)
      if (nums[i] == 0)
        swap(nums, i++, l++);
      else if (nums[i] == 1)
        ++i;
      else
        swap(nums, i, r--);
  }

  private void swap(int[] nums, int i, int j) {
    final int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}