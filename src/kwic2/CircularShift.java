package kwic2;

import java.util.ArrayList;
import java.util.Arrays;

public class CircularShift implements IFilter {

    private ArrayList<Line> lineStorage = new ArrayList<>();

    private IFilter lineStorageFilter;
    private IFilter previousFilter; //For the filter before this one

    public CircularShift(IFilter lineStorageFilter) {
        this.lineStorageFilter = lineStorageFilter;
    }

    public CircularShift(IFilter lineStorageFilter, IFilter previousFilter) {
        this(lineStorageFilter);
        this.previousFilter = previousFilter;
    }
    
    private void shiftLine(Line currentLine){
        System.out.println("--shiftLine-- \n" + currentLine.toString());
        
        Line tempLine = new Line();
        
        int wordCount = currentLine.wordCount();
        
        for(int x = 0; x < wordCount; x++){
            //Use position to wrap the words
            int position = x;
            
            for(int y = 0; y < wordCount; y++, position++){
                tempLine.add(currentLine.get(position % wordCount));
            }
        }
        
        lineStorage.add(tempLine);
    }

    @Override
    public void setup() {
        System.out.println("CircularShift Setup");
        
        lineStorage.clear();

        for(Line line: ((LineStorage)lineStorageFilter).getLine()){
            shiftLine(line);
        }
        
        System.out.println(lineStorage);
    }

    @Override
    public Line getLine(int lineNumber) {
        if (lineNumber > lineStorage.size()) {
            return null;
        }

        return lineStorage.get(lineNumber);
    }
}
