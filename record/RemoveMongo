# MongoDB 제거하기

# 목적
- 데이터베이스 경량화를 위해, 실시간 채팅을 컬렉션에 저장하고 싶음
- 기존 소스코드의 MongoDB를 통해 데이터를 받아오는 코드를 제거하자.
  
# 문제
- @Tailable기능을 구현해야 함.
  - [Tailable 참조](https://dalsacoo-log.tistory.com/entry/Spring-Data-MongoDB-Tailable-Cursors)
  - 무한 스트림을 만들고, client의 subscribe에 맞게 올바른 데이터만 출력해주어야 함.
  
# 시도
- 단순히 `Map<Integer, List..>` 형태로 add하고 list를 Flux로 바꿔서 구현.
  - 추가 된 데이터가 아닌, 계속 새로운 데이터를 조회하여 화면에 출력.
  
# 결론
- 아 모르겠다..... 안되네..
- 시간이 너무지연되서 일단 패스.
