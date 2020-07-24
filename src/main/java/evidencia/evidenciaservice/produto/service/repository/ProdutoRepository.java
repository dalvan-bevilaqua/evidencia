package evidencia.evidenciaservice.produto.service.repository;

import evidencia.evidenciaservice.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    @Query("SELECT p FROM Produto p where p.codigoBarras = ?1")
    Produto selecionaProdutoPorCodigoBarras(@Param("codigoBarras") Long codigoBarras);
}
