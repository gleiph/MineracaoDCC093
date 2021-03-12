/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.model.preprocessamento;

import br.ufjf.ice.dcc.mineracaolocal.vcs.utils.Arquivo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gleiph
 */
public class Preprocessing {

    public static List<CommitPreProcessed> preprocessing(String caminho) throws FileNotFoundException {

        String conteudo = Arquivo.lerArquivo(caminho);
        String[] linhas = conteudo.split("\n");
        List<CommitPreProcessed> result = null;

        if (linhas.length > 3) {
            
            result = new ArrayList<>();
            CommitDataMetric commitAnterior = CommitDataMetric.toCommitData(linhas[1]);

            for (int i = 2; i < linhas.length; i++) {
                CommitDataMetric commitAtual = CommitDataMetric.toCommitData(linhas[i]);
                CommitPreProcessed preProcessing = CommitPreProcessed.preProcessing(commitAnterior, commitAtual);
                result.add(preProcessing);
                commitAnterior = commitAtual;
                
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        List<CommitPreProcessed> preprocessing = preprocessing("/Users/gleiph/Desktop/commits.txt");
        String generate = ArffGenerator.generate(preprocessing);
        System.out.println(generate);
    }
}
