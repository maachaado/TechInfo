package br.com.techinfo2.core.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.techinfo2.core.bo.ClienteBO;
import br.com.techinfo2.core.dao.connection.ConexaoMysql;
import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.util.NegocioException;

public class ClienteService {

	public String salvarCliente(ClienteEntity cliente) throws NegocioException {
		ClienteBO bo = new ClienteBO();
		return bo.salvarCliente(cliente);
	}
	
	public List<ClienteEntity> listarCliente() throws NegocioException {
		
		String sql = "SELECT CODIGO_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, FONE_CLIENTE FROM cliente";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ClienteEntity cli = new ClienteEntity();
				cli.setCodigo(rs.getLong("CODIGO_CLIENTE"));
				cli.setNome(rs.getString("NM_CLIENTE"));
				cli.setCpf(rs.getString("CPF_CLIENTE"));
				cli.setEndereco(rs.getString("END_CLIENTE"));
				cli.setTelefone(rs.getString("FONE_CLIENTE"));
				clientes.add(cli);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO LISTAR CLIENTE");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return clientes;
	}
	
	public void excluirCliente(Long codigoCliente) throws NegocioException {
		new ClienteBO().excluirCliente(codigoCliente);
		
	}
			
	public ClienteEntity buscarPorId(Long codigoCliente) throws NegocioException {
		return new ClienteBO().buscarPorId(codigoCliente);
	}
	
	public String alterarCliente(ClienteEntity cliente) throws NegocioException {		
		return new ClienteBO().alterarCliente(cliente);
}
	public List<ClienteEntity> buscarFiltrado(ClienteEntity cliente) throws NegocioException {			
		return new ClienteBO().buscarFiltrado(cliente);
}
	}

		
		
		
		
		