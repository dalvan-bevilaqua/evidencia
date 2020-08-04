package evidencia.evidenciaservice.api.vendaproduto;

import evidencia.evidenciaservice.model.VendaProduto;
import evidencia.evidenciaservice.repository.vendaproduto.VendaProdutoRespository;
import evidencia.evidenciaservice.service.vendaproduto.VendaProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendaproduto")
@AllArgsConstructor

public class VendaProdutoApiController {

    private VendaProdutoRespository vendaProdutoRespository;
    private VendaProdutoService vendaProdutoService;

    @PostMapping
    public ResponseEntity<VendaProduto> salvar(@RequestBody VendaProduto vendaProduto) {
        VendaProduto vendaProdutoSalvo = vendaProdutoService.salvar(vendaProduto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendaProdutoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<VendaProduto>> buscar() {
        List<VendaProduto> vendaProdutos = vendaProdutoRespository.buscarProdutosVenda();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vendaProdutos);
    }

    @GetMapping("codigovenda/{codigoVenda}")
    public ResponseEntity<List<VendaProduto>> buscarProdutosVenda(@PathVariable Long codigoVenda) {
        List<VendaProduto> vendaProdutos = vendaProdutoRespository.buscarProdutoVendaPorCodigoVenda(codigoVenda);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vendaProdutos);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        vendaProdutoRespository.deleteById(codigo);
    }
}
