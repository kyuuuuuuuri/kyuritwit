package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static root.entity.FollowNames.*;

/**
 * {@link Follow}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2012/05/22 14:16:39")
public class FollowTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(Follow.class).id(1).getSingleResult();
    }

    /**
     * tuserとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tuser() throws Exception {
        jdbcManager.from(Follow.class).leftOuterJoin(tuser()).id(1).getSingleResult();
    }
}