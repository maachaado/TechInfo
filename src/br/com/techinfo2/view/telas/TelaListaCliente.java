package br.com.techinfo2.view.telas;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.core.service.ClienteService;
import br.com.techinfo2.util.NegocioException;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaListaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private List<ClienteEntity> clientes;
	private JButton btnExcluir;
	private JButton btnAdc;
	private JTextField fieldCodigo;
	private JTextField fieldCpf;
	private JTextField fieldNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaCliente frame = new TelaListaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListaCliente() {
		setTitle("TECHINFO - CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O CLIENTE: " + clienteSelecionado.getCodigo()) == JOptionPane.OK_OPTION){
					try {
						new ClienteService().excluirCliente(clienteSelecionado.getCodigo());
						popularTabela();
					} catch (NegocioException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}
				
			}
		});
		btnExcluir.setEnabled(false);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.buscarPorId(clienteSelecionado.getCodigo());
				tcc.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		
		btnAdc = new JButton("ADICIONAR");
		btnAdc.setForeground(Color.BLACK);
		btnAdc.setBackground(Color.GREEN);
		btnAdc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("PESQUISA:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2 = new JLabel("CÓDIGO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CPF");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("NOME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCpf = new JTextField();
		fieldCpf.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCpf.setColumns(10);
		
		fieldNome = new JTextField();
		fieldNome.setBackground(SystemColor.inactiveCaptionBorder);
		fieldNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldNome.setColumns(10);
		
		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setIcon(new ImageIcon(TelaListaCliente.class.getResource("/resourse/lupa-de-pesquisa.png")));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteFiltro = new ClienteEntity();
				clienteFiltro.setNome(fieldNome.getText());
				clienteFiltro.setCpf(fieldCpf.getText());
				
				try {
					if(!fieldCodigo.getText().equals("")) {
				Long codigo = Long.parseLong(fieldCodigo.getText());
				clienteFiltro.setCodigo(codigo);
		        }
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "O VALOR DO CAMPO CÓDIGO PRECISA SER NUMÉRICO");
				}
				popularTabelaFiltrada(clienteFiltro);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAdc)
							.addPreferredGap(ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar))
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdc)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addGap(11)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteEntity cliente = clientes.get(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "O CLIENTE SELECIONADO FOI: \n CÓDIGO: " + cliente.getCodigo() + "\n NOME: " + 
				cliente.getNome() + "\n CPF: " + cliente.getCpf() + "\n ENDEREÇO: " + cliente.getEndereco() + "\n TELEFONE: " + cliente.getTelefone());
			
							btnExcluir.setEnabled(true);
							btnEditar.setEnabled(true);
			
				}});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO ", "NOME", "CPF", "ENDERE\u00C7O", "TELEFONE"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		popularTabela();
	}
	
	private void popularTabela() {
		try {
			clientes = new ClienteService().listarCliente();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ClienteEntity clienteEntity : clientes) {
				model.addRow(new Object[] {clienteEntity.getCodigo(), 
										   clienteEntity.getNome(), 
						                   clienteEntity.getCpf(),
						                   clienteEntity.getEndereco(), 
						                   clienteEntity.getTelefone()});
			}
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAR CLIENTES" + e.getMensagemDeErro());
		
	}			
 }
	private void popularTabelaFiltrada(ClienteEntity clienteFiltro) {
		try {
			clientes = new ClienteService().buscarFiltrado(clienteFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ClienteEntity clienteEntity : clientes) {
				model.addRow(new Object[] {clienteEntity.getCodigo(), 
										   clienteEntity.getNome(), 
						                   clienteEntity.getCpf(),
						                   clienteEntity.getEndereco(), 
						                   clienteEntity.getTelefone()});
			}
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAR CLIENTES" + e.getMensagemDeErro());
		
	}			
}
}
