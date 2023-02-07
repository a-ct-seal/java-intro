package wordsCount;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WsppCountLastL {

    private static final String UTF8 = "utf8";

    public static class CharacterChecker implements WhitespaceChecker {
        public boolean isNotWhitespace(char ch) {
            return (ch == '\'' ||
                    Character.isLetter(ch) ||
                    Character.getType(ch) == Character.DASH_PUNCTUATION);
        }

        public boolean isWhitespace(char ch) {
            return !isNotWhitespace(ch);
        }
    }

    private static class WordCharacteristics {
        public WordCharacteristics(String word, int numOfEntries) {
            this.word = word;
            this.numOfEntries = numOfEntries;
        }

        public String word;
        public int numOfEntries;
    }

    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(args[0], UTF8, new CharacterChecker());
            try {
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(args[1]), UTF8)
                );
                try {
                    Map<String, IntList> wordsStatistics = new LinkedHashMap<>();
                    Map<String, Integer> wordsOccurences = new LinkedHashMap<>();

                    //reading input file
                    Set<String> occurrencesInLine = new HashSet<>();
                    int i = 1;
                    int curLine = 0;

                    while (reader.hasNextToken()) {
                        String word = reader.nextToken().toLowerCase();
                        IntList wordGet = wordsStatistics.get(word);

                        if (wordGet == null) {
                            wordGet = new IntList();
                            wordsStatistics.put(word, wordGet);
                            wordsOccurences.put(word, 0);
                        }

                        if (curLine != reader.getNumOfLine()) {
                            curLine = reader.getNumOfLine();
                            occurrencesInLine.clear();
                            i = 1;
                        }

                        if (occurrencesInLine.contains(word)) {
                            wordGet.pop();
                        }

                        occurrencesInLine.add(word);
                        wordGet.add(i);
                        wordsOccurences.put(word, wordsOccurences.get(word) + 1);
                        i++;
                    }

                    //writing to output file
                    List<WordCharacteristics> wordsSortingList = new ArrayList<>();
                    for (Map.Entry<String, Integer> entry : wordsOccurences.entrySet()) {
                        wordsSortingList.add(new WordCharacteristics(entry.getKey(), entry.getValue()));
                    }

                    wordsSortingList.sort((WordCharacteristics a, WordCharacteristics b) -> (a.numOfEntries - b.numOfEntries));

                    for (WordCharacteristics entry : wordsSortingList) {
                        writer.write(entry.word + " " + entry.numOfEntries + " " + wordsStatistics.get(entry.word));
                        writer.newLine();
                    }

                } finally {
                    writer.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Output file was not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("I/O error: " + e.getMessage());
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}