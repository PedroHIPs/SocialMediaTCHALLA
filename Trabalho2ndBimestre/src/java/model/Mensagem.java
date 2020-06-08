/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Usuario;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "mensagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT m FROM Mensagem m")
    , @NamedQuery(name = "Mensagem.findByCodigo", query = "SELECT m FROM Mensagem m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Mensagem.findByDatahora", query = "SELECT m FROM Mensagem m WHERE m.datahora = :datahora")
    , @NamedQuery(name = "Mensagem.findByDados", query = "SELECT m FROM Mensagem m WHERE m.dados = :dados")
    , @NamedQuery(name = "Mensagem.findByTipo", query = "SELECT m FROM Mensagem m WHERE m.tipo = :tipo")})
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "datahora", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    @Size(max = 1024)
    @Column(name = "dados")
    private String dados;
    @Size(max = 20)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "codigoremetente", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codigoremetente;

    public Mensagem() {
    }

    public Mensagem(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getCodigoremetente() {
        return codigoremetente;
    }

    public void setCodigoremetente(Usuario codigoremetente) {
        this.codigoremetente = codigoremetente;
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
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Mensagem[ codigo=" + codigo + " ]";
    }
    
}
