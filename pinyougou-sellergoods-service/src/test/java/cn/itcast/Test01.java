package cn.itcast;

import com.pinyougou.mapper.TbBrandMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-dao.xml")
public class Test01 {
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Test
    public void test01() {
        List<Map> maps = tbBrandMapper.selectOptionList();
        for (Map map : maps) {
            System.out.println(map);
        }

    }
}
