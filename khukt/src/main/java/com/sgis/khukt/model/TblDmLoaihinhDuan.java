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
@Table(name = "tbl_dm_loaihinh_duan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmLoaihinhDuan.findAll", query = "SELECT t FROM TblDmLoaihinhDuan t"),
    @NamedQuery(name = "TblDmLoaihinhDuan.findById", query = "SELECT t FROM TblDmLoaihinhDuan t WHERE t.id = :id"),
    @NamedQuery(name = "TblDmLoaihinhDuan.findByLoaihinhDuan", query = "SELECT t FROM TblDmLoaihinhDuan t WHERE t.loaihinhDuan = :loaihinhDuan"),
    @NamedQuery(name = "TblDmLoaihinhDuan.findByTenviettatLoaihinhDuan", query = "SELECT t FROM TblDmLoaihinhDuan t WHERE t.tenviettatLoaihinhDuan = :tenviettatLoaihinhDuan")})
public class TblDmLoaihinhDuan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "loaihinh_duan")
    private String loaihinhDuan;
    @Size(max = 2147483647)
    @Column(name = "tenviettat_loaihinh_duan")
    private String tenviettatLoaihinhDuan;

    public TblDmLoaihinhDuan() {
    }

    public TblDmLoaihinhDuan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaihinhDuan() {
        return loaihinhDuan;
    }

    public void setLoaihinhDuan(String loaihinhDuan) {
        this.loaihinhDuan = loaihinhDuan;
    }

    public String getTenviettatLoaihinhDuan() {
        return tenviettatLoaihinhDuan;
    }

    public void setTenviettatLoaihinhDuan(String tenviettatLoaihinhDuan) {
        this.tenviettatLoaihinhDuan = tenviettatLoaihinhDuan;
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
        if (!(object instanceof TblDmLoaihinhDuan)) {
            return false;
        }
        TblDmLoaihinhDuan other = (TblDmLoaihinhDuan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.TblDmLoaihinhDuan[ id=" + id + " ]";
    }
    
}
