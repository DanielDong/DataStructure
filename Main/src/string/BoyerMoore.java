package string;

/**
 * String Searching algorithm similar to KMP. But it is more efficient than KMP.
 * 
 * @author shichaodong
 *
 */
public class BoyerMoore {

	private String pattern;
	private String target;
	
	public BoyerMoore(){}
	public BoyerMoore(String tar, String pat){
		pattern = pat;
		target = tar;
	}
	
	public void setPattern(String pat){
		pattern = pat;
	}
	
	public void setTarget(String tar){
		target = tar;
	}
	// Bad character shifts.
	// Character at index is the character which has not a match.
	private int getBadCharOffset(char c, int index){
		int offset = index - (-1);
		for(int i = index - 1; i >= 0; i --){
			if(pattern.charAt(i) == c){
				offset = index - i;
				break;
			}
		}
		return offset;
	}
	// Good suffix shifts
	private int getGoodSuffixOffset(int index){
		int offset = 0;
		String goodSuffix = pattern.substring(index + 1);
		if(goodSuffix != ""){
			int tmpOffset = pattern.lastIndexOf(goodSuffix, pattern.length() - 1);
			// NO good suffix in the pattern.
			if(tmpOffset == -1){
				for(int i = index + 2; i < pattern.length(); i ++){
					String tmpSubGoodSuffix = pattern.substring(i);
					int tmpSubGoodSuffixOffset = pattern.indexOf(tmpSubGoodSuffix);
					if(tmpSubGoodSuffixOffset == 0){
						offset = i;
						break;
					}
				}
				if(offset == 0)
					offset = pattern.length();
			}else{
				offset = index + 1 - tmpOffset;
			}
		}
		return offset;
	}
	
	public void bm(){
		int it = pattern.length() - 1, ip = pattern.length() - 1, offset = 0, cnt = 0;
		while(true){
			it += offset;
			if(it >= target.length())
				break;
			for(; it >= 0 && ip >= 0 && target.charAt(it) == pattern.charAt(ip); it --, ip --);
			
			// One match is found.
			if(ip == -1){
				ip = pattern.length() - 1;
				offset = 2 * pattern.length();
				cnt ++;
			}
			// One character mismatch occurs.
			else{
				it += pattern.length() - 1 - ip;
				offset = Math.max(getBadCharOffset(target.charAt(it), ip), getGoodSuffixOffset(ip));
				ip = pattern.length() - 1;
			}
		}
		System.out.println(cnt + " found.");
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoyerMoore bm = new BoyerMoore();
		bm.setTarget("bcdabcccabcabc");
		bm.setPattern("abcabc");
		bm.bm();
	}

}
