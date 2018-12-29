package multiple.xiaolei.com.multiplefilter.vo;


import java.io.Serializable;

/**
 * 
 * @author 肖磊
 * 以健值对出现的实体
 *
 */
public class MapVo implements Serializable {
	private String id;
	private String name;
	private int path;
	private boolean isCheck;
	private String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getPath() {
		return path;
	}
	public void setPath(int path) {
		this.path = path;
	}
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
}
