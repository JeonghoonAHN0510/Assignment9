import controller.ProductController;
import view.MainView;

public class AppStart {
    public static void main(String[] args) {

        // [ VIEW없이 테스트 ]
        ProductController.getInstance().productRegis( "테스트", "테스트 상품", "테스트 상품입니다", 100000, "1234" );


        // 메인화면 호출
        MainView.getInstance().main();

    } // main end
} // class end
