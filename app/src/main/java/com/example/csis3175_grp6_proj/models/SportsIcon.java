package com.example.csis3175_grp6_proj.models;

public class SportsIcon {
    private int imgId;
    private String imgName;
    private int imgPic;

    public SportsIcon(int imgId, String imgName, int imgPic) {
        this.imgId = imgId;
        this.imgName = imgName;
        this.imgPic = imgPic;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getImgPic() {
        return imgPic;
    }

    public void setImgPic(int imgPic) {
        this.imgPic = imgPic;
    }
}
