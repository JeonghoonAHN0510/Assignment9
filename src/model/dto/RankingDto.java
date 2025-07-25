package model.dto;

public class RankingDto {
    // 1. 멤버변수
    private String pnickname;
    private int count;
    // 2. 생성자
    public RankingDto(){}
    public RankingDto(String pnickname, int count) {
        this.pnickname = pnickname;
        this.count = count;
    } // func end
    // 3. 메소드
    public String getPnickname() { return pnickname; }
    public void setPnickname(String pnickname) { this.pnickname = pnickname; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public String toString() {
        return "RankingDto{" +
                "pnickname='" + pnickname + '\'' +
                ", count=" + count +
                '}';
    } // func end
} // class end
