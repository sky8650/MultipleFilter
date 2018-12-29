package multiple.xiaolei.com.multiplefilter.vo;

import java.io.Serializable;

/**
 * Created by xiaolei on 2016/11/18.
 */
public class TaskFilterVo implements Serializable {
    private String id;
    private String name;
    private boolean isCheck;
    private  boolean isFirstCheck;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isFirstCheck() {
        return isFirstCheck;
    }

    public void setFirstCheck(boolean firstCheck) {
        isFirstCheck = firstCheck;
    }
}
