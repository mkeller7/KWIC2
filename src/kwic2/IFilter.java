package kwic2;

import java.util.ArrayList;

interface IFilter {

    static final char END_OF_FILE_FLAG = '\u0000'; //null char
    static final char SPACE_FLAG = ' ';
    static final char NEW_LINE_FLAG = '\n';

    ArrayList<Line> getLine();
    void process();
}
