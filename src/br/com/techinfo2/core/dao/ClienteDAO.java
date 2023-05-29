package br.com.techinfo2.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.techinfo2.core.dao.connection.ConexaoMysql;
import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.util.NegocioException;

public class ClienteDAO {

	public String salvarCliente(ClienteEntity cliente) throws NegocioException{
		
		String sql = "INSERT INTO cliente (NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, FONE_CLIENTE) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setString(4, cliente.getTelefone());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO CADASTRAR CLIENTE");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return "Cliente cadastrado \n";
	}
	
	public List<ClienteEntity> listarCliente() throws NegocioException{
		return null;
	}
	
	public void excluirCliente(Long codigoCliente) throws NegocioException {
		
		String sql = "DELETE FROM cliente WHERE CODIGO_CLIENTE = ?";
		PreparedStatement ps = null;
	
		try {
			
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("NÃO FOI POSSÍVEL EXCLUIR CLIENTE ");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ClienteEntity buscarPorId(Long codigoCliente) throws NegocioException{		
		String sql = "SELECT CODIGO_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, FONE_CLIENTE FROM cliente WHERE CODIGO_CLIENTE = ?";		
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {			
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);			
			rs = ps.executeQuery();			
			ClienteEntity clienteEncontrado = null;			
			if(rs.next()) {
				clienteEncontrado = new ClienteEntity();
				clienteEncontrado.setCodigo(rs.getLong("CODIGO_CLIENTE"));
				clienteEncontrado.setNome(rs.getString("NM_CLIENTE"));
				clienteEncontrado.setCpf(rs.getString("CPF_CLIENTE"));
				clienteEncontrado.setEndereco(rs.getString("END_CLIENTE"));
				clienteEncontrado.setTelefone(rs.getString("FONE_CLIENTE"));				
			}			
			return clienteEncontrado;			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO BUSCAR CLIENTE");
		}finally {
			try {				
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
 }		
	
	public String alterarCliente(ClienteEntity cliente) throws NegocioException {
		
		String sql = "UPDATE cliente SET NM_CLIENTE = ?, CPF_CLIENTE = ?, END_CLIENTE = ?, FONE_CLIENTE = ? WHERE CODIGO_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setString(4, cliente.getTelefone());
			ps.setLong(5, cliente.getCodigo());
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO ATUALIZAR DADOS DO CLIENTE");
			}finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return "CLIENTE ALTERADO";
		}
		
	public List<ClienteEntity> buscarFiltrado(ClienteEntity cliente) throws NegocioException {

		String sql = "SELECT CODIGO_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, FONE_CLIENTE FROM cliente";
		boolean adcWhere = true;
		
		List<ClienteEntity> resultado = new ArrayList<ClienteEntity>();
		
		if(cliente != null) {
			if(cliente.getCodigo() != null) {
				adcWhere = false;
				sql += " WHERE ";
				sql += " CODIGO_CLIENTE = ? ";
			}			
			if(cliente.getNome() != null && !cliente.getNome().equals("")) {
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " NM_CLIENTE LIKE ?"; 
			}
			
			if(cliente.getCpf() != null && !cliente.getCpf().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " CPF_CLIENTE LIKE ? "; 
			}
			if(cliente.getEndereco() != null && !cliente.getEndereco().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " END_CLIENTE LIKE ?"; 
			}
			if(cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}				
				sql += " FONE_CLIENTE LIKE ? "; 
			}
			}
		    
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    
		    try {
				
		    	ps = ConexaoMysql.getConexao().prepareStatement(sql);
				
				int indice = 0;
				
				if(cliente != null) {
					if(cliente.getCodigo() != null) {
						indice = indice + 1;
						ps.setLong(indice, cliente.getCodigo());
					}
					
					if(cliente.getNome() != null && !cliente.getNome().equals("")) {
						indice = indice + 1;
						ps.setString(indice, cliente.getNome() + "%");
					}
					if(cliente.getCpf() != null && !cliente.getCpf().equals("")) {
						indice = indice + 1;
						ps.setString(indice, cliente.getCpf() + "%");
				}
					
					if(cliente.getEndereco() != null && !cliente.getEndereco().equals("")) {
						indice = indice + 1;
						ps.setString(indice, cliente.getEndereco() + "%");
				}
					
					if(cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {
						indice = indice + 1;
						ps.setString(indice, cliente.getTelefone() + "%");
				}
					
			}
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					ClienteEntity clienteResultado = new ClienteEntity();
					clienteResultado.setCodigo(rs.getLong("CODIGO_CLIENTE"));
					clienteResultado.setNome(rs.getString("NM_CLIENTE"));
					clienteResultado.setCpf(rs.getString("CPF_CLIENTE"));
					clienteResultado.setEndereco(rs.getString("END_CLIENTE"));
					clienteResultado.setTelefone(rs.getString("FONE_CLIENTE"));
					resultado.add(clienteResultado);
				}
				
			} catch (SQLException e) {
				throw new NegocioException("ERRO NA BUSCA FILTRADA");			
			}finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		    return resultado;
		}			
}















