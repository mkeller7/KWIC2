package kwic2;

import java.util.ArrayList;

public class Line {
    
    private ArrayList<Word> wordStorage = new ArrayList<>();
    
    public Line() {
    }
    
    public boolean add(Word word) {
        return wordStorage.add(word);
    }
    
    public void clear() {
        System.out.println("Line: wordStorage clear");
        wordStorage.clear();
    }
    
    public Word get(int index) {
        if (index > wordStorage.size()) {
            return new Word(IFilter.END_OF_FILE_FLAG, IFilter.END_OF_FILE_FLAG);
        }
        
        return wordStorage.get(index);        
    }
    
    public ArrayList<Word> get(){
        return wordStorage;
    }
    
    public int wordCount(){
        return wordStorage.size();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(Word w: wordStorage){
            sb.append(w);
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
