package evidencia.evidenciaservice.repository.vendaproduto;

import evidencia.evidenciaservice.model.VendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaProdutoRespository extends JpaRepository<VendaProduto, Long> {

    @Query("select v from VendaProduto v where v.codigoVenda = null")
    List<VendaProduto> buscarProdutosVenda();

    @Modifying
    @Query("UPDATE VendaProduto v SET v.codigoVenda =:codigoVenda WHERE v.codigoVenda = null")
    void finalizarVenda(@Param("codigoVenda") Long codigoVenda);
}
