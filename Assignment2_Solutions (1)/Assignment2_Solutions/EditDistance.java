public class EditDistance {
	public static int min3(int val1, int val2, int val3) {
		if (val1 < val2) {
			return (val1 < val3) ? val1 : val3;
		}
		return (val2 < val3) ? val2 : val3;
	}
	
	public static int getEditDistance(String s1, String s2) {
		// initialize our subproblem table
		int distances[][] = new int[s1.length()+1][];
		for (int i = 0; i < distances.length; i++) {
			distances[i] = new int[s2.length()+1];
			for (int j = 0; j < distances[i].length; j++) {
				distances[i][j] = 0;
			}
		}
		
		// any string of length n is n edits (deletes) away from an empty string
		for (int i = 0; i < distances.length; i++) {
			distances[i][0] = i;
		}
		
		// an empty string is n edits (inserts) away from any string of length n
		for (int j = 0; j < distances[0].length; j++) {
			distances[0][j] = j;
		}
		
		// build on these subproblems until we have a full solution
/*
  for j from 1 to n {
      for i from 1 to m {
          if s[i] = t[j] then  //going on the assumption that string indexes are 1-based in your chosen language<!-- not: s[i-1] = t[j-1] -->
                               //else you will have to use s[i-1] = t[j-1] Not doing this might throw an IndexOutOfBound error
            d[i, j] := d[i-1, j-1]       // no operation required
          else
            d[i, j] := minimum
                    (
                      d[i-1, j] + 1,  // a deletion
                      d[i, j-1] + 1,  // an insertion
                      d[i-1, j-1] + 1 // a substitution
                    )
        }
    }
*/
		for (int j = 1; j <= s2.length(); j++) {
			for (int i = 1; i <= s1.length(); i++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					distances[i][j] = distances[i-1][j-1];    // no need to do anything, the characters match
				} else {
					int deleteCost = distances[i-1][j] + 1;   // we can delete this character
					int insertCost = distances[i][j-1] + 1;   // we can insert a new character
					int substCost  = distances[i-1][j-1] + 1; // we can substitute a character
					distances[i][j] = min3(deleteCost, insertCost, substCost);
				}
			}
		}
		
		// print out our distances, for testing
		/*
		for (int j = 0; j < distances[0].length; j++) {
			for (int i = 0; i < distances.length; i++) {
				System.out.print(" " + distances[i][j]);
			}
			System.out.println("");
		}
		*/
		
		return distances[s1.length()][s2.length()];
	}
	
	public static void main(String[] args) {
		System.out.println("");
		String s1 = "spoof";
		String s2 = "stool";
		System.out.println("getEditDistance('"+s1+"', '"+s2+"'): " + getEditDistance(s1, s2));
		s1 = "podiatrist";
		s2 = "pediatrician";
		System.out.println("getEditDistance('"+s1+"', '"+s2+"'): " + getEditDistance(s1, s2));
		s1 = "blaming";
		s2 = "conning";
		System.out.println("getEditDistance('"+s1+"', '"+s2+"'): " + getEditDistance(s1, s2));
	}
}
