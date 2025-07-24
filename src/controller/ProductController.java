package controller;

import model.dao.ProductDao;
import model.dto.ProductDto;

public class ProductController {
    // * 싱글톤 구성
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance() {
        return instance;
    } // func end

    // * ProductDao 싱글톤 가져오기
    private ProductDao productDao = ProductDao.getInstance();

    // 1. 물품등록 메소드
    // 기능설명 : 사용자로부터 닉네임, 상품명, 상품설명, 상품가격, 비밀번호를 입력받아 상품을 상품DB에 저장한다.
    // 메소드명 : productRegis()
    // 매개변수 : String pnickname, String pname, String pexplain, int pprice, String ppwd
    // 반환값 : true(성공) / false(실패) -> boolean
    public boolean productRegis( String pnickname, String pname, String pexplain, int pprice, String ppwd ){
        // 1. 유효성 검사

        // 2. 객체 생성
        ProductDto productDto = new ProductDto( pnickname, pname, pexplain, pprice, ppwd );
        // 3. 생성한 객체를 dao에게 전달 후, 결과 받기
        boolean result = productDao.productRegis( productDto );
        // 4. view에게 결과 전달하기
        return result;
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
