package kwic2;

import java.util.ArrayList;

public class LineStorage implements ISetFilter {

    private ArrayList<Character> storage = new ArrayList<>();

    @Override
    public char getChar(int index) {
        try {
            return storage.get(index);
        } catch (IndexOutOfBoundsException e) {
            //Invalid value return null char
            return IFilter.END_OF_LINE_FLAG;
        }
    }

    @Override
    public void setChar(char c) {
//        if(c == IFilter.NEW_LINE_FLAG || c == IFilter.END_OF_LINE_FLAG){
//            storage.add(IFilter.SPACE_FLAG);
//            storage.add(c);
//        }
//        else{
            storage.add(c);            
//        }
    }

    @Override
    public void setup() {
        storage.clear();
    }
}
