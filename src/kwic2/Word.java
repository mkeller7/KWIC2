package kwic2;

public class Word {

    private int start;
    private int end;
    
    public Word(int start){
        this.start = start;
    }

    public Word(int start, int end) {
        this(start);
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
    public int length(){
        return (end - start) + 1;
    }
    
    @Override
    public String toString(){
        return start + ", " + end;
    }
}
