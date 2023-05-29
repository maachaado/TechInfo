package br.com.techinfo2.view.principal;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.core.service.ClienteService;
import br.com.techinfo2.core.service.EstoqueService;
import br.com.techinfo2.core.service.ExternoService;
import br.com.techinfo2.core.service.ServicoService;
import br.com.techinfo2.util.NegocioException;

public class Principal {

	public static void main(String[] args) throws NegocioException {

		 ClienteEntity cliente = new ClienteEntity();
		 cliente.setNome("João Henrique");
		 cliente.setCpf("111.222.333-44");
		 cliente.setEndereco("Laguna SC");
		 cliente.setTelefone("40028922");
		
		 ClienteService cs = new ClienteService();
		 try {
			System.out.println(cs.salvarCliente(cliente));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
		}

		 ServicoEntity servico = new ServicoEntity();
		 servico.setEquipamento("Desktop");
		 servico.setProblema("Tela Azul");
		 servico.setTipoServico("Teste de componentes e efetuar troca");
		 servico.setData("12/02/2022");
		 servico.setValor("R$ 200,00");
	 
		 ServicoService ss = new ServicoService();
		 try {
			System.out.println(ss.salvarServico(servico));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
		}
			 
		 EstoqueEntity estoque = new EstoqueEntity();
		 estoque.setPlacaMae("5");
	 estoque.setProcessador("3");
	 estoque.setFonte("6");
		 estoque.setMemoria("12");
		 estoque.setPlacaVideo("3");
		 	
	
		 EstoqueService es = new EstoqueService();
		try {
			System.out.println(es.salvarEstoque(estoque));
		} catch (NegocioException e) {
		JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
		}
				 
		 ExternoEntity externo = new ExternoEntity();
		 externo.setVisitas("Rogério, rua Milagres n° 103 ás 14:30");
		 
		 ExternoService exs = new ExternoService();
		 try {
			System.out.println(exs.salvarExterno(externo));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
		}	

		List<ClienteEntity> clientes = new ClienteService().listarCliente();
		for (ClienteEntity clienteEntity : clientes) {
			System.out.println("Nome: " + clienteEntity.getNome() + " - Código: " + clienteEntity.getCodigo() + " \n");
		}	
		
		List<EstoqueEntity> estoques = new EstoqueService().listarEstoque();
		for (EstoqueEntity estoqueEntity : estoques) {
			System.out.println("CPU: " + estoqueEntity.getProcessador() + " \n");
		}
		
		List<ExternoEntity> externos = new ExternoService().listarExterno();
		for (ExternoEntity externoEntity : externos) {
			System.out.println("Visitas: " + externoEntity.getVisitas() + " \n");
		}
		
		List<ServicoEntity> servicos = new ServicoService().listarServico();
		for (ServicoEntity servicoEntity : servicos) {
			System.out.println("Equipamento: " + servicoEntity.getEquipamento() + " \n");
		
		
}}
	
}
	
	