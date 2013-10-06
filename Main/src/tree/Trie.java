package tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Trie {
	
	public static class TrieNode{
		private char val;
		private TrieNode[] childNodes;
		private int freq;
		private HashSet<Integer> preSet;
		
		public TrieNode(){
			childNodes = new TrieNode[26];
			preSet = new HashSet<Integer>();
		}
	}
	
	private TrieNode tree;
	public Trie(){
		tree = new TrieNode();
	}
	
	
	public void insert(String word, int id){
		insert(tree, word, id);
	}
	
	private void insert(TrieNode tree, String word, int id){
		if(word == null || word.length() == 0)
			return;
		
		int pos = word.charAt(0) - 'a';
//		System.out.println("Cur word: " + word + "	Cur pos: " + pos + "	id: " + id);
		if(tree.childNodes[pos] == null){
			tree.childNodes[pos] = new TrieNode();
			tree.childNodes[pos].val = word.charAt(0);
		}
		
		tree.childNodes[pos].preSet.add(id);
		String nextWord = word.substring(1);
		if(nextWord.length() == 0){
			tree.childNodes[pos].freq ++;
		}
		
		insert(tree.childNodes[pos], nextWord, id);
	}
	
	public void delete(String word, int id){
		delete(tree, word, id);
	}
	
	private void delete(TrieNode tree, String word, int id){
		if(word == null || word.length() == 0)
			return;
		
		int pos = word.charAt(0) - 'a';
		if(tree.childNodes[pos] == null)
			return;
		
		tree.childNodes[pos].preSet.remove(id);
		String nextWord = word.substring(1);
		if(nextWord.length() == 0){
			if(tree.childNodes[pos].freq > 0)
				tree.childNodes[pos].freq --;
		}
		
		delete(tree.childNodes[pos], nextWord, id);
	}
	
	/**
	 * Count word occurrence.
	 * @param word
	 * @return
	 */
	public HashSet<Integer> search(String word){
		return search(tree, word);
	}
	
	private HashSet<Integer> search(TrieNode tree, String word){
		if(word == null || word.length() == 0)
			return null;
		int pos = word.charAt(0) - 'a';
		if(tree.childNodes[pos] == null)
			return null;
		else{
			String nextWord = word.substring(1);
			if(nextWord.length() == 0)
				return tree.childNodes[pos].preSet;
			
			return search(tree.childNodes[pos], nextWord);
		}
	}
	
	public int wordCount(String word){
		return wordCount(tree, word);
	}
	
	private int wordCount(TrieNode tree, String word){
		if(word == null || word.length() == 0)
			return 0;
		
		int pos = word.charAt(0) - 'a';
		if(tree.childNodes[pos] == null)
			return 0;
		else{
			String nextWord = word.substring(1);
			if(nextWord.length() == 0)
				return tree.childNodes[pos].freq;
			return wordCount(tree.childNodes[pos], nextWord);
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("TrieTest.txt");
		FileReader fr = new FileReader(f);
		BufferedReader bir = new BufferedReader(fr);
		
		Trie t = new Trie();
		String curLine = null;
		while((curLine = bir.readLine()) != null){
			String[] strArr = curLine.split(" ");
			String word = strArr[1].trim();
			int id = Integer.valueOf(strArr[0]);
			t.insert(word.toLowerCase(), id);
		}
		
		System.out.println("Occurrence count of word \"go\":" + t.wordCount("go"));
		HashSet<Integer> preList = t.search("go");
		Iterator<Integer> ite = preList.iterator();
		while(ite.hasNext()){
			System.out.println("Current id: " + ite.next());
		}
	}

}
