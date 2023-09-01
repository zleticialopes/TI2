package com.ti2cc;

import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";
        String password = "senha";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null); // Corrigido para verificar se a conexão não é nula
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirAnimal(Animais animal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO canil (codigo, nome, raça, sexo, idade) "
                    + "VALUES (" + animal.getCodigo() + ", '" + animal.getNome() + "', '"
                    + animal.getRaca() + "', '" + animal.getSexo() + "', " + animal.getIdade() + ");");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarAnimal(Animais animal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE canil SET nome = '" + animal.getNome() + "', raça = '"
                    + animal.getRaca() + "', sexo = '" + animal.getSexo() + "'"
                    + " WHERE codigo = " + animal.getCodigo();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirAnimal(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM canil WHERE codigo = " + codigo); // Corrigido para "canil" em vez de "usuario"
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Animais[] getAnimais() {
        Animais[] animais = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM canil"); // Corrigido para "canil" em vez de "usuario"
            if (rs.next()) {
                rs.last();
                animais = new Animais[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    animais[i] = new Animais(rs.getInt("codigo"), rs.getString("nome"),
                            rs.getString("raça"), rs.getString("sexo").charAt(0), rs.getInt("idade"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }

    public Animais[] getAnimaisMasculinos() {
        Animais[] animais = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM canil WHERE sexo = 'M'"); // Corrigido para "canil" em vez de "usuario"
            if (rs.next()) {
                rs.last();
                animais = new Animais[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    animais[i] = new Animais(rs.getInt("codigo"), rs.getString("nome"),
                            rs.getString("raça"), rs.getString("sexo").charAt(0), rs.getInt("idade"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }
}