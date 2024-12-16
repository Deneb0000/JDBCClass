--scott
--MSSQL
create table cstVSBoard (
  seq int identity (1, 1) not null primary key clustered,   --�۹�ȣ
  writer varchar (20) not null ,    --�ۼ���
  pwd varchar (20) not null ,   --��й�ȣ
  email varchar (100) null ,    -- �̸���(�۾�����)
  title varchar (200) not null ,    --������
  writedate smalldatetime not null default (getdate()), --�ۼ���
  readed int not null default (0), --��ȸ��
  mode tinyint not null ,   
  content text null-- �۳���
)
  
  UPDATE tbl_cstVSBoard
  SET readed = 0
  WHERE seq <=150;
  
  title, writer, email, writedate, readed, content
  
   UPDATE tbl_cstVSBoard
  SET title = "aaa" and content = "bbbb"
  WHERE seq =151;
  
  commit;
  
  --����Ŭ
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

Table TBL_CSTVSBOARD��(��) �����Ǿ����ϴ�.

--���� ������
BEGIN
    FOR i IN 1..150 LOOP
        INSERT INTO tbl_cstvsboard  (seq, writer, pwd, email, title, readed, tag, content)
        VALUES (seq_tblcatVSBoard.NEXTVAL, 'ȫ�浿' || MOD(i,10), '1234', 'ȫ�浿'||MOD(i,10)||'@sist.co.kr', '����...', i,0,'����...'||i );
        END LOOP;
END;
commit;



BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '���ؿ�'
    WHERE MOD(seq, 15) IN (2);
    commit;
END;

BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '�Խ��� ����'
    WHERE MOD(seq, 15) IN (3, 5, 8);
    commit;
END;

COMMIT;
SELECT seq, title, writer, email,   writedate, readed, tag
FROM tbl_cstVSBoard
ORDER BY seq desc;

-- ���� �� ������ ��ȣ : 1
-- �� �������� ����� �Խñ� �� : 10
-- Top-N ��� ���
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

1������    start   end
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
    -- tag : 2�� ���߿��� ����
    -- tag : 1�� ������ ����
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[����] 2023�� �޸� ȸ�� ��� ���� �ȳ�', SYSDATE, 0, 2, '2023 ��ȣ�� ��� ���� �ȳ�

��������: 2023�� 12�� 8��



�ȳ��ϼ���. ���������� ��ȣ���� �� 39���� 6 ���������� �ı⿡ ���� Ư�� ���� ������ ����

��ȣ�� ���� ���� ������ �ȳ��帳�ϴ�.



���� �������� �ı� Ư�ʿ� ���� �и� ���� �Ǿ��� �޸� ȸ���� ������ �и� ���� ���� ó�� �˴ϴ�.



�� ���� ���� �ȳ�

�������� �ı� Ư�� (��ȿ�Ⱓ��) ����: ������ü�� �ǻ�� �����ϰ� 1�� ���� ���� �̿��� ���� ���

�ı� �Ǵ� ���� �и� ���� �� ��ġ�� �����ߴ� ���� ���� (�� 39���� 6���� ������ �ı⿡ ���� Ư�� ����)');

SET DEFINE OFF;

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����] ���� ���� ������ ���� �ȳ�', SYSDATE, 0, 2, '���Ե鲲�� ���������� �ۼ��� �ֽô� ����&������ ���信 �����ϰ��� �������� ���� �����ϰ� �Ǿ����ϴ�.



�� ���� �ñ�

- 2024.02.26



�� ���� ���� 

- ����&������ ���� ������ ����



[���� ��] 300��  

[���� ��] 300��/�ִ�500��

 *����/��� ��ǰ�� ������ �� ���� �ۼ��� ������ ��ǰ�� �����ϸ� ��/���鿡 ������ ����&������ ���� -> 500�� ����



 (����) ��ġ���̵� ���� �����Ͽ� ���� �ۼ��ϴ� ��� 

       -��ġ���̵� ��ǰ ������ ���� ���ε� > 500�� ����

       -���½���Ŭ�� ��ǰ ������ ���� ���ε� > 300�� ����



������ ��ǰ�� �ۼ��� ����&������ ���䰡 �ٸ� ���, ����� ����ε� ó�� �Ǵ� �� ���� ��Ź �帳�ϴ�.



�� ���� � ��å �ٷΰ��� : https://www.ohora.kr/article/faq/3/27267/page/1



�׻� ohora�� ����� �ֽô� ���Բ� �������� ����帮��, 

�����ε� �� ���� ��ǰ�� ���񽺷� ������ �� �ֵ��� �ּ��� ���ϰڽ��ϴ�.



�����մϴ�.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����] ���� ���� ������ ���� �ȳ�', SYSDATE, 0, 2, '���Ե鲲�� ���������� �ۼ��� �ֽô� ����&������ ���信 �����ϰ��� �������� ���� �����ϰ� �Ǿ����ϴ�.



�� ���� �ñ�

- 2024.02.26



�� ���� ���� 

- ����&������ ���� ������ ����



[���� ��] 300��  

[���� ��] 300��/�ִ�500��

 *����/��� ��ǰ�� ������ �� ���� �ۼ��� ������ ��ǰ�� �����ϸ� ��/���鿡 ������ ����&������ ���� -> 500�� ����



 (����) ��ġ���̵� ���� �����Ͽ� ���� �ۼ��ϴ� ��� 

       -��ġ���̵� ��ǰ ������ ���� ���ε� > 500�� ����

       -���½���Ŭ�� ��ǰ ������ ���� ���ε� > 300�� ����



������ ��ǰ�� �ۼ��� ����&������ ���䰡 �ٸ� ���, ����� ����ε� ó�� �Ǵ� �� ���� ��Ź �帳�ϴ�.



�� ���� � ��å �ٷΰ��� : https://www.ohora.kr/article/faq/3/27267/page/1



�׻� ohora�� ����� �ֽô� ���Բ� �������� ����帮��, 

�����ε� �� ���� ��ǰ�� ���񽺷� ������ �� �ֵ��� �ּ��� ���ϰڽ��ϴ�.



�����մϴ�.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����][6/17~6/28] ����ǰ ���� ���� ������ 5,000�� ���� �̺�Ʈ', SYSDATE, 0, 2, '[�̺�Ʈ ���� �� �� �о��ּ���]



- 6/17 10:00 - 6/28 10:00 �Ⱓ ���� ����Ȯ�� �� �ۼ��� ���� �ı⸦ ������� ������ ������ �̷�����ϴ�.



- ���� �ۼ� �� ������ ��ǰ�� �����ϸ�, ���� ���� 20�� �̻� & ���鿡 ������ ����&������ ���� ������� ���޵˴ϴ�. ( ��ǰ�� ���� ���� �̹����� �������� ���޵��� �ʽ��ϴ�)



- ���� �ۼ� ���信 ���� �̺�Ʈ ������ �����ϸ�, �ߺ�/���� ������ ��� ������ ���޿��� ���� �˴ϴ�.



- �� �̺�Ʈ�� ��ȣ�� ���ĸ� ȸ�� ID�� �α����ؾ� ���� �����մϴ�. ��ȸ�� �� ���̹����̷� �����Ͻ� ��� �̺�Ʈ ������ ����  �� �����ϴ�.



- �������� �� ID �� 1ȸ ���� �����մϴ�. 



- ����ǰ 8���� ��ǰ�� ���Ͽ� �̺�Ʈ ���� �����մϴ�.

(P ���庣�� ���, P ������ ���, P �ǹ� ���̺� ���, P ��� ���̽� ���, P ��� ���Ϲ��� ���, P ����  ����Ŭ   ���, P �۸��� ���� ���, P ��Ű ���)



- ���� �ۼ� �����ݰ� �̺�Ʈ �������� �ߺ� ���� ���� �ʽ��ϴ�.


- �ۼ��� �ֽ� ������ �ı�� ������ �뵵�� 2�� Ȱ��� �� �ֽ��ϴ�.



- �� �̺�Ʈ�� ��� ������ ���� ���� ����ǰų� ����� �� �ֽ��ϴ�.



- ���� � ��å�� FAQ�� �������ּ��� (FAQ - ���� � ��åurl)



[�̺�Ʈ ǰ��]

P ���庣�� ���, P ������ ���, P �ǹ� ���̺� ���, P ��� ���̽� ���, P ��� ���Ϲ��� ���, P ���� ����Ŭ   ���, P �۸��� ���� ���, P ��Ű ���



[�ۼ� �Ⱓ]

6/17 10:00 - 6/28 10:00



[������ ���� �� �Ҹ� �ȳ�]

������: 7/3 ���� 11�� ����

�̺�Ʈ ������ �Ҹ� ����: 2024.12.31');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����][6/24~7/1] ��ȣ�� ������ ���� �̺�Ʈ', SYSDATE, 0, 2, '[�̺�Ʈ ���� �� �� �о��ּ���]

 

- ���θ�� �Ⱓ: 2024.06.24 10:00 - 07.01 10:00



- ����ǰ�� ���� ���� ���� �� �� ���� �ݾ��� 6���� �̻��̾�� �����˴ϴ�.



- �̺�Ʈ �Ⱓ �� ���� �Ϸ� �ǿ� ���� ������ ����˴ϴ�.



- ���� �� �ֹ� ���������� ����ǰ�� ���õǾ����� Ȯ���� �ּž� �ϸ�, ���� ���� �� ���޵��� �ʽ��ϴ�.



- ��ǰ ��ǰ ��, �����Ͻ� ��ǰ�� �Բ� ����ǰ�� �����ּž� ���� ȯ�� ó���˴ϴ�.

- ��ȯ/��ǰ/ȯ�ҷ� ���� �̺�Ʈ ���� ���ؿ� �̴��� ���, ����ǰ�� ������ ����ǰ�� ��ǰ ��ġ �Ѽ�(����, �ļ� ��) ���� �����ּž� ���� ó���˴ϴ�.



- ����ǰ�� 600�� ���� �������� ���� ���� �� �� �ֽ��ϴ�.



- �� �̺�Ʈ�� ����� �������� ���� ���� ���� ���� ���� �� ����� �� �ֽ��ϴ�.



- ���ĸ� ȸ�� �α����� ���� �ʰ� ���̹����̷� �����Ͻ� ���, ���� ������ ���� �� �����ϴ�. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[����]�������� ó����ħ �� ��Ź��ü �������', SYSDATE, 0, 2, '2024 �������� ó����ħ ����



�ȳ��ϼ���

��ȣ�� �������� ó�� ��ħ �� ��Ź ��ü�� �Ʒ��� ���� ����Ǿ� �ȳ� �帳�ϴ�.



1.���� ����

: �������� ó�� ��ħ ��6��(���� ���� ��� ��Ź) �� ��Ź ��ü�� �űԷ� �߰��Ͽ����ϴ�.





2.���� �� ��������

- �������� : 2024�� 06�� 26��

- �������� : 2024�� 07�� 03��');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[7/8~7/15] ���� �ݾ״뺰 ������ �̺�Ʈ', SYSDATE, 0, 2, '[�̺�Ʈ ���� �� �� �о��ּ���]



- ���� ������: 2024.07.08 10:00 - 07.15 10:00

- ���� ��� ���� ���űݾ��� �� ���� �ݾ� �����Դϴ�.

- Ÿ ���� ������ �ߺ����� ����� �� �����ϴ�.

- �� ���� �� ��� ���� ������ �����մϴ�. ���� ���λ��� ���� ��Ź�帳�ϴ�.

- �̺�Ʈ �Ⱓ ���� ��, ȯ��/��ǰ ������ ���� �籸�� �� ���� ������ ���� ������ �� �����ϴ�.





[���� ���λ���]

*�� ���� �ݾ� �����Դϴ�

(1) 3���� �̻� 5% �߰� ����

(2) 5���� �̻� 10% �߰� ����

(3) 7���� �̻� 15% �߰� ����





[���� ���� ���]

(1) ����������>���� �����Կ��� �ڵ� �߱޵� ������ Ȯ���ϼ���

(2) ���� �ܰ迡�� �������� ��ȸ/���� �κп��� ������ �����ϼž� ������ ����˴ϴ�. 

');


INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, null, '[����]ģ�� ��õ ���θ�� ���� �ȳ�', SYSDATE, 0, 2, '�ȳ��ϼ��� ��ȣ���Դϴ�.

���� ���Ӱ� �پ��� �̺�Ʈ�� ã�ƺ˱� ���� ģ�� ��õ ���θ���� ����˴ϴ�.



���� �Ͻ�  : 2024�� 08�� 05��



���θ�� ���� �� ģ�� ��õ�� �̺�Ʈ�� �����ֽ� ���Ե鲲�� ���������� �������� ���� �� �����Դϴ�.

(8�� 5�� ���� �� ���Űǿ� ���� ��õ�� �������� ������ �����ϰ� ����õ�� �ֹ��Ϸ� ���� 20�� �̳� �������� ���޵� �����Դϴ�.)



�����մϴ�.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����]�߼� ���� ��� �� �� ��� ���� �ȳ�', SYSDATE, 0, 1, '�ȳ��ϼ��� ����!

�߼� ���� �Ⱓ ���/�ֹ�/��� ���� �ȳ� �帳�ϴ�.

 

[�������]

 

1. 9/11(��) 13�� 59�б��� ���� �Ϸ�� �ֹ� ��: �߼� ���� �� ��� ����

 

2. 9/11(��) 14�� 00�� ���� �ֹ���: 9/19(��)���� ���� �߼�

 

3. ���ֵ� �� ���� �갣 ����: 9/9(��) 13�� 59�� ���� ���� �Ϸ�Ǳ��� ��� ����

>�߼����ޱⰣ ���ķ� ���� ������ ��۰��ɽ����� ��Ȳ�� ���� ����� �� �ֽ��ϴ�.

 

4. ���� �Ϸ� �� �����̸�, ������� �ֹ� �� ���Աݿ� ���� �ֹ����� ������� �ʽ��ϴ�.

 

5. ���� �� ����� ���۵Ǿ �ù�� ���� �� ����,������ ���� ������ ���� ��� ���� ���� �����Ͻ� �� �ֽ��ϴ�.



 

[�� ��� � �ȳ�]



1. �޹�: 9/16(��) ~ 9/18(��)����

2. 1:1 ���� �Խ����� �̿��� �ֽø� 9/19(��)���� ���������� �亯 �帱 �� �ֵ��� �ϰڽ��ϴ�.

 

 

��̰� �ǰ��� �߼� ���� ��������.

�����մϴ�. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����][10/7~10/14] ��ȣ�� ���� ���� �̺�Ʈ', SYSDATE, 0, 2, '[�̺�Ʈ ���� �� �� �о��ּ���]

 

- ���θ�� �Ⱓ: 2024.10.07 10:00 ~ 2024.10.14 10:00



- ����ǰ�� ���� ���� ���� �� �� ���� �ݾ��� 2���� �̻��̾�� �����˴ϴ�.



- �̺�Ʈ �Ⱓ �� ���� �Ϸ� �ǿ� ���� ������ ����˴ϴ�.



- ���� �� �ֹ� ���������� ����ǰ�� ���õǾ����� Ȯ���� �ּž� �ϸ�, ���� ���� �� ���޵��� �ʽ��ϴ�.



- ��ǰ ��ǰ ��, �����Ͻ� ��ǰ�� �Բ� ����ǰ�� �����ּž� ���� ȯ�� ó���˴ϴ�.

- ��ȯ/��ǰ/ȯ�ҷ� ���� �̺�Ʈ ���� ���ؿ� �̴��� ���, ����ǰ�� ������ ����ǰ�� ��ǰ ��ġ �Ѽ�(����, �ļ� ��) ���� �����ּž� ���� ó���˴ϴ�.



- �� �̺�Ʈ�� ����� �������� ���� ���� ���� ���� ���� �� ����� �� �ֽ��ϴ�.



- ���ĸ� ȸ�� �α����� ���� �ʰ� ���̹����̷� �����Ͻ� ���, ���� ������ ���� �� �����ϴ�');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', '[����][10/14~10/28] ��ȣ�� ������ ���� �̺�Ʈ', SYSDATE, 0, 2, '[�̺�Ʈ ���� �� �� �о��ּ���]

 

- ���θ�� �Ⱓ: 2024.10.14 10:00 - 10.28 10:00



- ����ǰ�� ���� ���� ���� �� �� ���� �ݾ��� 8���� �̻��̾�� �����˴ϴ�.



- �̺�Ʈ �Ⱓ �� ���� �Ϸ� �ǿ� ���� ������ ����˴ϴ�.



- ���� �� �ֹ� ���������� ����ǰ�� ���õǾ����� Ȯ���� �ּž� �ϸ�, ���� ���� �� ���޵��� �ʽ��ϴ�.



- ��ǰ ��ǰ ��, �����Ͻ� ��ǰ�� �Բ� ����ǰ�� �����ּž� ���� ȯ�� ó���˴ϴ�.

- ��ȯ/��ǰ/ȯ�ҷ� ���� �̺�Ʈ ���� ���ؿ� �̴��� ���, ����ǰ�� ������ ����ǰ�� ��ǰ ��ġ �Ѽ�(����, �ļ� ��) ���� �����ּž� ���� ó���˴ϴ�.



- ����ǰ�� 200�� ���� �������� ���� ���� �� �� �ֽ��ϴ�.



- �� �̺�Ʈ�� ����� �������� ���� ���� ���� ���� ���� �� ����� �� �ֽ��ϴ�.



- ���ĸ� ȸ�� �α����� ���� �ʰ� ���̹����̷� �����Ͻ� ���, ���� ������ ���� �� �����ϴ�. ');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, 'ohora', 'ohora membership ��ȣ�� ����� �ȳ�', SYSDATE, 0, 2, '�̰��� �������� ���� �����Դϴ�.');

INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '������', '�������� ���� ����', SYSDATE, 0, 1, '�̰��� �������� ���� �����Դϴ�.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '������', '�������� ���� ����', SYSDATE, 0, 1, '�̰��� �������� ���� �����Դϴ�.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '������', '�������� ���� ����', SYSDATE, 0, 1, '�̰��� �������� ���� �����Դϴ�.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '������', '�������� ���� ����', SYSDATE, 0, 1, '�̰��� �������� ���� �����Դϴ�.');
INSERT INTO ohora_notice_Board (seq, writer, title, writedate, readed, tag, content) 
VALUES (ohora_notice_seq.NEXTVAL, '������', '�������� ���� ����', SYSDATE, 0, 1, '�̰��� �������� ���� �����Դϴ�.');

