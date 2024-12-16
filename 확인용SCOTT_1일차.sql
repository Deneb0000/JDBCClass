-- SCOTT
select *
FROM dept
WHERE deptno = 50;

UPDATE deptSET dname = 'cc', loc='ph' WHERE deptno = 60

select *
from dept
Where deptno > 0; --==

SELECT grade||'���', losal, hisal, empno, ename, dname, hiredate , sal
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
--emp ���̺��� pk(empno) / �ε���
SELECT *
FROM emp
WHERE substr(empno, 0,2) = 76;  -- full ��ĵ where���� �����ϸ� �ε����� �Ȱɸ��� -> FULL 0.021��
WHERE empno = 7369; --0.009�� index (unique scan)
WHERE  deptno = 30 and sal > 1300; full ��ĵ

CREATE INDEX DS_EMP on emp (deptno, sal); --����Ű �ε��� range scan �����˻� �ð��� �� �ɸ�
--Index DS_EMP��(��) �����Ǿ����ϴ�.
ALTER
DROP INDEX DS_EMP ;
--Index DS_EMP��(��) �����Ǿ����ϴ�.

WHERE deptno = 10;  FULL
WHERE empno > 7600; range scan ������ĵ
WHERE ename = 'SMITH'; FULL ��ĵ
WHERE empno = 7369; index ��ĵ
--�ε��� �˻�
SELECT *
FROM user_indexes
WHERE table_name = 'EMP';

desc emp;

�̸�       ��?       ����           
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

--�����ȣ, �����, �μ���, �Ի�����, pay, ��� ��ȸ
--join : emp, dept, salgrade
SELECT empno, ename, dname, hiredate, sal
FROm emp e JOIN dept d ON  e. deptno=d.deptno
           JOIN salgrade s ON e.sal BETWEEN s.losal AND s. hisal;
