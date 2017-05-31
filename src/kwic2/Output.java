package kwic2;

import java.util.ArrayList;

public class Output implements IFilter {

    private IFilter lineStorageFilter;
    private IFilter previousFilter; //For the filter before this one

    public Output(IFilter lineStorageFilter) {
        this.lineStorageFilter = lineStorageFilter;
    }

    public Output(IFilter lineStorageFilter, IFilter previousFilter) {
        this(lineStorageFilter);
        this.previousFilter = previousFilter;
    }

    //No need to store anything
    @Override
    public ArrayList<Line> getLine() {
        return null;
    }

    @Override
    public void setup() {
        System.out.println("Output Setup");
    }

    private String rebuildText() {
        StringBuilder sb = new StringBuilder();
        
        for (Line currentLine :  previousFilter.getLine()) {
            for (Word currentWord : currentLine.get()) {
                for (int letter = currentWord.getStart();
                        letter <= currentWord.getEnd(); letter++) {
                    sb.append(((LineStorage) lineStorageFilter).getChar(letter));
                }
                
                sb.append(IFilter.SPACE_FLAG);
            }
            
            sb.append(IFilter.NEW_LINE_FLAG);
        }
        return sb.toString();
    }

    public String getText() {
        return rebuildText();
    }
}
