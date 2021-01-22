/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pious Joseph
 */
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
public class assignment_2_4 {
    
    //Sorted List of Characters with their corresponding frequencies
    static LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
    
    //Holds the Characters with their corresponding Prefixes
    static TreeMap<Character, String> prefix = new TreeMap<>();    
    
    //Holds the Tree of Huffman's Algorithm to compare the 2 tree nodes of the Node Class 
    static PriorityQueue<Node> nodes = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            if(node1.value < node2.value){
               return -1; 
            }
            else{
                return 1;
            }
        }
    });
    
   
    
    public static void main(String[] args) throws IOException{
        
        //Obtain the file which is named "file.txt"
        //Output the file contents into a String called s
        //Put the string into a HashMap to hold the character
        //and count the frequencies of each character
        //then store the corresponding character with the frequencies
        FileReader freader = new FileReader("file.txt");  
        BufferedReader br = new BufferedReader(freader); 
        HashMap<Character,Integer> frequencies = new HashMap<>();
        String s;  
        
        System.out.println("=== Input of string ===");
        while((s = br.readLine()) != null) {  
            System.out.println(s); 
            for (char ch : s.toCharArray()) {
                frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
            }
        }
        
        
        //Output the current List of characters with their corresponding frequencies
        //This list is unsorted
        System.out.println("===================================================================");
        //Base List UnSorted--------------------------------------------------------------------
        System.out.println("=== List of Frequencies ===");
        for (HashMap.Entry<Character, Integer> entry : frequencies.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        
        //Output the Sorted List of characters with their corresponding frequencies
        //Least to Greatest based on Frequencies
        System.out.println("===================================================================");
        System.out.println("=== Sorted List ===");
        //Sorted List-------------------------------------------------------------------------
        sortedMap=sortHashMapByValues(frequencies);
        for (HashMap.Entry<Character, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
            
            //Add the Character with their corresponding Frequencies from
            //the sorted list for each node in the Node class
            
            nodes.add(new Node(entry.getValue(), entry.getKey().toString()));
        }
        //Build Huffman Tree 
        //The list of nodes are ordered from least to greatest from sortMap
        //using PriorityQueue and .poll() to create the Huffman Tree
       while (nodes.size() > 1){
           
          nodes.add(new Node(nodes.poll(), nodes.poll()));
        }

        //Use getPrefix to get the prefix method
        getPrefix(nodes.peek(), "");
        
        
        System.out.println("===================================================================");
        //Print Prefix
        System.out.println("=== Prefix ===");
        prefix.forEach(new BiConsumer<Character, String>() {
            @Override
            public void accept(Character k, String v) {
                System.out.println(k + " : " + v);
            }
        });
        
        
        freader.close();    
    }
    
    //sortMap is used to sort the Hashmap based on frequencies from least to greatest
    public static LinkedHashMap<Character, Integer> sortHashMapByValues(HashMap<Character, Integer> passedMap) {
    List<Character> mapKeys = new ArrayList<>(passedMap.keySet());
    List<Integer> mapValues = new ArrayList<>(passedMap.values());
    Collections.sort(mapValues);
    Collections.sort(mapKeys);
    LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();

    Iterator<Integer> valueIt = mapValues.iterator();
    while (valueIt.hasNext()) {
        int val = valueIt.next();
        Iterator<Character> keyIt = mapKeys.iterator();

        while (keyIt.hasNext()) {
            char key = keyIt.next();
            int comp1 = passedMap.get(key);
            int comp2 = val;

            if (comp1==comp2) {
                keyIt.remove();
                sortedMap.put(key, val);
                break;
            }
        }
    }
    return sortedMap;
}
    
    
    //Get Prefix and update the node left/right branch  
    //have the prefix be in a string since i can combine the characters of 0s and 1s
    //   
    
     static void getPrefix(Node node, String s) {
        if (node != null) {
            if (node.left != null){
                getPrefix(node.left, s + "0");
            }
            
            if (node.right != null){
               getPrefix(node.right, s + "1"); 
            }
            if (node.left == null && node.right == null){
                prefix.put(node.character.charAt(0), s);//character key and string
            }        
        }
    }
    

    
   
    
}









class Node {
    Node left, right;
    int value;
    String character;

    public Node(int value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}