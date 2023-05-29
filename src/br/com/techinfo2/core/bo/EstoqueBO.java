package br.com.techinfo2.core.bo;

import java.util.List;

import br.com.techinfo2.core.dao.EstoqueDAO;
import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.util.NegocioException;

public class EstoqueBO {

	public String salvarEstoque(EstoqueEntity estoque) throws NegocioException {
		
		validarEstoque(estoque);
			
		EstoqueDAO edao = new EstoqueDAO();		
		return edao.salvarEstoque(estoque);
	}
	
	public List<EstoqueEntity> listarEstoque () throws NegocioException{
		return new EstoqueDAO().listarEstoque();
	}
	
	public void excluirEstoque(Long codigoEstoque) throws NegocioException {
		new EstoqueDAO().excluirEstoque(codigoEstoque);
	}
	
	public EstoqueEntity buscarPorId(Long codigoEstoque) throws NegocioException {
		return new EstoqueDAO().buscarPorId(codigoEstoque);
	}
	
	public String alterarEstoque(EstoqueEntity estoque) throws NegocioException {
		validarEstoque(estoque);
		return new EstoqueDAO().alterarEstoque(estoque);
	}
	
	private void validarEstoque(EstoqueEntity estoque) throws NegocioException {
		
		if(estoque.getPlacaMae() != null && estoque.getPlacaMae().equals("")) {
			throw new NegocioException("O CAMPO *PLACA MÃE* PRECISA SER PREENCHIDO");
		}
			if(estoque.getProcessador() != null && estoque.getProcessador().equals("")) {
				throw new NegocioException( "O CAMPO *PROCESSADOR* PRECISA SER PREENCHIDO");
			}
			if(estoque.getFonte() != null && estoque.getFonte().equals("")) {
				throw new NegocioException("O CAMPO *FONTE* PRECISA SER PREENCHIDO");
			}
			if(estoque.getMemoria() != null && estoque.getMemoria().equals("")) {
				throw new NegocioException("O CAMPO *MEMÓRIA* PRECISA SER PREENCHIDO");		
			}
			if(estoque.getPlacaVideo() != null && estoque.getPlacaVideo().equals("")) {
				throw new NegocioException("O CAMPO *PLACA DE VÍDEO* PRECISA SER PREENCHIDO");
		
	}
}
	
	public List<EstoqueEntity> buscarFiltrado(EstoqueEntity estoque) throws NegocioException {			
		return new EstoqueDAO().buscarFiltrado(estoque);
	}
	
}


