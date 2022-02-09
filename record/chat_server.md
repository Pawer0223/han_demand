# 목적

- 1:1 채팅기능 구현, 채팅 데이터는 영속화 하지 않을 것.

# Reference 1 - WebFlux + SSE 프로토콜 사용

### 참조
- [링크1](https://www.youtube.com/watch?v=Oo_eHCr_HsQ&list=PL93mKxaRDidHRYNYYFr1x3mLKIx1wFeFc&index=2&ab_channel=%EB%A9%94%ED%83%80%EC%BD%94%EB%94%A9)
- [공식문서](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)

### 필요 기술
- WebFlux(with Netty), mongo db

### 장점
- 단일 스레드, 비동기 처리가 가능.
- 채팅서버 구현에 적합해보임.
  - 단일 스레드로 처리하기 때문에 컨텍스트 스위칭 비용이 발생하지 않음.

### 적용못하는 이유

- 데이터 영속화 하고싶지 않은데, Repository를 컬렉션으로 구현하면서 [비동기 처리 + pub/sub모델]로 적용해주기가 쉽지 않다...


# Reference 2 - Tomcat(동기 서버) + Web Socket

### 참조
- [링크1](https://supawer0728.github.io/2018/03/30/spring-websocket/)
- [공식문서](https://spring.io/guides/gs/messaging-stomp-websocket/)


### 필요 기술
- WebSocket
  - 양방향 통신이 가능하도록 연결을 유지한다.
- SockJS
  - websocket을 지원하지 않는 브라우저에서도 호환가능하도록 도와준다.
- STOMP
  - stomp 프로토콜을 사용해 pub/sub 모델로 구현할 수 있다. session의 관리도 직접하지 않아도 된다는 장점이 있다.

### 장점

- 멀티 쓰레드로 병렬 처리할 수 있기 때문에, 동시에 많은 요청을 처리하는데 유리하다.
  - 근데 채팅 서버에서 병렬로 이득을 취할 수 있는게 있나?

### 단점

- 동시에 여러채팅이 진행되면, 말 그대로 컨텍스트 스위칭이 자주 일어날 것 같은데..
  - 부적절하지 않을까?
  
  
# Reference 3 - Webflux + WebSocket

- [참조](https://www.baeldung.com/spring-5-reactive-websockets)



# 공부가 필요 부분

- [정리 링크](https://github.com/Pawer0223/han_demand/blob/main/record/chat_server_study.md)
