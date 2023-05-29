package br.com.techinfo2.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.techinfo2.core.dao.connection.ConexaoMysql;
import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.util.NegocioException;

public class EstoqueDAO {

	public String salvarEstoque(EstoqueEntity estoque) throws NegocioException{
		
		String sql = "INSERT INTO estoque (PM_ESTOQUE, CPU_ESTOQUE, FONTE_ESTOQUE, MEMO_ESTOQUE, PV_ESTOQUE) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setString(1, estoque.getPlacaMae());
			ps.setString(2, estoque.getProcessador());
			ps.setString(3, estoque.getFonte());
			ps.setString(4, estoque.getMemoria());
			ps.setString(5, estoque.getPlacaVideo());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO CADASTRAR ESTOQUE");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return "Estoque cadastrado \n";
	}
	
	public List<EstoqueEntity> listarEstoque() throws NegocioException{
		
		String sql = "SELECT CODIGO_ESTOQUE, PM_ESTOQUE, CPU_ESTOQUE, FONTE_ESTOQUE, MEMO_ESTOQUE, PV_ESTOQUE FROM estoque";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<EstoqueEntity> estoques = new ArrayList<EstoqueEntity>();
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
		while(rs.next()) {
			EstoqueEntity est = new EstoqueEntity();
			est.setCodigo(rs.getLong("CODIGO_ESTOQUE"));
			est.setPlacaMae(rs.getString("PM_ESTOQUE"));
			est.setProcessador(rs.getString("CPU_ESTOQUE"));
			est.setFonte(rs.getString("FONTE_ESTOQUE"));
			est.setMemoria(rs.getString("MEMO_ESTOQUE"));
			est.setPlacaVideo(rs.getString("PV_ESTOQUE"));
			estoques.add(est);
			}					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO LISTAR ESTOQUE");
		}finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return estoques;
	}
	
	public void excluirEstoque(Long codigoEstoque) throws NegocioException {
		
		String sql = "DELETE FROM estoque WHERE CODIGO_ESTOQUE = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoEstoque);
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("NÃO FOI POSSÍVEL EXCLUIR ESTOQUE");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public EstoqueEntity buscarPorId(Long codigoEstoque) throws NegocioException {
		
		String sql = "SELECT CODIGO_ESTOQUE, PM_ESTOQUE, CPU_ESTOQUE, FONTE_ESTOQUE, MEMO_ESTOQUE, PV_ESTOQUE FROM estoque WHERE CODIGO_ESTOQUE = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoEstoque);			
			rs = ps.executeQuery();
			
			EstoqueEntity estoqueEncontrado = null;
			
			if(rs.next()) {				
				 estoqueEncontrado = new EstoqueEntity();
				estoqueEncontrado.setCodigo(rs.getLong("CODIGO_ESTOQUE")); 
				estoqueEncontrado.setPlacaMae(rs.getString("PM_ESTOQUE"));
				estoqueEncontrado.setProcessador(rs.getString("CPU_ESTOQUE"));
				estoqueEncontrado.setFonte(rs.getString("FONTE_ESTOQUE"));
				estoqueEncontrado.setMemoria(rs.getString("MEMO_ESTOQUE"));
				estoqueEncontrado.setPlacaVideo(rs.getString("PV_ESTOQUE"));
			}
			
			return estoqueEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO BUSCAR ESTOQUE");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}		
	
	public String alterarEstoque(EstoqueEntity estoque) throws NegocioException {
		
		String sql = "UPDATE estoque SET PM_ESTOQUE = ?, CPU_ESTOQUE = ?, FONTE_ESTOQUE = ?, MEMO_ESTOQUE = ?, PV_ESTOQUE = ? WHERE CODIGO_ESTOQUE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			
			ps.setString(1, estoque.getPlacaMae());
			ps.setString(2, estoque.getProcessador());
			ps.setString(3, estoque.getFonte());
			ps.setString(4, estoque.getMemoria());
			ps.setString(5, estoque.getPlacaVideo());
			ps.setLong(6, estoque.getCodigo());
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO ATUALIZAR DADOS DO ESTOQUE");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "ESTOQUE ALTERADO";
	}
	
	public List<EstoqueEntity> buscarFiltrado(EstoqueEntity estoque) throws NegocioException{
		
		String sql = "SELECT CODIGO_ESTOQUE, PM_ESTOQUE, CPU_ESTOQUE, FONTE_ESTOQUE, MEMO_ESTOQUE, PV_ESTOQUE FROM estoque";
		boolean adcWhere = true;
		
		List<EstoqueEntity> resultado = new ArrayList<EstoqueEntity>();
		
		if(estoque != null) {
			if(estoque.getCodigo() != null) {
				adcWhere = false;
				sql += " WHERE ";
				sql += " CODIGO_ESTOQUE = ? ";
			}			
			if(estoque.getPlacaMae() != null && !estoque.getPlacaMae().equals("")) {
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " PM_ESTOQUE LIKE ?"; 
			}
			
			if(estoque.getProcessador() != null && !estoque.getProcessador().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " CPU_ESTOQUE LIKE ? "; 
			}
			if(estoque.getFonte() != null && !estoque.getFonte().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}			
				sql += " FONTE_ESTOQUE LIKE ?"; 
			}
			if(estoque.getMemoria() != null && !estoque.getMemoria().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}				
				sql += " MEMO_ESTOQUE LIKE ? "; 
			}
			if(estoque.getPlacaVideo() != null && !estoque.getPlacaVideo().equals("")) {			
				if(adcWhere) {
					sql += " WHERE ";
					adcWhere = false;
				}else {
					sql += " AND ";
				}				
				sql += " PV_ESTOQUE LIKE ? "; 
			}
			}
		
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    
		    try {
				
		    	ps = ConexaoMysql.getConexao().prepareStatement(sql);
				
				int indice = 0;
				
				if(estoque != null) {
					if(estoque.getCodigo() != null) {
						indice = indice + 1;
						ps.setLong(indice, estoque.getCodigo());
					}
					
					if(estoque.getPlacaMae() != null && !estoque.getPlacaMae().equals("")) {
						indice = indice + 1;
						ps.setString(indice, estoque.getPlacaMae() + "%");
					}
					if(estoque.getProcessador() != null && !estoque.getProcessador().equals("")) {
						indice = indice + 1;
						ps.setString(indice, estoque.getProcessador() + "%");
				}
					
					if(estoque.getFonte() != null && !estoque.getFonte().equals("")) {
						indice = indice + 1;
						ps.setString(indice, estoque.getFonte() + "%");
				}
					
					if(estoque.getMemoria() != null && !estoque.getMemoria().equals("")) {
						indice = indice + 1;
						ps.setString(indice, estoque.getMemoria() + "%");
				}
					if(estoque.getPlacaVideo() != null && !estoque.getPlacaVideo().equals("")) {
						indice = indice + 1;
						ps.setString(indice, estoque.getPlacaVideo() + "%");
				}
			}
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					EstoqueEntity estoqueResultado = new EstoqueEntity();
					estoqueResultado.setCodigo(rs.getLong("CODIGO_ESTOQUE"));
					estoqueResultado.setPlacaMae(rs.getString("PM_ESTOQUE"));
					estoqueResultado.setProcessador(rs.getString("CPU_ESTOQUE"));
					estoqueResultado.setFonte(rs.getString("FONTE_ESTOQUE"));
					estoqueResultado.setMemoria(rs.getString("MEMO_ESTOQUE"));
					estoqueResultado.setPlacaVideo(rs.getString("PV_ESTOQUE"));
					resultado.add(estoqueResultado);
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






























