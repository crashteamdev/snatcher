package dev.crashteam.snatcher.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @SequenceGenerator(name = "reviewIdSeq", sequenceName = "review_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewIdSeq")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private UserProduct userProduct;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

}
