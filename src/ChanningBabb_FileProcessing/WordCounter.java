package readers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class WordCounter implements Runnable, Callable<Long>{

	HashMap<String, Integer> words;
	String file;
	final String MAGIC_WORD;
	
	
	public WordCounter(HashMap<String, Integer> words, String file, String magic_word ) {
		this.words = words;
		this.file = file;
		this.MAGIC_WORD = magic_word;
	}
	
	
	
	public void run() {
		try {
		countWords(words, file);
		}
		catch (IOException e) {
			System.out.println("Could not find file: " + file);
		}
	}
	
	
	public static void countWords(HashMap<String, Integer> words, String fileName ) throws IOException {
		Long startTime = System.currentTimeMillis();	
		String line;
		Scanner fileScan, lineScan;   
		fileScan = new Scanner (new File(fileName));

		// Read each line of the file
		while (fileScan.hasNext())
		{
			line = fileScan.nextLine();
			lineScan = new Scanner (line);
       
			// Read each word of the line
			while (lineScan.hasNext()) {
				String word = lineScan.next().toLowerCase();
				if (words.containsKey(word.toLowerCase())) {
					Integer num = words.get(word);
					if (num != null) {
						words.replace(word, num, ++num);
						System.out.println("Success!:  " + word + "\t" + num.toString());
      			
					} else 
						words.replace(word, num, 2);
      			
				}
				words.putIfAbsent(word.toLowerCase(), 1);
			}
		}
		Long endTime = System.currentTimeMillis();
		   System.out.println("TIME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + (endTime - startTime));
	}
	
	public Long countWord(String word, String fileName ) throws IOException {
		
		Long counter = 0L;
		String line;
		Scanner fileScan, lineScan;   
		fileScan = new Scanner (new File(fileName));

		// Read each line of the file
		while (fileScan.hasNext())
		{
			line = fileScan.nextLine();
			lineScan = new Scanner (line);
       
			// Read each word of the line
			while (lineScan.hasNext()) {
				if(word.equalsIgnoreCase(lineScan.next()))
						counter++;
			}
		}
		return counter;
	}
	
	


	
	
	public Long call() throws Exception {
		
		return countWord(MAGIC_WORD, file);
		
		
	}
}
