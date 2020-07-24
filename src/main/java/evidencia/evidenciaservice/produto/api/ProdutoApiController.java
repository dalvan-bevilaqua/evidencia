package evidencia.evidenciaservice.produto.api;

import evidencia.evidenciaservice.produto.model.Produto;
import evidencia.evidenciaservice.produto.service.ProdutoService;
import evidencia.evidenciaservice.produto.service.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoApiController {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Produto> buscarProdutoPorCodigoBarras(@PathVariable Long codigoBarras) {
        Produto produtoSalvo = produtoRepository.selecionaProdutoPorCodigoBarras(codigoBarras);
        return (produtoSalvo != null) ? ResponseEntity.ok(produtoSalvo) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoSalvo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> buscarProdutos() {
        List<Produto> produtoSalvo = produtoRepository.findAll();
        return (produtoSalvo != null) ? ResponseEntity.ok(produtoSalvo) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoSalvo);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {

        Produto produtoSalvo = produtoRepository
                .save(produto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Produto> Editar(@PathVariable Long codigo,
                                          @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.atualizar(codigo, produto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        produtoRepository.deleteById(codigo);
    }

}