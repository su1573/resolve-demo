DROP table GZB;
create table GZB
(
  id         VARCHAR2(20) not null,
  fbjsj      FLOAT,
  fbjsj_gzzz FLOAT,
  fbjsj_sz   FLOAT,
  fcb_jz_bl  VARCHAR2(255 CHAR),
  fcur_jy_fy FLOAT,
  fdate      TIMESTAMP(6),
  fgz_zz     FLOAT,
  fhqbz      VARCHAR2(255 CHAR),
  fhqjg      FLOAT,
  fkmbm      VARCHAR2(255 CHAR),
  fkmmc      VARCHAR2(255 CHAR),
  fqyxx      VARCHAR2(255 CHAR),
  fsjsj      FLOAT,
  fsjsj_gzzz FLOAT,
  fsjsj_sz   FLOAT,
  fsz_jz_bl  VARCHAR2(255 CHAR),
  ftpxx      VARCHAR2(255 CHAR),
  fzqcb      FLOAT,
  fzqsl      FLOAT,
  fzqsz      FLOAT,
  serial_no  VARCHAR2(255 CHAR)
);

DROP table JxlTable;
create table JXLTABLE
(
  id        VARCHAR2(20) not null,
  serial_no VARCHAR2(100),
  cloum0    VARCHAR2(100),
  cloum1    VARCHAR2(100),
  cloum2    VARCHAR2(100),
  cloum3    VARCHAR2(100),
  cloum4    VARCHAR2(100),
  cloum5    VARCHAR2(100),
  cloum6    VARCHAR2(100),
  cloum7    VARCHAR2(100),
  cloum8    VARCHAR2(100),
  cloum9    VARCHAR2(100),
  cloum10   VARCHAR2(100),
  cloum11   VARCHAR2(100)
);

DROP table FICRM_SYS_MAXNO;
create table FICRM_SYS_MAXNO
(
  notype         VARCHAR2(30) not null,
  nolimit        VARCHAR2(30) not null,
  maxno          INTEGER not null,
  createoperator VARCHAR2(30) not null,
  createoptdate  DATE not null,
  createopttime  VARCHAR2(8) not null,
  lastoperator   VARCHAR2(30) not null,
  lastoptdate    DATE not null,
  lastopttime    VARCHAR2(8) not null
)
;
alter table FICRM_SYS_MAXNO
  add constraint PK_FICRM_SYS_MAXNO primary key (NOTYPE, NOLIMIT);
  
  
  
create or replace function createmaxno(v_notype varchar2, v_nolimit varchar2,v_pad varchar2,v_len integer)
  return varchar2 is
	PRAGMA AUTONOMOUS_TRANSACTION;
  v_maxno varchar2(120);
  v_val    number := 0;
	v_suffix varchar2(100);
	v_limit varchar2(100);
begin
	v_suffix := v_nolimit;
	v_limit := v_nolimit;
	if v_nolimit is null then 
		v_limit := 'SN';
		v_suffix := '';
	end if;
	
  select max(sm.maxno) into v_val from ficrm_sys_maxno sm where sm.notype=upper(v_notype) and sm.nolimit=v_limit;
  if v_val is null then
		insert into ficrm_sys_maxno(NOTYPE,NOLIMIT,MAXNO,CREATEOPERATOR,CREATEOPTDATE,CREATEOPTTIME,LASTOPERATOR,LASTOPTDATE,LASTOPTTIME) 
		values (upper(v_notype),v_limit,1,'admin',to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_char(sysdate,'hh24-mi-ss'),'admin',to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_char(sysdate,'hh24-mi-ss'));
    commit;
		v_val := '1';
	else
		v_val := v_val+1;
		update ficrm_sys_maxno set MAXNO=v_val where NOTYPE=upper(v_notype) and NOLIMIT=v_limit;
    commit;
	end if;
	
	if v_suffix is null then
			v_maxno := lpad(v_val,v_len,v_pad);
		else 
		  v_maxno := v_suffix||lpad(v_val,v_len-length(v_suffix),v_pad);
		end if;
  return(v_maxno);
Exception
  when NO_DATA_FOUND THEN
    dbms_output.put_line('获取最大号码失败!');
end createmaxno;

 
  