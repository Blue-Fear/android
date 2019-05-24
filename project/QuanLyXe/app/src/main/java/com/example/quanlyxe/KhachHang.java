package com.example.quanlyxe;

public class KhachHang {
    private int MAKH;
    private String TENKH;
    private String DIACHI;

    public KhachHang(int MAKH, String TENKH, String DIACHI) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.DIACHI = DIACHI;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }
}
