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

import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.core.service.EstoqueService;
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
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaListaEstoque extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<EstoqueEntity> estoques;
	private JButton btnExcluir;
	private JButton btnAdc;
	private JLabel lblNewLabel;
	private JButton btnPesquisar;
	private JLabel lblNewLabel_1;
	private JTextField fieldCódigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaEstoque frame = new TelaListaEstoque();
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
	public TelaListaEstoque() {
		setTitle("TECHINFO - ESTOQUE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstoqueEntity estoqueSelecionado = estoques.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O ESTOQUE: " + estoqueSelecionado.getCodigo()) == JOptionPane.OK_OPTION) {
					
					try {
						new EstoqueService().excluirEstoque(estoqueSelecionado.getCodigo());
						popularTabela();
					} catch (NegocioException e1) {
						e1.printStackTrace();
					}
				}								
			}
		});
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstoqueEntity estoqueSelecionado = estoques.get(table.getSelectedRow());
				TelaCadastroEstoque tce = new TelaCadastroEstoque();
				tce.buscarPorId(estoqueSelecionado.getCodigo());
				tce.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		
		btnAdc = new JButton("ADICIONAR");
		btnAdc.setBackground(Color.GREEN);
		btnAdc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdc.setForeground(Color.BLACK);
		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroEstoque tce = new TelaCadastroEstoque();
				tce.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel = new JLabel("CÓDIGO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setIcon(new ImageIcon(TelaListaEstoque.class.getResource("/resourse/lupa-de-pesquisa.png")));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			EstoqueEntity estoqueFiltro = new EstoqueEntity();
			
			try {	
				if(!fieldCódigo.getText().equals("")) {
				Long codigo = Long.parseLong(fieldCódigo.getText());
				estoqueFiltro.setCodigo(codigo);
			}
			}catch(Exception e2){
			JOptionPane.showMessageDialog(null, "O VALOR DO CAMPO CÓDIGO PRECISA SER NUMÉRICO");
			}
				popularTabelaFiltrada(estoqueFiltro);
			}
		});
		
		lblNewLabel_1 = new JLabel("PESQUISA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCódigo = new JTextField();
		fieldCódigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCódigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCódigo.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdc)
							.addPreferredGap(ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCódigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar))
						.addComponent(lblNewLabel_1))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnAdc))
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(fieldCódigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EstoqueEntity estoque = estoques.get(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "O ESTOQUE SELECIONADO FOI: \n CÓDIGO: " + estoque.getCodigo() + "A QUANTIDADE RELACIONADA É: \n PLACA MÃE: " + estoque.getPlacaMae() + "\n PROCESSADOR: " + 
						estoque.getProcessador() + "\n FONTES: " + estoque.getFonte() + "\n MEMÓRIA: " +
						estoque.getMemoria() + "\n PLACA DE VÍDEO: " + estoque.getPlacaVideo());
				
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "PLACA M\u00C3E", "PROCESSADOR", "FONTE", "MEM\u00D3RIA", "PLACA DE V\u00CDDEO"
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
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		popularTabela();
		
	}
	
	private void popularTabela() {
		try {
			estoques = new EstoqueService().listarEstoque();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for(EstoqueEntity estoqueEntity : estoques) {
				model.addRow(new Object[] {estoqueEntity.getCodigo(),
						estoqueEntity.getPlacaMae(),
						estoqueEntity.getProcessador(),
						estoqueEntity.getFonte(),
						estoqueEntity.getMemoria(),
						estoqueEntity.getPlacaVideo()});
						
				}
				
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());;
			}
		}
	
	private void popularTabelaFiltrada(EstoqueEntity estoqueFiltro) {
		try {
			estoques = new EstoqueService().buscarFiltrado(estoqueFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for(EstoqueEntity estoqueEntity : estoques) {
				model.addRow(new Object[] {estoqueEntity.getCodigo(),
						estoqueEntity.getPlacaMae(),
						estoqueEntity.getProcessador(),
						estoqueEntity.getFonte(),
						estoqueEntity.getMemoria(),
						estoqueEntity.getPlacaVideo()});
						
				}
				
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());;
			}
		}
	}
