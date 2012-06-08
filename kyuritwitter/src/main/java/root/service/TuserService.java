package root.service;

import java.util.List;
import javax.annotation.Generated;
import root.entity.Tuser;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.TuserNames.*;

/**
 * {@link Tuser}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2012/05/22 14:16:36")
public class TuserService extends AbstractService<Tuser> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param userid
     *            識別子
     * @return エンティティ
     */
    public Tuser findById(Integer userid) {
        return select().id(userid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tuser> findAllOrderById() {
        return select().orderBy(asc(userid())).getResultList();
    }
}