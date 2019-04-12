import java.util.List;

public interface UserInfoDao {

    int insert(@Param("pojo") UserInfo pojo);

    int insertList(@Param("pojos") List< UserInfo> pojo);

    List<UserInfo> select(@Param("pojo") UserInfo pojo);

    int update(@Param("pojo") UserInfo pojo);

}
