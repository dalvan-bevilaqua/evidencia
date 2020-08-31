package evidencia.evidenciaservice.repository.produto;

import evidencia.evidenciaservice.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p where p.codigoBarras = ?1")
    Produto selecionaProdutoPorCodigoBarras(@Param("codigoBarras") Long codigoBarras);

    @Query(value = "SELECT " +
                            "estoqueProduto.codigo as codigo, " +
                            "estoqueProduto.codigo_barras as codigoBarras, " +
                            "estoqueProduto.nome as nome, " +
                            "FLOOR(sum(estoqueProduto.qtdVendida)) as quantidadeVendida, " +
                            "FLOOR(sum(estoqueProduto.quantidade)) as quantidadeEntrada, " +
                            "FLOOR(sum(estoqueProduto.quantidade - estoqueProduto.qtdVendida)) as quantidadeEstoque " +
                        "FROM " +
                            "(" +
                        "SELECT " +
                            "p.codigo, " +
                            "p.codigo_barras, "+
                            "p.nome, " +
                            "p.valor, " +
                            "COUNT(vp.valor_vendido) qtdVendida, " +
                            "0 as quantidade "+
                        "FROM "+
                            "produto p "+
                            "LEFT JOIN vendaproduto vp ON vp.codigo_produto = p.codigo "+
                            "GROUP BY p.codigo "+
                  "UNION "+
                        "SELECT "+
                                "p.codigo, "+
                                "p.codigo_barras, "+
                                "p.nome, "+
                                "p.valor, "+
                                "0 as qtdVendida, "+
                                "FLOOR(Sum(ep.quantidade)) as quantidade "+
                        "FROM "+
                                "produto p "+
                        "LEFT JOIN entradaproduto ep ON ep.codigo_produto = p.codigo "+
                        "GROUP BY p.codigo " +
                    ") estoqueProduto " +
                  "GROUP BY "+
            "estoqueProduto.codigo", nativeQuery = true)
    List<Object[]> buscarEstoqueProduto();
}