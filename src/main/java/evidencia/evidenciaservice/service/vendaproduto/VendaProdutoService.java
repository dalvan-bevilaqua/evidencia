package evidencia.evidenciaservice.service.vendaproduto;

import evidencia.evidenciaservice.model.VendaProduto;
import evidencia.evidenciaservice.repository.vendaproduto.VendaProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VendaProdutoService {

    @Autowired
    private VendaProdutoRespository vendaProdutoRespository;

    public VendaProduto salvar(VendaProduto vendaProduto) {
        if (Objects.isNull(vendaProduto.getValorVendido())){
            vendaProduto.setValorVendido(vendaProduto.getValor());
        }
        return vendaProdutoRespository.save(vendaProduto);
    }

}
