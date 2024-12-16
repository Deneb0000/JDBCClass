-- SCOTT
select *
FROM dept
WHERE deptno = 50;

UPDATE deptSET dname = 'cc', loc='ph' WHERE deptno = 60

select *
from dept
Where deptno > 0; --==

SELECT grade||'등급', losal, hisal, empno, ename, dname, hiredate , sal
			 FROM emp e JOIN dept d ON e.deptno = d.deptno 
			           JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal 
                       ORder by grade, sal;

SELECT grade, losal, hisal, count(ename)
FROM emp e JOIN dept d ON e.deptno = d.deptno 
           JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal 
GROUP BY grade, losal, hisal
ORder by grade, losal, hisal;
                       
SELECT d.deptno, job, e.empno, ename, sal  
FROM emp e JOIN dept d ON e.deptno = d.deptno 
            JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal 
WHERE grade = 2;

select *
from emp

select *
from salgrade;


SELECT empno, ename, dname, hiredate , sal+NVL(comm,0) pay, grade  
			 FROM emp e JOIN dept d ON e.deptno = d.deptno 
			           JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal ;

select rowid, emp.*
from emp;
--emp 테이블이 pk(empno) / 인덱스
SELECT *
FROM emp
WHERE substr(empno, 0,2) = 76;  -- full 스캔 where문을 가공하면 인덱스가 안걸린다 -> FULL 0.021초
WHERE empno = 7369; --0.009초 index (unique scan)
WHERE  deptno = 30 and sal > 1300; full 스캔

CREATE INDEX DS_EMP on emp (deptno, sal); --복합키 인덱스 range scan 범위검색 시간은 더 걸림
--Index DS_EMP이(가) 생성되었습니다.
ALTER
DROP INDEX DS_EMP ;
--Index DS_EMP이(가) 삭제되었습니다.

WHERE deptno = 10;  FULL
WHERE empno > 7600; range scan 범위스캔
WHERE ename = 'SMITH'; FULL 스캔
WHERE empno = 7369; index 스캔
--인덱스 검색
SELECT *
FROM user_indexes
WHERE table_name = 'EMP';

desc emp;

이름       널?       유형           
-------- -------- ------------ 
EMPNO    NOT NULL NUMBER(4)    
ENAME             VARCHAR2(10) 
JOB               VARCHAR2(9)  
MGR               NUMBER(4)    
HIREDATE          DATE         
SAL               NUMBER(7,2)  
COMM              NUMBER(7,2)  
DEPTNO            NUMBER(2)

Select emp.* , TO_char(hiredate, 'yyyy-MM-dd')
From emp; 

--사원번호, 사원명, 부서명, 입사일자, pay, 등급 조회
--join : emp, dept, salgrade
SELECT empno, ename, dname, hiredate, sal
FROm emp e JOIN dept d ON  e. deptno=d.deptno
           JOIN salgrade s ON e.sal BETWEEN s.losal AND s. hisal;
