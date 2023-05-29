package br.com.techinfo2.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.techinfo2.core.dao.connection.ConexaoMysql;
import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.util.NegocioException;

public class ServicoDAO {

	public String salvarServico(ServicoEntity servico)  throws NegocioException {

		String sql = "INSERT INTO servico (EQUIP_SERVICO, PROB_SERVICO, TIPO_SERVICO, DATA_SERVICO, VALOR_SERVICO) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setString(1, servico.getEquipamento());
			ps.setString(2, servico.getProblema());
			ps.setString(3, servico.getTipoServico());
			ps.setString(4, servico.getData());
			ps.setString(5, servico.getValor());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao cadastrar serviço");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return "Serviço cadastrado \n";
	}	
	public List<ServicoEntity> listarServico() throws NegocioException{
		
		String sql = "SELECT CODIGO_SERVICO, EQUIP_SERVICO, PROB_SERVICO, TIPO_SERVICO, DATA_SERVICO, VALOR_SERVICO FROM servico";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ServicoEntity> servicos = new ArrayList<ServicoEntity>();
		
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ServicoEntity ser = new ServicoEntity();
				ser.setCodigo(rs.getLong("CODIGO_SERVICO"));
				ser.setEquipamento(rs.getString("EQUIP_SERVICO"));
				ser.setProblema(rs.getString("PROB_SERVICO"));
				ser.setTipoServico(rs.getString("TIPO_SERVICO"));
				ser.setData(rs.getString("DATA_SERVICO"));
				ser.setValor(rs.getString("VALOR_SERVICO"));
				servicos.add(ser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("ERRO AO LISTAR SERVIÇOS");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return servicos;
	}
	
	public void excluirServico(Long codigoServico) throws NegocioException {
		
		String sql = "DELETE FROM servico WHERE CODIGO_SERVICO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoServico);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("NÃO FOI POSSÍVEL EXCLUIR SERVICO");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public ServicoEntity buscarPorId(Long codigoServico) throws NegocioException {
		
		String sql = "SELECT CODIGO_SERVICO, EQUIP_SERVICO, PROB_SERVICO, TIPO_SERVICO, DATA_SERVICO, VALOR_SERVICO FROM servico WHERE CODIGO_SERVICO = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMysql.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoServico);
			rs = ps.executeQuery();
			
			ServicoEntity servicoEncontrado = null;
			if(rs.next()) {
				servicoEncontrado = new ServicoEntity();
				servicoEncontrado.setCodigo(rs.getLong("CODIGO_SERVICO"));
				servicoEncontrado.setEquipamento(rs.getString("EQUIP_SERVICO"));
				servicoEncontrado.setProblema(rs.getString("PROB_SERVICO"));
				servicoEncontrado.setTipoServico(rs.getString("TIPO_SERVICO"));
				servicoEncontrado.setData(rs.getString("DATA_SERVICO"));
				servicoEncontrado.setValor(rs.getString("VALOR_SERVICO"));				
			}
			
			return servicoEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("ERRO AO BUSCAR SERVIÇO");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
		public String alterarServico(ServicoEntity servico) throws NegocioException {
			
			String sql = "UPDATE servico SET EQUIP_SERVICO = ?, PROB_SERVICO = ?, TIPO_SERVICO = ?, DATA_SERVICO = ?, VALOR_SERVICO = ? WHERE CODIGO_SERVICO = ?";
			
			PreparedStatement ps = null;
			
			try {
				ps = ConexaoMysql.getConexao().prepareStatement(sql);
				
				ps.setString(1, servico.getEquipamento());
				ps.setString(2, servico.getProblema());
				ps.setString(3, servico.getTipoServico());
				ps.setString(4, servico.getData());
				ps.setString(5, servico.getValor());
				ps.setLong(6, servico.getCodigo());
				
				ps.execute();
				
			} catch (SQLException e) {
				throw new NegocioException("ERRO AO ATUALIZAR DADOS DO SERVIÇO");
			}finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return "SERVIÇO ALTERADO";
			
		}
		public List<ServicoEntity> buscarFiltrado(ServicoEntity servico) throws NegocioException {

			String sql = "SELECT CODIGO_SERVICO, EQUIP_SERVICO, PROB_SERVICO, TIPO_SERVICO, DATA_SERVICO, VALOR_SERVICO  FROM servico";
			boolean adcWhere = true;
			List<ServicoEntity> resultado = new ArrayList<ServicoEntity>();

			if(servico != null) {
				if(servico.getCodigo() != null) {
					adcWhere = false;
					sql += " WHERE ";
					sql += " CODIGO_SERVICO = ? ";
				}			
				if(servico.getEquipamento() != null && !servico.getEquipamento().equals("")) {
					if(adcWhere) {
						sql += " WHERE ";
						adcWhere = false;
					}else {
						sql += " AND ";
					}			
					sql += " EQUIP_SERVICO LIKE ?"; 
				}
				
				if(servico.getProblema() != null && !servico.getProblema().equals("")) {			
					if(adcWhere) {
						sql += " WHERE ";
						adcWhere = false;
					}else {
						sql += " AND ";
					}			
					sql += " PROB_SERVICO LIKE ? "; 
				}
				if(servico.getTipoServico() != null && !servico.getTipoServico().equals("")) {			
					if(adcWhere) {
						sql += " WHERE ";
						adcWhere = false;
					}else {
						sql += " AND ";
					}			
					sql += " TIPO_SERVICO LIKE ?"; 
				}
				if(servico.getData() != null && !servico.getData().equals("")) {			
					if(adcWhere) {
						sql += " WHERE ";
						adcWhere = false;
					}else {
						sql += " AND ";
					}				
					sql += " DATA_SERVICO LIKE ? "; 
				}
				if(servico.getValor() != null && !servico.getValor().equals("")) {			
					if(adcWhere) {
						sql += " WHERE ";
						adcWhere = false;
					}else {
						sql += " AND ";
					}				
					sql += " VALOR_SERVICO LIKE ? "; 
				}
				}
			
			    PreparedStatement ps = null;
			    ResultSet rs = null;
			    
			    try {
					
			    	ps = ConexaoMysql.getConexao().prepareStatement(sql);
					
					int indice = 0;
					
					if(servico != null) {
						if(servico.getCodigo() != null) {
							indice = indice + 1;
							ps.setLong(indice, servico.getCodigo());
						}
						
						if(servico.getEquipamento() != null && !servico.getEquipamento().equals("")) {
							indice = indice + 1;
							ps.setString(indice, servico.getEquipamento() + "%");
						}
						if(servico.getProblema() != null && !servico.getProblema().equals("")) {
							indice = indice + 1;
							ps.setString(indice, servico.getProblema() + "%");
					}
						
						if(servico.getTipoServico() != null && !servico.getTipoServico().equals("")) {
							indice = indice + 1;
							ps.setString(indice, servico.getTipoServico() + "%");
					}
						
						if(servico.getData() != null && !servico.getData().equals("")) {
							indice = indice + 1;
							ps.setString(indice, servico.getData() + "%");
					}
						if(servico.getValor() != null && !servico.getValor().equals("")) {
							indice = indice + 1;
							ps.setString(indice, servico.getValor() + "%");
					}
				}
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						ServicoEntity servicoResultado = new ServicoEntity();
						servicoResultado.setCodigo(rs.getLong("CODIGO_SERVICO"));
						servicoResultado.setEquipamento(rs.getString("EQUIP_SERVICO"));
						servicoResultado.setProblema(rs.getString("PROB_SERVICO"));
						servicoResultado.setTipoServico(rs.getString("TIPO_SERVICO"));
						servicoResultado.setData(rs.getString("DATA_SERVICO"));
						servicoResultado.setValor(rs.getString("VALOR_SERVICO"));
						resultado.add(servicoResultado);
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

	
	



