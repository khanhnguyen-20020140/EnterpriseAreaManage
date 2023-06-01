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
@Table(name = "tbl_dm_loai_doanhnghiep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmLoaiDoanhnghiep.findAll", query = "SELECT t FROM TblDmLoaiDoanhnghiep t"),
    @NamedQuery(name = "TblDmLoaiDoanhnghiep.findByIdLoaiDoanhnghiep", query = "SELECT t FROM TblDmLoaiDoanhnghiep t WHERE t.idLoaiDoanhnghiep = :idLoaiDoanhnghiep"),
    @NamedQuery(name = "TblDmLoaiDoanhnghiep.findByLoaiDoanhnghiep", query = "SELECT t FROM TblDmLoaiDoanhnghiep t WHERE t.loaiDoanhnghiep = :loaiDoanhnghiep")})
public class TblDmLoaiDoanhnghiep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loai_doanhnghiep")
    private Integer idLoaiDoanhnghiep;
    @Size(max = 2147483647)
    @Column(name = "loai_doanhnghiep")
    private String loaiDoanhnghiep;

    public TblDmLoaiDoanhnghiep() {
    }

    public TblDmLoaiDoanhnghiep(Integer idLoaiDoanhnghiep) {
        this.idLoaiDoanhnghiep = idLoaiDoanhnghiep;
    }

    public Integer getIdLoaiDoanhnghiep() {
        return idLoaiDoanhnghiep;
    }

    public void setIdLoaiDoanhnghiep(Integer idLoaiDoanhnghiep) {
        this.idLoaiDoanhnghiep = idLoaiDoanhnghiep;
    }

    public String getLoaiDoanhnghiep() {
        return loaiDoanhnghiep;
    }

    public void setLoaiDoanhnghiep(String loaiDoanhnghiep) {
        this.loaiDoanhnghiep = loaiDoanhnghiep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaiDoanhnghiep != null ? idLoaiDoanhnghiep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDmLoaiDoanhnghiep)) {
            return false;
        }
        TblDmLoaiDoanhnghiep other = (TblDmLoaiDoanhnghiep) object;
        if ((this.idLoaiDoanhnghiep == null && other.idLoaiDoanhnghiep != null) || (this.idLoaiDoanhnghiep != null && !this.idLoaiDoanhnghiep.equals(other.idLoaiDoanhnghiep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDmLoaiDoanhnghiep[ idLoaiDoanhnghiep=" + idLoaiDoanhnghiep + " ]";
    }
    
}
