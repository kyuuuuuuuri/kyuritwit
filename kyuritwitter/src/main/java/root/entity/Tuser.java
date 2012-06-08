package root.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Tuserエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2012/05/22 14:16:24")
public class Tuser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** useridプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer userid;

    /** usernameプロパティ */
    @Column(length = 50, nullable = false, unique = false)
    public String username;

    /** usernickプロパティ */
    @Column(length = 50, nullable = false, unique = false)
    public String usernick;

    /** passWordプロパティ */
    @Column(length = 20, nullable = false, unique = false)
    public String passWord;

    /** mailadプロパティ */
    @Column(length = 100, nullable = false, unique = false)
    public String mailad;

    /** skeyプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer skey;

    /** postNumプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer postNum;

    /** followプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer follow;

    /** followedプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer followed;

    /** newMurプロパティ */
    @Column(length = 140, nullable = true, unique = false)
    public String newMur;

    /** newMurDプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp newMurD;

    /** followList関連プロパティ */
    @OneToMany(mappedBy = "tuser")
    public List<Follow> followList;

    /** murmurList関連プロパティ */
    @OneToMany(mappedBy = "tuser")
    public List<Murmur> murmurList;
}