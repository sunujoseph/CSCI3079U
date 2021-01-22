import java.io.*;
import java.util.*;

public class Huffman {
   private ArrayList<PrefixNode> prefixes;
   private String fileContents = null;
   
   public Huffman(String filename) {
      String contents = getFileContents(filename);
      System.out.println("Contents: " + contents);
	  this.fileContents = contents;
	  
	  prefixes = new ArrayList<PrefixNode>();
	  for (int i = 0; i < contents.length(); i++) {
	     // first, check if there is already a frequency
		 // for this character
		 char ch = contents.charAt(i);
		 boolean newChar = true;
		 for (int j = 0; j < prefixes.size(); j++) {
		    PrefixNode curr = prefixes.get(j);
			if (curr.getCharacter() == ch) {
			   newChar = false;
			   curr.increment();
			}
		 }
		 
		 if (newChar) {
		    PrefixNode newNode = new PrefixNode(ch);
			newNode.increment();
			prefixes.add(newNode);
		 }
	  }
   }

   public int getOriginalFileSize() {
      return fileContents.length() * 8;
   }
   
   public int getCompressedFileSize() {
      int fileSize = 0;
      for (int i = 0; i < fileContents.length(); i++) {
	     char ch = fileContents.charAt(i);
		 PrefixNode node = prefixes.get(0).findNode(ch);
		 String prefix = node.getPrefix();
		 fileSize += prefix.length();
	  }
	  return fileSize;
   }
   
   public void printFrequencies() {
      System.out.println("Frequencies:");
      for (int j = 0; j < prefixes.size(); j++) {
	     PrefixNode curr = prefixes.get(j);
		 System.out.printf("%c   %d\n", curr.getCharacter(), curr.getFrequency());
      }
   }
   
   public void printPrefixes() {
      if (prefixes.size() < 1) {
	     System.out.println("Invalid prefix tree");
		 return;
	  }
	  
      System.out.println("Prefixes:");
      PrefixNode root = prefixes.get(0);
	  root.printNode(0);
   }

   public void sortPrefixes() {
      Collections.sort(prefixes, new Comparator<PrefixNode>() {
         public int compare(PrefixNode a, PrefixNode b) {
            return a.getFrequency() - b.getFrequency();
         }
      });
   }

   public void calculatePrefixes() {
      sortPrefixes();
	  
	  while (prefixes.size() > 1) {
	     // remove the first two prefixes (the lowest frequency)
		 PrefixNode node1 = prefixes.get(0);
		 PrefixNode node2 = prefixes.get(1);
		 prefixes.remove(node1);
		 prefixes.remove(node2);
		 
		 // merge both prefixes into a single node
		 PrefixNode newNode = new PrefixNode('~');
		 newNode.setFrequency(node1.getFrequency() + node2.getFrequency());
		 newNode.setLeft(node1);
		 newNode.setRight(node2);
		 node1.setParent(newNode);
		 node2.setParent(newNode);
		 
		 // add the merged prefix into the list
		 prefixes.add(newNode);
		 
		 // sort the prefixes by frequency
		 sortPrefixes();
	  }
   }
   
   private String getFileContents(String filename) {
      try {
         File file = new File(filename);
		 System.out.println("file size: " + file.length());
         FileInputStream fis = new FileInputStream(file);
         byte[] data = new byte[(int)file.length()];
		 fis.read(data);
	     fis.close();
	     return new String(data);
	  } catch (Exception e) {
         e.printStackTrace();
	  }
	  return "";
   }
   
   public static void main(String[] args) {
     String filename = args[0];
	 
	 Huffman huffman = new Huffman(filename);
	 huffman.printFrequencies();
	 huffman.calculatePrefixes();
	 huffman.printPrefixes();
	 
	 System.out.println("Original size:   " + huffman.getOriginalFileSize());
	 System.out.println("Compressed size: " + huffman.getCompressedFileSize());
   }
}