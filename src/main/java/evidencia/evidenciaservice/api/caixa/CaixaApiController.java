package evidencia.evidenciaservice.api.caixa;

import evidencia.evidenciaservice.model.Caixa;
import evidencia.evidenciaservice.model.dto.CaixaDTO;
import evidencia.evidenciaservice.repository.caixa.CaixaRepository;
import evidencia.evidenciaservice.service.caixa.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/caixa")
public class CaixaApiController {

    @Autowired
    CaixaService caixaService;

    @Autowired
    CaixaRepository caixaRepository;

    @PostMapping
    public ResponseEntity<Caixa> salvar(@RequestBody Caixa caixa) {
        caixa.setData(new Date());
        Caixa caixaSalvo = caixaRepository.save(caixa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(caixaSalvo);
    }

    @GetMapping
    public ResponseEntity<CaixaDTO> buscar() {
        CaixaDTO caixaDTO = caixaService.getCaixaDTO();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(caixaDTO);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        caixaRepository.deleteById(codigo);
    }

}
