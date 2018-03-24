package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.FinanceiroAluno;

public class FinanceiroAlunoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public FinanceiroAluno retornarFinanceiroAlunoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,data,valor,flag,qtd,tipo,servico_id,aluno_id,descricao from financeiro where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		FinanceiroAluno financeiroAluno = null;

		while (rs.next()) {

			financeiroAluno = new FinanceiroAluno();

			financeiroAluno.setId(rs.getLong("id"));
			financeiroAluno.setData(rs.getDate("data"));
			financeiroAluno.setValor(rs.getDouble("valor"));
			financeiroAluno.setFlag(rs.getInt("flag"));
			financeiroAluno.setQtd(rs.getInt("qtd"));
			financeiroAluno.setTipo(rs.getInt("tipo"));
			financeiroAluno.setDescricao(rs.getString("descricao"));
		    
		
		}

		stmt.close();
		con.close();

		return financeiroAluno;

	}

	public List<FinanceiroAluno> listarTodos() throws SQLException {

		List<FinanceiroAluno> lista = new ArrayList<FinanceiroAluno>();

		String sql = "select id,data,valor,flag,qtd,tipo,CASE WHEN DATEDIFF(data, curdate()) = 5 THEN 'FALTAM 5 DIAS' " 
	            + "WHEN DATEDIFF(data, curdate())  = 4 THEN 'FALTAM 4 DIAS' "
                + "WHEN DATEDIFF(data, curdate())  = 3 THEN 'FALTAM 3 DIAS' "
    			+ "WHEN DATEDIFF(data, curdate())  = 2 THEN 'FALTAM 2 DIAS' "
                + "WHEN DATEDIFF(data, curdate())  = 1 THEN 'FALTA 1 DIA' "
    			+ "WHEN DATEDIFF(data, curdate())  = 0 THEN 'DIA DO PAGAMENTO !' " 
                + "WHEN DATEDIFF(data, curdate())  <= 0 THEN 'EM ATRASO !' ELSE 'AGUARDANDO PAGAMENTO ! ' END AS dia FROM financeiro order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		FinanceiroAluno financeiroAluno = null;

		while (rs.next()) {

			financeiroAluno = new FinanceiroAluno();

			financeiroAluno.setId(rs.getLong("id"));
			financeiroAluno.setData(rs.getDate("data"));
			financeiroAluno.setValor(rs.getDouble("valor"));
			financeiroAluno.setFlag(rs.getInt("flag"));
			financeiroAluno.setQtd(rs.getInt("qtd"));
			financeiroAluno.setTipo(rs.getInt("tipo"));
			
			lista.add(financeiroAluno);

			

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public double getTotal() throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT sum(valor) as total FROM financeiro";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()){
			if(rs.getDouble("total") != 0){
			total = rs.getDouble("total");
			}else{
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	public double getTotalPago() throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "select sum(valor) as total from financeiro where flag = 1 ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()){
			if(rs.getDouble("total") != 0){
			total = rs.getDouble("total");
			}else{
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	public double getTotalAberto() throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "select sum(valor) as total from financeiro where flag = 2 ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()){
			if(rs.getDouble("total") != 0){
			total = rs.getDouble("total");
			}else{
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}




	public void incluir(FinanceiroAluno financeiroAluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(financeiroAluno.getData());

		String sql = "INSERT INTO financeiro (data,valor,flag,qtd,tipo,aluno_id,servico_id,categoria_id,descricao,caixa_id,pagamento_id,usuario_id,valorExtenso) " 
		           + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setDouble(2, financeiroAluno.getValor());
		stmt.setInt(3, financeiroAluno.getFlag());
		stmt.setInt(4, financeiroAluno.getQtd());
		stmt.setInt(5, financeiroAluno.getTipo());
        stmt.setLong(6, financeiroAluno.getIdAluno());
        stmt.setLong(7, financeiroAluno.getIdServico());
        stmt.setLong(8, financeiroAluno.getIdCategoria());
        stmt.setString(9, financeiroAluno.getDescricao());
        stmt.setLong(10, financeiroAluno.getIdCaixa());
        stmt.setLong(11, financeiroAluno.getIdFormaPagamento());
        stmt.setLong(12, financeiroAluno.getUsuario().getId());
        stmt.setString(13, financeiroAluno.getValorExtenso());
		
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(FinanceiroAluno financeiroAluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(financeiroAluno.getData());

		String sql = "update financeiro set " 
		           + "data=?,valor=?,flag=?,qtd=?,tipo=?,aluno_id=?,servico_id=?,categoria_id=?,descricao=?,caixa_id=?,pagamento_id=?,valorExtenso=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setDouble(2, financeiroAluno.getValor());
		stmt.setInt(3, financeiroAluno.getFlag());
		stmt.setInt(4, financeiroAluno.getQtd());
		stmt.setInt(5, financeiroAluno.getTipo());
        stmt.setLong(6, financeiroAluno.getIdAluno());
        stmt.setLong(7, financeiroAluno.getIdServico());
        stmt.setLong(8, financeiroAluno.getIdCategoria());
        stmt.setString(9, financeiroAluno.getDescricao());
        stmt.setLong(10, financeiroAluno.getIdCaixa());
        stmt.setLong(11, financeiroAluno.getIdFormaPagamento());
        stmt.setString(12, financeiroAluno.getValorExtenso());
		stmt.setLong(13, financeiroAluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(FinanceiroAluno financeiroAluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from financeiro where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, financeiroAluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
