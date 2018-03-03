/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author gibajunior
 */
public class BancoDados {
    
    public Connection setConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/protocolo?user=postgres&password=ge2018";
        Connection con = DriverManager.getConnection(url);
        return con;
    }
    
    public void difConnection(Connection con) throws SQLException{
        con.close();
    }
    
    public String cadastraProtocolo(String cliente, String contato, String telefone, String email, String filtro1, String filtro2, String filtro3, String filtro4, String situacao, String tratamento, String memorando) throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        Calendario cal = new Calendario();
        String cons = bd.consultaRegistro();
        if(cons==null){
            bd.cadastraRegistro();
        }else{
            bd.atualizaRegistro();
        }
        cons=bd.consultaRegistro();
        int cont = Integer.parseInt(cons);
        String sql = "INSERT INTO geprotocolo.protocolo VALUES ("+cal.getPrefix()+String.format("%03d", cont)+",'"+cliente+"','"+contato+"','"+telefone+"','"+email+"','"+filtro1+"','"+filtro2+"','"+filtro3+"','"+filtro4+"','"+situacao+"','"+tratamento+"','"+memorando+"');";
        System.out.print(sql);
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        difConnection(con);
        return cal.getPrefix()+String.format("%03d", cont);
    }
    
    public void cadastraRegistro() throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        Calendario cal = new Calendario();
        String sql = "INSERT INTO geprotocolo.registro VALUES ("+cal.getPrefix()+",1)";
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        difConnection(con);
    } 
    
    public void atualizaRegistro() throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        Calendario cal = new Calendario();
        String str = bd.consultaRegistro();
        if(str!=null){
            String sql1 = "UPDATE geprotocolo.registro SET nm_registro="+(Integer.parseInt((str))+1)+" WHERE cd_prefixo="+cal.getPrefix()+";";
            Statement st = con.createStatement();
            st.executeUpdate(sql1);
            difConnection(con);
        }else{
            bd.cadastraRegistro();
        }
    } 
    
    public String consultaRegistro() throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        Calendario cal = new Calendario();
        String sql = "SELECT nm_registro FROM geprotocolo.registro WHERE cd_prefixo="+cal.getPrefix()+";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            return rs.getString(1);
        }
        return null;
    } 
       
    public List<String> listaFiltro(String filSelect, String filWhere, String valor) throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        List<String> strList = new ArrayList<String>();
        String query = "SELECT DISTINCT "+filSelect+" FROM geprotocolo.filtros WHERE "+filWhere+" = '"+valor+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        strList.add("----------");
        while (rs.next()){
            strList.add(rs.getString(1));
        }
        difConnection(con);
        return strList;
    }
    
    public List<String> listaFiltro2(String filSelect, String filWhere1, String filWhere2, String valor1, String valor2) throws SQLException{
        BancoDados bd = new BancoDados();
        Connection con = bd.setConnection();
        List<String> strList = new ArrayList<String>();
        String query = "SELECT DISTINCT "+filSelect+" FROM geprotocolo.filtros WHERE "+filWhere1+" = '"+valor1+"' AND "+filWhere2+" = '"+valor2+"';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        strList.add("----------");
        while (rs.next()){
            strList.add(rs.getString(1));
        }
        difConnection(con);
        return strList;
    }
}
