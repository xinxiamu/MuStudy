package com.didispace.domain;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/15 下午1:58.
 * @blog http://blog.didispace.com
 */
public class Address extends BaseVo {

    /**
	 * 
	 */
	private static final long serialVersionUID = -818974531305422708L;
	
	private String prv;
    private String city;
    private String area;
    private long zcode;//邮政编码
	public String getPrv() {
		return prv;
	}
	public void setPrv(String prv) {
		this.prv = prv;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public long getZcode() {
		return zcode;
	}
	public void setZcode(long zcode) {
		this.zcode = zcode;
	}
}
