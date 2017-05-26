package kwic2;

import java.util.ArrayList;
import java.util.Arrays;

public class CircularShift implements IFilter {

    private IFilter ls;
    private IFilter previousFilter;
    private ArrayList<Integer> first = new ArrayList<>();
    private ArrayList<Integer> offset = new ArrayList<>();
    private ArrayList<Integer> lineLength = new ArrayList<>();

    public CircularShift(IFilter ls) {
        this.ls = ls;
    }

    public CircularShift(IFilter ls, IFilter previousFilter) {
        this(ls);
        this.previousFilter = previousFilter;
    }

    @Override
    public char getChar(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setup() {
        first.clear();
        offset.clear();
        lineLength.clear();

        first.add(0);
        offset.add(0);

        boolean spaceFound = false;
        boolean newLine = false;

        int currentPosition = 0;
        int firstPosition = 0;
        int length = 0;

        //Get the first value
        char value = ls.getChar(0);

        //Until the null unicode char is found
        while (value != IFilter.END_OF_LINE_FLAG) {
            //If a new line char was found mark the next position as the start
            if (newLine) { // && value != IFilter.NEW_LINE_FLAG){
                //Set to start of the next line
                firstPosition = currentPosition;

                first.add(firstPosition);
                offset.add(currentPosition);
                newLine = false;
            }

            //Do not copy the new line char
            if (value == IFilter.NEW_LINE_FLAG) {
                spaceFound = false;
                newLine = true;

                lineLength.add(currentPosition);
            } //After a space look for a char for next offset
            else if (spaceFound) { // && value != IFilter.SPACE_FLAG) {
                first.add(firstPosition);
                offset.add(currentPosition);
                spaceFound = false;
            } else if (value == IFilter.SPACE_FLAG) {
                spaceFound = true;
            }

            currentPosition++;
            value = ls.getChar(currentPosition);
        }//end of while

        //****************************** For testing only ******************************
        System.out.println();

        System.out.println("First \tOffset \t Length");
        for (int x = 0; x < first.size(); x++) {
            System.out.println(first.get(x) + "\t" + offset.get(x) + "\t");
            // + "\t" + lineLength.get(x));
        }

        System.out.println();

        System.out.println(lineLength.toString());
        
        System.out.println();

        //Loop through the lines
        for (int line = 0; line < first.size(); line++) {
            //Output the line starting with the offset
            for (int charPosition = offset.get(line);; charPosition++) {
                if (ls.getChar(charPosition) == IFilter.NEW_LINE_FLAG
                        || ls.getChar(charPosition) == IFilter.END_OF_LINE_FLAG) {
                    break;
                }

                System.out.print(ls.getChar(charPosition));
            }

            //Output the remainder of the line if needed
            for (int charPosition = first.get(line);
                    charPosition < offset.get(line); charPosition++) {

                System.out.print(ls.getChar(charPosition));
            }

            System.out.println();
        }
    }
}
