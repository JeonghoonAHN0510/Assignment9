package model.dto;

public class InquiryDto {
    // 1. 멤버변수
    private int ino;            // 문의번호
    private String inickname;   // 문의자닉네임
    private String iexplain;    // 문의내용
    private String ipwd;        // 문의비밀번호
    private int pno;            // 문의상품번호
    // 2. 생성자 : 기본생성자 / 전체생성자 + 필요시 추가
    public InquiryDto(){}
    public InquiryDto(int ino, String inickname, String iexplain, String ipwd, int pno) {
        this.ino = ino;
        this.inickname = inickname;
        this.iexplain = iexplain;
        this.ipwd = ipwd;
        this.pno = pno;
    } // func end
    public InquiryDto(String inickname, String iexplain, String ipwd, int pno) {
        this.inickname = inickname;
        this.iexplain = iexplain;
        this.ipwd = ipwd;
        this.pno = pno;
    } // func end // 문의등록 메소드에서 사용할 생성자

    // 3. 메소드 : getter / setter / toString()
    public int getIno() { return ino; }
    public void setIno(int ino) { this.ino = ino; }
    public String getInickname() { return inickname; }
    public void setInickname(String inickname) { this.inickname = inickname; }
    public String getIexplain() { return iexplain; }
    public void setIexplain(String iexplain) { this.iexplain = iexplain; }
    public String getIpwd() { return ipwd; }
    public void setIpwd(String ipwd) { this.ipwd = ipwd; }
    public int getPno() { return pno; }
    public void setPno(int pno) { this.pno = pno; }
    public String toString() {
        return "InquiryDto{" +
                "ino=" + ino +
                ", inickname='" + inickname + '\'' +
                ", iexplain='" + iexplain + '\'' +
                ", ipwd='" + ipwd + '\'' +
                ", pno=" + pno +
                '}';
    } // func end
} // class end
