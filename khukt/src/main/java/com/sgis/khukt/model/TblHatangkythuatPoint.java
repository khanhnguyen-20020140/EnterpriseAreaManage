/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_hatangkythuat_point")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHatangkythuatPoint.findAll", query = "SELECT t FROM TblHatangkythuatPoint t"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByGid", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.gid = :gid"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByTen", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByThongso", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.thongso = :thongso"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByHighlight", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.highlight = :highlight"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByDuanXaydunghatang", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.idDuanXdht.id = :idDuanXdht"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByDuandautusanxuat", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.idDuanDtsx.id = :idDuanDtsx"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByIdLoaiquyhoach", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.idLoaiquyhoach = :idLoaiquyhoach"),
    @NamedQuery(name = "TblHatangkythuatPoint.findByTenobj", query = "SELECT t FROM TblHatangkythuatPoint t WHERE t.tenobj = :tenobj")})
public class TblHatangkythuatPoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Size(max = 100)
    @Column(name = "ten")
    private String ten;
    @Size(max = 150)
    @Column(name = "thongso")
    private String thongso;
    @Column(name = "highlight")
    private Integer highlight;
    @Column(name = "id_loaiquyhoach")
    private Integer idLoaiquyhoach;
    @Size(max = 100)
    @Column(name = "tenobj")
    private String tenobj;
    @Column(name = "long")
    private Float longitude;//kinh do
    @Column(name = "lat")
    private Float latitude;//vi do
    @JoinColumn(name = "id_duan_dtsx", referencedColumnName = "id")
    @ManyToOne
    private TblDuanDautusanxuat idDuanDtsx;
    @JoinColumn(name = "id_duan_xdht", referencedColumnName = "id")
    @ManyToOne
    private TblDuanXaydunghatang idDuanXdht;
    @JoinColumn(name = "id_khucn", referencedColumnName = "id")
    @ManyToOne
    private TblKhuChuyennganh idKhucn;
    @JoinColumn(name = "id_khukt", referencedColumnName = "id")
    @ManyToOne
    private TblKhuKinhte idKhukt;

    public TblHatangkythuatPoint() {
    }

    public TblHatangkythuatPoint(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThongso() {
        return thongso;
    }

    public void setThongso(String thongso) {
        this.thongso = thongso;
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

    public String getTenobj() {
        return tenobj;
    }

    public void setTenobj(String tenobj) {
        this.tenobj = tenobj;
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

    public TblDuanDautusanxuat getIdDuanDtsx() {
        return idDuanDtsx;
    }

    public void setIdDuanDtsx(TblDuanDautusanxuat idDuanDtsx) {
        this.idDuanDtsx = idDuanDtsx;
    }

    public TblDuanXaydunghatang getIdDuanXdht() {
        return idDuanXdht;
    }

    public void setIdDuanXdht(TblDuanXaydunghatang idDuanXdht) {
        this.idDuanXdht = idDuanXdht;
    }

    public TblKhuChuyennganh getIdKhucn() {
        return idKhucn;
    }

    public void setIdKhucn(TblKhuChuyennganh idKhucn) {
        this.idKhucn = idKhucn;
    }

    public TblKhuKinhte getIdKhukt() {
        return idKhukt;
    }

    public void setIdKhukt(TblKhuKinhte idKhukt) {
        this.idKhukt = idKhukt;
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
        if (!(object instanceof TblHatangkythuatPoint)) {
            return false;
        }
        TblHatangkythuatPoint other = (TblHatangkythuatPoint) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.TblHatangkythuatPoint[ gid=" + gid + " ]";
    }
    
}
