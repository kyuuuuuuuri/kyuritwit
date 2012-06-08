package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Follow}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2012/05/22 14:19:25")
public class FollowNames {

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
    }

    /**
     * followidのプロパティ名を返します。
     * 
     * @return followidのプロパティ名
     */
    public static PropertyName<Integer> followid() {
        return new PropertyName<Integer>("followid");
    }

    /**
     * fuseridのプロパティ名を返します。
     * 
     * @return fuseridのプロパティ名
     */
    public static PropertyName<Integer> fuserid() {
        return new PropertyName<Integer>("fuserid");
    }

    /**
     * tuserのプロパティ名を返します。
     * 
     * @return tuserのプロパティ名
     */
    public static _TuserNames tuser() {
        return new _TuserNames("tuser");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _FollowNames extends PropertyName<Follow> {

        /**
         * インスタンスを構築します。
         */
        public _FollowNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _FollowNames(final String name) {
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
        public _FollowNames(final PropertyName<?> parent, final String name) {
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
         * followidのプロパティ名を返します。
         *
         * @return followidのプロパティ名
         */
        public PropertyName<Integer> followid() {
            return new PropertyName<Integer>(this, "followid");
        }

        /**
         * fuseridのプロパティ名を返します。
         *
         * @return fuseridのプロパティ名
         */
        public PropertyName<Integer> fuserid() {
            return new PropertyName<Integer>(this, "fuserid");
        }

        /**
         * tuserのプロパティ名を返します。
         * 
         * @return tuserのプロパティ名
         */
        public _TuserNames tuser() {
            return new _TuserNames(this, "tuser");
        }
    }
}