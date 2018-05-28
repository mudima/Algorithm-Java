package Leetcode;

import Leetcode.ImplementTrie.TrieNode;

public class AddAndSearchWord {
	class TrieNode {
		public boolean isLeaf = false;
		public char val;
		public TrieNode[] children = new TrieNode[26];
		
		public TrieNode(char value) {
			this.val = value;
		}
		
		public boolean search(String word) {
			if (word.length() == 0) return isLeaf;
			
			char start = word.charAt(0);
			if (start >= 'a' && start <= 'z' && children[start - 'a'] != null) {
				return children[start - 'a'].search(word.substring(1));
			} else if (start == '.') {
				for (TrieNode n : children) {
					if (n != null && n.search(word.substring(1))) return true;
				}
				return false;
			} else {
				return false;
			}
		}
	}
	
	private TrieNode root;
	
	public AddAndSearchWord() {
		this.root = new TrieNode('\0');
	}
	
	//Adds a word into the trie.
	public void addWord(String word) {
		TrieNode cur = root;
		while (word.length() != 0) {
			TrieNode t;
			char start = word.charAt(0);
			int idx = start - 'a';
			if (cur.children[idx] != null) {
				t = cur.children[idx];
			} else {
				t = new TrieNode(start);
				cur.children[idx] = t;
			}
			cur = t;
			word = word.substring(1);
		}
		cur.isLeaf = true;
	}
	
	//returns if the word is in the trie.
	//A word could contains '.' to represent any one letter.
	public booelan search(String word) {
		return root.search(word);
	}
}
