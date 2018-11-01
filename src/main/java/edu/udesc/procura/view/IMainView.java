/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udesc.procura.view;

import java.util.ArrayList;

/**
 *
 * @author udesc
 */
public interface IMainView {
   public ArrayList<String> getListFiles();
   public void setTextResultValue(String values);
   public String getTextResultValue();
   public String getTextWordToFind();
}
