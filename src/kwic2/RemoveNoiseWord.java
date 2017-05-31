package kwic2;

import java.util.ArrayList;

public class RemoveNoiseWord implements IFilter {

    private ArrayList<Line> lineStorage = new ArrayList<>();
    private ArrayList<String> noiseWordsList = new ArrayList<>();

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
    public void setup() {
        System.out.println("RemoveNoiseWord Setup");

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
    }

    private int checkNoiseWordList(Word w) {
        //Reconstruct the forst word to check for a noise word
        StringBuilder sb = new StringBuilder();

        for (int index = w.getStart(); index <= w.getEnd(); index++) {
            sb.append(((LineStorage) lineStorageFilter).getChar(index));
        }

//        System.out.println("--Word--\n" + sb + "\n" + w.getStart() + ", " + 
//                w.getEnd());
        
        return noiseWordsList.indexOf(sb.toString().toLowerCase());
    }

    private void removeNoiseWords() {
        for (Line currentLine : previousFilter.getLine()) {      
            //Check the first word for a noise word
            if(checkNoiseWordList(currentLine.get(0)) == -1){
//                System.out.println("Not a noise word");
                
                lineStorage.add(currentLine);
            }
        }
        
//        System.out.println("Lines stored \n" + lineStorage.size());
    }
}
