package view;

import controller.InquiryController;
import controller.ProductController;
import model.dto.InquiryDto;
import model.dto.ProductDto;
import model.dto.RankingDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    // * 싱글톤 구성
    private MainView(){}
    private static final MainView instance = new MainView();
    public static MainView getInstance() {
        return instance;
    } // func end

    // 입력 객체 생성
    Scanner scan = new Scanner(System.in);

    // 각 controller 싱글톤 가져오기
    private ProductController productController = ProductController.getInstance();
    private InquiryController inquiryController = InquiryController.getInstance();

    // 0. 메인화면
    public void main(){
        for( ; ; ){
            try {
                System.out.println("================================================ 뚝딱마켓 ================================================");
                System.out.println("  1.상품등록 | 2.상품전체조회 | 3.상품정보수정 | 4.상품삭제 | 5.문의등록 | 6.상품상세조회 | 7.등록랭킹조회 | 8.검색");
                System.out.println("========================================================================================================");
                // 사용자에게 선택받기
                System.out.print("선택 : ");      int choose = scan.nextInt();
                if ( choose == 1 ){             // 1을 선택하면
                    productRegisPrint();
                }else if ( choose == 2 ){       // 2를 선택하면
                    productListPrint();
                }else if ( choose == 3 ){       // 3을 선택하면
                    productUpdate();
                }else if ( choose == 4 ){       // 4를 선택하면
                    productDelete();
                }else if ( choose == 5 ){       // 5를 선택하면
                    inquiryRegis();
                }else if ( choose == 6 ){       // 6을 선택하면
                    productDetailPrint();
                }else if ( choose == 7 ){       // 7을 선택하면
                    rankingPrint();
                }else if ( choose == 8 ){       // 8을 선택하면
                    searchPrint();
                }else {                         // 잘못 선택하면
                    System.out.println("[경고] 존재하지않는 선택입니다. 다시 입력하세요.");
                } // if end
            } catch ( InputMismatchException e ){
                System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
                // 잘못된 입력이니 입력객체 초기화
                scan = new Scanner(System.in);
            } catch ( Exception e ){
                System.out.println("[경고] 관리자에게 문의하세요. <admin@admin.com> ");
            } // try-catch end
        } // 무한루프 end
    } // for end

    // 1. 상품등록 화면
    public void productRegisPrint(){
        try {
            System.out.println("========================================================================================================");
            // 1. 사용자로부터 입력받기
            System.out.print("닉네임 : ");         String pnickname = scan.next();
            scan.nextLine(); // 의미없는 nextLine() 필요
            System.out.print("상품명 : ");         String pname = scan.nextLine();
            System.out.print("상품설명 : ");        String pexplain = scan.nextLine();
            System.out.print("상품가격 : ");        int pprice = scan.nextInt();
            System.out.print("비밀번호 : ");        String ppwd = scan.next();
            // 2. controller에게 전달 후 결과 받기
            boolean result = productController.productRegis( pnickname, pname, pexplain, pprice, ppwd );
            // 3. 결과에 따른 출력하기
            if ( result ){
                System.out.println("[안내] 상품 등록 성공");
            }else {
                System.out.println("[경고] 상품 등록 실패");
            } // if end
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end

    // 2. 상품전체조회 화면
    public void productListPrint(){
        // 1. controller에게 전달 후 결과 받기
        ArrayList<ProductDto> productDtos = productController.productListPrint();
        // 2. 결과에 따른 출력하기
        System.out.println("========================================================================================================");
        for ( ProductDto productDto : productDtos ){
            System.out.println("상품번호 : " + productDto.getPno());
            System.out.println("닉네임 : " + productDto.getPnickname());
            System.out.println("상품명 : " + productDto.getPname());
            System.out.println("상품설명 : " + productDto.getPexplain());
            System.out.printf("상품가격 : %d원\n", productDto.getPprice());         // 천단위 , 추가
            System.out.println("등록일 : " + productDto.getPdate());             // 월 일 추가방법 생각하기
            System.out.println("판매여부 : " + productDto.getPsale());
            System.out.println("========================================================================================================");
        } // for end
    } // func end

    // 3. 상품수정 화면
    public void productUpdate(){
        try {
            // 1. 사용자로부터 입력받기
            System.out.print("수정할 상품번호 : ");        int pno = scan.nextInt();
            System.out.print("비밀번호 : ");              String ppwd = scan.next();
            // * 비밀번호 검증하기
            boolean pwdCheck = productController.pwdCheck( pno, ppwd );
            if ( pwdCheck ){
                // 2. controller에게 전달 후, 결과받기
                ProductDto productDto = productController.productDetailPrint( pno );
                // 3. 결과에 따른 출력하기
                System.out.println("========================================================================================================");
                System.out.println("[기존 상품정보]");
                System.out.println("상품명 : " + productDto.getPname());
                System.out.println("상품설명 : " + productDto.getPprice());
                System.out.println("가격 : " + productDto.getPprice());
                System.out.println("판매여부 : " + productDto.getPsale());
                System.out.println("========================================================================================================");
                System.out.println("                                  1.상품정보수정 | 2.판매여부수정");
                System.out.println("========================================================================================================");
                System.out.print("선택 : ");          int update = scan.nextInt();
                if ( update == 1 ){
                    // 1. 사용자로부터 입력받기
                    System.out.println("[수정 상품정보]");
                    scan.nextLine(); // 의미없는 nextLine();
                    System.out.print("상품명 : ");          String pname = scan.nextLine();
                    System.out.print("상품설명 : ");        String pexplain = scan.nextLine();
                    System.out.print("상품가격 : ");        int pprice = scan.nextInt();
                    // 2. controller에게 전달 후, 결과 받기
                    boolean result = productController.productInfoUpdate( pno, pname, pexplain, pprice );
                    // 3. 결과에 따른 출력하기
                    if ( result ){
                        System.out.println("[안내] 상품 수정 성공");
                    }else {
                        System.out.println("[경고] 상품 수정 실패");
                    } // if end
                }else if ( update == 2 ){
                    // 1. 사용자로부터 입력받기
                    System.out.println("[수정 상품정보]");
                    scan.nextLine(); // 의미없는 nextLine();
                    System.out.print("판매여부 : ");          String psale = scan.nextLine();
                    // 2. controller에게 전달 후, 결과받기
                    boolean result = productController.productSaleUpdate( pno, psale );
                    // 3. 결과에 따른 출력하기
                    if ( result ){
                        System.out.println("[안내] 상품 수정 성공");
                    }else {
                        System.out.println("[경고] 상품 수정 실패");
                    } // if end
                }else {
                    System.out.println("[경고] 존재하지않는 선택입니다. 다시 입력하세요.");
                } // if end
            }else {
                System.out.println("[경고] 비밀번호가 일치하지 않습니다.");
            } // if end
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end

    // 4. 상품삭제 화면 + 상품정보가 없을 때, 입력하면 비밀번호 불일치로 나온다(수정필요)
    public void productDelete(){
        try {
            // 1. 사용자로부터 입력받기
            System.out.println("========================================================================================================");
            System.out.print("삭제할 상품번호 : ");        int pno = scan.nextInt();
            System.out.print("비밀번호 : ");              String ppwd = scan.next();
            // * 비밀번호 검증하기
            boolean pwdCheck = productController.pwdCheck( pno, ppwd );
            if ( pwdCheck ){
                // 2. controller에게 전달 후, 결과받기
                boolean result = productController.productDelete( pno );
                // 3. 결과에 따른 출력하기
                if ( result ){
                    System.out.println("[안내] 상품 삭제 성공");
                }else {
                    System.out.println("[경고] 상품 삭제 실패");
                } // if end
            }else {
                System.out.println("[경고] 비밀번호가 일치하지 않습니다.");
            } // if end
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end

    // 5. 문의등록 화면
    public void inquiryRegis(){
        try {
            // 1. 사용자로부터 입력받기
            System.out.println("========================================================================================================");
            System.out.print("문의할 상품번호 : ");        int pno = scan.nextInt();
            System.out.print("문의자 닉네임 : ");          String inickname = scan.next();
            scan.nextLine(); // 의미없는 nextLine()
            System.out.print("문의내용 : ");              String iexplain = scan.nextLine();
            System.out.print("비밀번호 : ");              String ipwd = scan.next();
            System.out.println("========================================================================================================");
            // 2. controller에게 전달 후, 결과 받기
            boolean result = inquiryController.inquiryRegis( inickname, iexplain, ipwd, pno );
            // 3. 결과에 따른 출력하기
            if ( result ){
                System.out.println("[안내] 문의 등록 성공");
            }else {
                System.out.println("[경고] 문의 등록 실패");
            } // if end
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end

    // 6. 상품상세조회 화면
    public void productDetailPrint(){
        try {
            // 1. 사용자로부터 입력받기
            System.out.println("========================================================================================================");
            System.out.print("상세조회할 상품번호 : ");      int pno = scan.nextInt();
            System.out.println("========================================================================================================");
            // 2. controller에게 전달 후, 결과 받기
            ProductDto productDto = productController.productDetailPrint( pno );
            InquiryDto inquiryDto = inquiryController.inquiryPrint( pno );
            // 3. 결과에 따른 출력하기
            System.out.println("상품번호 : " + pno);
            System.out.println("닉네임 : " + productDto.getPnickname());
            System.out.println("상품명 : " + productDto.getPname());
            System.out.printf("상품가격 : %d원\n", productDto.getPprice());
            System.out.println("등록일 : " + productDto.getPdate());
            System.out.println("판매여부 : " + productDto.getPsale());
            System.out.println("[문의내역]");
            System.out.printf("%s | %s\n", inquiryDto.getIexplain(), inquiryDto.getInickname());
            System.out.println("========================================================================================================");
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end

    // 7. 등록랭킹조회 화면
    public void rankingPrint(){
        // 1. 사용자로부터 입력받기

        // 2. controller에게 전달 후, 결과 받기
        ArrayList<RankingDto> rankingDtos = productController.rankingPrint();
        // 3. 결과에 따른 출력하기
        System.out.println("================================================ 등록랭킹 ================================================");
        for ( int i = 0; i < rankingDtos.size(); i++ ){
            RankingDto rankingDto = rankingDtos.get(i);
            System.out.printf("%d등 \t %s \t 등록수 : %d\n", i+1, rankingDto.getPnickname(), rankingDto.getCount() );
        } // for end
        System.out.println("========================================================================================================");
    } // func end

    // 8. 검색 화면
    public void searchPrint(){
        try {
            // 1. 사용자로부터 입력받기
            System.out.println("========================================================================================================");
            System.out.print("검색 : ");      String keyword = scan.next();
            System.out.println("========================================================================================================");
            // 2. controller에게 전달 후, 결과 받기
            ArrayList<ProductDto> productDtos = productController.search( keyword );
            // 3. 결과에 따른 출력하기
            for ( ProductDto productDto : productDtos ){
                System.out.println("상품번호 : " + productDto.getPno());
                System.out.println("닉네임 : " + productDto.getPnickname());
                System.out.println("상품명 : " + productDto.getPname());
                System.out.println("상품설명 : " + productDto.getPexplain());
                System.out.printf("상품가격 : %d원\n", productDto.getPprice());
                System.out.println("등록일 : " + productDto.getPdate());
                System.out.println("판매여부 : " + productDto.getPsale());
                System.out.println("========================================================================================================");
            } // for end
        } catch ( InputMismatchException e ){
            System.out.println("[경고] 입력타입이 일치하지 않습니다. 다시 입력하세요.");
        } // try-catch end
    } // func end
} // class end
