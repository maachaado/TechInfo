package br.com.techinfo2.core.service;

import java.util.List;

import br.com.techinfo2.core.bo.EstoqueBO;
import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.util.NegocioException;

public class EstoqueService {

	public String salvarEstoque(EstoqueEntity estoque) throws NegocioException {
		EstoqueBO bo = new EstoqueBO();
		return bo.salvarEstoque(estoque);
	}
	
	public List<EstoqueEntity> listarEstoque() throws NegocioException{
		return new EstoqueBO().listarEstoque();
	}
	
	public void excluirEstoque(Long codigoEstoque) throws NegocioException {
		new EstoqueBO().excluirEstoque(codigoEstoque);
	}

	public EstoqueEntity buscarPorId(Long codigoEstoque) throws NegocioException {
		return new EstoqueBO().buscarPorId(codigoEstoque);
	}
	public String alterarEstoque(EstoqueEntity estoque) throws NegocioException {	
		return new EstoqueBO().alterarEstoque(estoque);
}
	public List<EstoqueEntity> buscarFiltrado(EstoqueEntity estoque) throws NegocioException {			
		return new EstoqueBO().buscarFiltrado(estoque);
	}
}