/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.nisjfx.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_ind_usi_registro_lecturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndUsiRegistroLecturas.findAll", query = "SELECT t FROM TblIndUsiRegistroLecturas t"),
    @NamedQuery(name = "TblIndUsiRegistroLecturas.findByFechahora", query = "SELECT t FROM TblIndUsiRegistroLecturas t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblIndUsiRegistroLecturas.findByNroserie", query = "SELECT t FROM TblIndUsiRegistroLecturas t WHERE t.nroserie = :nroserie"),
    @NamedQuery(name = "TblIndUsiRegistroLecturas.findByKwh", query = "SELECT t FROM TblIndUsiRegistroLecturas t WHERE t.kwh = :kwh"),
    @NamedQuery(name = "TblIndUsiRegistroLecturas.findByKvar", query = "SELECT t FROM TblIndUsiRegistroLecturas t WHERE t.kvar = :kvar")})
public class TblIndUsiRegistroLecturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @Column(name = "nroserie")
    private int nroserie;
    @Basic(optional = false)
    @Column(name = "kwh")
    private int kwh;
    @Basic(optional = false)
    @Column(name = "kvar")
    private int kvar;

    public TblIndUsiRegistroLecturas() {
    }

    public TblIndUsiRegistroLecturas(Date fechahora) {
        this.fechahora = fechahora;
    }

    public TblIndUsiRegistroLecturas(Date fechahora, int nroserie, int kwh, int kvar) {
        this.fechahora = fechahora;
        this.nroserie = nroserie;
        this.kwh = kwh;
        this.kvar = kvar;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public int getNroserie() {
        return nroserie;
    }

    public void setNroserie(int nroserie) {
        this.nroserie = nroserie;
    }

    public int getKwh() {
        return kwh;
    }

    public void setKwh(int kwh) {
        this.kwh = kwh;
    }

    public int getKvar() {
        return kvar;
    }

    public void setKvar(int kvar) {
        this.kvar = kvar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechahora != null ? fechahora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndUsiRegistroLecturas)) {
            return false;
        }
        TblIndUsiRegistroLecturas other = (TblIndUsiRegistroLecturas) object;
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.nis.TblIndUsiRegistroLecturas[ fechahora=" + fechahora + " ]";
    }

}
