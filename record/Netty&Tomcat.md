# Ref
- [Netty vs Tomcat 참조 블로그](https://ooeunz.tistory.com/109)
- [메시지 큐 참조 블로그](https://12bme.tistory.com/176)

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
- [참조](https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/record/Asynchronous.md)

# 그럼 무조건 Netty 써야 하는가 ?
- 아니다.
- 메시지 지향 미들웨어(MOM)의 기능을 하는 메시지 큐(Message Queue)를 사용하게 되면, 비동기로 처리할 수 있다.
  - MOM은 비동기 메시지를 사용한, 다른 응용 프로그램 사이에서 데이터 송수신을 담당하는 프로그램을 의미한다.
  - 서로 다른 프로세스나 프로그램 사이에 메시지를 교환할 때 AMQP(Advanced Message Queuing Protocol)을 이용. 
    - AMQP는 메시지 지향 미들웨어를 위한 open standard application layer protocol.
  - 오픈소스 메시지 큐로는 RabbitMQ, ActiveMQ, ZeroMQ, Kafka등이 있다.
