package com.sgis.khukt.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_hatangsanxuat_polygon")
public class TblHatangsanxuatPolygon {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Size(max = 200)
    @Column(name = "ten")
    private String ten;
    @Size(max = 200)

    @JoinColumn(name = "id_doanhnghiep", referencedColumnName = "id")
    @ManyToOne
    private TblDoanhnghiep idDoanhnghiep;

    @Column(name = "highlight")
    private Integer highlight;

    @Column(name = "id_loaiquyhoach")
    private Integer idLoaiquyhoach;

    @Column(name = "long")
    private Float longitude;//kinh do

    @Column(name = "lat")
    private Float latitude;//vi do

    @JoinColumn(name = "id_duan_dtsx", referencedColumnName = "id")
    @ManyToOne
    private TblDuanDautusanxuat idDuanDtsx;


    @Column(name = "dientich")
    private BigDecimal dientich;


    @Column(name = "ghichu")
    private String ghichu;


    @Column(name = "kyhieu")
    private String kyhieu;


    @JoinColumn(name = "id_khucn", referencedColumnName = "id")
    @ManyToOne
    private TblKhuChuyennganh idKhucn;

    @JoinColumn(name = "id_khukt", referencedColumnName = "id")
    @ManyToOne
    private TblKhuKinhte idKhukt;
}
