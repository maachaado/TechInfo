package br.com.techinfo2.view.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.core.service.ExternoService;
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
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaCadastroExterno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldVisita;
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
					TelaCadastroExterno frame = new TelaCadastroExterno();
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
	public TelaCadastroExterno() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("TECHINFO - EXTERNO");
		setBounds(100, 100, 440, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("CADASTRO EXTERNO ");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.setIcon(new ImageIcon(TelaCadastroExterno.class.getResource("/resourse/salvar-silhueta-de-icone.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ExternoEntity ext = new ExternoEntity();
				ext.setVisitas(fieldVisita.getText());
				
				
				String msg = null;
						
				try {
				if(fieldCodigo.getText().equals("")) {				
				msg = new ExternoService().salvarExterno(ext);
				}else {
					ext.setCodigo(Long.parseLong(fieldCodigo.getText()));
					msg = new ExternoService().alterarExterno(ext);
				}
				
				
				limparCampo();
				JOptionPane.showMessageDialog(btnSalvar, msg);
				TelaListaExterno tle = new TelaListaExterno();
				tle.setVisible(true);
				dispose();
				
				} catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro() , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		fieldVisita = new JTextField();
		fieldVisita.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldVisita.setBackground(SystemColor.inactiveCaptionBorder);
		fieldVisita.setHorizontalAlignment(SwingConstants.LEFT);
		fieldVisita.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" AGENDAMENTOS, VISITAS E COMPROMISSOS ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_2 = new JLabel("CÓDIGO:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setColumns(10);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setIcon(new ImageIcon(TelaCadastroExterno.class.getResource("/resourse/volte.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaExterno tle = new 	TelaListaExterno();
				tle.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroExterno.class.getResource("/resourse/link-externo 2.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(93)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(125)
									.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(52))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldVisita, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addComponent(btnVoltar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelTitulo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fieldVisita, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvar))
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void limparCampo() {
		fieldCodigo.setText("");
		fieldVisita.setText("");
	}
	
	public void buscarPorId(Long codigoExterno) {
		try {
			ExternoEntity externoEncontrado = new ExternoService().buscarPorId(codigoExterno);			
			if(externoEncontrado == null) {
			JOptionPane.showMessageDialog(null,"EXTERNO NÃO LOCALIZADO", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText("" + externoEncontrado.getCodigo());
				fieldVisita.setText(externoEncontrado.getVisitas());
			}			
			
			labelTitulo.setText("ALTERAÇÃO DE EXTERNO");
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}			
}
