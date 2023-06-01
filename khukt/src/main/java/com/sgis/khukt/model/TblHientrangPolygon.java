/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_hientrang_polygon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHientrangPolygon.findAll", query = "SELECT t FROM TblHientrangPolygon t"),
    @NamedQuery(name = "TblHientrangPolygon.findByGid", query = "SELECT t FROM TblHientrangPolygon t WHERE t.gid = :gid"),
    @NamedQuery(name = "TblHientrangPolygon.findByTangcaoTb", query = "SELECT t FROM TblHientrangPolygon t WHERE t.tangcaoTb = :tangcaoTb"),
    @NamedQuery(name = "TblHientrangPolygon.findByHesoSdd", query = "SELECT t FROM TblHientrangPolygon t WHERE t.hesoSdd = :hesoSdd"),
    @NamedQuery(name = "TblHientrangPolygon.findByMatdoXd", query = "SELECT t FROM TblHientrangPolygon t WHERE t.matdoXd = :matdoXd"),
    @NamedQuery(name = "TblHientrangPolygon.findByMaloaidat", query = "SELECT t FROM TblHientrangPolygon t WHERE t.maloaidat = :maloaidat"),
    @NamedQuery(name = "TblHientrangPolygon.findByKhLodat", query = "SELECT t FROM TblHientrangPolygon t WHERE t.khLodat = :khLodat"),
    @NamedQuery(name = "TblHientrangPolygon.findByDtichKhu", query = "SELECT t FROM TblHientrangPolygon t WHERE t.dtichKhu = :dtichKhu"),
    @NamedQuery(name = "TblHientrangPolygon.findByDientichlo", query = "SELECT t FROM TblHientrangPolygon t WHERE t.dientichlo = :dientichlo"),
    @NamedQuery(name = "TblHientrangPolygon.findByMalo", query = "SELECT t FROM TblHientrangPolygon t WHERE t.malo = :malo"),
    @NamedQuery(name = "TblHientrangPolygon.findById", query = "SELECT t FROM TblHientrangPolygon t WHERE t.id = :id"),
    @NamedQuery(name = "TblHientrangPolygon.findByTen", query = "SELECT t FROM TblHientrangPolygon t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblHientrangPolygon.findByTendnghiep", query = "SELECT t FROM TblHientrangPolygon t WHERE t.tendnghiep = :tendnghiep"),
    @NamedQuery(name = "TblHientrangPolygon.findByKyhieudn", query = "SELECT t FROM TblHientrangPolygon t WHERE t.kyhieudn = :kyhieudn"),
    @NamedQuery(name = "TblHientrangPolygon.findByHighlight", query = "SELECT t FROM TblHientrangPolygon t WHERE t.highlight = :highlight"),
    @NamedQuery(name = "TblHientrangPolygon.findByIdLoaiquyhoach", query = "SELECT t FROM TblHientrangPolygon t WHERE t.idLoaiquyhoach = :idLoaiquyhoach")})
public class TblHientrangPolygon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Column(name = "tangcao_tb")
    private BigInteger tangcaoTb;
    @Column(name = "heso_sdd")
    private BigInteger hesoSdd;
    @Size(max = 20)
    @Column(name = "matdo_xd")
    private String matdoXd;
    @Size(max = 20)
    @Column(name = "maloaidat")
    private String maloaidat;
    @Size(max = 20)
    @Column(name = "kh_lodat")
    private String khLodat;
    @Column(name = "dtich_khu")
    private BigInteger dtichKhu;
    @Column(name = "dientichlo")
    private BigInteger dientichlo;
    @Size(max = 20)
    @Column(name = "malo")
    private String malo;
    @Column(name = "id")
    private Short id;
    @Size(max = 150)
    @Column(name = "ten")
    private String ten;
    @Size(max = 250)
    @Column(name = "tendnghiep")
    private String tendnghiep;
    @Column(name = "kyhieudn")
    private Short kyhieudn;
    @Column(name = "highlight")
    private Integer highlight;
    @Column(name = "id_loaiquyhoach")
    private Integer idLoaiquyhoach;
    @Column(name = "long")
    private Float longitude;//kinh do
    @Column(name = "lat")
    private Float latitude;//vi do

    public TblHientrangPolygon() {
    }

    public TblHientrangPolygon(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public BigInteger getTangcaoTb() {
        return tangcaoTb;
    }

    public void setTangcaoTb(BigInteger tangcaoTb) {
        this.tangcaoTb = tangcaoTb;
    }

    public BigInteger getHesoSdd() {
        return hesoSdd;
    }

    public void setHesoSdd(BigInteger hesoSdd) {
        this.hesoSdd = hesoSdd;
    }

    public String getMatdoXd() {
        return matdoXd;
    }

    public void setMatdoXd(String matdoXd) {
        this.matdoXd = matdoXd;
    }

    public String getMaloaidat() {
        return maloaidat;
    }

    public void setMaloaidat(String maloaidat) {
        this.maloaidat = maloaidat;
    }

    public String getKhLodat() {
        return khLodat;
    }

    public void setKhLodat(String khLodat) {
        this.khLodat = khLodat;
    }

    public BigInteger getDtichKhu() {
        return dtichKhu;
    }

    public void setDtichKhu(BigInteger dtichKhu) {
        this.dtichKhu = dtichKhu;
    }

    public BigInteger getDientichlo() {
        return dientichlo;
    }

    public void setDientichlo(BigInteger dientichlo) {
        this.dientichlo = dientichlo;
    }

    public String getMalo() {
        return malo;
    }

    public void setMalo(String malo) {
        this.malo = malo;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTendnghiep() {
        return tendnghiep;
    }

    public void setTendnghiep(String tendnghiep) {
        this.tendnghiep = tendnghiep;
    }

    public Short getKyhieudn() {
        return kyhieudn;
    }

    public void setKyhieudn(Short kyhieudn) {
        this.kyhieudn = kyhieudn;
    }

    public Integer getHighlight() {
        return highlight;
    }

    public void setHighlight(Integer highlight) {
        this.highlight = highlight;
    }

    public Integer getIdLoaiquyhoach() {
        return idLoaiquyhoach;
    }

    public void setIdLoaiquyhoach(Integer idLoaiquyhoach) {
        this.idLoaiquyhoach = idLoaiquyhoach;
    }
    
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
    
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHientrangPolygon)) {
            return false;
        }
        TblHientrangPolygon other = (TblHientrangPolygon) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.TblHientrangPolygon[ gid=" + gid + " ]";
    }
    
}
