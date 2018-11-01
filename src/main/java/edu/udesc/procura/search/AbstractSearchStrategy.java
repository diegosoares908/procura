package edu.udesc.procura.search;

/**
 *
 * @author udesc
 */
public abstract class AbstractSearchStrategy implements ISearchStrategy {
    protected String word;
    protected StringBuffer text;

    @Override
    public String getWord() {
        return word;
    }
    @Override
    public void setWord(String word) {
        this.word = word;
    }
    @Override
    public StringBuffer getText() {
        return text;
    }
    @Override
    public void setText(StringBuffer text) {
        this.text = text;
    }
}
