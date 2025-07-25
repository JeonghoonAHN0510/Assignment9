import controller.InquiryController;
import controller.ProductController;
import model.dto.InquiryDto;
import model.dto.ProductDto;
import view.MainView;

import java.util.ArrayList;

public class AppStart {
    public static void main(String[] args) {

        // [ VIEW없이 테스트 ]
        // * 상품등록 기능
        // boolean test = ProductController.getInstance().productRegis( "테스트", "테스트 상품", "테스트 상품입니다", 100000, "1234" );
        // * 상품전체조회 기능
        // ArrayList<ProductDto> test = ProductController.getInstance().productListPrint();
        // * 상품상세조회 기능
        // ProductDto test = ProductController.getInstance().productDetailPrint( 1 );
        // * 비밀번호검사 기능
        // boolean test = ProductController.getInstance().pwdCheck( 1, "1234" );
        // * 상품정보수정 기능
        // boolean test = ProductController.getInstance().productInfoUpdate( 1, "수정테스트중2", "수정테스트2", 99999 );
        // * 상품판매여부수정 기능
        // boolean test = ProductController.getInstance().productSaleUpdate( 1, "판매완료 테스트");
        // * 상품삭제 기능
        // boolean test = ProductController.getInstance().productDelete( 1 );
        // * 문의등록 기능
        // boolean test = InquiryController.getInstance().inquiryRegis( "테스트", "테스트중입니다", "1234", 3 );
        // * 문의조회 기능
        // InquiryDto test = InquiryController.getInstance().inquiryPrint( 5 );
        // * 검색 기능
        // ProductDto test = ProductController.getInstance().search( "블루투스" );



        // System.out.println( test );
        // 메인화면 호출
        MainView.getInstance().main();

    } // main end
} // class end
