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
//            System.out.println("--shiftLine-- \n" + currentLine.toString());

            Line tempLine;
            int wordCount = currentLine.wordCount();

            for (int x = 0; x < wordCount; x++) {
                //Use position to wrap the words
                int position = x;
                tempLine = new Line();

                for (int y = 0; y < wordCount; y++, position++) {
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

//        System.out.println(lineStorage);
    }

    @Override
    public ArrayList<Line> getLine() {
        return lineStorage;
    }
}
