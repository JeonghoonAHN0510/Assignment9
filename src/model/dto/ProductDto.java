package model.dto;

public class ProductDto {
    // 1. 멤버변수
    private int pno;                // 상품번호
    private String pnickname;       // 판매자닉네임
    private String pname;           // 상품명
    private String pexplain;        // 상품설명
    private int pprice;             // 상품가격
    private String ppwd;            // 상품비밀번호
    private String pdate;           // 상품등록일
    private String psale;           // 상품판매여부
    // 2. 생성자 : 기본생성자 / 전체생성자 + 필요시 추가
    public ProductDto(){}
    public ProductDto(int pno, String pnickname, String pname, String pexplain, int pprice, String ppwd, String pdate, String psale) {
        this.pno = pno;
        this.pnickname = pnickname;
        this.pname = pname;
        this.pexplain = pexplain;
        this.pprice = pprice;
        this.ppwd = ppwd;
        this.pdate = pdate;
        this.psale = psale;
    } // func end
    public ProductDto(String pnickname, String pname, String pexplain, int pprice, String ppwd) {
        this.pnickname = pnickname;
        this.pname = pname;
        this.pexplain = pexplain;
        this.pprice = pprice;
        this.ppwd = ppwd;
    } // func end // 물품등록 메소드에서 사용할 생성자
    public ProductDto(int pno, String pnickname, String pname, String pexplain, int pprice, String pdate, String psale) {
        this.pno = pno;
        this.pnickname = pnickname;
        this.pname = pname;
        this.pexplain = pexplain;
        this.pprice = pprice;
        this.pdate = pdate;
        this.psale = psale;
    } // func end // 상품전체조회 + 상품상세조회 메소드에서 사용할 생성자
    public ProductDto(int pno, String ppwd) {
        this.pno = pno;
        this.ppwd = ppwd;
    } // func end // 비밀번호검사 메소드에서 사용할 생성자
    public ProductDto(int pno, String pname, String pexplain, int pprice) {
        this.pno = pno;
        this.pname = pname;
        this.pexplain = pexplain;
        this.pprice = pprice;
    } // func end // 상품정보수정 메소드에서 사용할 생성자

    // 3. 메소드 : getter / setter / toString()
    public int getPno() { return pno; }
    public void setPno(int pno) { this.pno = pno; }
    public String getPnickname() { return pnickname; }
    public void setPnickname(String pnickname) { this.pnickname = pnickname; }
    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }
    public String getPexplain() { return pexplain; }
    public void setPexplain(String pexplain) { this.pexplain = pexplain; }
    public int getPprice() { return pprice; }
    public void setPprice(int pprice) { this.pprice = pprice; }
    public String getPpwd() { return ppwd; }
    public void setPpwd(String ppwd) { this.ppwd = ppwd; }
    public String getPdate() { return pdate; }
    public void setPdate(String pdate) { this.pdate = pdate; }
    public String getPsale() { return psale; }
    public void setPsale(String psale) { this.psale = psale; }
    public String toString() {
        return "ProductDto{" +
                "pno=" + pno +
                ", pnickname='" + pnickname + '\'' +
                ", pname='" + pname + '\'' +
                ", pexplain='" + pexplain + '\'' +
                ", pprice=" + pprice +
                ", ppwd='" + ppwd + '\'' +
                ", pdate='" + pdate + '\'' +
                ", psale='" + psale + '\'' +
                '}';
    } // func end
} // class end
