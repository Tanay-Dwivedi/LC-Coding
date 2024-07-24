class Pair{
    int f;
    int s;

    Pair(int f,int s){
        this.f=f;
        this.s=s;
    }
}
class Solution {

    public int[] sortJumbled(int[] mapping, int[] nums) {

        int newa[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int a=0;

            int temp=nums[i];
            int ten=1;
            if(temp==0){
                newa[i]=mapping[0];
            }
            else{
            while(temp>0){
                int rem=temp%10;
                a+=(mapping[rem]*ten);
                temp=temp/10;
                ten=ten*10;
            }
            newa[i]=a;}
        }
         Pair p[]=new Pair[nums.length];
        for(int i=0;i<nums.length;i++){
               p[i]=new Pair(newa[i],nums[i]);
        }
        Arrays.sort(p,(a,b)->
            a.f-b.f);

        for(int i=0;i<nums.length;i++){
            nums[i]=p[i].s;
        }

        return nums;
    }
}