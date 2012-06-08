package root.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.FollowNames._FollowNames;
import root.entity.MurmurNames._MurmurNames;

/**
 * {@link Tuser}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2012/05/22 14:19:25")
public class TuserNames {

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
    }

    /**
     * usernameのプロパティ名を返します。
     * 
     * @return usernameのプロパティ名
     */
    public static PropertyName<String> username() {
        return new PropertyName<String>("username");
    }

    /**
     * usernickのプロパティ名を返します。
     * 
     * @return usernickのプロパティ名
     */
    public static PropertyName<String> usernick() {
        return new PropertyName<String>("usernick");
    }

    /**
     * passWordのプロパティ名を返します。
     * 
     * @return passWordのプロパティ名
     */
    public static PropertyName<String> passWord() {
        return new PropertyName<String>("passWord");
    }

    /**
     * mailadのプロパティ名を返します。
     * 
     * @return mailadのプロパティ名
     */
    public static PropertyName<String> mailad() {
        return new PropertyName<String>("mailad");
    }

    /**
     * skeyのプロパティ名を返します。
     * 
     * @return skeyのプロパティ名
     */
    public static PropertyName<Integer> skey() {
        return new PropertyName<Integer>("skey");
    }

    /**
     * postNumのプロパティ名を返します。
     * 
     * @return postNumのプロパティ名
     */
    public static PropertyName<Integer> postNum() {
        return new PropertyName<Integer>("postNum");
    }

    /**
     * followのプロパティ名を返します。
     * 
     * @return followのプロパティ名
     */
    public static PropertyName<Integer> follow() {
        return new PropertyName<Integer>("follow");
    }

    /**
     * followedのプロパティ名を返します。
     * 
     * @return followedのプロパティ名
     */
    public static PropertyName<Integer> followed() {
        return new PropertyName<Integer>("followed");
    }

    /**
     * newMurのプロパティ名を返します。
     * 
     * @return newMurのプロパティ名
     */
    public static PropertyName<String> newMur() {
        return new PropertyName<String>("newMur");
    }

    /**
     * newMurDのプロパティ名を返します。
     * 
     * @return newMurDのプロパティ名
     */
    public static PropertyName<Timestamp> newMurD() {
        return new PropertyName<Timestamp>("newMurD");
    }

    /**
     * followListのプロパティ名を返します。
     * 
     * @return followListのプロパティ名
     */
    public static _FollowNames followList() {
        return new _FollowNames("followList");
    }

    /**
     * murmurListのプロパティ名を返します。
     * 
     * @return murmurListのプロパティ名
     */
    public static _MurmurNames murmurList() {
        return new _MurmurNames("murmurList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TuserNames extends PropertyName<Tuser> {

        /**
         * インスタンスを構築します。
         */
        public _TuserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TuserNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _TuserNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * useridのプロパティ名を返します。
         *
         * @return useridのプロパティ名
         */
        public PropertyName<Integer> userid() {
            return new PropertyName<Integer>(this, "userid");
        }

        /**
         * usernameのプロパティ名を返します。
         *
         * @return usernameのプロパティ名
         */
        public PropertyName<String> username() {
            return new PropertyName<String>(this, "username");
        }

        /**
         * usernickのプロパティ名を返します。
         *
         * @return usernickのプロパティ名
         */
        public PropertyName<String> usernick() {
            return new PropertyName<String>(this, "usernick");
        }

        /**
         * passWordのプロパティ名を返します。
         *
         * @return passWordのプロパティ名
         */
        public PropertyName<String> passWord() {
            return new PropertyName<String>(this, "passWord");
        }

        /**
         * mailadのプロパティ名を返します。
         *
         * @return mailadのプロパティ名
         */
        public PropertyName<String> mailad() {
            return new PropertyName<String>(this, "mailad");
        }

        /**
         * skeyのプロパティ名を返します。
         *
         * @return skeyのプロパティ名
         */
        public PropertyName<Integer> skey() {
            return new PropertyName<Integer>(this, "skey");
        }

        /**
         * postNumのプロパティ名を返します。
         *
         * @return postNumのプロパティ名
         */
        public PropertyName<Integer> postNum() {
            return new PropertyName<Integer>(this, "postNum");
        }

        /**
         * followのプロパティ名を返します。
         *
         * @return followのプロパティ名
         */
        public PropertyName<Integer> follow() {
            return new PropertyName<Integer>(this, "follow");
        }

        /**
         * followedのプロパティ名を返します。
         *
         * @return followedのプロパティ名
         */
        public PropertyName<Integer> followed() {
            return new PropertyName<Integer>(this, "followed");
        }

        /**
         * newMurのプロパティ名を返します。
         *
         * @return newMurのプロパティ名
         */
        public PropertyName<String> newMur() {
            return new PropertyName<String>(this, "newMur");
        }

        /**
         * newMurDのプロパティ名を返します。
         *
         * @return newMurDのプロパティ名
         */
        public PropertyName<Timestamp> newMurD() {
            return new PropertyName<Timestamp>(this, "newMurD");
        }

        /**
         * followListのプロパティ名を返します。
         * 
         * @return followListのプロパティ名
         */
        public _FollowNames followList() {
            return new _FollowNames(this, "followList");
        }

        /**
         * murmurListのプロパティ名を返します。
         * 
         * @return murmurListのプロパティ名
         */
        public _MurmurNames murmurList() {
            return new _MurmurNames(this, "murmurList");
        }
    }
}