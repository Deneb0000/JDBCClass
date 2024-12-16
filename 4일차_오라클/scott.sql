--scott
--MSSQL
create table cstVSBoard (
  seq int identity (1, 1) not null primary key clustered,   --글번호
  writer varchar (20) not null ,    --작성자
  pwd varchar (20) not null ,   --비밀번호
  email varchar (100) null ,    -- 이메일(글쓴이의)
  title varchar (200) not null ,    --글제목
  writedate smalldatetime not null default (getdate()), --작성일
  readed int not null default (0), --조회수
  mode tinyint not null ,   
  content text null-- 글내용
)
  
  UPDATE tbl_cstVSBoard
  SET readed = 0
  WHERE seq <=150;
  
  title, writer, email, writedate, readed, content
  
   UPDATE tbl_cstVSBoard
  SET title = "aaa" and content = "bbbb"
  WHERE seq =151;
  
  commit;
  
  --오라클
CREATE SEQUENCE seq_tblcatVSBoard
NOCACHE;

CREATE TAbLE tbl_cstVSBoard (

  seq NUMBER NOT NULL PRIMARY KEY,
  writer VARCHAR2 (20) NOT NULL ,
  pwd VARCHAR2 (20) NOT NULL ,
  email VARCHAR2 (100),
  title VARCHAR2 (200) NOT NULL ,
  writedate DATE DEFAULT SYSDATE ,
  readed NUMBER DEFAULT (0),
  tag NUMBER(1)NOT NULL ,
  content CLOB
);

Table TBL_CSTVSBOARD이(가) 생성되었습니다.

--더미 데이터
BEGIN
    FOR i IN 1..150 LOOP
        INSERT INTO tbl_cstvsboard  (seq, writer, pwd, email, title, readed, tag, content)
        VALUES (seq_tblcatVSBoard.NEXTVAL, '홍길동' || MOD(i,10), '1234', '홍길동'||MOD(i,10)||'@sist.co.kr', '더미...', i,0,'더미...'||i );
        END LOOP;
END;
commit;



BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '박준용'
    WHERE MOD(seq, 15) IN (2);
    commit;
END;

BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '게시판 구현'
    WHERE MOD(seq, 15) IN (3, 5, 8);
    commit;
END;

COMMIT;
SELECT seq, title, writer, email,   writedate, readed, tag
FROM tbl_cstVSBoard
ORDER BY seq desc;

-- 현재 ㅍ ㅔ이지 번호 : 1
-- 한 페이지에 출력할 게시글 수 : 10
-- Top-N 방식 사용
SELECT *
FROM (
SELECT ROWNUM no, t.* 
FROM (SELECT seq, title, writer, email,   writedate, readed, tag
FROM tbl_cstVSBoard
ORDER BY seq desc
)t
)b
WHERE no BETWEEN ? AND ?;
WHERE no BETWEEN 11 AND 20;
WHERE no BETWEEN 1 AND 10;

1페이지    start   end
            1       10
            11       20

--------------------------------------------------------------------------------

CREATE TAbLE ohora_notice_Board (

  seq NUMBER NOT NULL PRIMARY KEY,
  writer VARCHAR2 (20),
  title VARCHAR2 (200),
  writedate DATE DEFAULT SYSDATE ,
  readed NUMBER DEFAULT (0),
  tag NUMBER(1)NOT NULL ,
  content CLOB
);

CREATE SEQUENCE ohora_notice_seq 
    START WITH 1 
    INCREMENT BY 1 
    NOCACHE;
    -- tag : 2는 안중요한 공지
    -- tag : 1은 고정할 공지
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[공지] 2023년 휴면 회원 약관 개정 안내', SYSDATE, 0, 2, '2023 오호라 약관 개정 안내

시행일자: 2023년 12월 8일



안녕하세요. 「개인정보 보호법」 제 39조의 6 개인정보의 파기에 대한 특례 조항 삭제에 따른

오호라 서비스 변경 사항을 안내드립니다.



기존 개인정보 파기 특례에 따라 분리 보관 되었던 휴면 회원의 정보가 분리 보관 해제 처리 됩니다.



■ 법령 변경 안내

개인정보 파기 특례 (유효기간제) 정비: 정보주체의 의사와 무관하게 1년 동안 서비스 이용이 없는 경우

파기 또는 별도 분리 보관 등 조치를 강제했던 규정 삭제 (제 39조의 6개인 정보의 파기에 대한 특례 삭제)');

SET DEFINE OFF;

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[공지] 포토 리뷰 적립금 변경 안내', SYSDATE, 0, 2, '고객님들께서 정성스럽게 작성해 주시는 포토&동영상 리뷰에 보답하고자 적립금을 상향 조정하게 되었습니다.



▶ 변경 시기

- 2024.02.26



▶ 변경 내용 

- 포토&동영상 리뷰 적립금 변경



[변경 전] 300원  

[변경 후] 300원/최대500원

 *네일/페디 상품을 구매한 후 리뷰 작성시 구매한 제품과 동일하며 손/발톱에 부착한 포토&동영상 리뷰 -> 500원 지급



 (예시) 피치에이드 네일 선택하여 리뷰 작성하는 경우 

       -피치에이드 제품 부착한 사진 업로드 > 500원 지급

       -선셋스파클링 제품 부착한 사진 업로드 > 300원 지급



구매한 제품과 작성한 포토&동영상 리뷰가 다를 경우, 리뷰는 블라인드 처리 되는 점 참고 부탁 드립니다.



※ 리뷰 운영 정책 바로가기 : https://www.ohora.kr/article/faq/3/27267/page/1



항상 ohora를 사랑해 주시는 고객님께 진심으로 감사드리며, 

앞으로도 더 나은 제품과 서비스로 보답할 수 있도록 최선을 다하겠습니다.



감사합니다.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[공지] 포토 리뷰 적립금 변경 안내', SYSDATE, 0, 2, '고객님들께서 정성스럽게 작성해 주시는 포토&동영상 리뷰에 보답하고자 적립금을 상향 조정하게 되었습니다.



▶ 변경 시기

- 2024.02.26



▶ 변경 내용 

- 포토&동영상 리뷰 적립금 변경



[변경 전] 300원  

[변경 후] 300원/최대500원

 *네일/페디 상품을 구매한 후 리뷰 작성시 구매한 제품과 동일하며 손/발톱에 부착한 포토&동영상 리뷰 -> 500원 지급



 (예시) 피치에이드 네일 선택하여 리뷰 작성하는 경우 

       -피치에이드 제품 부착한 사진 업로드 > 500원 지급

       -선셋스파클링 제품 부착한 사진 업로드 > 300원 지급



구매한 제품과 작성한 포토&동영상 리뷰가 다를 경우, 리뷰는 블라인드 처리 되는 점 참고 부탁 드립니다.



※ 리뷰 운영 정책 바로가기 : https://www.ohora.kr/article/faq/3/27267/page/1



항상 ohora를 사랑해 주시는 고객님께 진심으로 감사드리며, 

앞으로도 더 나은 제품과 서비스로 보답할 수 있도록 최선을 다하겠습니다.



감사합니다.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[종료][6/17~6/28] 신제품 포토 리뷰 적립금 5,000점 지급 이벤트', SYSDATE, 0, 2, '[이벤트 참여 전 꼭 읽어주세요]



- 6/17 10:00 - 6/28 10:00 기간 내에 구매확정 후 작성한 포토 후기를 대상으로 적립금 지급이 이루어집니다.



- 리뷰 작성 시 구매한 제품과 동일하며, 띄어쓰기 포함 20자 이상 & 발톱에 부착한 포토&동영상 리뷰 대상으로 지급됩니다. ( 제품에 맞지 않은 이미지는 적립금이 지급되지 않습니다)



- 최초 작성 리뷰에 한해 이벤트 참여가 가능하며, 중복/수정 리뷰의 경우 적립금 지급에서 제외 됩니다.



- 본 이벤트는 오호라 공식몰 회원 ID로 로그인해야 참여 가능합니다. 비회원 및 네이버페이로 결제하실 경우 이벤트 혜택을 받을  수 없습니다.



- 적립금은 한 ID 당 1회 지급 가능합니다. 



- 신제품 8가지 제품에 한하여 이벤트 참여 가능합니다.

(P 레드베리 페디, P 스위츠 페디, P 실버 웨이브 페디, P 블루 아이스 페디, P 모브 유니버스 페디, P 러브  스파클   페디, P 글리터 샤워 페디, P 실키 페디)



- 리뷰 작성 적립금과 이벤트 적립금은 중복 지급 되지 않습니다.


- 작성해 주신 소중한 후기는 마케팅 용도로 2차 활용될 수 있습니다.



- 본 이벤트는 당사 사정에 따라 조기 종료되거나 변경될 수 있습니다.



- 리뷰 운영 정책은 FAQ를 참고해주세요 (FAQ - 리뷰 운영 정책url)



[이벤트 품목]

P 레드베리 페디, P 스위츠 페디, P 실버 웨이브 페디, P 블루 아이스 페디, P 모브 유니버스 페디, P 러브 스파클   페디, P 글리터 샤워 페디, P 실키 페디



[작성 기간]

6/17 10:00 - 6/28 10:00



[적립금 지급 및 소멸 안내]

지급일: 7/3 오전 11시 이후

이벤트 적립금 소멸 일자: 2024.12.31');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[종료][6/24~7/1] 오호라 보관함 증정 이벤트', SYSDATE, 0, 2, '[이벤트 참여 전 꼭 읽어주세요]

 

- 프로모션 기간: 2024.06.24 10:00 - 07.01 10:00



- 사은품은 쿠폰 할인 적용 후 실 결제 금액이 6만원 이상이어야 증정됩니다.



- 이벤트 기간 내 결제 완료 건에 한해 증정이 적용됩니다.



- 결제 전 주문 페이지에서 사은품이 선택되었는지 확인해 주셔야 하며, 선택 해제 시 지급되지 않습니다.



- 제품 반품 시, 구매하신 상품과 함께 사은품도 보내주셔야 정상 환불 처리됩니다.

- 교환/반품/환불로 인해 이벤트 혜택 기준에 미달한 경우, 사은품을 포함한 구성품을 상품 가치 훼손(개봉, 파손 등) 없이 보내주셔야 정상 처리됩니다.



- 증정품은 600개 한정 수량으로 조기 소진 될 수 있습니다.



- 본 이벤트는 당사의 사정으로 인해 사전 예고 없이 변경 및 종료될 수 있습니다.



- 공식몰 회원 로그인을 하지 않고 네이버페이로 결제하실 경우, 증정 혜택을 받을 수 없습니다. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[공지]개인정보 처리방침 내 수탁업체 변경사항', SYSDATE, 0, 2, '2024 개인정보 처리방침 변경



안녕하세요

오호라 개인정보 처리 방침 내 수탁 업체가 아래와 같이 변경되어 안내 드립니다.



1.변경 내용

: 개인정보 처리 방침 제6조(개인 정보 취급 위탁) 내 수탁 업체를 신규로 추가하였습니다.





2.공고 및 시행일자

- 공고일자 : 2024년 06월 26일

- 시행일자 : 2024년 07월 03일');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[7/8~7/15] 구매 금액대별 쿠폰팩 이벤트', SYSDATE, 0, 2, '[이벤트 참여 전 꼭 읽어주세요]



- 쿠폰 사용기한: 2024.07.08 10:00 - 07.15 10:00

- 쿠폰 사용 가능 구매금액은 실 결제 금액 기준입니다.

- 타 할인 쿠폰과 중복으로 사용할 수 없습니다.

- 각 쿠폰 별 사용 가능 기준이 상이합니다. 쿠폰 세부사항 참고 부탁드립니다.

- 이벤트 기간 종료 후, 환불/반품 등으로 인한 재구매 시 동일 혜택을 적용 받으실 수 없습니다.





[쿠폰 세부사항]

*실 결제 금액 기준입니다

(1) 3만원 이상 5% 추가 할인

(2) 5만원 이상 10% 추가 할인

(3) 7만원 이상 15% 추가 할인





[쿠폰 적용 방법]

(1) 마이페이지>마이 쿠폰함에서 자동 발급된 쿠폰을 확인하세요

(2) 결제 단계에서 할인쿠폰 조회/적용 부분에서 쿠폰을 선택하셔야 할인이 적용됩니다. 

');


INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[공지]친구 추천 프로모션 종료 안내', SYSDATE, 0, 2, '안녕하세요 오호라입니다.

더욱 새롭고 다양한 이벤트를 찾아뵙기 위해 친구 추천 프로모션이 종료됩니다.



종료 일시  : 2024년 08월 05일



프로모션 종료 전 친구 추천을 이벤트에 참해주신 고객님들께는 정상적으로 적립금이 지급 될 예정입니다.

(8월 5일 이전 기 구매건에 대해 추천인 적립금은 기존과 동일하게 피추천인 주문일로 부터 20일 이내 적립금이 지급될 예정입니다.)



감사합니다.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[공지]추석 연휴 배송 및 고객 상담 마감 안내', SYSDATE, 0, 1, '안녕하세요 고객님!

추석 연휴 기간 배송/주문/상담 마감 안내 드립니다.

 

[배송일정]

 

1. 9/11(수) 13시 59분까지 결제 완료된 주문 건: 추석 연휴 전 배송 가능

 

2. 9/11(수) 14시 00분 이후 주문건: 9/19(목)부터 순차 발송

 

3. 제주도 및 도서 산간 지역: 9/9(월) 13시 59분 이전 결제 완료건까지 배송 가능

>추석연휴기간 전후로 물량 증가로 배송가능시점이 상황에 따라 변경될 수 있습니다.

 

4. 결제 완료 건 기준이며, 가상계좌 주문 시 미입금에 대한 주문건은 적용되지 않습니다.

 

5. 연휴 전 배송이 시작되어도 택배사 사정 및 물량,날씨에 따라 지연이 있을 경우 연휴 이후 수령하실 수 있습니다.



 

[고객 상담 운영 안내]



1. 휴무: 9/16(월) ~ 9/18(수)까지

2. 1:1 문의 게시판을 이용해 주시면 9/19(목)부터 순차적으로 답변 드릴 수 있도록 하겠습니다.

 

 

즐겁고 건강한 추석 연휴 보내세요.

감사합니다. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[종료][10/7~10/14] 오호라 팝젤 증정 이벤트', SYSDATE, 0, 2, '[이벤트 참여 전 꼭 읽어주세요]

 

- 프로모션 기간: 2024.10.07 10:00 ~ 2024.10.14 10:00



- 사은품은 쿠폰 할인 적용 후 실 결제 금액이 2만원 이상이어야 증정됩니다.



- 이벤트 기간 내 결제 완료 건에 한해 증정이 적용됩니다.



- 결제 전 주문 페이지에서 사은품이 선택되었는지 확인해 주셔야 하며, 선택 해제 시 지급되지 않습니다.



- 제품 반품 시, 구매하신 상품과 함께 사은품도 보내주셔야 정상 환불 처리됩니다.

- 교환/반품/환불로 인해 이벤트 혜택 기준에 미달한 경우, 사은품을 포함한 구성품을 상품 가치 훼손(개봉, 파손 등) 없이 보내주셔야 정상 처리됩니다.



- 본 이벤트는 당사의 사정으로 인해 사전 예고 없이 변경 및 종료될 수 있습니다.



- 공식몰 회원 로그인을 하지 않고 네이버페이로 결제하실 경우, 증정 혜택을 받을 수 없습니다');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[종료][10/14~10/28] 오호라 보관함 증정 이벤트', SYSDATE, 0, 2, '[이벤트 참여 전 꼭 읽어주세요]

 

- 프로모션 기간: 2024.10.14 10:00 - 10.28 10:00



- 사은품은 쿠폰 할인 적용 후 실 결제 금액이 8만원 이상이어야 증정됩니다.



- 이벤트 기간 내 결제 완료 건에 한해 증정이 적용됩니다.



- 결제 전 주문 페이지에서 사은품이 선택되었는지 확인해 주셔야 하며, 선택 해제 시 지급되지 않습니다.



- 제품 반품 시, 구매하신 상품과 함께 사은품도 보내주셔야 정상 환불 처리됩니다.

- 교환/반품/환불로 인해 이벤트 혜택 기준에 미달한 경우, 사은품을 포함한 구성품을 상품 가치 훼손(개봉, 파손 등) 없이 보내주셔야 정상 처리됩니다.



- 증정품은 200개 한정 수량으로 조기 소진 될 수 있습니다.



- 본 이벤트는 당사의 사정으로 인해 사전 예고 없이 변경 및 종료될 수 있습니다.



- 공식몰 회원 로그인을 하지 않고 네이버페이로 결제하실 경우, 증정 혜택을 받을 수 없습니다. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', 'ohora membership 오호라 멤버쉽 안내', SYSDATE, 0, 2, '이것은 공지사항 본문 내용입니다.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '관리자', '공지사항 제목 예시', SYSDATE, 0, 1, '이것은 공지사항 본문 내용입니다.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '관리자', '공지사항 제목 예시', SYSDATE, 0, 1, '이것은 공지사항 본문 내용입니다.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '관리자', '공지사항 제목 예시', SYSDATE, 0, 1, '이것은 공지사항 본문 내용입니다.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '관리자', '공지사항 제목 예시', SYSDATE, 0, 1, '이것은 공지사항 본문 내용입니다.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '관리자', '공지사항 제목 예시', SYSDATE, 0, 1, '이것은 공지사항 본문 내용입니다.');

