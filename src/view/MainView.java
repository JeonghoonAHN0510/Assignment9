package view;

import controller.InquiryController;
import controller.ProductController;

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

                }else if ( choose == 2 ){       // 2를 선택하면

                }else if ( choose == 3 ){       // 3을 선택하면

                }else if ( choose == 4 ){       // 4를 선택하면

                }else if ( choose == 5 ){       // 5를 선택하면

                }else if ( choose == 6 ){       // 6을 선택하면

                }else if ( choose == 7 ){       // 7을 선택하면

                }else if ( choose == 8 ){       // 8을 선택하면

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


    // 2. 상품전체조회 화면


    // 3. 상품수정 화면


    // 4. 상품삭제 화면


    // 5. 문의등록 화면


    // 6. 상품상세조회 화면


    // 7. 등록랭킹조회 화면


    // 8. 검색 화면


} // class end
