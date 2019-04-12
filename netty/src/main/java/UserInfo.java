import java.io.Serializable;

/**
 * 用户实体类
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-08 11:57
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 9090162230085784206L;
    /**
     * ID
     */
    private String id;
    /**
     * 用户姓名
     */
    private String age;
    /**
     * 电话
     */
    private String iphone;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 城市
     */
    private String city;
    /**
     * 省
     */
    private String province;
    /**
     * 用户呢称
     */
    private String nickName;
    /**
     * 车牌号
     */
    private String carNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
