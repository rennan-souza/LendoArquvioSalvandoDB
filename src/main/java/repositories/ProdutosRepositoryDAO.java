package repositories;

import connection.ConnectionMySQL;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosRepositoryDAO {

    private Connection connection;

    public ProdutosRepositoryDAO() {
        connection = ConnectionMySQL.getConnection();
    }

    public List<Produto> listarProdutos() throws Exception {

        List<Produto> list = new ArrayList<>();

        String sql = "SELECT * FROM PRODUTOS";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            Produto produto = new Produto();
            produto.setNome(resultado.getString("NOME"));
            produto.setPreco(resultado.getDouble("PRECO"));
            produto.setQuantidade(resultado.getInt("QUANTIDADE"));

            list.add(produto);
        }

        return list;
    }

    public void salvarProdutoDB(Produto produto) throws Exception {
        String sql = "INSERT INTO PRODUTOS (NOME, PRECO, QUANTIDADE) VALUES (?, ?, ?)";
        PreparedStatement prepareSql = connection.prepareStatement(sql);
        prepareSql.setString(1, produto.getNome());
        prepareSql.setDouble(2, produto.getPreco());
        prepareSql.setInt(3, produto.getQuantidade());

        prepareSql.execute();
        connection.commit();
    }
}
