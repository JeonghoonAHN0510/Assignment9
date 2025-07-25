package model.dao;

import model.dto.ProductDto;
import model.dto.RankingDto;

import java.sql.*;
import java.util.ArrayList;

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

    // 1. 상품등록 메소드
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

    // 2. 상품전체조회 메소드
    // 기능설명 : DB에 저장된 모든 상품 정보(상품번호, 닉네임, 상품명, 상품설명, 상품가격, 등록일, 판매여부)를 호출한다.
    // 메소드명 : productListPrint()
    // 매개변수 : X
    // 반환값 : ArrayList<ProductDto>
    public ArrayList<ProductDto> productListPrint(){
        // 반환할 리스트 생성
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select pno, pnickname, pname, pexplain, pprice, date_format( pdate, '%Y-%m-%d' ) pdate, psale from product";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입

            // 4. SQL 실행 : ps.executeQuery() -> ResultSet 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴 : rs.next() + rs.getXXX
            while ( rs.next() ){
                // 1) rs에서 값 하나씩 꺼내기
                int pno = rs.getInt("pno");
                String pnickname = rs.getString("pnickname");
                String pname = rs.getString("pname");
                String pexplain = rs.getString("pexplain");
                int pprice = rs.getInt("pprice");
                String pdate = rs.getString("pdate");
                String psale = rs.getString("psale");
                // 2) 값을 객체에 넣기
                ProductDto productDto = new ProductDto( pno, pnickname, pname, pexplain, pprice, pdate, psale);
                // 3) 생성한 객체를 리스트에 추가하기
                productDtos.add(productDto);
            } // while end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        return productDtos;
    } // func end

    // 3 + 6-1. 상품상세조회 메소드
    // 기능설명 : 사용자로부터 수정할 상품번호, 비밀번호를 입력받아 기본정보를 출력하고 3-2로 연계한다.
    // 기능설명 : 사용자로부터 상세조회할 상품번호를 입력받아 해당 상품번호, 닉네임, 상품명, 상품가격, 등록일, 판매여부를 호출한다.
    // 메소드명 : productDetailPrint()
    // 매개변수 : int pno
    // 반환값 : ProductDto { pno, pnickname, pname, pexplain, pprice, pdate, psale }
    public ProductDto productDetailPrint( int pno ){
        // 실패했을 때 리턴할 객체 생성
        ProductDto blank = new ProductDto();
        try {
            // 1. SQL 작성
            String SQL = "select pno, pnickname, pname, pexplain, pprice, date_format( pdate, '%Y-%m-%d' ) pdate, psale from product where pno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, pno );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴 : pno에 따른 select기 때문에 rs가 1개만 나올 것임
            while ( rs.next() ){
                // 5-1. 결과에서 값 뽑아내기
                int pno1 = rs.getInt("pno");
                String pnickname = rs.getString("pnickname");
                String pname = rs.getString("pname");
                String pexplain = rs.getString("pexplain");
                int pprice = rs.getInt("pprice");
                String pdate = rs.getString("pdate");
                String psale = rs.getString("psale");
                // 5-2. 리턴할 객체 생성
                ProductDto productDto = new ProductDto( pno1, pnickname, pname, pexplain, pprice, pdate, psale);
                // 5-3. 생성한 객체 리턴
                return productDto;
            }
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        // 빈 객체 리턴
        return blank;
    } // func end

    // 3-1. 상품정보수정 메소드
    // 기능설명 : 사용자가 1을 선택하면, 입력한 pno를 갖고오고, 사용자로부터 수정할 부분을 입력받아 상품명, 상품설명, 가격을 수정한다.
    // 메소드명 : productInfoUpdate()
    // 매개변수 : ProductDto
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean productInfoUpdate( ProductDto productDto ){
        try {
            // 1. SQL 작성
            String SQL = "update product set pname = ?, pexplain = ?, pprice = ? where pno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, productDto.getPname() );
            ps.setString( 2, productDto.getPexplain() );
            ps.setInt( 3, productDto.getPprice() );
            ps.setInt( 4, productDto.getPno() );
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

    // 3-2. 상품판매여부수정 메소드
    // 기능설명 : 사용자가 2를 선택하면, 입력한 pno를 갖고오고, 사용자로부터 수정할 부분을 입력받아 판매여부를 수정한다.
    // 메소드명 : productSaleUpdate()
    // 매개변수 : ProductDto
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean productSaleUpdate( ProductDto productDto ){
        try {
            // 1. SQL 작성
            String SQL = "update product set psale = ? where pno = ? ";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, productDto.getPsale());
            ps.setInt( 2, productDto.getPno());
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

    // 4. 상품삭제 메소드
    // 기능설명 : 사용자로부터 삭제할 상품번호와 비밀번호를 입력받아 해당하는 상품을 삭제한다.
    // 메소드명 : productDelete()
    // 매개변수 : int pno
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean productDelete( int pno ){
        try {
            // 1. SQL 작성
            String SQL = "delete from product where pno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, pno );
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

    // 7. 등록랭킹조회 메소드
    // 기능설명 : 상품DB에 등록된 상품개수를 세어 상위 10명까지 닉네임과 등록수를 호출한다.
    // 메소드명 : rankingPrint()
    // 매개변수 : X
    // 반환값 : ArrayList<RankingDto>
    public ArrayList<RankingDto> rankingPrint(){
        ArrayList<RankingDto> rankingDtos = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select pnickname, count(*) count from product group by pnickname order by count desc, pnickname;";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입

            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴
            while ( rs.next() ){
                // 결과에서 값 뽑아내기
                String pnickname = rs.getString("pnickname");
                int count = rs.getInt("count");
                // 객체 생성하기
                RankingDto rankingDto = new RankingDto( pnickname, count );
                // 리스트에 객체 추가하기
                rankingDtos.add( rankingDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        return rankingDtos;
    } // func end

    // 8. 검색 메소드
    // 기능설명 : 사용자로부터 검색어를 입력받아 상품명 / 설명에 해당 키워드가 포함된 상품의 (닉네임, 상품명, 상품설명, 상품가격, 등록일, 판매여부)를 호출한다.
    // 메소드명 : search()
    // 매개변수 : String keyword
    // 반환값 : ArrayList<ProductDto>
    public ArrayList<ProductDto> search( String keyword ){
        // 실패했을 때 리턴할 객체 생성
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select pno, pnickname, pname, pexplain, pprice, date_format( pdate, '%Y-%m-%d' ) pdate, psale from product where pname like ? or pexplain like ?; ";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입 : like %?%를 하려면 setString에서 구성
            ps.setString( 1, "%" + keyword + "%" );
            ps.setString( 2, "%" + keyword + "%" );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴
            while ( rs.next() ){
                // 5-1. 결과에서 값 뽑아내기
                int pno1 = rs.getInt("pno");
                String pnickname = rs.getString("pnickname");
                String pname = rs.getString("pname");
                String pexplain = rs.getString("pexplain");
                int pprice = rs.getInt("pprice");
                String pdate = rs.getString("pdate");
                String psale = rs.getString("psale");
                // 5-2. 리턴할 객체 생성
                ProductDto productDto = new ProductDto( pno1, pnickname, pname, pexplain, pprice, pdate, psale);
                // 5-3. 생성한 객체를 리스트에 추가
                productDtos.add(productDto);
            } // while end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        return productDtos;
    } // func end

    // *. 비밀번호검사 메소드
    // 기능설명 : 사용자로부터 상품번호와 비밀번호를 입력받아, 비밀번호 검사를 한다.
    // 메소드명 : pwdCheck()
    // 매개변수 : ProductDto
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean pwdCheck( ProductDto productDto ){
        int check = 0;      // 체크할 int 선언
        try {
            // 1. SQL 작성
            String SQL = "select count(*) count from product where pno = ? and ppwd = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, productDto.getPno() );
            ps.setString( 2, productDto.getPpwd() );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 및 리턴
            while ( rs.next() ){
                check = rs.getInt("count");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[경고] SQL 기재 실패");
        } // try-catch end
        if ( check == 1 ){
            return true;
        }else {
            return false;
        }
    } // func end
} // class end
