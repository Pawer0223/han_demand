# Contents
- [WebSocket vs SSE](https://github.com/Pawer0223/han_demand/blob/main/record/chat_server_study.md#WebSocket-vs-SSE)
- [멀티 스레드 동기 vs 싱글 스레드 비동기](https://github.com/Pawer0223/han_demand/blob/main/record/chat_server_study.md#멀티-스레드-동기-vs-싱글-스레드-비동기)
- [pub/sub 모델](https://github.com/Pawer0223/han_demand/blob/main/record/chat_server_study.md#pub/sub-모델)
- [WebFlux](https://github.com/Pawer0223/han_demand/blob/main/record/chat_server_study.md#WebFlux)

## WebSocket vs SSE

[참조](https://www.onlyfullstack.com/polling-vs-server-sent-events-vs-websocket/)

### Server Sent Events
- 서버에서 client로 요청을 보냄. response만 연결을 유지하고 있다.
![image](https://user-images.githubusercontent.com/26343023/153138878-221e5285-659a-4444-8286-ba494f0f030c.png)


### WebSocket
- request와 response를 모두 연결을 유지한다. 양방향으로 통신을 한다.
![image](https://user-images.githubusercontent.com/26343023/153138906-e4edddfa-f29a-40a6-8e66-b1a7c8994026.png)

# 멀티 스레드 동기 vs 싱글 스레드 비동기
- [참조](https://ooeunz.tistory.com/109)

# pub/sub 모델
- [참조](https://jistol.github.io/software%20engineering/2018/04/11/observer-pubsub-pattern/)
- Publisher가 Subscriber의 위치나 존재를 알 필요없이 Message Queue와 같은 Broker역활을 하는 중간지점에 메시지를 던져 놓기만 하면 된다.
- 반대로 Subscriber 역시 Publisher의 위치나 존재를 알 필요없이 Broker에 할당된 작업만 모니터링하다 할당 받아 작업하면 된다.
- 따라서 Publisher와 Subscriber가 서로 알 필요가 없다.

# WebFlux

- [참조](https://www.youtube.com/watch?v=Sz1Ve_1KZII&list=PL93mKxaRDidFH5gRwkDX5pQxtp0iv3guf&index=3&ab_channel=%EB%A9%94%ED%83%80%EC%BD%94%EB%94%A9)
1. contentType=text/event-stream 을 사용하여, 간단한 실시간 통신을 해본다. - [more](https://github.com/Pawer0223/study_codes/tree/main/simple_webflux)
2. reactive_streams를 직접 사용해본다. - [more](https://github.com/Pawer0223/study_codes/tree/main/using_reactive_streams)
3. WebFlux 사용해보기(with R2DBC) - [more](https://github.com/Pawer0223/study_codes/tree/main/flux_example)
