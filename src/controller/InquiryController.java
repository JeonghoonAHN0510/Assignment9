package controller;

import model.dao.InquiryDao;

public class InquiryController {
    // * 싱글톤 구성
    private InquiryController(){}
    private static final InquiryController instance = new InquiryController();
    public static InquiryController getInstance() {
        return instance;
    } // func end

    // * InquiryDao 싱글톤 가져오기
    private InquiryDao inquiryDao = InquiryDao.getInstance();

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
