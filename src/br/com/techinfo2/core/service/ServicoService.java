package br.com.techinfo2.core.service;

import java.util.List;

import br.com.techinfo2.core.bo.ServicoBO;
import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.util.NegocioException;

public class ServicoService {

	public String salvarServico(ServicoEntity servico) throws NegocioException {
		ServicoBO bo = new ServicoBO();
		return bo.salvarServico(servico);
	}

	public List<ServicoEntity> listarServico() throws NegocioException {
		return new ServicoBO().listarServico();
	}
	
	public void excluirServico(Long codigoServico) throws NegocioException {
		new ServicoBO().excluirServico(codigoServico);
	}
	
	public ServicoEntity buscarPorId(Long codigoServico) throws NegocioException {
		return new ServicoBO().buscarPorId(codigoServico);
	}
	public String alterarServico(ServicoEntity servico) throws NegocioException {		
		return new ServicoBO().alterarServico(servico);
 }
	public List<ServicoEntity> buscarFiltrado(ServicoEntity servico) throws NegocioException{			
		return new ServicoBO().buscarFiltrado(servico);
 }
}
