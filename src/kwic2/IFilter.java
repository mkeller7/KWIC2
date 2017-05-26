package kwic2;

interface IFilter {

    static final char END_OF_LINE_FLAG = '\u0000'; //null char
    static final char SPACE_FLAG = ' ';
    static final char NEW_LINE_FLAG = '\n';

    char getChar(int index);
    void setup();
}
