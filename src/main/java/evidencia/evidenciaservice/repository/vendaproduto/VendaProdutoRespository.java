package evidencia.evidenciaservice.repository.vendaproduto;

import evidencia.evidenciaservice.model.VendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VendaProdutoRespository extends JpaRepository<VendaProduto, Long> {

    @Query("select v from VendaProduto v where v.codigoVenda = null")
    List<VendaProduto> buscarProdutosVenda();

    @Query("select SUM(v.valorVendido) from VendaProduto v where v.codigoVenda = null")
    BigDecimal somarValorVenda();

    @Query("select v from VendaProduto v where v.codigoVenda = ?1")
    List<VendaProduto> buscarProdutoVendaPorCodigoVenda(Long codigoVenda);

    @Query("select v from VendaProduto v where v.codigoProduto = ?1")
    List<VendaProduto> buscarProdutoVendaPorCodigoProduto(Long codigoProduto);

    @Modifying
    @Query("UPDATE VendaProduto v SET v.codigoVenda =:codigoVenda WHERE v.codigoVenda = null")
    void finalizarVenda(@Param("codigoVenda") Long codigoVenda);
}
