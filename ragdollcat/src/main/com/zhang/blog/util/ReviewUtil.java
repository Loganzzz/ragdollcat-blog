package main.com.zhang.blog.util;
/** * @author zhang_chl 
    * @date 2017年7月28日下午10:37:59
    */
public class ReviewUtil {      //引入工具类，专门传输评论相关，避免传输的数据太多，缺点是无法扩展！！！
	private String imgUrl;
	private String personName;
	private int personid;
	private String content;

	public ReviewUtil(String imgUrl, String personName, int i, String content) {
		super();
		this.imgUrl = imgUrl;
		this.personName = personName;
		this.personid = i;
		this.content = content;
	}
	public ReviewUtil() {

	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
