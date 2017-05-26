package kwic2;

import java.util.ArrayList;

public class LineStorage implements ISetFilter {

    private ArrayList<Line> storage = new ArrayList<>();
    private Line currentLine = new Line();

    private boolean wordStarted = false;

    private int charPosition = 0;
    private int wordStartPosition = 0;
//    int linePosition = 0;

    @Override
    public void setChar(char c) {
        switch (c) {
            case IFilter.SPACE_FLAG:
                System.out.println("Its a space");

                if (wordStarted) {
                    //New word add it to the line
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include the space
                    wordStarted = false;
                }
                break;

            case IFilter.NEW_LINE_FLAG:
                System.out.println("New line");

                if (wordStartPosition != charPosition) {
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include the space

                    //Store the current line then start a new line
                    storage.add(currentLine);
                    currentLine = new Line();
                }

                wordStarted = false;
                charPosition = 0;
                wordStartPosition = 0;

                break;

            case IFilter.END_OF_FILE_FLAG:
                System.out.println("End of file");

                //Add word if one was started
                if (wordStarted) {
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include 
                }

                //Add the line before finishing
                storage.add(currentLine);
                break;

            default:
                //If only the start of a word
                if (!wordStarted) {
                    wordStarted = true;
                    wordStartPosition = charPosition;
                }
                break;
        }
        charPosition++;
    }

    @Override
    public void setup() {
        System.out.println("Line storage setup");
        storage.clear();
        currentLine = new Line();
        wordStarted = false;
        charPosition = 0;
        wordStartPosition = 0;
    }

    @Override
    public Line getLine(int lineNumber) {
        if (lineNumber > storage.size()) {
            return new Line();
        }

        return storage.get(lineNumber);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < storage.size(); x++) {
            for (int y = 0; y < ((Line) storage.get(x)).wordCount(); y++) {
                sb.append("line: ").append(x).append(": word ")
                        .append(storage.get(x).get(y)).append("\n");
            }
        }
        return sb.toString();
    }
}
