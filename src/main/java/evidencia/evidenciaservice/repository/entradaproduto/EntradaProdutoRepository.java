package evidencia.evidenciaservice.repository.entradaproduto;

import evidencia.evidenciaservice.model.EntradaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntradaProdutoRepository extends JpaRepository<EntradaProduto, Long> {

    @Query("select v from EntradaProduto v where v.codigoEntrada = null")
    List<EntradaProduto> buscarProdutosEntrada();

    @Modifying
    @Query("UPDATE EntradaProduto v SET v.codigoEntrada =:codigoEntrada WHERE v.codigoEntrada = null")
    void finalizarEntrada(@Param("codigoEntrada") Long codigoEntrada);

    @Query("select v from EntradaProduto v where v.codigoProduto = ?1")
    List<EntradaProduto> getEntradaPorCodigoProduto(Long codigo);
}
