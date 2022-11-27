
// voter 중에서 vote 없는 가짜 데이터들 삭제할떄
delete from voter where vote_id not in (select id from vote);

// picture 중에서 vote에 없는 가짜 데이터들 삭제할때
delete from picture where id not in (select id from vote);