package root.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Murmurエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2012/05/22 14:16:24")
public class Murmur implements Serializable {

    private static final long serialVersionUID = 1L;

    /** useridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userid;

    /** murmuridプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer murmurid;

    /** murmurプロパティ */
    @Column(length = 180, nullable = true, unique = false)
    public String murmur;

    /** dateTimeプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp dateTime;

    /** tuser関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    public Tuser tuser;
}