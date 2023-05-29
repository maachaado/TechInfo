package br.com.techinfo2.core.bo;

import java.util.List;

import br.com.techinfo2.core.dao.ExternoDAO;
import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.util.NegocioException;

public class ExternoBO {

	public String salvarExterno(ExternoEntity externo) throws NegocioException {
		
		validarExterno(externo);
		
		ExternoDAO exdao = new ExternoDAO();	
		return exdao.salvarExterno(externo);
		}
	
		public List<ExternoEntity> listarExterno() throws NegocioException{
			return new ExternoDAO().listarExterno();
		}
		
		public void excluirExterno(Long codigoExterno) throws NegocioException {
			new ExternoDAO().excluirExterno(codigoExterno);
		}
		
		public ExternoEntity buscarPorId(Long codigoExterno) throws NegocioException {
			return new ExternoDAO().buscarPorId(codigoExterno);
			
		}
		
		public String alterarExterno(ExternoEntity externo) throws NegocioException{
			validarExterno(externo);
			return new ExternoDAO().alterarExterno(externo);
		}
		
		private void validarExterno(ExternoEntity externo) throws NegocioException {
			
			if(externo.getVisitas() != null && externo.getVisitas().equals("")) {
				throw new NegocioException("O CAMPO *VISITA* PRECISA SER PREENCHIDO");
				
			}
		}
		
		public List<ExternoEntity> buscarFiltrado(ExternoEntity externo) throws NegocioException {			
			return new ExternoDAO().buscarFiltrado(externo);
		}
	}

