package root.entity;

import javax.annotation.Generated;
import root.entity.FollowNames._FollowNames;
import root.entity.MurmurNames._MurmurNames;
import root.entity.TuserNames._TuserNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2012/05/22 14:19:25")
public class Names {

    /**
     * {@link Follow}の名前クラスを返します。
     * 
     * @return Followの名前クラス
     */
    public static _FollowNames follow() {
        return new _FollowNames();
    }

    /**
     * {@link Murmur}の名前クラスを返します。
     * 
     * @return Murmurの名前クラス
     */
    public static _MurmurNames murmur() {
        return new _MurmurNames();
    }

    /**
     * {@link Tuser}の名前クラスを返します。
     * 
     * @return Tuserの名前クラス
     */
    public static _TuserNames tuser() {
        return new _TuserNames();
    }
}