# Ref
- [Netty vs Tomcat 참조 블로그](https://ooeunz.tistory.com/109)
- [메시지 큐 참조 블로그](https://12bme.tistory.com/176)
- [토비님 강의 1:24:47 부터](https://www.youtube.com/watch?v=aSTuQiPB4Ns&list=PLOLeoJ50I1kkqC4FuEztT__3xKSfR2fpw&index=4)


# Netty의 장점
- Netty는 단일 쓰레드 비동기 처리.
- Tomcat은 멀티 쓰레드 동기 처리.
- 블로그를 통해 정리한 내용은 아래와 같다.
  - Netty를 사용하는 이유는 tomcat은 요청 당 하나의 스레드가 동작하는 반면, netty는 1개의 이벤트를 받는 스레드와 다수의 worker스레드로 동작한다.
  - 멀티 스레드 동작방식의 문제는, 이벤트 발생 순서와 실행 순서가 일치하지 않는다는 것.
  - 이유는, 여러개의 쓰레드가 1개의 이벤트 루프(이벤트를 실행하기 위한 무한루프 스레드)를 공유하기 때문이다.
  - 단일 쓰레드 비동기(Netty)서버는 이벤트가 Channel(중계자)에서 발생하고, Channel에서 새로운 worker스레드를 동작시키게 된다.
  - 그리고 각각의 worker스레드 내부에 이벤트 루프가존재한다. 이를 통해 해결이 가능한 것이다.

# 그럼 채팅서버에서는 비동기가 좋은가 ?
- 그렇다.
- 왜냐하면 멀티스레드로 동시에 여러채팅이 진행되면, 잦은 컨텍스트 스위칭이 일어난다.
- 따라서 요청이 많아질수록 속도는 점점 느려지게 된다.

# 단일 스레드 비동기로, 컨텍스트 스위칭으로 인한 속도저하 문제를 해결할 수 있는게 맞는것인가?

- Netty 내부적으로 결국 다수의 worker스레드로 동작한다면, 여러개의 쓰레드를 다루기 때문에 컨텍스트 스위칭이 자주 일어나는것은 똑같지 않나???
  - 결국 여러개의 쓰레드가 동작하는 방식이라면.. 근본적인 문제는 해결안되는거 아닌가...
 
### 서블릿의 비동기 처리방식부터.. 이해해보자
- [내용 및 사진 출처 - 토비님 강의](https://www.youtube.com/watch?v=aSTuQiPB4Ns&list=PLOLeoJ50I1kkqC4FuEztT__3xKSfR2fpw&index=4)
- 서블릿은 기본적으로 Blocking IO (InputStream자체가 Blocking)
- 여러개의 Request를 받게되면, 다른 스레드는 대기했다가 끝나면 처리한다.
 - 스레드가 지연되는 이유 
 - 스레드는 대부분 `[ServletThread1] req -> (logic) -> res(html)` 와 같은 흐름으로 처리 됨.
 - 보통 logic에서 Blocking IO가 발생한다. `[ServletThread1] req -> Blocking IO(DB, API) -> res(html)`
 - 이런 Blocking IO를 별도의 스레드로 처리하게 된다면??
   - 적어도 ServletThread에서 처리되는 나머지 작업은 비동기적으로 처리가가능해진다.
   - [ServletThread1] req -> (WorkThread) -> res(html)
   - 즉, WorkThread를 만들어 지연이 되는 처리를 비동기로 처리함으로써 ServletThread를 쓰레드 풀에 반환하고, 다른 요청을 처리할 수 있다.

> 금방 끝나는 쓰레드에서 오래 걸리는 작업이 처리되고 있을 때, 쓰레드를 하나 더 만들어서 처리해버린다.
> 금방 끝나는 쓰레드를 종료하고 반환할 수 있다는 것이 포인트라고 이해했다.

<img width="799" alt="image" src="https://user-images.githubusercontent.com/26343023/153713394-27191f11-296c-42d8-9374-322b15ec1e55.png">

- client의 요청을 NIO Connector가 받는다.
- NIO Connector가 서블릿 스레드를 계속 만들면서 풀에서 가져온다.
- 이 때 오래걸리는 작업들은 작업쓰레드 풀을 사용해서 실행한다. 그리고 서블릿 쓰레드는 계속 대기하는 것이아니고 바로 리턴한다.
  - 문제는 제대로 된 응답을 못받는다.. Html, Json이든 뭐든.. (일단 리턴!)
  - 응답은, `비동기 서블릿 엔진이 처리`해준다. 작업 쓰레드에서 Return이 되는 시점에 다시 서블릿 쓰레드 풀에서 할당을 한다 !
  - 그리고 아주 빠르게 응답을 처리하는 코드를 실행해서, 여전히 물고 있는 Connection에 응답을 주고 바로 반환 !

> 이러한 메커니즘으로, 적은 쓰레드 풀로 많은 작업을 동시에 처리할 수 있다.
> 무작정 쓰레드가 많다고 좋은것은아니다. 컨텍스트 스위칭이 자주일어나기 때문에. 따라서 지연시키지 않고 적은양의 쓰레드를 사용하는것이 좋다 !
> 작업 쓰레드를 추가함으로써 실현가능.. !

# 그럼 무조건 Netty 써야 하는가 ?
- 아니다.
- 메시지 지향 미들웨어(MOM)의 기능을 하는 메시지 큐(Message Queue)를 사용하게 되면, 비동기로 처리할 수 있다.
  - MOM은 비동기 메시지를 사용한, 다른 응용 프로그램 사이에서 데이터 송수신을 담당하는 프로그램을 의미한다.
  - 서로 다른 프로세스나 프로그램 사이에 메시지를 교환할 때 AMQP(Advanced Message Queuing Protocol)을 이용. 
    - AMQP는 메시지 지향 미들웨어를 위한 open standard application layer protocol.
  - 오픈소스 메시지 큐로는 RabbitMQ, ActiveMQ, ZeroMQ, Kafka등이 있다.
