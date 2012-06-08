package root.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Followエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2012/05/22 14:16:23")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /** useridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userid;

    /** followidプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer followid;

    /** fuseridプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer fuserid;

    /** tuser関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    public Tuser tuser;
}