package evidencia.evidenciaservice.service.produto;

import evidencia.evidenciaservice.model.EntradaProduto;
import evidencia.evidenciaservice.model.Produto;
import evidencia.evidenciaservice.model.VendaProduto;
import evidencia.evidenciaservice.model.dto.EstoqueProdutoDTO;
import evidencia.evidenciaservice.repository.entradaproduto.EntradaProdutoRepository;
import evidencia.evidenciaservice.repository.produto.ProdutoRepository;
import evidencia.evidenciaservice.repository.vendaproduto.VendaProdutoRespository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private VendaProdutoRespository vendaProdutoRespository;
    private EntradaProdutoRepository entradaProdutoRepository;

    public Produto atualizar(long codigo, Produto produto) {

        Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        return produtoRepository.save(produtoSalvo);
    }

    private Produto buscarProdutoPorCodigo(Long codigo) {
        return produtoRepository.getOne(codigo);
    }

    public Produto buscarProdutoPeloCodigo(Long codigo) {
        Produto produtoSalvo = produtoRepository.findById(codigo).orElse(null);
        if (produtoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return produtoSalvo;
    }

    public List<EstoqueProdutoDTO> estoqueProduto() {
        List<Object[]> objects = produtoRepository.buscarEstoqueProduto();

        List<EstoqueProdutoDTO> estoqueProdutoDTOS = objects.stream().map(object ->
                EstoqueProdutoDTO.builder()
                        .codigo(Long.parseLong(object[0].toString()))
                        .codigoBarras(Long.parseLong(object[1].toString()))
                        .nome(object[2].toString())
                        .quantidadeVendida(Long.parseLong(object[3].toString()))
                        .quantidadeEntrada((long) ((Double) object[4]).intValue())
                        .quantidadeEstoque((long) ((Double) object[5]).intValue())
                        .build()).collect(Collectors.toList());

        return estoqueProdutoDTOS;
    }

    public void deleteById(Long codigo) throws Exception {
        validaSeProdutoJaTemEntrada(codigo);
        validaSeProdutoJaTemVenda(codigo);

        produtoRepository.deleteById(codigo);
    }

    public void validaSeProdutoJaTemEntrada(Long codigo) throws Exception {
        List<VendaProduto> vendaProdutos = vendaProdutoRespository.buscarProdutoVendaPorCodigoProduto(codigo);

        if (Objects.nonNull(vendaProdutos)) {
            throw new Exception(" já existe entrada para esse produto, não é possivel deletar.");
        }
    }

    public void validaSeProdutoJaTemVenda(Long codigo) throws Exception {
        List<EntradaProduto> entradaProdutos = entradaProdutoRepository.getEntradaPorCodigoProduto(codigo);
        if (Objects.nonNull(entradaProdutos)) {
            throw new Exception(" já existe entrada para esse produto, não é possivel deletar.");
        }
    }

}
