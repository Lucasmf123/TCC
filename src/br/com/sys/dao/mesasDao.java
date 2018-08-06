package br.com.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Generated;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.sys.bean.mesasBean;
import br.com.sys.connection.ConnectionFactory;

public class mesasDao {

	private Connection conexao;

	public mesasDao() {

		this.conexao = new ConnectionFactory().obterConexao();
	}

	// Cadastro
	public void cadastrarDados(mesasBean eb) {

		// Comando SQL
		String sql = "INSERT INTO mesas (idPedido) VALUES (?)";

		try {

			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, eb.getIdPedido());
			pstmt.execute();

			pstmt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Falha ao inserir dados");

		}

	}

	public DefaultTableModel listarDados() {

		// DefaultTableModel
		DefaultTableModel tabela = new DefaultTableModel();

		tabela.addColumn("Id Mesas");
		tabela.addColumn("Id Pedido");

		// Comando SQL
		String sql = "SELECT * FROM mesas";

		try {

			Statement stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				tabela.addRow(
						new Object[] { 
								
								rs.getInt("idMesa"),
								rs.getInt("idPedido")
								
						});

			}

			stmt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Falha ao selecionar os dados."+e.getMessage());

		}

		return tabela;

	}

	// Alterar dados
	public void alterar(mesasBean mb) {

		String sql = "UPDATE mesas SET idPedido = ? WHERE idMesa = ?";

		try {

			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, mb.getIdPedido());
			pstmt.setInt(2, mb.getIdMesa());
			pstmt.execute();

			pstmt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");

		}

	}

	// Retornar Dados
	public mesasBean retornarDados(int codigo) {

		String sql = "SELECT * FROM mesas WHERE idMesa = ?";

		mesasBean mb = new mesasBean();

		try {

			Statement stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			mb.setIdMesa(rs.getInt("idMesa"));
			mb.setIdPedido(rs.getInt("idPedido"));

			stmt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Falha ao selecionar os dados.");

		}

		return mb;

	}

	// Deletar dados
	public void deletarCursos(int idCodigo) {

		String sql = "DELETE FROM `mesas` WHERE idMesa = ?";

		try {

			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			mesasBean mb = new mesasBean();
			pstmt.setInt(1, idCodigo);
			pstmt.execute();

			pstmt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");

		}

	}

}