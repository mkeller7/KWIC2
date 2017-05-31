package kwic2;

import java.util.ArrayList;

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

    private void shiftLine() {
        for (Line currentLine : ((LineStorage) lineStorageFilter).getLine()) {
            Line tempLine;
            int wordCount = currentLine.wordCount();

            for (int x = 0; x < wordCount; x++) {
                //Use position to wrap the words
                int position = x;
                tempLine = new Line();

                for (int y = 0; y < wordCount; y++, position++) {
                    //Use modular division to add the words before the x value
                    //to the end of the line
                    tempLine.add(currentLine.get(position % wordCount));
                }

                lineStorage.add(tempLine);
            }
        }
    }

    @Override
    public void setup() {
        System.out.println("CircularShift Setup");

        lineStorage.clear();
        shiftLine();
    }

    @Override
    public ArrayList<Line> getLine() {
        return lineStorage;
    }
}
