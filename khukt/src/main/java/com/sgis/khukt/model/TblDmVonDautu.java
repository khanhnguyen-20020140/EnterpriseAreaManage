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
@Table(name = "tbl_dm_von_dautu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmVonDautu.findAll", query = "SELECT t FROM TblDmVonDautu t"),
    @NamedQuery(name = "TblDmVonDautu.findByIdLoaivonDautu", query = "SELECT t FROM TblDmVonDautu t WHERE t.idLoaivonDautu = :idLoaivonDautu"),
    @NamedQuery(name = "TblDmVonDautu.findByVonDautu", query = "SELECT t FROM TblDmVonDautu t WHERE t.vonDautu = :vonDautu")})
public class TblDmVonDautu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loaivon_dautu")
    private Integer idLoaivonDautu;
    @Size(max = 2147483647)
    @Column(name = "von_dautu")
    private String vonDautu;
    
    public TblDmVonDautu() {
    }

    public TblDmVonDautu(Integer idLoaivonDautu) {
        this.idLoaivonDautu = idLoaivonDautu;
    }

    public Integer getIdLoaivonDautu() {
        return idLoaivonDautu;
    }

    public void setIdLoaivonDautu(Integer idLoaivonDautu) {
        this.idLoaivonDautu = idLoaivonDautu;
    }

    public String getVonDautu() {
        return vonDautu;
    }

    public void setVonDautu(String vonDautu) {
        this.vonDautu = vonDautu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaivonDautu != null ? idLoaivonDautu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDmVonDautu)) {
            return false;
        }
        TblDmVonDautu other = (TblDmVonDautu) object;
        if ((this.idLoaivonDautu == null && other.idLoaivonDautu != null) || (this.idLoaivonDautu != null && !this.idLoaivonDautu.equals(other.idLoaivonDautu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDmVonDautu[ idLoaivonDautu=" + idLoaivonDautu + " ]";
    }
    
}
