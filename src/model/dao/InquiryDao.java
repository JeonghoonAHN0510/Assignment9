package model.dao;

import model.dto.InquiryDto;

import java.sql.*;
import java.util.ArrayList;

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
    // 매개변수 : String inickname, String iexplain, String ipwd, int pno -> InquiryDto
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean inquiryRegis( InquiryDto inquiryDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into inquiry( inickname, iexplain, ipwd, pno ) values ( ?, ?, ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, inquiryDto.getInickname());
            ps.setString( 2, inquiryDto.getIexplain());
            ps.setString( 3, inquiryDto.getIpwd());
            ps.setInt( 4, inquiryDto.getPno());
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환 및 리턴
            if ( count == 1 ){
                return true;
            }else {
                return false;
            } // if end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
            return false;
        } // try-catch end
    } // func end

    // 6-2. 문의조회 메소드
    // 기능설명 : 사용자로부터 상세조회할 상품번호를 입력받아 문의내역을 호출한다.
    // 메소드명 : inquiryPrint()
    // 매개변수 : int pno
    // 반환값 : ArrayList<InquiryDto>
    public ArrayList<InquiryDto> inquiryPrint(int pno ){
        ArrayList<InquiryDto> inquiryDtos = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select * from inquiry where pno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, pno );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴
            while ( rs.next() ){
                // rs에서 값 꺼내기
                int ino = rs.getInt("ino");
                String inickname = rs.getString("inickname");
                String iexplain = rs.getString("iexplain");
                String ipwd = rs.getString("ipwd");
                // 객체에 대입하기
                InquiryDto inquiryDto = new InquiryDto( ino, inickname, iexplain, ipwd, pno );
                // 리스트에 객체 추가하기
                inquiryDtos.add( inquiryDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        return inquiryDtos;
    } // func end
} // class end
