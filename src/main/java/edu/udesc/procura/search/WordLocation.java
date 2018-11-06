package edu.udesc.procura.search;

/**
 *
 * @author udesc
 */
public class WordLocation {

    private int line    = -1;
    private int column  = -1;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public boolean found() {
        return line != -1 || column != -1;
    }

}
