package root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import root.entity.Follow;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.FollowNames.*;
import static root.entity.Names.follow;

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

    public List<Follow> findUserFollow(Integer userid) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("userid", userid);

    	return select().where(map).getResultList();
    }

    /**
     * follow しているユーザの数
     * @param id
     * @return long
     */
    public long followCount(Integer id){
    	return select().where(eq(follow().userid(),id)).getCount();
    }

    public long beFollowedCount(Integer id){
    	return select().where(eq(follow().fuserid(),id)).getCount();
    }

    public List<Follow> beFollowedList(Integer id){
    	return select().where(eq(follow().fuserid(),id)).getResultList();
    }

    /**
     * フォローユーザの検索
     * @param delete_userID
     * @param userid
     * @return delete following UserID
     */
    public Follow delFollow(Integer f_userID, Integer userid){
    	return select()
    			.where(
    					and(
    							eq(follow().fuserid(), f_userID),
    							eq(follow().userid(), userid)
    							)
    					)
    					.getSingleResult();
    }

}