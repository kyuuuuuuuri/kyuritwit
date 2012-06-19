package root.service;

import java.util.List;
import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Murmur;
import root.dto.MurmurDto;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.MurmurNames.*;
import static root.entity.Names.murmur;

/**
 * {@link Murmur}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2012/05/22 14:16:36")
public class MurmurService extends AbstractService<Murmur> {

	private static final String MurMur_DTO =
			" select murmur.*, tuser.username, tuser.usernick"
			+ " from murmur inner join tuser"
			+ " on murmur.userid = tuser.userid"
			+ " where murmur.userid = ? "
			+ " order by murmur.murmurid desc" ;

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

    public List<Murmur> mainListPager(int LIMIT,int page, List<Integer> murmur_userid){
    	return jdbcManager.from(Murmur.class).innerJoin("tuser")
    			.where(new SimpleWhere().in("userid",murmur_userid.toArray()))
    			.orderBy(desc("murmurid"))
    			.limit(LIMIT)
    			.offset(page*LIMIT)
    			.getResultList();
    }
    public List<Murmur> listPager(int LIMIT,int page, int id){
    	return select()
    			.innerJoin("tuser")
    			.where(eq(murmur().userid(),id))
    			.orderBy(desc("murmurid"))
    			.limit(LIMIT)
    			.offset(page * LIMIT)
    			.getResultList();
    }

   public long listCount(List<Integer> murmuruserid){
	   return select().innerJoin("tuser").where(new SimpleWhere().in("userid",murmuruserid.toArray())).getCount();
   }

   public long Count(Integer id){
	   return select().innerJoin("tuser").where(eq(murmur().userid(),id)).getCount();
   }

   public boolean hasPrev(int page){
	   return (page!=0) ? true: false;
   }

   public boolean hasNext(int limit, long total, int page){
	   return ((page+1)*limit < total) ? true:false;
   }

   public List<MurmurDto> another_user_twitList(int show_userID){
	   return jdbcManager
			   .selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
			   .getResultList();
   }

   public List<MurmurDto> another_user_twitList(int LIMIT, int page, int show_userID){
	   return jdbcManager
				.selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
				.limit(LIMIT)
				.offset(page*LIMIT)
				.getResultList();
   }

}