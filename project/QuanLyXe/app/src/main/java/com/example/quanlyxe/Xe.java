package com.example.quanlyxe;

public class Xe {
    private int MAXE;
    private String TENXE;
    private String XUATXU;

    public Xe(int MAXE, String TENXE, String XUATXU) {
        this.MAXE = MAXE;
        this.TENXE = TENXE;
        this.XUATXU = XUATXU;
    }

    public int getMAXE() {
        return MAXE;
    }

    public void setMAXE(int MAXE) {
        this.MAXE = MAXE;
    }

    public String getTENXE() {
        return TENXE;
    }

    public void setTENXE(String TENXE) {
        this.TENXE = TENXE;
    }

    public String getXUATXU() {
        return XUATXU;
    }

    public void setXUATXU(String XUATXU) {
        this.XUATXU = XUATXU;
    }
}
