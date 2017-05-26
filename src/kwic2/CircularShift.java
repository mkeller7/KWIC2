package kwic2;

import java.util.ArrayList;
import java.util.Arrays;

public class CircularShift implements IFilter {

    private IFilter ls;
    private IFilter previousFilter; //For the filter before this one

    public CircularShift(IFilter ls) {
        this.ls = ls;
    }

    public CircularShift(IFilter ls, IFilter previousFilter) {
        this(ls);
        this.previousFilter = previousFilter;
    }
    
    @Override
    public void setup() {

    }

    @Override
    public Line getLine(int lineNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
