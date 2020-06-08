/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.BancoJPA;
import model.Usuario;

public class DAOJPA {
    public void gravar(Object obj) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            bb.sessao.getTransaction().begin();
            bb.sessao.persist(obj);
            bb.sessao.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar: " + ex.getMessage());

        }
    }

    public void alterar(Object obj) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            bb.sessao.getTransaction().begin();
            bb.sessao.merge(obj);
            bb.sessao.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Erro ao alterar: " + ex.getMessage());

        }
    }

    public void remover(Class tipo,int id) throws Exception {
        BancoJPA bb;
        Object obj;
        try {
            bb = new BancoJPA();
            bb.sessao.getTransaction().begin();
            obj=bb.sessao.find(tipo, id);
            if(obj!=null)
                    bb.sessao.remove(obj);
            bb.sessao.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Erro ao remover: " + ex.getMessage());

        }
    }

    public Object getById(Class tipo,final int id) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return bb.sessao.find(tipo, id);
        } catch (Exception ex) {
            throw new Exception("Erro ao getById: " + ex.getMessage());

        }
    }
    
    public Object getAmIds(Class tipo, int idU, int idA) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
             return  bb.sessao.createQuery("SELECT am From Amigo am where am.codusuario.codigo = :codU and am.codamigo.codigo = :codA")
                    .setParameter("codU", idU)
                    .setParameter("codA", idA).getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Erro ao getAmizadesByIds: " + ex.getMessage());

        }
    }

    public List<Usuario> listarTodosUsuarios() throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return bb.sessao.createNamedQuery("Usuario.findAll" , Usuario.class).getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao listarTodosUsuarios: " + ex.getMessage());
        }
    }
    
    public List<Object> mensagens0(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT  m FROM Mensagem m  where m.codigoremetente in (select u from Usuario u where u.codigo=:cod) or m.codigoremetente in "
          + " (select a.codusuario from Amigo a, Usuario u where a.codamigo= u.codigo and u.codigo=:cod) order by m.datahora desc")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Mensagens: " + ex.getMessage());
        }
    }
    //Retorna a lista de mensagem que o usuario com codigo passado pode receber.
     public List<Object> mensagens(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return bb.sessao.createQuery("SELECT m FROM Mensagem m inner join m.codigoremetente u where  u.codigo=:cod or m.codigoremetente in "
          + " (select a.codusuario from Amigo a inner join a.codamigo u where u.codigo=:cod) order by m.datahora desc")
          .setParameter("cod", codigo)
          .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Mensagens: " + ex.getMessage());
        }
    }
    
    public Object login(String n, String s) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return   bb.sessao.createQuery("SELECT u FROM Usuario u where u.nome=:nome and u.senha=:senha")
                    .setParameter("nome",n)
                    .setParameter("senha", s).getSingleResult();
        } catch (Exception ex) {
            return(null);
        }
    }
    
    
    // retorna a lista de usuarios que receberam a declaracao de amizade do codigo recebido
    public List<Usuario> listarAmigos(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT a.codamigo FROM Amigo a inner join a.codusuario u where u.codigo=:cod")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Amigos: " + ex.getMessage());
        }
    }
    
    public List<Usuario> listarNotAmigos(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();//("SELECT u from Usuario u where u not in (select a.codamigo from Amigo a where a.codusuario = :cod) and u.codigo <> :cod")
            return  bb.sessao.createQuery("SELECT u from Usuario u where u not in ( SELECT a.codamigo FROM Amigo a inner join a.codusuario u where u.codigo=:cod) and u.codigo <> :cod").setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Usuarios: " + ex.getMessage());
        }
    }
    
    // retorna a lista de usuarios que receberam a declaracao de amizade do codigo recebido
    public List<Usuario> listarUsuariosDeclaramAmizade(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT a.codusuario FROM Amigo a inner join a.codamigo u where u.codigo=:cod")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar quem declarou amizade: " + ex.getMessage());
        }
    }
}
