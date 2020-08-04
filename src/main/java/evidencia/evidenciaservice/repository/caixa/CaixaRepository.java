package evidencia.evidenciaservice.repository.caixa;

import evidencia.evidenciaservice.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    @Query("select SUM(c.valorEntrada) from Caixa c")
    BigDecimal getTotalEntrada();

    @Query("select SUM(c.valorSaida) from Caixa c")
    BigDecimal getTotalSaida();


}
