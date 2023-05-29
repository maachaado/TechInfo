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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.core.service.ServicoService;
import br.com.techinfo2.util.NegocioException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TelaListaServico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<ServicoEntity> servicos;
	private JButton btnEditar;
	private JLabel lblNewLabel;
	private JTextField fieldCodigo;
	private JLabel lblNewLabel_1;
	private JTextField fieldEquipamento;
	private JLabel lblNewLabel_2;
	private JTextField fieldData;
	private JButton btnPesquisar;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaServico frame = new TelaListaServico();
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
	public TelaListaServico() {
		setTitle("TECHINFO - EXTERNO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 728, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		ServicoEntity servicoSelecionado = servicos.get(table.getSelectedRow());
		if(JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O SERVIÇO: " + servicoSelecionado.getCodigo()) == JOptionPane.OK_OPTION) {
			
			try {
				new ServicoService().excluirServico(servicoSelecionado.getCodigo());
				popularTabela();
			} catch (NegocioException e1) {
				JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
			}
		}
				
			}
		});
		
		btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			
				ServicoEntity servicoSelecionado = servicos.get(table.getSelectedRow());
				TelaCadastroServico tcs = new TelaCadastroServico();
				tcs.buscarPorId(servicoSelecionado.getCodigo());
				tcs.setVisible(true);
				dispose();
			}
		});
		
		JButton btnAdc = new JButton("ADICIONAR");
		btnAdc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdc.setBackground(Color.GREEN);
		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroServico tcs = new TelaCadastroServico();
				tcs.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel = new JLabel("CÓDIGO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setColumns(10);
		
		lblNewLabel_1 = new JLabel("EQUIPAMENTO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldEquipamento = new JTextField();
		fieldEquipamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldEquipamento.setColumns(10);
		
		lblNewLabel_2 = new JLabel("DATA");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldData = new JTextField();
		fieldData.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldData.setColumns(10);
		
		btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setIcon(new ImageIcon(TelaListaServico.class.getResource("/resourse/lupa-de-pesquisa.png")));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ServicoEntity servicoFiltro = new ServicoEntity();
				servicoFiltro.setEquipamento(fieldEquipamento.getText());
				servicoFiltro.setData(fieldData.getText());
				
				try {
					if(!fieldCodigo.getText().equals("")) {
				Long codigo = Long.parseLong(fieldCodigo.getText());
				servicoFiltro.setCodigo(codigo);
		        }
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "O VALOR DO CAMPO CÓDIGO PRECISA SER NUMÉRICO");
				}
				popularTabelaFiltrada(servicoFiltro);
				
				
				
				
				
			}
		});
		
		lblNewLabel_3 = new JLabel("PESQUISA:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane, Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnEditar)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fieldEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addComponent(btnAdc))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnExcluir)
									.addComponent(btnPesquisar)))))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdc)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addGap(34)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ServicoEntity servico = servicos.get(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "DETALHES: \n" + "CÓDIGO: " + servico.getCodigo() + "\nEQUIPAMENTO: " + servico.getEquipamento() + "\n PROBLEMA: " + 
						servico.getProblema() + "\n TIPO DE SERVIÇO: " + servico.getTipoServico() + "\n DATA: " +
						servico.getData() + "\n VALOR: " + servico.getValor());

				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "EQUIPAMENTO", "PROBLEMA", "TIPO DE SERVI\u00C7O", "DATA", "VALOR"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		popularTabela();
		
	}
	
	private void popularTabela() {
		
		try {
			servicos = new ServicoService().listarServico();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ServicoEntity servicoEntity : servicos) {
			model.addRow(new Object [] { servicoEntity.getCodigo(),		                                                       
					servicoEntity.getEquipamento(),
					servicoEntity.getProblema(),
					servicoEntity.getTipoServico(),
					servicoEntity.getData(),
					servicoEntity.getValor()});
			}		
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAR SERVIÇOS" + e.getMensagemDeErro());
		}		
	}	
		private void popularTabelaFiltrada(ServicoEntity servicoFiltro) {		
		try {
			servicos = new ServicoService().buscarFiltrado(servicoFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ServicoEntity servicoEntity : servicos) {
			model.addRow(new Object [] { servicoEntity.getCodigo(),		                                                       
					servicoEntity.getEquipamento(),
					servicoEntity.getProblema(),
					servicoEntity.getTipoServico(),
					servicoEntity.getData(),
					servicoEntity.getValor()});
			}		
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAR SERVIÇOS" + e.getMensagemDeErro());
		}		
	}	
}
