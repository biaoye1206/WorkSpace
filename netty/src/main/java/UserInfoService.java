package java;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import .UserInfo;
import java.UserInfoDao;

@Service
public class UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    public int insert(UserInfo pojo){
        return userInfoDao.insert(pojo);
    }

    public int insertList(List< UserInfo> pojos){
        return userInfoDao.insertList(pojos);
    }

    public List<UserInfo> select(UserInfo pojo){
        return userInfoDao.select(pojo);
    }

    public int update(UserInfo pojo){
        return userInfoDao.update(pojo);
    }

}
