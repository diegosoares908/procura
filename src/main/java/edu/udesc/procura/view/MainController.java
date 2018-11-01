package edu.udesc.procura.view;

import edu.udesc.procura.search.BoyerMooreSearch;
import edu.udesc.procura.search.ISearchStrategy;
import edu.udesc.procura.search.KMPSearch;
import edu.udesc.procura.search.NaiveSearch;
import edu.udesc.procura.search.RabinKarpSearch;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author udesc
 */
public class MainController {
    
    private static final Logger LOG = Logger.getLogger(MainController.class.getCanonicalName());
    

    IMainView view;
    Model model;

    ISearchStrategy[] search = {new NaiveSearch(),
        new KMPSearch(),
        new RabinKarpSearch(),
        new BoyerMooreSearch()};

    private StringBuffer getFileContent(String file) {
        LOG.fine("Iniciando a leitura do conteudo do arquivo " + file);
        // ler o texto do arquivo
        LOG.fine("Finalizando a leitura do conteudo do arquivo " + file);
        return new StringBuffer();
    }

    private void execute(String file) {
        LOG.fine("Iniciando a procura");
        StringBuffer buffer = this.getFileContent(file);
        prepareSearch(buffer);
        searchFile();
        LOG.fine("Finalizando a procura");
    }

    private void searchFile() {
        for (ISearchStrategy strategy : search) {
            LOG.fine("Inicio da procura por " + strategy.getStrategyName());
            strategy.search();
            LOG.fine("Fim da procura por " + strategy.getStrategyName());
        }
    }

    private void prepareSearch(StringBuffer buffer) {
        LOG.fine("Iniciando a definicao de dados para cada algoritmo");
        for (ISearchStrategy strategy : search) {
            LOG.fine("Inicio da definicao do " + strategy.getStrategyName());
            strategy.setWord(model.getWordToFind());
            strategy.setText(buffer);
            strategy.prepareSearch();
            LOG.fine("Fim da definicao do " + strategy.getStrategyName());
        }
        LOG.fine("Finalizando a definicao de dados para cada algoritmo");
    }

    public void search() {
        this.updateModel();
        
        List<String> files = model.getFiles();
        LOG.fine("Iniciando a leitura da lista de arquivos");
        for (String file : files) {
            execute(file);
        }
        LOG.fine("Finalizando a leitura da lista de arquivos");
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

    void addFile(File absoluteFile) {
        model.addFile(absoluteFile);
    }

}
