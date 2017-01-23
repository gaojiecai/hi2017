package cn.com.hiserivice.hiservice.vo;

import java.util.List;

/**
 * Created by gaojicai1 on 2017/1/20.
 */

public class HomeData {
    private List<BannerList> bannerList;
    private CommentList commentList;
    private List<ItemList> itemList;
    private Integer new_system_msg_num;
    private SecKill secKill;
    private ServicepointInfo servicepointInfo;
    private SiteList siteList;

    public List<BannerList> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerList> bannerList) {
        this.bannerList = bannerList;
    }

    public CommentList getCommentList() {
        return commentList;
    }

    public void setCommentList(CommentList commentList) {
        this.commentList = commentList;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public Integer getNew_system_msg_num() {
        return new_system_msg_num;
    }

    public void setNew_system_msg_num(Integer new_system_msg_num) {
        this.new_system_msg_num = new_system_msg_num;
    }

    public SecKill getSecKill() {
        return secKill;
    }

    public void setSecKill(SecKill secKill) {
        this.secKill = secKill;
    }

    public ServicepointInfo getServicepointInfo() {
        return servicepointInfo;
    }

    public void setServicepointInfo(ServicepointInfo servicepointInfo) {
        this.servicepointInfo = servicepointInfo;
    }

    public SiteList getSiteList() {
        return siteList;
    }

    public void setSiteList(SiteList siteList) {
        this.siteList = siteList;
    }
}
