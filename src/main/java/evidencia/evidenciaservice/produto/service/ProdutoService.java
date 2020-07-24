package evidencia.evidenciaservice.produto.service;

import evidencia.evidenciaservice.produto.model.Produto;
import evidencia.evidenciaservice.produto.service.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto atualizar (long codigo, Produto produto){
        Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        return produtoRepository.save(produtoSalvo);
    }

    public Produto buscarProdutoPeloCodigo(Long codigo){
        Produto produtoSalvo = produtoRepository.findById(codigo).orElse(null);
        if (produtoSalvo == null){
            throw new EmptyResultDataAccessException(1);
        }
        return produtoSalvo;
    }
}
