/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_dm_trangthai")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmTrangthai.findAll", query = "SELECT t FROM TblDmTrangthai t"),
    @NamedQuery(name = "TblDmTrangthai.findById", query = "SELECT t FROM TblDmTrangthai t WHERE t.id = :id"),
    @NamedQuery(name = "TblDmTrangthai.findByTrangthai", query = "SELECT t FROM TblDmTrangthai t WHERE t.trangthai = :trangthai")})
public class TblDmTrangthai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "trangthai")
    private String trangthai;    

    public TblDmTrangthai() {
    }

    public TblDmTrangthai(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
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
        if (!(object instanceof TblDmTrangthai)) {
            return false;
        }
        TblDmTrangthai other = (TblDmTrangthai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDmTrangthai[ id=" + id + " ]";
    }
    
}
