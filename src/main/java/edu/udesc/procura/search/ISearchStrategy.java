package edu.udesc.procura.search;

/**
 *
 * @author udesc
 */
public interface ISearchStrategy {
    /**
     * Inserido no Java 8
     * @param text
     * @param word 
     */
    
    public default void prepareSearch() {
    }
    public WordLocation search();
    
    public String getStrategyName();
    
    public String getWord();

    public void setWord(String word);

    public StringBuffer getText();

    public void setText(StringBuffer text);
}
