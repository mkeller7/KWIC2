package kwic2;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;

public class Output implements IFilter {

    private IFilter lineStorageFilter;
    private IFilter previousFilter; //For the filter before this one
    
    private Collator collator;

    public Output(IFilter lineStorageFilter, Collator collator) {
        this.lineStorageFilter = lineStorageFilter;
        this.collator = collator;
    }

    public Output(IFilter lineStorageFilter, Collator collator,
            IFilter previousFilter) {
        this(lineStorageFilter, collator);
        this.previousFilter = previousFilter;
    }

    //No need to store anything
    @Override
    public ArrayList<Line> getLine() {
        return null;
    }

    @Override
    public void process() {
        System.out.println("Output");
    }

    private String rebuildText() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();

        for (Line currentLine : previousFilter.getLine()) {
            for (Word currentWord : currentLine.get()) {
                for (int letter = currentWord.getStart();
                        letter <= currentWord.getEnd(); letter++) {
                    sb.append(((LineStorage) lineStorageFilter).getChar(letter));
                }

                sb.append(IFilter.SPACE_FLAG);
            }
            //Store the lines for sorting
            sb.append(IFilter.NEW_LINE_FLAG);
            lines.add(sb.toString());
            sb = new StringBuilder();
        }

        Collections.sort(lines, collator);

        //Put all of the lines into one string after they are sorted
        for (String s : lines) {
            sb.append(s);
        }

        return sb.toString();
    }

    public String getText() {
        return rebuildText();
    }
}
