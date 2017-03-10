package kwic2;

interface iFilter {
    static final char END_FLAG = '\u0000';
    
    char getChar(int index);
    void setChar(char c);
    int wordCount(int lintPosition);
    void setup();
}
