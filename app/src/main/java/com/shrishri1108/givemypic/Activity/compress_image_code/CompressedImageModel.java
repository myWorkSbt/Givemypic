package com.shrishri1108.givemypic.Activity.compress_image_code;

public class CompressedImageModel {
    private int id;
    private String name;
    private byte[] image;
    private String compressed_ratio;
    private String size;
    private String type;
    private String created_at;

    public CompressedImageModel(int id,String name, byte[] image, String compressed_ratio, String size, String type, String created_at) {
        this.name = name;
        this.id = id ;
        this.image = image;
        this.compressed_ratio = compressed_ratio;
        this.size = size;
        this.type = type;
        this.created_at = created_at;
    }

    public CompressedImageModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCompressed_ratio(String compressed_ratio) {
        this.compressed_ratio = compressed_ratio;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public byte[] getImage() {
        return image;
    }

    public String getCompressed_ratio() {
        return compressed_ratio;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getCreated_at() {
        return created_at;
    }
}
