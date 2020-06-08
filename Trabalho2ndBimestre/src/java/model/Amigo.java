/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Usuario;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "amigo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amigo.findAll", query = "SELECT a FROM Amigo a")
    , @NamedQuery(name = "Amigo.findByCodigo", query = "SELECT a FROM Amigo a WHERE a.codigo = :codigo")})
public class Amigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "codamigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codamigo;
    @JoinColumn(name = "codusuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codusuario;

    public Amigo() {
    }

    public Amigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Usuario getCodamigo() {
        return codamigo;
    }

    public void setCodamigo(Usuario codamigo) {
        this.codamigo = codamigo;
    }

    public Usuario getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Usuario codusuario) {
        this.codusuario = codusuario;
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
        if (!(object instanceof Amigo)) {
            return false;
        }
        Amigo other = (Amigo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Amigo[ codigo=" + codigo + " ]";
    }
    
}
