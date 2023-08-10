package dev.crashteam.snatcher.model.domain;

import dev.crashteam.snatcher.model.UserProductStatus;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user_product")
public class UserProduct {

    @Id
    @SequenceGenerator(name = "productIdSeq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdSeq")
    private Long id;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "query")
    private String query;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    @Type(type = "dev.crashteam.snatcher.model.domain.UserProductPsqlType")
    private UserProductStatus productStatus;

}
