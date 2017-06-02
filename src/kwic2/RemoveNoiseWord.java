package kwic2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RemoveNoiseWord implements IFilter {

    private ArrayList<Line> lineStorage = new ArrayList<>();
    private ArrayList<String> noiseWordsList = new ArrayList<>();
    private int noiseWordMaxLength;

    private IFilter lineStorageFilter;
    private IFilter previousFilter; //For the filter before this one

    public RemoveNoiseWord(IFilter lineStorageFilter) {
        this.lineStorageFilter = lineStorageFilter;
    }

    public RemoveNoiseWord(IFilter lineStorageFilter, IFilter previousFilter) {
        this(lineStorageFilter);
        this.previousFilter = previousFilter;
    }

    @Override
    public ArrayList<Line> getLine() {
        return lineStorage;
    }

    @Override
    public void process() {
        System.out.println("RemoveNoiseWord");

        lineStorage.clear();
        makeNoiseWordsList();
        removeNoiseWords();
    }

    private void makeNoiseWordsList() {
        noiseWordsList.add("a");
        noiseWordsList.add("an");
        noiseWordsList.add("the");
        noiseWordsList.add("and");
        noiseWordsList.add("or");
        noiseWordsList.add("of");
        noiseWordsList.add("to");
        noiseWordsList.add("be");
        noiseWordsList.add("is");
        noiseWordsList.add("in");
        noiseWordsList.add("out");
        noiseWordsList.add("by");
        noiseWordsList.add("as");
        noiseWordsList.add("at");
        noiseWordsList.add("off");

        //Find the longest word 
        String longest = Collections.max(noiseWordsList,
                Comparator.comparing(s -> s.length()));

        noiseWordMaxLength = longest.length();
    }

    private int checkNoiseWordList(Word w) {
            //If the word is larger than the largest noise word it is not a 
            //noiseword
        if (w.length() > noiseWordMaxLength) {
            //If the word is larger than the largest noise word it is not a 
            //noiseword
            return -1;
            
        } else {            
            //Reconstruct the forst word to check for a noise word
            StringBuilder sb = new StringBuilder();

            for (int index = w.getStart(); index <= w.getEnd(); index++) {
                sb.append(((LineStorage) lineStorageFilter).getChar(index));
            }

            return noiseWordsList.indexOf(sb.toString().toLowerCase());
        }
    }

    private void removeNoiseWords() {
        for (Line currentLine : previousFilter.getLine()) {
            //Check the first word for a noise word
            if (checkNoiseWordList(currentLine.get(0)) == -1) {
                lineStorage.add(currentLine);
            }
        }
    }
}
