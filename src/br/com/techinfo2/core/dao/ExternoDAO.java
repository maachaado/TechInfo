package br.com.techinfo2.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.techinfo2.core.dao.connection.ConexaoMysql;
import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.util.NegocioException;

public class ExternoDAO {

	public String salvarExterno(ExternoEntity externo)  throws NegocioException{

		String sql = "INSERT INTO externo (VISIT_EXTERNO) VALUES (?)";
		PreparedStatement ps = null;
		
		try {
			
		ps = ConexaoMysql.getConexao().prepareStatement(sql);
		ps.setString(1, externo.getVisitas());
		
		ps.execute();
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO CADASTRAR EXTERNO");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return "Externo cadastrado \n";
	}
	
	public List<ExternoEntity> listarExterno() throws NegocioException {
		
		String sql = "SELECT CODIGO_EXTERNO, VISIT_EXTERNO FROM externo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ExternoEntity> externos = new ArrayList<ExternoEntity>();
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ExternoEntity ext = new ExternoEntity();
				ext.setCodigo(rs.getLong("CODIGO_EXTERNO"));
				ext.setVisitas(rs.getString("VISIT_EXTERNO"));
				externos.add(ext);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO LISTAR EXTERNO");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return externos;
	}
	
	public void excluirExterno(Long codigoExterno) throws NegocioException {
		
		String sql = "DELETE FROM externo WHERE CODIGO_EXTERNO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoExterno);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("NÃO FOI POSSÍVEL EXCLUIR EXTERNO");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public ExternoEntity buscarPorId(Long codigoCliente) throws NegocioException {
		
		String sql = "SELECT CODIGO_EXTERNO, VISIT_EXTERNO FROM externo WHERE CODIGO_EXTERNO = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			rs = ps.executeQuery();
			ExternoEntity externoEncontrado = null;
			
			if(rs.next()) {
				externoEncontrado = new ExternoEntity();
				externoEncontrado.setCodigo(rs.getLong("CODIGO_EXTERNO"));
				externoEncontrado.setVisitas(rs.getString("VISIT_EXTERNO"));
			}
			return externoEncontrado;
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO BUSCAR SERVIÇO");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String alterarExterno(ExternoEntity externo) throws NegocioException {
		
		String sql = "UPDATE externo SET VISIT_EXTERNO = ? WHERE CODIGO_EXTERNO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			
			ps.setString(1, externo.getVisitas());
			ps.setLong(2, externo.getCodigo());
			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO ATUALIZAR DADOS DE EXTERNO");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "EXTERNO ALTERADO";
		
	}		
	
	public List<ExternoEntity> buscarFiltrado(ExternoEntity externo) throws NegocioException{

		String sql = "SELECT CODIGO_EXTERNO, VISIT_EXTERNO FROM externo";
		boolean adcWhere = true;
		
		List<ExternoEntity> resultado = new ArrayList<ExternoEntity>();
		
		if(externo != null) {
			if(externo.getCodigo() != null) {
				adcWhere = false;
				sql += " WHERE ";
				sql += " CODIGO_EXTERNO = ? ";
			}			
			if(externo.getVisitas() != null && !externo.getVisitas().equals("")) {
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " VISIT_EXTERNO LIKE ?"; 
			}
			}	
		    
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    
		    try {
				
		    	ps = ConexaoMysql.getConexao().prepareStatement(sql);
				
				int indice = 0;
				
				if(externo != null) {
					if(externo.getCodigo() != null) {
						indice = indice + 1;
						ps.setLong(indice, externo.getCodigo());
					}
					
					if(externo.getVisitas() != null && !externo.getVisitas().equals("")) {
						indice = indice + 1;
						ps.setString(indice, externo.getVisitas() + "%");
					}
					
				rs = ps.executeQuery();
				
				while(rs.next()) {
				 ExternoEntity externoResultado = new 	ExternoEntity();
				 externoResultado.setCodigo(rs.getLong("CODIGO_externo"));
				 externoResultado.setVisitas(rs.getString("VISIT_EXTERNO"));				
					resultado.add(externoResultado);
				}
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










