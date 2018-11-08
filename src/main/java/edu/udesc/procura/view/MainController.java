package edu.udesc.procura.view;

import edu.udesc.procura.search.ISearchStrategy;
import edu.udesc.procura.search.NaiveSearch;
import edu.udesc.procura.search.WordLocation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author udesc
 */
public class MainController {
    
    private static final Logger LOG = Logger.getLogger(MainController.class.getCanonicalName());
    

    IMainView view;
    Model model;
    
      ISearchStrategy[] search = {new NaiveSearch()};  

  /*  ISearchStrategy[] search = {new NaiveSearch(),
        new KMPSearch(),
        new RabinKarpSearch(),
        new BoyerMooreSearch()};*/

    private StringBuffer getFileContent(String file) {
       showLog("Iniciando a leitura do conteudo do arquivo " + file);
        
            try {
                Scanner sc = new Scanner(new FileReader(file));
                
                String text = "";
                
                while(sc.hasNext()){
                    text += sc.next();
                }
                
                return new StringBuffer(text);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NaiveSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       showLog("Finalizando a leitura do conteudo do arquivo " + file);
        return new StringBuffer();
    }

    public Model getModel() {
        return model;
    }
    
     private void showLog(String msg) {
         this.showLog(msg,false);
     }

    private Date showLog(String msg, boolean showOnScreen) {
        Date date = GregorianCalendar.getInstance().getTime();
        String formatedMessage = "[ " + date +  " ]" +  msg;
        System.out.println(formatedMessage);
        if( showOnScreen ) {
            view.setTextResultValue("[ " + date +  " ] " +  msg);
        }
        return date;
     }
    
    private void execute(String file) {
       showLog("Iniciando a procura");
        StringBuffer buffer = this.getFileContent(file);
        //view.setTextResultValue(buffer.toString());
        prepareSearch(buffer);
        searchFile();
        
       showLog("Finalizando a procura");
    }
    
    private void showInterval(String algoritmo,Date dataInicial,Date dataFinal) {
        long duracao = (dataFinal.getTime() - dataInicial.getTime());
        view.setTextResultValue("[ " + duracao +   " ] ");
        
    }

    private void searchFile() {
        for (ISearchStrategy strategy : search) {
           Date inicio = showLog("[ " + strategy.getStrategyName() + " ], inicio",true);
           // strategy.prepareSearch();
           WordLocation location =  strategy.search();
           if( location.found() ) {
               showLog("[ " + strategy.getStrategyName() + "]," +location.getColumn(),true );
               this.updateModel();
               this.refreshView();
               
           } else {
               showLog("--> Não encontrou!!!");
           }
           Date fim = showLog("[ " + strategy.getStrategyName() + " ], fim",true);
           showInterval(strategy.getStrategyName(), inicio,fim);

        }
    }

    private void prepareSearch(StringBuffer buffer) {
       showLog("Iniciando a definicao de dados para cada algoritmo");
        for (ISearchStrategy strategy : search) {
           showLog("Inicio da definicao do " + strategy.getStrategyName());
            strategy.setWord(model.getWordToFind());
            strategy.setText(buffer);
            strategy.prepareSearch();
           showLog("Fim da definicao do " + strategy.getStrategyName());
        }
       showLog("Finalizando a definicao de dados para cada algoritmo");
    }

    public void search() {
        this.updateModel();
        
        List<String> files = model.getFiles();
        showLog("Iniciando a leitura da lista de arquivos");
        for (String file : files) {
            execute(file);
        }
       showLog("Finalizando a leitura da lista de arquivos");
    }

    public MainController(IMainView view, Model model) {
        this.view = view;
        this.model = model;
        updateModel();
    }

    private void updateModel() {
        this.model.setFiles(view.getListFiles());
        this.model.setResultValues(view.getTextResultValue());
        this.model.setWordToFind(view.getTextWordToFind());
    }
    
    private void refreshView() {
        // pegar dados do model e colocar na view
    }

    void addFile(File absoluteFile) {
        model.addFile(absoluteFile);
    }

}
