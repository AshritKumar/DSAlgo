package practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {
	
	private static class TrieNode{
		Map<Character,TrieNode> children;
		boolean eow;
		TrieNode(){
			this.eow = false;
			this.children = new HashMap<>();
		}
	}
	private TrieNode root;
	private int size = 0;
	private int totalNodes = 0;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode current = root;
		int wl = word.length();
		for(int k=0; k<wl; k++) {
			char c = word.charAt(k);
			//convert to lower case
			if(c < 97)
				c = (char)( c + 32);
			TrieNode n = current.children.get(c);
			if(n == null) {
				n = new TrieNode();
				current.children.put(c, n);
				++totalNodes;
			}
			current = n;
		}
		if(!current.eow) {
			current.eow = true;
			++size;
		}
	}
	
	public void insert(String[] words) {
		for(String w : words)
			insert(w);
	}
	
	public boolean delete(String word) {
		TrieNode current = root;
		boolean d = delete(current, word, 0);
		return d;
	}
	
	private boolean delete(TrieNode current, String word, int indx) {
		if(indx == word.length()) {
			if(!current.eow)
				return false;
			current.eow = false;
			--size;
			return current.children.size() == 0;
		}
		char c = word.charAt(indx);
		if(c < 97)
			c = (char)( c + 32);
		// get the child node in separate variable, do not store it in current. this child variable is recursively passed to delete, if child delete returns true we delete child from current
		TrieNode node = current.children.get(c);
		if(node == null)
			return false;
		boolean delCurrentNode = delete(node, word, indx+1);
		if(delCurrentNode) {
			current.children.remove(c);
			--totalNodes;
			return current.children.size() == 0;
		}
		
		return false;
	}

	public int getSize() {
		return size;
	}
	
	public int getTotalInternalNodes() {
		return totalNodes;
	}
	
	public List<String> getAsList() {
		ArrayList<String> wList = new ArrayList<String>();
		recursivePrint(root.children, "", wList);
		return wList;
	}
	
	private void recursivePrint(Map<Character, TrieNode> children, String c, ArrayList<String> words) {
		if(children.size() >0) {
			Set<Character> keys = children.keySet();
			for(Character c1 : keys) {
				TrieNode tn = children.get(c1);
				if(tn.eow) {
					words.add(c+c1);
					//System.out.println(c+c1);
				}
				recursivePrint(tn.children, c+c1,words);
			}
		}
	}
	
	public boolean search(String word, boolean partial) {
		TrieNode current = root;
		int i = 0;
		while(current != null && i < word.length()) {
			char c = word.charAt(i);
			//convert to lower case
			if(c < 97)
				c = (char)( c + 32);
			current = current.children.get(word.charAt(i));
			i++;
		}
		if(partial) {
			return current != null;
		}
		else if(current != null && i == word.length()) {
			return current.eow;
		}
		return false;
	}
	
	public List<String> getWordsWith(String word) {
		TrieNode current = root;
		int i = 0;
		while(current != null && i < word.length()) {
			char c = word.charAt(i);
			//convert to lower case
			if(c < 97)
				c = (char)( c + 32);
			current = current.children.get(word.charAt(i));
			i++;
		}
		ArrayList<String> al = new ArrayList<>();
		if(current != null) {
			if(current.eow)
				al.add(word);
			recursivePrint(current.children, word,al);
		}
		return al;
		
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert(new String[] {"ant","an", "a","At","boy","fan","man","made","ant","any", "was","Ate","funny","phone","promo","prone",
				"bot","fun","main","mad","movie","move","ace","acer","notification","note","notice","none","nun","come","cone","Cote"});
		//t.getAsList();
		System.out.println(t.getTotalInternalNodes());
		System.out.println(t.search("ma", true));
		System.out.println(t.getWordsWith(""));
		System.out.println(t.getSize());
		t.delete("ant");
		System.out.println(t.getWordsWith(""));
		System.out.println(t.getSize());
		System.out.println(t.getTotalInternalNodes());
	}

}
