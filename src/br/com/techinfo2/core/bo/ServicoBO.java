package br.com.techinfo2.core.bo;

import java.util.List;

import br.com.techinfo2.core.dao.ServicoDAO;
import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.util.NegocioException;

public class ServicoBO {

	public String salvarServico(ServicoEntity servico) throws NegocioException {
		
		
			
		ServicoDAO sdao = new ServicoDAO();
		return sdao.salvarServico(servico);
	}
	
	public List<ServicoEntity> listarServico() throws NegocioException {
		return new ServicoDAO().listarServico();
	}
	
	public void excluirServico(Long codigoServico) throws NegocioException {
		new ServicoDAO().excluirServico(codigoServico);
	}
	
	public ServicoEntity buscarPorId(Long codigoServico) throws NegocioException {
		return new ServicoDAO().buscarPorId(codigoServico);
	}
	
	public String alterarServico(ServicoEntity servico) throws NegocioException {
		validarServico(servico);
		return new ServicoDAO().alterarServico(servico);
	}
	
	private void validarServico(ServicoEntity servico) throws NegocioException {
		
		if(servico.getEquipamento() != null && servico.getEquipamento().equals("")) {
			throw new NegocioException("O equipamento precisa ser preenchido");
		}
			if(servico.getProblema() != null && servico.getProblema().equals("")) {
				throw new NegocioException("O problema precisa ser preenchido");
			}
			if(servico.getTipoServico() != null && servico.getTipoServico().equals("")) {
				throw new NegocioException( "O tipo de serviço precisa ser preenchido");
			}
			if(servico.getData() != null && servico.getData().equals("")) {
				throw new NegocioException("A data precisa ser preenchida");
				
			}
			if(servico.getValor() != null && servico.getValor().equals("")) {
				throw new NegocioException("O valor precisa ser preenchido");
			
			}
			}
	
			public List<ServicoEntity> buscarFiltrado(ServicoEntity servico) throws NegocioException{			
				return new ServicoDAO().buscarFiltrado(servico);
	}
}
	












