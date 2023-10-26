package ChanningBabb_FileProcessing;

import java.util.List;
import java.util.Map;

public class WordProcessor {

	
	private List<String> stopWords;
	private int beg, end;
	
	
	public WordProcessor(List<String> stopWords, int beg, int end) {
		this.stopWords = stopWords;
		this.beg = beg;
		this.end = end;
	}
	
	public List<String> textToWords(String text) {
		return List.of(text.split("\\PL+"));
	}
	
	
	public List<String> shrink(List<String> words) {
		
		return words.stream()
		//.forEach(w -> System.out.println(w))
		.map(w -> w.toLowerCase())
		.toList();
	}
	
	public List<String> removeStopWords(List<String> words) {
		
		return words.stream()
				.filter(w -> (!stopWords.contains(w)))
				.filter(w -> !w.equalsIgnoreCase("s"))
				.toList();
	}
	
	public void printWords(List<String> words) {
		words.stream().forEach(w -> System.out.println(w));
	}
	
	public List<String> removeEnds(List<String> words) {
		
		return words.stream().skip(beg)
		.limit((words.size() - beg - end))
		.toList();
		
	}
	
	public List<String> scrub(List<String> words){
		// 1st removes the end words from beginning and end
		// 2nd sends all the words to lower case
		// 3rd takes out stopWords
		return removeStopWords(shrink(removeEnds(words)));

	}

	public void findWordsBeginningWithVowels(List<String> words) {
		words.stream().filter(w -> w.matches("[aeiou].*")) // regex to check if it starts with a,e,i,o, or u.
		.forEach(w -> System.out.println(w)); // print
	}

	// code that creates a map of all the frequencies of the target in the words list
	public Map<String,Integer> findWordFrequency(List<String> words, List<String> targets)
	{
		 Map<String, Integer> frequencies = words.stream() // create new word stream
				.filter(w -> targets.contains(w)) // pass current iterated word and check if the targets list contains the word
				.collect(java.util.stream.Collectors.groupingBy(w -> w, java.util.stream.Collectors.summingInt(w -> 1))); // grouping by the word and having a counter for how many times the word has appeared
		return frequencies;

	}

	
	
}
