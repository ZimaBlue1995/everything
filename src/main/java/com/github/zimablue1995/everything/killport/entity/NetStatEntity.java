//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.killport.entity;

import java.util.Objects;

public class NetStatEntity {
    private String pid;
    private String protocol;
    private String localAddress;
    private String foreignAddress;
    private String status;

    public NetStatEntity() {
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

    public String getLocalAddress() {
        return this.localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getForeignAddress() {
        return this.foreignAddress;
    }

    public void setForeignAddress(String foreignAddress) {
        this.foreignAddress = foreignAddress;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof NetStatEntity)) {
            return false;
        } else {
            NetStatEntity that = (NetStatEntity)o;
            return Objects.equals(this.getPid(), that.getPid());
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getPid(), this.getProtocol(), this.getLocalAddress(), this.getForeignAddress(), this.getStatus()});
    }

    public String toString() {
        return "NetStatEntity{pid='" + this.pid + "', protocol='" + this.protocol + "', localAddress='" + this.localAddress + "', foreignAddress='" + this.foreignAddress + "', status='" + this.status + "'}";
    }
}
