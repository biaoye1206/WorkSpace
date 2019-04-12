/**
 * @author Zhangxq
 * @version v1.0User
 * @CreateDate 2019-03-20 10:20
 */
public class Server {
    public static void main(String[] args) {
        UserInfo info = new UserInfo();
    }
}

enum Type{
    /**
     * 余额
     */
    BALANCE(0,"余额"),
    /**
     * 微信
     */
    wechat(1,"微信");
    private Integer type;
    private String name;

    Type(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

}
class Order{
    private Type type;

    public Order() {
    }

    public Order(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String balance) {
       this.type =  Type.valueOf(balance);
    }

    @Override
    public String toString() {
        return "Order{" +
                "type=" + type +
                '}';
    }
}