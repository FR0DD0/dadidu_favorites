package com.makiia.crosscutting.persistence.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recmaesfavorito")

public class EntyRecmaesfavorito implements Serializable{

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_nroreg_refv")
    private String recNroregRefv;

    @Basic(optional = false)
    @Column(name = "rec_nroreg_reus")
    private String recNroregReus;

    @Basic(optional = false)
    @Column(name = "apj_nroreg_aphp")
    private String apjNroregAphp;

    @Basic(optional = false)
    @Column(name = "rec_fecrec_refv")
    private String recFecrecRefv;

    @Basic(optional = false)
    @Column(name = "rec_ordvis_refv")
    private Integer recOrdvisRefv;

    @Basic(optional = false)
    @Column(name = "rec_estreg_refv")
    private String recEstregRefv;

}
