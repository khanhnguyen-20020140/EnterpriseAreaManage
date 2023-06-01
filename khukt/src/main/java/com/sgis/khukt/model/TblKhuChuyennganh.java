/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tbl_khu_chuyennganh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblKhuChuyennganh.findAll", query = "SELECT t FROM TblKhuChuyennganh t"),
    @NamedQuery(name = "TblKhuChuyennganh.findById", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.id = :id"),
    @NamedQuery(name = "TblKhuChuyennganh.findByTen", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblKhuChuyennganh.findByTomtat", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.tomtat = :tomtat"),
    @NamedQuery(name = "TblKhuChuyennganh.findByIdTinh", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.idTinh = :idTinh"),
    @NamedQuery(name = "TblKhuChuyennganh.findByDientich", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.dientich = :dientich"),
    @NamedQuery(name = "TblKhuChuyennganh.findByLat", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.lat = :lat"),
    @NamedQuery(name = "TblKhuChuyennganh.findByLon", query = "SELECT t FROM TblKhuChuyennganh t WHERE t.lon = :lon")})
public class TblKhuChuyennganh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "ten")
    private String ten;
    @Size(max = 2147483647)
    @Column(name = "tomtat")
    private String tomtat;
    @Column(name = "id_tinh")
    private Integer idTinh;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dientich")
    private BigDecimal dientich;
    @Column(name = "lat")
    private Float lat;
    @Column(name = "lon")
    private Float lon;
    @JoinColumn(name = "loai_khu_chuyennganh", referencedColumnName = "id")
    @ManyToOne
    private TblDmLoaihinhKhucn loaiKhuChuyennganh;
    @JoinColumn(name = "id_khu_kinhte", referencedColumnName = "id")
    @ManyToOne
    private TblKhuKinhte idKhuKinhte;

    public TblKhuChuyennganh() {
    }

    public TblKhuChuyennganh(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTomtat() {
        return tomtat;
    }

    public void setTomtat(String tomtat) {
        this.tomtat = tomtat;
    }

    public Integer getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(Integer idTinh) {
        this.idTinh = idTinh;
    }

    public BigDecimal getDientich() {
        return dientich;
    }

    public void setDientich(BigDecimal dientich) {
        this.dientich = dientich;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public TblDmLoaihinhKhucn getLoaiKhuChuyennganh() {
        return loaiKhuChuyennganh;
    }

    public void setLoaiKhuChuyennganh(TblDmLoaihinhKhucn loaiKhuChuyennganh) {
        this.loaiKhuChuyennganh = loaiKhuChuyennganh;
    }

    public TblKhuKinhte getIdKhuKinhte() {
        return idKhuKinhte;
    }

    public void setIdKhuKinhte(TblKhuKinhte idKhuKinhte) {
        this.idKhuKinhte = idKhuKinhte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKhuChuyennganh)) {
            return false;
        }
        TblKhuChuyennganh other = (TblKhuChuyennganh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.mavenproject1.TblKhuChuyennganh[ id=" + id + " ]";
    }
    
}
