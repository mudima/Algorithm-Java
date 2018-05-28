package Leetcode;

public class ImplementTrie {
	class TrieNode {
		public boolean isLeaf = false;
		public char val;
		public TrieNode[] children = new TrieNode[26];
		
		//initial data structure.
		public TrieNode() {
			
		}
		public TrieNode(char value) {
			this.val = value;
		}
	}
	
	private TrieNode root;
	
	public ImplementTrie() {
		root = new TrieNode();
	}
	
	//Inserts a word into the trie.
	public void insert(String word) {
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if (cur.children[idx] == null) {
				cur.children[idx] = new TrieNode(word.charAt(i));
			}
			cur = cur.children[idx];
		}
		cur.isLeaf = true;
	}
	
	//Return true if the word is in the trie.
	public boolean search(String word) {
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if (cur.children[idx] == null) {
				return false;
			} else {
				cur = cur.children[idx];
			}
		}
		return cur.isLeaf;
	}
	
	//Return true if there is any word in the trie that start with given prefix
	public boolean startWith(String prefix) {
		TrieNode cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			int idx = prefix.charAt(i) - 'a';
			if (cur.children[idx] == null) {
				return false;
			} else {
				cur = cur.children[idx];
			}
		}
		return true;
	}
}
