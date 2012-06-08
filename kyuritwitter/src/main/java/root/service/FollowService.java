package root.service;

import java.util.List;
import javax.annotation.Generated;
import root.entity.Follow;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.FollowNames.*;

/**
 * {@link Follow}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2012/05/22 14:16:36")
public class FollowService extends AbstractService<Follow> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param followid
     *            識別子
     * @return エンティティ
     */
    public Follow findById(Integer followid) {
        return select().id(followid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Follow> findAllOrderById() {
        return select().orderBy(asc(followid())).getResultList();
    }
}