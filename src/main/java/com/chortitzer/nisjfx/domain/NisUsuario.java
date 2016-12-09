/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.nisjfx.domain;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "nis_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NisUsuario.findAll", query = "SELECT n FROM NisUsuario n"),
    @NamedQuery(name = "NisUsuario.findByFid1", query = "SELECT n FROM NisUsuario n WHERE n.fid1 = :fid1"),
    @NamedQuery(name = "NisUsuario.findByFid", query = "SELECT n FROM NisUsuario n WHERE n.fid = :fid"),
    @NamedQuery(name = "NisUsuario.findByUsuario", query = "SELECT n FROM NisUsuario n WHERE n.usuario = :usuario"),
    @NamedQuery(name = "NisUsuario.findByNis", query = "SELECT n FROM NisUsuario n WHERE n.nis = :nis"),
    @NamedQuery(name = "NisUsuario.findByLocalidad", query = "SELECT n FROM NisUsuario n WHERE n.localidad = :localidad"),
    @NamedQuery(name = "NisUsuario.findByDistrito", query = "SELECT n FROM NisUsuario n WHERE n.distrito = :distrito"),
    @NamedQuery(name = "NisUsuario.findByTarifa", query = "SELECT n FROM NisUsuario n WHERE n.tarifa = :tarifa"),
    @NamedQuery(name = "NisUsuario.findByCateg", query = "SELECT n FROM NisUsuario n WHERE n.categ = :categ"),
    @NamedQuery(name = "NisUsuario.findByZonapdNis", query = "SELECT n FROM NisUsuario n WHERE n.zonapdNis = :zonapdNis"),
    @NamedQuery(name = "NisUsuario.findByTipoAcom", query = "SELECT n FROM NisUsuario n WHERE n.tipoAcom = :tipoAcom"),
    @NamedQuery(name = "NisUsuario.findByNroMed", query = "SELECT n FROM NisUsuario n WHERE n.nroMed = :nroMed"),
    @NamedQuery(name = "NisUsuario.findByNroFases", query = "SELECT n FROM NisUsuario n WHERE n.nroFases = :nroFases"),
    @NamedQuery(name = "NisUsuario.findByNumande", query = "SELECT n FROM NisUsuario n WHERE n.numande = :numande"),
    @NamedQuery(name = "NisUsuario.findByObs", query = "SELECT n FROM NisUsuario n WHERE n.obs = :obs"),
    @NamedQuery(name = "NisUsuario.findByFase", query = "SELECT n FROM NisUsuario n WHERE n.fase = :fase"),
    @NamedQuery(name = "NisUsuario.findByXCoord", query = "SELECT n FROM NisUsuario n WHERE n.xCoord = :xCoord"),
    @NamedQuery(name = "NisUsuario.findByYCoord", query = "SELECT n FROM NisUsuario n WHERE n.yCoord = :yCoord"),
    @NamedQuery(name = "NisUsuario.findByNrserie", query = "SELECT n FROM NisUsuario n WHERE n.nrserie = :nrserie"),
    @NamedQuery(name = "NisUsuario.findByCateg1", query = "SELECT n FROM NisUsuario n WHERE n.categ1 = :categ1"),
    @NamedQuery(name = "NisUsuario.findByDistrito1", query = "SELECT n FROM NisUsuario n WHERE n.distrito1 = :distrito1"),
    @NamedQuery(name = "NisUsuario.findByFase1", query = "SELECT n FROM NisUsuario n WHERE n.fase1 = :fase1"),
    @NamedQuery(name = "NisUsuario.findByLocalida1", query = "SELECT n FROM NisUsuario n WHERE n.localida1 = :localida1"),
    @NamedQuery(name = "NisUsuario.findByNis1", query = "SELECT n FROM NisUsuario n WHERE n.nis1 = :nis1"),
    @NamedQuery(name = "NisUsuario.findByNroFase1", query = "SELECT n FROM NisUsuario n WHERE n.nroFase1 = :nroFase1"),
    @NamedQuery(name = "NisUsuario.findByNroMed1", query = "SELECT n FROM NisUsuario n WHERE n.nroMed1 = :nroMed1"),
    @NamedQuery(name = "NisUsuario.findByNrserie1", query = "SELECT n FROM NisUsuario n WHERE n.nrserie1 = :nrserie1"),
    @NamedQuery(name = "NisUsuario.findByNumande1", query = "SELECT n FROM NisUsuario n WHERE n.numande1 = :numande1"),
    @NamedQuery(name = "NisUsuario.findByObs1", query = "SELECT n FROM NisUsuario n WHERE n.obs1 = :obs1"),
    @NamedQuery(name = "NisUsuario.findByTarifa1", query = "SELECT n FROM NisUsuario n WHERE n.tarifa1 = :tarifa1"),
    @NamedQuery(name = "NisUsuario.findByTipoAco1", query = "SELECT n FROM NisUsuario n WHERE n.tipoAco1 = :tipoAco1"),
    @NamedQuery(name = "NisUsuario.findByUsuario1", query = "SELECT n FROM NisUsuario n WHERE n.usuario1 = :usuario1"),
    @NamedQuery(name = "NisUsuario.findByXCoord1", query = "SELECT n FROM NisUsuario n WHERE n.xCoord1 = :xCoord1"),
    @NamedQuery(name = "NisUsuario.findByYCoord1", query = "SELECT n FROM NisUsuario n WHERE n.yCoord1 = :yCoord1"),
    @NamedQuery(name = "NisUsuario.findByZonapdN1", query = "SELECT n FROM NisUsuario n WHERE n.zonapdN1 = :zonapdN1"),
    @NamedQuery(name = "NisUsuario.findByCateg2", query = "SELECT n FROM NisUsuario n WHERE n.categ2 = :categ2"),
    @NamedQuery(name = "NisUsuario.findByDistrito2", query = "SELECT n FROM NisUsuario n WHERE n.distrito2 = :distrito2"),
    @NamedQuery(name = "NisUsuario.findByFase2", query = "SELECT n FROM NisUsuario n WHERE n.fase2 = :fase2"),
    @NamedQuery(name = "NisUsuario.findByLocalidad1", query = "SELECT n FROM NisUsuario n WHERE n.localidad1 = :localidad1"),
    @NamedQuery(name = "NisUsuario.findByNis2", query = "SELECT n FROM NisUsuario n WHERE n.nis2 = :nis2"),
    @NamedQuery(name = "NisUsuario.findByNroFases1", query = "SELECT n FROM NisUsuario n WHERE n.nroFases1 = :nroFases1"),
    @NamedQuery(name = "NisUsuario.findByNroMed2", query = "SELECT n FROM NisUsuario n WHERE n.nroMed2 = :nroMed2"),
    @NamedQuery(name = "NisUsuario.findByNrserie2", query = "SELECT n FROM NisUsuario n WHERE n.nrserie2 = :nrserie2"),
    @NamedQuery(name = "NisUsuario.findByNumande2", query = "SELECT n FROM NisUsuario n WHERE n.numande2 = :numande2"),
    @NamedQuery(name = "NisUsuario.findByObs2", query = "SELECT n FROM NisUsuario n WHERE n.obs2 = :obs2"),
    @NamedQuery(name = "NisUsuario.findByTarifa2", query = "SELECT n FROM NisUsuario n WHERE n.tarifa2 = :tarifa2"),
    @NamedQuery(name = "NisUsuario.findByTipoAcom1", query = "SELECT n FROM NisUsuario n WHERE n.tipoAcom1 = :tipoAcom1"),
    @NamedQuery(name = "NisUsuario.findByUsuario2", query = "SELECT n FROM NisUsuario n WHERE n.usuario2 = :usuario2"),
    @NamedQuery(name = "NisUsuario.findByXCoord2", query = "SELECT n FROM NisUsuario n WHERE n.xCoord2 = :xCoord2"),
    @NamedQuery(name = "NisUsuario.findByYCoord2", query = "SELECT n FROM NisUsuario n WHERE n.yCoord2 = :yCoord2"),
    @NamedQuery(name = "NisUsuario.findByZonapdNis1", query = "SELECT n FROM NisUsuario n WHERE n.zonapdNis1 = :zonapdNis1")})
public class NisUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid_1")
    private Integer fid1;
    @Lob
    @Column(name = "the_geom")
    private Object theGeom;
    @Column(name = "fid")
    private BigInteger fid;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "NIS")
    private String nis;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Column(name = "DISTRITO")
    private String distrito;
    @Column(name = "TARIFA")
    private String tarifa;
    @Column(name = "CATEG")
    private String categ;
    @Column(name = "ZONAPD_NIS")
    private String zonapdNis;
    @Column(name = "TIPO_ACOM")
    private String tipoAcom;
    @Column(name = "NRO_MED")
    private String nroMed;
    @Column(name = "NRO_FASES")
    private String nroFases;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NUMANDE")
    private Double numande;
    @Column(name = "OBS")
    private String obs;
    @Column(name = "FASE")
    private Double fase;
    @Column(name = "X_COORD")
    private Double xCoord;
    @Column(name = "Y_COORD")
    private Double yCoord;
    @Column(name = "NRSERIE")
    private Double nrserie;
    @Column(name = "categ_1")
    private String categ1;
    @Column(name = "distrito_1")
    private String distrito1;
    @Column(name = "fase_1")
    private Double fase1;
    @Column(name = "localida_1")
    private String localida1;
    @Column(name = "nis_1")
    private String nis1;
    @Column(name = "nro_fase_1")
    private String nroFase1;
    @Column(name = "nro_med_1")
    private String nroMed1;
    @Column(name = "nrserie_1")
    private Double nrserie1;
    @Column(name = "numande_1")
    private Double numande1;
    @Column(name = "obs_1")
    private String obs1;
    @Column(name = "tarifa_1")
    private String tarifa1;
    @Column(name = "tipo_aco_1")
    private String tipoAco1;
    @Column(name = "usuario_1")
    private String usuario1;
    @Column(name = "x_coord_1")
    private Double xCoord1;
    @Column(name = "y_coord_1")
    private Double yCoord1;
    @Column(name = "zonapd_n_1")
    private String zonapdN1;
    @Column(name = "categ")
    private String categ2;
    @Column(name = "distrito")
    private String distrito2;
    @Column(name = "fase")
    private Double fase2;
    @Column(name = "localidad")
    private String localidad1;
    @Column(name = "nis")
    private String nis2;
    @Column(name = "nro_fases")
    private String nroFases1;
    @Column(name = "nro_med")
    private String nroMed2;
    @Column(name = "nrserie")
    private Double nrserie2;
    @Column(name = "numande")
    private Double numande2;
    @Column(name = "obs")
    private String obs2;
    @Column(name = "tarifa")
    private String tarifa2;
    @Column(name = "tipo_acom")
    private String tipoAcom1;
    @Column(name = "usuario")
    private String usuario2;
    @Column(name = "x_coord")
    private Double xCoord2;
    @Column(name = "y_coord")
    private Double yCoord2;
    @Column(name = "zonapd_nis")
    private String zonapdNis1;

    public NisUsuario() {
    }

    public NisUsuario(Integer fid1) {
        this.fid1 = fid1;
    }

    public Integer getFid1() {
        return fid1;
    }

    public void setFid1(Integer fid1) {
        this.fid1 = fid1;
    }

    public Object getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(Object theGeom) {
        this.theGeom = theGeom;
    }

    public BigInteger getFid() {
        return fid;
    }

    public void setFid(BigInteger fid) {
        this.fid = fid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getZonapdNis() {
        return zonapdNis;
    }

    public void setZonapdNis(String zonapdNis) {
        this.zonapdNis = zonapdNis;
    }

    public String getTipoAcom() {
        return tipoAcom;
    }

    public void setTipoAcom(String tipoAcom) {
        this.tipoAcom = tipoAcom;
    }

    public String getNroMed() {
        return nroMed;
    }

    public void setNroMed(String nroMed) {
        this.nroMed = nroMed;
    }

    public String getNroFases() {
        return nroFases;
    }

    public void setNroFases(String nroFases) {
        this.nroFases = nroFases;
    }

    public Double getNumande() {
        return numande;
    }

    public void setNumande(Double numande) {
        this.numande = numande;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getFase() {
        return fase;
    }

    public void setFase(Double fase) {
        this.fase = fase;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public void setXCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }

    public void setYCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    public Double getNrserie() {
        return nrserie;
    }

    public void setNrserie(Double nrserie) {
        this.nrserie = nrserie;
    }

    public String getCateg1() {
        return categ1;
    }

    public void setCateg1(String categ1) {
        this.categ1 = categ1;
    }

    public String getDistrito1() {
        return distrito1;
    }

    public void setDistrito1(String distrito1) {
        this.distrito1 = distrito1;
    }

    public Double getFase1() {
        return fase1;
    }

    public void setFase1(Double fase1) {
        this.fase1 = fase1;
    }

    public String getLocalida1() {
        return localida1;
    }

    public void setLocalida1(String localida1) {
        this.localida1 = localida1;
    }

    public String getNis1() {
        return nis1;
    }

    public void setNis1(String nis1) {
        this.nis1 = nis1;
    }

    public String getNroFase1() {
        return nroFase1;
    }

    public void setNroFase1(String nroFase1) {
        this.nroFase1 = nroFase1;
    }

    public String getNroMed1() {
        return nroMed1;
    }

    public void setNroMed1(String nroMed1) {
        this.nroMed1 = nroMed1;
    }

    public Double getNrserie1() {
        return nrserie1;
    }

    public void setNrserie1(Double nrserie1) {
        this.nrserie1 = nrserie1;
    }

    public Double getNumande1() {
        return numande1;
    }

    public void setNumande1(Double numande1) {
        this.numande1 = numande1;
    }

    public String getObs1() {
        return obs1;
    }

    public void setObs1(String obs1) {
        this.obs1 = obs1;
    }

    public String getTarifa1() {
        return tarifa1;
    }

    public void setTarifa1(String tarifa1) {
        this.tarifa1 = tarifa1;
    }

    public String getTipoAco1() {
        return tipoAco1;
    }

    public void setTipoAco1(String tipoAco1) {
        this.tipoAco1 = tipoAco1;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public Double getXCoord1() {
        return xCoord1;
    }

    public void setXCoord1(Double xCoord1) {
        this.xCoord1 = xCoord1;
    }

    public Double getYCoord1() {
        return yCoord1;
    }

    public void setYCoord1(Double yCoord1) {
        this.yCoord1 = yCoord1;
    }

    public String getZonapdN1() {
        return zonapdN1;
    }

    public void setZonapdN1(String zonapdN1) {
        this.zonapdN1 = zonapdN1;
    }

    public String getCateg2() {
        return categ2;
    }

    public void setCateg2(String categ2) {
        this.categ2 = categ2;
    }

    public String getDistrito2() {
        return distrito2;
    }

    public void setDistrito2(String distrito2) {
        this.distrito2 = distrito2;
    }

    public Double getFase2() {
        return fase2;
    }

    public void setFase2(Double fase2) {
        this.fase2 = fase2;
    }

    public String getLocalidad1() {
        return localidad1;
    }

    public void setLocalidad1(String localidad1) {
        this.localidad1 = localidad1;
    }

    public String getNis2() {
        return nis2;
    }

    public void setNis2(String nis2) {
        this.nis2 = nis2;
    }

    public String getNroFases1() {
        return nroFases1;
    }

    public void setNroFases1(String nroFases1) {
        this.nroFases1 = nroFases1;
    }

    public String getNroMed2() {
        return nroMed2;
    }

    public void setNroMed2(String nroMed2) {
        this.nroMed2 = nroMed2;
    }

    public Double getNrserie2() {
        return nrserie2;
    }

    public void setNrserie2(Double nrserie2) {
        this.nrserie2 = nrserie2;
    }

    public Double getNumande2() {
        return numande2;
    }

    public void setNumande2(Double numande2) {
        this.numande2 = numande2;
    }

    public String getObs2() {
        return obs2;
    }

    public void setObs2(String obs2) {
        this.obs2 = obs2;
    }

    public String getTarifa2() {
        return tarifa2;
    }

    public void setTarifa2(String tarifa2) {
        this.tarifa2 = tarifa2;
    }

    public String getTipoAcom1() {
        return tipoAcom1;
    }

    public void setTipoAcom1(String tipoAcom1) {
        this.tipoAcom1 = tipoAcom1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public Double getXCoord2() {
        return xCoord2;
    }

    public void setXCoord2(Double xCoord2) {
        this.xCoord2 = xCoord2;
    }

    public Double getYCoord2() {
        return yCoord2;
    }

    public void setYCoord2(Double yCoord2) {
        this.yCoord2 = yCoord2;
    }

    public String getZonapdNis1() {
        return zonapdNis1;
    }

    public void setZonapdNis1(String zonapdNis1) {
        this.zonapdNis1 = zonapdNis1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid1 != null ? fid1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NisUsuario)) {
            return false;
        }
        NisUsuario other = (NisUsuario) object;
        if ((this.fid1 == null && other.fid1 != null) || (this.fid1 != null && !this.fid1.equals(other.fid1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.nis.NisUsuario[ fid1=" + fid1 + " ]";
    }

}
