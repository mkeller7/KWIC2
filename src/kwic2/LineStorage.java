package kwic2;

import java.util.ArrayList;

public class LineStorage implements IFilter {

    private ArrayList<Character> storage = new ArrayList<>();

    @Override
    public char getChar(int index) {
        try{
            return storage.get(index);
        }catch(IndexOutOfBoundsException e){
            //Invalid value return null char
            return IFilter.END_OF_LINE_FLAG;
        }
    }

    @Override
    public void setChar(char c) {
        storage.add(c);
    }

    @Override
    public int wordCount(int lintPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setup() {
        storage.clear();
    }

}
