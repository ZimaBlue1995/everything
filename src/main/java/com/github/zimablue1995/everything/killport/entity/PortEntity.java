//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.killport.entity;

public class PortEntity {
    private String pid;
    private String protocol;
    private String iamge;
    private String status;

    public String[] getRow() {
        String[] raw = new String[]{this.pid, this.iamge, this.protocol, this.status};
        return raw;
    }

    public PortEntity(String pid, String protocol, String iamge, String status) {
        this.pid = pid;
        this.protocol = protocol;
        this.iamge = iamge;
        this.status = status;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getIamge() {
        return this.iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "PortEntity{pid='" + this.pid + "', protocol='" + this.protocol + "', iamge='" + this.iamge + "', status='" + this.status + "'}";
    }
}
