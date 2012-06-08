package root.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Murmur}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2012/05/22 14:19:25")
public class MurmurNames {

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
    }

    /**
     * murmuridのプロパティ名を返します。
     * 
     * @return murmuridのプロパティ名
     */
    public static PropertyName<Integer> murmurid() {
        return new PropertyName<Integer>("murmurid");
    }

    /**
     * murmurのプロパティ名を返します。
     * 
     * @return murmurのプロパティ名
     */
    public static PropertyName<String> murmur() {
        return new PropertyName<String>("murmur");
    }

    /**
     * dateTimeのプロパティ名を返します。
     * 
     * @return dateTimeのプロパティ名
     */
    public static PropertyName<Timestamp> dateTime() {
        return new PropertyName<Timestamp>("dateTime");
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
    public static class _MurmurNames extends PropertyName<Murmur> {

        /**
         * インスタンスを構築します。
         */
        public _MurmurNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MurmurNames(final String name) {
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
        public _MurmurNames(final PropertyName<?> parent, final String name) {
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
         * murmuridのプロパティ名を返します。
         *
         * @return murmuridのプロパティ名
         */
        public PropertyName<Integer> murmurid() {
            return new PropertyName<Integer>(this, "murmurid");
        }

        /**
         * murmurのプロパティ名を返します。
         *
         * @return murmurのプロパティ名
         */
        public PropertyName<String> murmur() {
            return new PropertyName<String>(this, "murmur");
        }

        /**
         * dateTimeのプロパティ名を返します。
         *
         * @return dateTimeのプロパティ名
         */
        public PropertyName<Timestamp> dateTime() {
            return new PropertyName<Timestamp>(this, "dateTime");
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