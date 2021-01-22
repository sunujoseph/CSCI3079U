public class PrefixNode {
  private char character;
  private int frequency;
  
  private PrefixNode left = null;
  private PrefixNode right = null;
  private PrefixNode parent = null;
  
  public PrefixNode(char c) {
     character = c;
     frequency = 0;
	 left = null;
	 right = null;
  }
  
  public char getCharacter() { return character; }
  public int getFrequency() { return frequency; }
  public void setFrequency(int freq) { frequency = freq; }
  public void increment() { frequency++; }
  
  public void setLeft(PrefixNode left) { this.left = left; }
  public PrefixNode getLeft() { return left; }
  public void setRight(PrefixNode right) { this.right = right; }
  public PrefixNode getRight() { return right; }
  public void setParent(PrefixNode parent) { this.parent = parent; }
  public PrefixNode getParent() { return parent; }
  
  public void printNode(int depth) {
     if (left != null) {
        left.printNode(depth + 1);
	 }
	 
	 // print the indent
	 for (int i = 0; i < depth; i++) {
	    System.out.print("   ");
	 }
	 
	 // print the node itself
	 System.out.println(getCharacter() + " (freq:" + getFrequency() + ", prefix: "+getPrefix()+")");
	 
	 if (right != null) {
        right.printNode(depth + 1);
	 }
  }
  
  public String getPrefix() {
	 PrefixNode parent = getParent();
	 if (parent == null) {
	    return "";
	 } else if (parent.getLeft() == this) {
	    return parent.getPrefix() + "0";
	 } else if (parent.getRight() == this) {
	    return parent.getPrefix() + "1";
	 } else {
	    return null; // unexpected condition
	 }
  }
  
  public PrefixNode findNode(char ch) {
     PrefixNode foundNode = null;
	 
	 if (getCharacter() == ch) {
	    return this;
	 }
	 
     if (left != null) {
	    foundNode = left.findNode(ch);
		if (foundNode != null) {
		   return foundNode;
		}
	 }
	 
	 if (right != null) {
	    foundNode = right.findNode(ch);
	 }
	 
	 return foundNode;
  }

  public String toString() {
     return getCharacter() + " (freq:" + getFrequency() + ", prefix:"+getPrefix()+")";
  }  
}
