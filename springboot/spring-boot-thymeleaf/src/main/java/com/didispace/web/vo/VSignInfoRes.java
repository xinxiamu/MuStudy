package com.didispace.web.vo;

/**
 * Created by Administrator on 2017-07-13.
 */
public class VSignInfoRes {

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 收货人姓名
     */
    private String receiveName;

    /**
     * 收货人手机
     */
    private String receiveTel;

    /**
     * 收货人地址
     */
    private String receiveAddres;

    /**
     * 提货电话/物流公司电话
     */
    private String takeTel;

    /**
     * 提货验证码
     */
    private String takeCode;

    /**
     * 提货地址
     */
    private String takeAddr;

    /**
     * 发往区域
     */
    private String area;

    /**
     * 天猫是否核销
     */
    private String tmall_check_;

    /**
     * 任务单号
     */
    private String waybillid;

    private String goodsName_1;
    private String goodsName_2;
    private String goodsName_3;
    private String goodsName_4;

    private String goodsNum_1;
    private String goodsNum_2;
    private String goodsNum_3;
    private String goodsNum_4;

    /**
     * 品名总重量、体积、包装件数
     */
    /**
     * 总包装数
     */
    private Integer totalPackingItems;

    /**
     * 总重量
     */
    private double totalWeight;

    /**
     * 总体积
     */
    private double totalVolumes;

    /**
     * 服务
     */
    private String serviceType;

    public String getBarCode() {
        return barCode;
    }

    public String getGoodsName_1() {
        return goodsName_1;
    }

    public void setGoodsName_1(String goodsName_1) {
        this.goodsName_1 = goodsName_1;
    }

    public String getGoodsName_2() {
        return goodsName_2;
    }

    public void setGoodsName_2(String goodsName_2) {
        this.goodsName_2 = goodsName_2;
    }

    public String getGoodsName_3() {
        return goodsName_3;
    }

    public void setGoodsName_3(String goodsName_3) {
        this.goodsName_3 = goodsName_3;
    }

    public String getGoodsName_4() {
        return goodsName_4;
    }

    public void setGoodsName_4(String goodsName_4) {
        this.goodsName_4 = goodsName_4;
    }

    public String getGoodsNum_1() {
        return goodsNum_1;
    }

    public void setGoodsNum_1(String goodsNum_1) {
        this.goodsNum_1 = goodsNum_1;
    }

    public String getGoodsNum_2() {
        return goodsNum_2;
    }

    public void setGoodsNum_2(String goodsNum_2) {
        this.goodsNum_2 = goodsNum_2;
    }

    public String getGoodsNum_3() {
        return goodsNum_3;
    }

    public void setGoodsNum_3(String goodsNum_3) {
        this.goodsNum_3 = goodsNum_3;
    }

    public String getGoodsNum_4() {
        return goodsNum_4;
    }

    public void setGoodsNum_4(String goodsNum_4) {
        this.goodsNum_4 = goodsNum_4;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public String getReceiveAddres() {
        return receiveAddres;
    }

    public void setReceiveAddres(String receiveAddres) {
        this.receiveAddres = receiveAddres;
    }

    public String getTakeTel() {
        return takeTel;
    }

    public void setTakeTel(String takeTel) {
        this.takeTel = takeTel;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public String getTakeAddr() {
        return takeAddr;
    }

    public void setTakeAddr(String takeAddr) {
        this.takeAddr = takeAddr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTmall_check_() {
        return tmall_check_;
    }

    public void setTmall_check_(String tmall_check_) {
        this.tmall_check_ = tmall_check_;
    }

    public String getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(String waybillid) {
        this.waybillid = waybillid;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getTotalPackingItems() {
        return totalPackingItems;
    }

    public void setTotalPackingItems(Integer totalPackingItems) {
        this.totalPackingItems = totalPackingItems;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalVolumes() {
        return totalVolumes;
    }

    public void setTotalVolumes(double totalVolumes) {
        this.totalVolumes = totalVolumes;
    }
}
