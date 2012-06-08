package root.service;

import java.util.List;
import javax.annotation.Generated;
import root.entity.Murmur;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.MurmurNames.*;

/**
 * {@link Murmur}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2012/05/22 14:16:36")
public class MurmurService extends AbstractService<Murmur> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param murmurid
     *            識別子
     * @return エンティティ
     */
    public Murmur findById(Integer murmurid) {
        return select().id(murmurid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Murmur> findAllOrderById() {
        return select().orderBy(asc(murmurid())).getResultList();
    }
}