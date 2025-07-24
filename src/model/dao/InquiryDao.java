package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InquiryDao {
    // * 싱글톤 구성
    private InquiryDao(){
        connectDB();        // DB 연동
    } // func end
    private static final InquiryDao instance = new InquiryDao();
    public static InquiryDao getInstance() {
        return instance;
    } // func end

    // 0. DB 연동 : dao가 생성될 때, 실행
    private String DB_URL = "jdbc:mysql://localhost:3306/assignment9";
    private String DB_ID = "root";
    private String DB_PWD = "1234";
    private Connection conn;
    private void connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");                      System.out.println("[안내] 드라이버 연동 성공");
            conn = DriverManager.getConnection( DB_URL, DB_ID, DB_PWD );    System.out.println("[안내] DB 연동 성공");
        } catch ( ClassNotFoundException e ){
            System.out.println("[경고] 드라이버 연동 실패");
        } catch ( SQLException e ){
            System.out.println("[경고] DB 연동 실패");
        } // try-catch end
    } // func end

    // 5. 문의등록 메소드
    // 기능설명 : 사용자로부터 문의할 상품번호, 닉네임, 문의내용, 비밀번호를 입력받아 문의를 문의DB에 저장한다.
    // 메소드명 : inquiryRegis()
    // 매개변수 : int pno, String inickname, String iexplain, String ipwd
    // 반환값 : true(성공) / false(실패) -> boolean


    // 6-2. 문의조회 메소드
    // 기능설명 : 사용자로부터 상세조회할 상품번호를 입력받아 문의내역을 호출한다.
    // 메소드명 : inquiryPrint()
    // 매개변수 : int pno
    // 반환값 : InquiryDto



} // class end
