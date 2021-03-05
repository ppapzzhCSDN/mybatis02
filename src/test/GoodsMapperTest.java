import com.dao.GoodsDao;
import com.entity.Goods;
import com.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author zzh
 * @description
 * @date
 */
public class GoodsMapperTest {
    @Test
    public void insert() {
        SqlSession session = MybatisUtils.getSqlSession();
        GoodsDao sessionMapper = session.getMapper(GoodsDao.class);
        Goods goods = new Goods();
        goods.setGoodId(21);
        goods.setGoodName("火箭炮");
        goods.setGoodPrice(120.22);
        goods.setGoodStock(22222122);

        int count = sessionMapper.insert(goods);

        session.commit();
        session.close();
        System.out.println("增加成功");
        System.out.println("增加数量为" + count);
    }

    @Test
    public void delete() {
        SqlSession session = MybatisUtils.getSqlSession();
        GoodsDao sessionMapper = session.getMapper(GoodsDao.class);
        int count = sessionMapper.delete(1);
        session.commit();
        session.close();
        System.out.println("删除成功");
        System.out.println("删除成功的数量" + count);

    }
@Test
    public void update() {
        SqlSession session = MybatisUtils.getSqlSession();
        GoodsDao sessionMapper = session.getMapper(GoodsDao.class);
        Goods goods = new Goods();
        goods.setGoodId(2);
        goods.setGoodName("dada大的坦克");
        goods.setGoodPrice(220.22);
        goods.setGoodStock(212);

        int count =sessionMapper.update(goods);
        session.commit();
        session.close();
        System.out.println("更新成功");
        System.out.println("更新成功的数量"+count);
    }
    @Test
    public void findAll() {
        SqlSession session = MybatisUtils.getSqlSession();
        GoodsDao mapper = session.getMapper(GoodsDao.class);
        List<Goods> list = mapper.findAll();
        System.out.println(list.size());
        list.stream().forEach(n->{
            System.out.println(n.getGoodName()
            );
        });
//        for (Goods s : list) {
//            System.out.println("姓名:" + s.getGoodName()+"价格"+s.getGoodName());
//        }
        session.commit();
        session.close();
        System.out.println("查询成功");
    }


}
