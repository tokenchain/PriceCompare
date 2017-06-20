package com.price.dto;


public class FollowDTO {
    private long productId;
    private boolean cancelFollow;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean isCancelFollow() {
        return cancelFollow;
    }

    public void setCancelFollow(boolean cancelFollow) {
        this.cancelFollow = cancelFollow;
    }
}
