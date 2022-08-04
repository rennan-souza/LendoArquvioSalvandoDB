import models.Produto;
import repositories.ProdutosRepositoryDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        /*
        //Listando os produtos
        ProdutosRepositoryDAO prdao = new ProdutosRepositoryDAO();

        try {
            List<Produto> list = prdao.listarProdutos();
            System.out.println(list);
        } catch (Exception e) {
            System.out.println(e);
        }
        */


        String path = "C:\\Users\\souza\\IdeaProjects\\LendoArquvioSalvandoDB\\arquivosCsv\\produtos.csv";

        List<Produto> list =  new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            line = br.readLine();
            while (line != null) {

                String[] vect = line.split(",");
                String nome = vect[0];
                Double preco = Double.parseDouble(vect[1]);
                Integer quatidade = Integer.parseInt(vect[2]);

                Produto product = new Produto(nome, preco, quatidade);
                list.add(product);

                line = br.readLine();
            }

            /*
            //Exibindo no console os produtos do arquivo.csv
            System.out.println("====PRODUTOS====");
            for (Produto p : list) {
                System.out.println(p);
            }
            */

            //Salvando os produtos do arquivo.csv no DB
            try {
                for (Produto p : list) {
                    ProdutosRepositoryDAO prdao = new ProdutosRepositoryDAO();
                    prdao.salvarProdutoDB(p);
                }
            } catch (Exception e) {
                System.out.println(e);
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
