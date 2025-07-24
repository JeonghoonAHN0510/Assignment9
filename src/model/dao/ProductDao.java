package model.dao;

import model.dto.ProductDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {
    // * 싱글톤 구성
    private ProductDao(){
        connectDB();        // DB 연동
    } // func end
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance() {
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

    // 1. 물품등록 메소드
    // 기능설명 : 사용자로부터 닉네임, 상품명, 상품설명, 상품가격, 비밀번호를 입력받아 상품을 상품DB에 저장한다.
    // 메소드명 : productRegis()
    // 매개변수 : String pnickname, String pname, String pexplain, int pprice, String ppwd -> ProductDto
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean productRegis(ProductDto productDto){
        try {
            // 1. SQL 작성
            String SQL = "insert into product( pnickname, pname, pexplain, pprice, ppwd ) values ( ?, ?, ?, ?, ? )";
            // 2. SQL 기재 : PreparedStatement
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입 : ps.setXXX()
            ps.setString( 1, productDto.getPnickname() );
            ps.setString( 2, productDto.getPname() );
            ps.setString( 3, productDto.getPexplain() );
            ps.setInt( 4, productDto.getPprice() );
            ps.setString( 5, productDto.getPpwd() );
            // 4. SQL 실행 : ps.executeUpdate() -> int 반환
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

    // 2. 물품전체조회 메소드
    // 기능설명 : DB에 저장된 모든 물품 정보(닉네임, 상품번호, 상품명, 상품설명, 상품가격, 등록일, 판매여부)를 호출한다.
    // 메소드명 : productListPrint()
    // 매개변수 : X
    // 반환값 : ArrayList<ProductDto>


    // 3. 수정정보선택 메소드
    // 기능설명 : 사용자로부터 수정할 상품번호, 비밀번호를 입력받아 기본정보를 출력하고 3-2로 연계한다.
    // 메소드명 : productUpdate()
    // 매개변수 : int pno
    // 반환값 : ProductDto { pname, pexplain, pprice, psale }


    // 3-1. 물품정보수정 메소드
    // 기능설명 : 사용자가 1을 선택하면, 입력한 pno를 갖고오고, 사용자로부터 수정할 부분을 입력받아 상품명, 상품설명, 가격을 수정한다.
    // 메소드명 : productInfoUpdate()
    // 매개변수 : int pno, String pname, String pexplain, int pprice
    // 반환값 : true(성공) / false(실패) -> boolean


    // 3-2. 물품판매여부수정 메소드
    // 기능설명 : 사용자가 2를 선택하면, 입력한 pno를 갖고오고, 사용자로부터 수정할 부분을 입력받아 판매여부를 수정한다.
    // 메소드명 : productSaleUpdate()
    // 매개변수 : int pno, String psale
    // 반환값 : true(성공) / false(실패) -> boolean


    // 4. 물품삭제 메소드
    // 기능설명 : 사용자로부터 삭제할 상품번호와 비밀번호를 입력받아 해당하는 상품을 삭제한다.
    // 메소드명 : productDelete()
    // 매개변수 : int pno, String ppwd
    // 반환값 : true(성공) / false(실패) -> boolean


    // 6-1. 물품상세조회 메소드
    // 기능설명 : 사용자로부터 상세조회할 상품번호를 입력받아 해당 상품번호, 닉네임, 상품명, 상품가격, 등록일, 판매여부를 호출한다.
    // 메소드명 : productDetailPrint()
    // 매개변수 : int pno
    // 반환값 : ProductDto


    // 7. 등록랭킹조회 메소드
    // 기능설명 : 상품DB에 등록된 상품개수를 세어 상위 10명까지 닉네임과 등록수를 호출한다.
    // 메소드명 : rankingPrint()
    // 매개변수 : X
    // 반환값 : ArrayList<ProductDto>


    // 8. 검색 메소드
    // 기능설명 : 사용자로부터 검색어를 입력받아 상품명 / 설명에 해당 키워드가 포함된 상품의 (닉네임, 상품명, 상품설명, 상품가격, 등록일, 판매여부)를 호출한다.
    // 메소드명 : search()
    // 매개변수 : String keyword
    // 반환값 : ProductDto


    // *. 비밀번호검사 메소드
    // 기능설명 : 사용자로부터 비밀번호를 입력받아, 비밀번호 검사를 한다.
    // 메소드명 : pwdCheck()
    // 매개변수 : String ppwd
    // 반환값 : true(성공) / false(실패) -> boolean


} // class end
