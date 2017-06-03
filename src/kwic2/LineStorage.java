package kwic2;

import java.util.ArrayList;

public class LineStorage implements ISetFilter {

    private final ArrayList<Line> lineStorage = new ArrayList<>();
    private Line currentLine = new Line();

    private final ArrayList<Character> fullText = new ArrayList<>();

    private boolean wordStarted = false;
    private int charPosition = 0;
    private int wordStartPosition = 0;

    @Override
    public void setText(String text) {
        process();

        for (char c : text.toCharArray()) {
            setChar(c);
        }
    }

    //Store the index of the words for each line and store the char so the words
    //can be reconstructed later.
    private void setChar(char c) {
        //Store the text
        fullText.add(c);

        //Store the position of the words and the line they are in
        switch (c) {
            case IFilter.SPACE_FLAG:
                if (wordStarted) {
                    //New word add it to the line
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include the space
                    wordStarted = false;
                }
                break;

            case IFilter.NEW_LINE_FLAG:
                if (wordStartPosition != -1) {
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include the space

                    //Store the current line then start a new line
                    lineStorage.add(currentLine);
                    currentLine = new Line();
                }

                wordStarted = false;
                wordStartPosition = -1;
                break;

            case IFilter.END_OF_FILE_FLAG:
                //Add word if one was started
                if (wordStarted) {
                    currentLine.add(new Word(wordStartPosition,
                            charPosition - 1)); //Do not include the flag

                    //Add the line before finishing
                    lineStorage.add(currentLine);
                }
                break;

            default:
                //If only the start of a word
                if (!wordStarted) {
                    wordStarted = true;
                    wordStartPosition = charPosition;
                }
                break;
        }

        //Keep track of the chacters
        charPosition++;
    }

    @Override
    public void process() {
        System.out.println("LineStorage");
        lineStorage.clear();
        fullText.clear();
        currentLine = new Line();
        wordStarted = false;
        charPosition = 0;
        wordStartPosition = 0;
    }

    @Override
    public ArrayList<Line> getLine() {
        return lineStorage;
    }

    public char getChar(int index) {
        return fullText.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < lineStorage.size(); x++) {
            for (int y = 0; y < ((Line) lineStorage.get(x)).wordCount(); y++) {
                sb.append("line: ").append(x).append(": word ")
                        .append(lineStorage.get(x).get(y)).append("\n");
            }
        }

        sb.append("\n");
        sb.append("Full Text \n");
        sb.append(fullText.toString());

        return sb.toString();
    }
}
