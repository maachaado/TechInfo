package br.com.techinfo2.core.entity;

import java.util.Objects;

public class ClienteEntity {
	
		private Long codigo;
		private String nome;
		private String cpf;
		private String endereco;
		private String telefone;
		
		
		public Long getCodigo() {
			return codigo;
		}
		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(cpf);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClienteEntity other = (ClienteEntity) obj;
			return Objects.equals(cpf, other.cpf);
}
		}

