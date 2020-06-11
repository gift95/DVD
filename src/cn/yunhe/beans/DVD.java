package cn.yunhe.beans;

public class DVD {
    int state;
    String dvdName;
    String borrowTime;
    int number;
    public DVD() {
    }

    public DVD(int state, String dvdName, String borrowTime, int number) {
        this.state = state;
        this.dvdName = dvdName;
        this.borrowTime = borrowTime;
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDvdName() {
        return dvdName;
    }

    public void setDvdName(String dvdName) {
        this.dvdName = dvdName;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public int getNumber() {
        return number;
    }



    public void setNumber(int number) {
        this.number = number;

    }

    @Override
    public String toString() {
        if (borrowTime == null) {
            borrowTime="--";
        }
        return state +
                "," + dvdName +
                "," + borrowTime +
                "," + number ;
    }
}
