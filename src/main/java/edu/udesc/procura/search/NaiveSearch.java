/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udesc.procura.search;

import edu.udesc.procura.view.MainView;

/**
 *
 * @author udesc
 */
public class NaiveSearch extends AbstractSearchStrategy{
    
    @Override
    public String getStrategyName() {
        return NaiveSearch.class.getCanonicalName();
    }
    
    @Override
    public WordLocation search() {
        int textLength = this.getText().length();
        int wordLength = this.getWord().length();
        WordLocation wordLocation = new WordLocation();
        
        for(int i = 0; i < textLength - wordLength; i++){
            
            int j;
            
            for(j = 0; j < wordLength; j++){
                if(this.getText().charAt(i + j) != this.getWord().charAt(j)){
                    break;
                }
            }
            
            if(j == wordLength){
                int index = i;
                wordLocation.setColumn(index);
            }
        }
        
        return wordLocation;        
    }
    
}
