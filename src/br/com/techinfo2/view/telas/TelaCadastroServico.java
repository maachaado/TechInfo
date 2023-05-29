package br.com.techinfo2.view.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.techinfo2.core.entity.ServicoEntity;
import br.com.techinfo2.core.service.ServicoService;
import br.com.techinfo2.util.NegocioException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaCadastroServico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldTipo;
	private JTextField fieldData;
	private JTextField fieldValor;
	private JTextField fieldProblema;
	private JTextField fieldEquip;
	private JTextField fieldCodigo;
	private JLabel labelTitulo;
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroServico frame = new TelaCadastroServico();
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
	public TelaCadastroServico() {
		setResizable(false);
		setTitle("TECHINFO - CLIENTES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("CADASTRO DE SERVIÇOS");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/resourse/salvar-silhueta-de-icone.png")));
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ServicoEntity ser = new ServicoEntity();
				ser.setEquipamento(fieldEquip.getText());
				ser.setProblema(fieldProblema.getText());
				ser.setTipoServico(fieldTipo.getText());
				ser.setData(fieldData.getText());
				ser.setValor(fieldValor.getText());
				
				String msg = null;
						
				try {
				if(fieldCodigo.getText().equals("")) {
				msg = new ServicoService().salvarServico(ser);
				}else {
				ser.setCodigo(Long.parseLong(fieldCodigo.getText()));
				msg = new ServicoService().alterarServico(ser);
				}
				limparCampo();				
				JOptionPane.showMessageDialog(btnSalvar, msg);
				
				TelaListaServico tls = new TelaListaServico();
				tls.setVisible(true);
				dispose();
						
				} catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro() , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("VALOR:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2 = new JLabel("DATA DE ENTRADA:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_3 = new JLabel("TIPO DE SERVIÇO:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("PROBLEMA:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_5 = new JLabel("EQUIPAMENTO:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldTipo = new JTextField();
		fieldTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldTipo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldTipo.setColumns(10);
		
		fieldData = new JTextField();
		fieldData.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldData.setBackground(SystemColor.inactiveCaptionBorder);
		fieldData.setColumns(10);
		
		fieldValor = new JTextField();
		fieldValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldValor.setBackground(SystemColor.inactiveCaptionBorder);
		fieldValor.setColumns(10);
		
		fieldProblema = new JTextField();
		fieldProblema.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldProblema.setBackground(SystemColor.inactiveCaptionBorder);
		fieldProblema.setColumns(10);
		
		fieldEquip = new JTextField();
		fieldEquip.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldEquip.setBackground(SystemColor.inactiveCaptionBorder);
		fieldEquip.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("CÓDIGO:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setColumns(10);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/resourse/volte.png")));
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaServico tls = new TelaListaServico();
				tls.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/resourse/servico 2.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(116)
							.addComponent(labelTitulo))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(btnVoltar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(98, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(fieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(fieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(fieldEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(fieldProblema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(fieldTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(55))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTitulo)
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(fieldEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(fieldProblema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(fieldTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(fieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(fieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void limparCampo() {
		fieldCodigo.setText("");
		fieldEquip.setText("");
		fieldProblema.setText("");
		fieldTipo.setText("");
		fieldData.setText("");
		fieldValor.setText("");
	}

	public void buscarPorId(Long codigoServico) {
		try {
			ServicoEntity servicoEncontrado = new ServicoService().buscarPorId(codigoServico);
			if(servicoEncontrado == null) {
				JOptionPane.showMessageDialog(null, "SERVIÇO NÃO ENCONTRADO", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText("" + servicoEncontrado.getCodigo());
				fieldEquip.setText(servicoEncontrado.getEquipamento());
				fieldProblema.setText(servicoEncontrado.getProblema());
				fieldTipo.setText(servicoEncontrado.getTipoServico());
				fieldData.setText(servicoEncontrado.getData());
				fieldValor.setText(servicoEncontrado.getValor());
			}
			
			labelTitulo.setText("ALTERAÇÃO DE SERVIÇO");
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);

		}
	}
}
