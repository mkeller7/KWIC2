package kwic2;

interface IFilter {
    //Use a char that is unlikely to be found in any text
    static final char END_OF_LINE_FLAG = '\u0000';
    static final char SPACE_FLAG = ' ';
    static final char NEW_LINE_FLAG = '\n';
    
    char getChar(int index);
    void setChar(char c);
    int wordCount(int lintPosition);
    void setup();
}
