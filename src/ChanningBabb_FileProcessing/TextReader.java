package readers;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TextReader {

	   public static void main (String[] args) throws IOException
	   {
		   Long startTime = System.currentTimeMillis();
		   HashMap<String, Integer> words = new HashMap();
		   String magic_word = "to";


		   String[] files = {"Melville/MobyDick.txt"};
//		   , "Melville/Bartleby.txt",
//				   "Melville/IAndMyChimney.txt", "Melville/Omoo.txt", "Melville/Pierre.txt",
//				   "Melville/Redburn.txt", "Melville/TheConfidenceMan.txt", "Melville/ThePiazzaTales.txt",
//				   "Melville/Typee.txt", "Melville/WhiteJacket.txt"

		   ArrayList<readers.WordCounter> counters = new ArrayList();

		   for (int i =0; i < files.length; i++) {
			   counters.add(new readers.WordCounter(words, files[i], magic_word));
		   }

		   ExecutorService executor = Executors.newCachedThreadPool();


		   // Not Explicitly Concurrent
//		   for (String file : files)
//			   WordCounter.countWords(words, file);


		   // USING RUNNABLE
		   for (readers.WordCounter c : counters)
			   executor.execute(c);


		   // RACE CONDITION
		   //int runnableMagicCount = words.get(magic_word);
		   //System.out.println("Total (Runnable) occurances of " + magic_word + " is " + runnableMagicCount);


		   // USING FUTURES AND CALLABLE
		   try {
			   List<Future<Long>> futures = executor.invokeAll(counters);
			   long total = 0;
			   for (Future<Long> f : futures)
			   	total += f.get();

			   //long total = executor.invokeAny(counters);


			   System.out.println("Total occurances of " + magic_word + " is " + total);
		   }
		   catch(InterruptedException e) {
			   System.out.println("Task Interrupted!!!!");
		   }
		   catch(ExecutionException e) {
			   System.out.println("Exceptional Execution!!!!");
		   }


		   Long endTime = System.currentTimeMillis();
		   System.out.println("End TIME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + (endTime - startTime));
	   }





}
