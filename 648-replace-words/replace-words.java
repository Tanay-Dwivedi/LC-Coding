class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        return calculate(dict, sentence);
    }
    
    public String calculate(List<String> dict, String sentence) {
        Node root = new Node('*');
        for (String input : dict) {
            root.addToTrie(input);
        }
        
        String sA[] = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : sA) {
            String ret = root.getPrefix(s);
            if (ret == ""){
                sb.append(s);
            } else {
                sb.append(ret);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    
    public static class Node{
        Map<Character, Node> children= new HashMap<>();
        Character val;
        boolean isEnd;

        public Node(Character ch) {
            this.val = ch;
            this.isEnd = false;
        }

        public Node add(char input) {
            if (!children.containsKey(input)) {
                Node node = new Node(input);
                children.put(input, node);
                return node;
            } else{
                return children.get(input);
            }
        }

        public void addToTrie(String input) {
            char inputChars[] = input.toCharArray();
            Node node = this;
            for (char inputChar : inputChars) {
                node = node.add(inputChar);
            }
            node.isEnd = true;
        }

        public String getPrefix(String input) {
            char inputChars[] = input.toCharArray();
            Node node = this;
            StringBuilder sb = new StringBuilder();
            for (char inputChar : inputChars) {
                node = node.children.get(inputChar);
                if (node == null) return "";
                sb.append(inputChar);
                if (node.isEnd) {
                    return sb.toString();
                }
            }
            return sb.toString();
        }

    }
}