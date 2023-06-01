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
@Table(name = "tbl_khu_kinhte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblKhuKinhte.findAll", query = "SELECT t FROM TblKhuKinhte t"),
    @NamedQuery(name = "TblKhuKinhte.findById", query = "SELECT t FROM TblKhuKinhte t WHERE t.id = :id"),
    @NamedQuery(name = "TblKhuKinhte.findByTen", query = "SELECT t FROM TblKhuKinhte t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblKhuKinhte.findBySoqdThanhlap", query = "SELECT t FROM TblKhuKinhte t WHERE t.soqdThanhlap = :soqdThanhlap"),
    @NamedQuery(name = "TblKhuKinhte.findByTomtat", query = "SELECT t FROM TblKhuKinhte t WHERE t.tomtat = :tomtat"),
    @NamedQuery(name = "TblKhuKinhte.findByIdTinh", query = "SELECT t FROM TblKhuKinhte t WHERE t.idTinh = :idTinh")})
public class TblKhuKinhte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ten")
    private String ten;
    @Size(max = 100)
    @Column(name = "soqd_thanhlap")
    private String soqdThanhlap;
    @Size(max = 1000)
    @Column(name = "tomtat")
    private String tomtat;
    @Column(name = "id_tinh")
    private Integer idTinh;

    public TblKhuKinhte() {
    }

    public TblKhuKinhte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoqdThanhlap() {
        return soqdThanhlap;
    }

    public void setSoqdThanhlap(String soqdThanhlap) {
        this.soqdThanhlap = soqdThanhlap;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKhuKinhte)) {
            return false;
        }
        TblKhuKinhte other = (TblKhuKinhte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.mavenproject1.TblKhuKinhte[ id=" + id + " ]";
    }
    
}
