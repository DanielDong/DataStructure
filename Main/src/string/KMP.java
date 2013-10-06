package string;

public class KMP {
	// In KMP, only mode's index needs modification every time
	// when target(i) != mode(j). target's index just advance by 1 step each time.
	// This is similar to Boyer-Moore algorithm.
	public void kmp(String target, String mode) {
		  int modeLength = mode.length();
		  int targetLength = target.length();
		  // Calculate the "next" array
		  int[] result = preProcess(mode);
		  int j = 0; // Index of substring
		  int k = 0; // Count how many substrings are found
		  for(int i = 0; i < targetLength; i++){
		     
			  while(j > 0 && mode.charAt(j) != target.charAt(i)){
		      
				  j = result[j - 1];
			  }
		      
			  if(mode.charAt(j) == target.charAt(i)){
				  j++;
			  }
		      
			  if(j == modeLength){
//				  j = result[j-1];
				  j = 0;
				  k++;
				  System.out.println("find");
			  }
		  }
		  System.out.println(k + " found");
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	public int[] preProcess(final String s) {
		  int size = s.length();
		  int[] k = new int[size];
		  k[0] = 0;
		  int j = 0;
		  //
		  for(int i=1;i<size;i++){
			  while(j>0 && s.charAt(j) != s.charAt(i)){
				  j = k[j - 1];
			  }
			  if(s.charAt(j) == s.charAt(i)){
				  j++;
			  }
		      //
			  k[i] = j;
		  }
		  System.out.println(java.util.Arrays.toString(k));
		  return k;
		 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KMP k = new KMP();
//		String s = "abcdabcd";
//		k.kmp(s, "abcd");
//		String s = "aacaabddddaab";
//		k.kmp(s, "aab");
		String s = "aaaaa";
		k.kmp(s, "aaa");
	}

}
