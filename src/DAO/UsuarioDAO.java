
package DAO;
//
import Factory.ConnectorFactory;
//
import java.sql.*;
//
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
//
public class UsuarioDAO {
   
    private Connection connection ;
    
    public UsuarioDAO(){
         this.connection = new ConnectorFactory().getconeConnetor();
    }
    //Coloca os vaalores no bando de dados
    public void AdicionaUsuario(Usuario usuario){
        
      String Sql = "INSERT INTO usuario(nome , cpf ,email , telefone) VALUES (?,?,?,?)";
           
        try {
            PreparedStatement  stmt = connection.prepareStatement(Sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getCpf());
            
            stmt.execute();
            stmt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeUsuario(){
        
      String Sql = "DELETE FROM usuario";
           
        try {
            PreparedStatement  stmt = connection.prepareStatement(Sql);
            
           
            stmt.execute();
            stmt.close();
             JOptionPane.showMessageDialog(null, "limpou");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
        public List<Usuario> ListaCombo(){
    
        String sql = "SELECT * FROM usuario ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                  Usuario u = new Usuario();
                  u.setId(rs.getInt(1));                  
                  u.setNome(rs.getString(2));

                  lista.add(u);
                }
                return lista;
            }else{
                return null;
            }
            
        }catch(Exception ex){
           return null;
        }


        }}
