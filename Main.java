/*
 * Code by Dr. Marriot
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		long duration = 0;
		
		FileReader inputStream = null;
		String fileName = "pg2600.txt";
		FileOutputStream outputStream = new FileOutputStream(new File("compressed.txt"));
		FileOutputStream codeStream = new FileOutputStream(new File("codes.txt"));
		
		try {
				inputStream = new FileReader(fileName);
				int c;
				List<Character> chars = new ArrayList<Character>();
				
				// read characters into a list of characters
				while ((c = inputStream.read()) != -1) {
					chars.add((char)c);
				}
				inputStream.close();

				// pass list of characters to constructor
				// constructor carries out all operations 
				// necessary for creating the encoding
				CodingTree ct = new CodingTree(chars);
				ct.printThis();
				
				
				//To show how long it takes to do my part of the code
				System.out.println("Time it took to make Tree: " + (System.currentTimeMillis() - start));
				
				//ct.printThis(); 
				// write the code file
				// codeStr is a String member of CodingTree
				// consisting of one (char, binary-codeword) pair
				codeStream.write(ct.codeStr.getBytes());
				codeStream.close();
				
	
				
				List<String> codes = new ArrayList<String>();
				
				// an easy to index array of code words
				for(int i = 0; i < 256; i++){
					codes.add("");
				}
				// ct.codes is a List<Code> member of CodingTree
				for(int i = 0; i < ct.codes.size(); i++){
					codes.set((int)ct.codes.get(i).c, ct.codes.get(i).bits);
				}
			
				
				
				
				String buffer = "";
				long asciiCost = chars.size()*8;
				long compressedCost = 0;
				
				
				//System.out.println("Duration check " + (System.currentTimeMillis() - start));
				/*
				 * This part of the code until the next duration check comment takes 19 seconds on my computer
				 */
				
				for(int i = 0; i < chars.size(); i++){
					// inefficient since String += is O(n) where n is the length of the string
					buffer += codes.get((int)chars.get(i));;
					if(buffer.length() > 256){
						
						while(buffer.length() > 8){	
							int chr = Integer.parseInt(buffer.substring(0, 8),2);
							outputStream.write(chr);
							buffer = buffer.substring(8, buffer.length());
							compressedCost += 8;
						}
					}					
				}
				
				//System.out.println("Duration Check " + (System.currentTimeMillis() - start));
				
				compressedCost += buffer.length();
				while(buffer.length() > 8){
					int chr = Integer.parseInt(buffer.substring(0, 7),2);
					outputStream.write(chr);
					buffer = buffer.substring(8, buffer.length());
				}
				outputStream.close();
				
				duration = System.currentTimeMillis() - start;
/*
 * For my computer the compression rate was 41%
 * with a 20 second duration
 * Note: it takes me 1 second to make my tree and 19 more to generate the file
 */
				System.out.println("Uncompressed file size: " + asciiCost/8 + " bytes");
				System.out.println("Compressed file size: " + compressedCost/8 + " bytes");
				System.out.println("Compression ratio: " + compressedCost*100/asciiCost + "%");
				System.out.println("Running Time: " + duration + " milliseconds");
		} finally {}
	
	}

}
