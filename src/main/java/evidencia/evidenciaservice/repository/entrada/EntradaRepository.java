package evidencia.evidenciaservice.repository.entrada;

import evidencia.evidenciaservice.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
