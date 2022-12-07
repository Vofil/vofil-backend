ALTER TABLE Voter
ADD FOREIGN KEY (vote_id) REFERENCES Vote (id) on update cascade on delete cascade;

ALTER TABLE Picture
ADD FOREIGN KEY (id) REFERENCES Vote (id) on update cascade on delete cascade;


// **** 실수로 update delete 안 썼을 경우
1. 아래 sql 문장을 돌린다
select * from information_schema.table_constraints;

2. 그의 결과값으로 foreign key 로 되어 있는 잘못된 제약들의 이름을 파악해준다 (예시 : CONSTRAINT_4E1)
여러개 있을 수도 있다. 그것들 다 기록 (table 명과 같이 기록해야함)

3.그 제약들 다 삭제

alter table [테이블명] drop constraint [확인한 제약조건명];

예시) alter table Voter drop constraint CONSTRAINT_4E1;
// 아마도 2개 해줘야할거임

4. 다시 맨위의 저 2 문장을 실행해주면 된다 (on update, on delete 붙어있는 문장을 말한다)

