/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Mensagem;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome")
    , @NamedQuery(name = "Usuario.findByDatanasc", query = "SELECT u FROM Usuario u WHERE u.datanasc = :datanasc")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 20)
    @Column(name = "senha")
    private String senha;
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;
    @Column(name = "datanasc")
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoremetente", fetch = FetchType.LAZY)
    private List<Mensagem> mensagemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codamigo", fetch = FetchType.LAZY)
    private List<Amigo> amigoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codusuario", fetch = FetchType.LAZY)
    private List<Amigo> amigoList1;

    public Usuario() {
    }

    public Usuario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    @XmlTransient
    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    @XmlTransient
    public List<Amigo> getAmigoList() {
        return amigoList;
    }

    public void setAmigoList(List<Amigo> amigoList) {
        this.amigoList = amigoList;
    }

    @XmlTransient
    public List<Amigo> getAmigoList1() {
        return amigoList1;
    }

    public void setAmigoList1(List<Amigo> amigoList1) {
        this.amigoList1 = amigoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Usuario[ codigo=" + codigo + " ]";
    }
    
}
