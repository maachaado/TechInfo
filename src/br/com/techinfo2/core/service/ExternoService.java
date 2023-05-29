package br.com.techinfo2.core.service;

import java.util.List;

import br.com.techinfo2.core.bo.ExternoBO;
import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.util.NegocioException;

public class ExternoService {

	public String salvarExterno(ExternoEntity externo) throws NegocioException {
		ExternoBO bo = new ExternoBO();
		return bo.salvarExterno(externo);
	}
	
	public List<ExternoEntity> listarExterno() throws NegocioException {
		return new ExternoBO().listarExterno();
	}
	
	public void excluirExterno(Long codigoExterno) throws NegocioException {
		new ExternoBO().excluirExterno(codigoExterno);
	}
	
	public ExternoEntity buscarPorId(Long codigoExterno) throws NegocioException {
		return new ExternoBO().buscarPorId(codigoExterno);
		
	}
	public String alterarExterno(ExternoEntity externo) throws NegocioException{		
		return new ExternoBO().alterarExterno(externo);
}
	public List<ExternoEntity> buscarFiltrado(ExternoEntity externo) throws NegocioException{			
		return new ExternoBO().buscarFiltrado(externo);
	}
}