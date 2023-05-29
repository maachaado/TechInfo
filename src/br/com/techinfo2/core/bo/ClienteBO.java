package br.com.techinfo2.core.bo;

import java.util.List;

import br.com.techinfo2.core.dao.ClienteDAO;
import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.util.NegocioException;

public class ClienteBO {

	public String salvarCliente(ClienteEntity cliente) throws NegocioException {
		
		validarCliente(cliente);
		
		ClienteDAO cdao = new ClienteDAO();
		return cdao.salvarCliente(cliente);
}
	
		public List<ClienteEntity> listarCliente()throws NegocioException{
			return new ClienteDAO().listarCliente();
}

		public void excluirCliente(Long codigoCliente) throws NegocioException {
			new ClienteDAO().excluirCliente(codigoCliente);
		}
		
		public ClienteEntity buscarPorId(Long codigoCliente) throws NegocioException {
			return new ClienteDAO().buscarPorId(codigoCliente);
		}
		
		public String alterarCliente(ClienteEntity cliente) throws NegocioException {
			validarCliente(cliente);
			return new ClienteDAO().alterarCliente(cliente);
		}
		
		private void validarCliente(ClienteEntity cliente) throws NegocioException {
			
			if(cliente.getNome() != null && cliente.getNome().equals("")) {
				throw new NegocioException("O *NOME* PRECISA SER PREENCHIDO"); 
		}
			if(cliente.getCpf() != null && cliente.getCpf().equals("")) {
				throw new NegocioException("O *CPF* PRECISA SER PREENCHIDO") ;
			}
			if(cliente.getEndereco() != null && cliente.getEndereco().equals("")) {
				throw new NegocioException( "O *ENDEREÇO* PRECISA SER PREENCHIDO");
			}
			if(cliente.getTelefone() != null && cliente.getTelefone().equals("")) {
				throw new NegocioException( "O *TELEFONE* PRECISA SER PREENCHIDO");
			}			
		}

		public List<ClienteEntity> buscarFiltrado(ClienteEntity cliente) throws NegocioException {			
			return new ClienteDAO().buscarFiltrado(cliente);
		}
		
}

