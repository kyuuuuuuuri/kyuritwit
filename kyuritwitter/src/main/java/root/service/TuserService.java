package root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Tuser;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.tuser;
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

    /**
     * findById の usernick ver
     * @param userni
     * @return
     */
    public Tuser findByName(String userni){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("usernick", userni);
    	return select().where(map).getSingleResult();
    }

    /**
     * idにマッチしたTwitterユーザをすべて取得する
     * @param Integer id
     * @return Long
     */
    public long tuserListcount(List<Integer> id){
    	return select().where(new SimpleWhere().in("userid", id)).getCount();
    }

    public List<Tuser> tuserPager(int LIMIT,int page, List<Integer> idList){
    	return select().where(new SimpleWhere().in("userid", idList))
				.limit(LIMIT)
				.offset(page*LIMIT)
				.getResultList();
    }

    /**
     * User検索メソッド
     * @param String
     * @return Tuser List
     */
    public List<Tuser> tuserSerch(String searchUser){
    	return jdbcManager
        		.from(Tuser.class)
        		.where(
        				or(
        						like(tuser().username(), searchUser),
        						like(tuser().usernick(), searchUser)
        				)
        		)
        		.getResultList();
    }
    public List<Tuser> tuserSerch(int LIMIT, int page, String searchUser){
    	return jdbcManager
        		.from(Tuser.class)
        		.where(
        				or(
        						like(tuser().username(), searchUser),
        						like(tuser().usernick(), searchUser)
        				)
        		)
        		.limit(LIMIT)
        		.offset(page*LIMIT)
        		.getResultList();
    }

}
