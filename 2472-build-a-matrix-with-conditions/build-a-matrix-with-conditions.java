class Solution {
    List<Integer>[] al;
    
    public int[][] buildMatrix(int k, int[][] rc, int[][] cc) {
        
        int ans[][] = new int[k][k];
        this.al = new ArrayList[k+1];
        
        for(int i=0;i<=k;i++) al[i] = new ArrayList<>();
        for(int r[] : rc) al[r[0]].add(r[1]);
        
        boolean[] vis = new boolean[k+1];
        boolean dfsvis[] = new boolean[k+1];
        List<Integer> rowStack = new ArrayList<>();
        
        for(int node=1;node<=k;node++){
            if(!vis[node]) {
                if(!dfs(node,vis,rowStack,dfsvis)) return new int[0][0];
            }
        }

        for(int i=0;i<=k;i++) al[i] = new ArrayList<>();
        for(int c[] : cc) al[c[0]].add(c[1]);
        List<Integer> colStack = new ArrayList<>();
        
        vis = new boolean[k+1];
        dfsvis = new boolean[k+1];
        
        for(int node=1;node<=k;node++){
            if(!vis[node]) {
                if(!dfs(node,vis,colStack,dfsvis)) return new int[0][0];
            }
        }
        
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int i=0;i<colStack.size();i++){
            map.put(colStack.get(i),i);
        }
        
        int row = 0;
        
        for(int i=rowStack.size()-1;i>=0;i--){
			// should place this value leaving required cells ahead for other values to come to make column condition true. 
            ans[row++][k-map.get(rowStack.get(i))-1] = rowStack.get(i);
        }
        
        return ans;
    
    }
    
	// toposort dfs along with cycle check (returns false if cycle exists)
    private boolean dfs(int node,boolean[] vis,List<Integer> stack,boolean[] dfsvis){
        vis[node] = true;
        dfsvis[node] = true;
        for(int next : al[node]){
            if(!vis[next]) {
                if(!dfs(next,vis,stack,dfsvis)) return false;
            }else if(dfsvis[next]) return false;
        }
        stack.add(node);
        dfsvis[node] = false;
        return true;
    }
}

