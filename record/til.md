# Today I Learned

<details>
<summary>week 1</summary>
  <table>
    <th>Day</th><th>Content</th><th>Keyword</th><th>Why?</th><th>Trace</th>
    <tr>
      <td>2022.02.05</td>
      <td>문제 정의</td>
      <td>프로젝트 틀 잡기</td>
      <td>정리 및 문서화</td>
      <td></td>
    </tr>
    <tr>
      <td>2022.02.06</td>
      <td>채팅 서버 구현 방식 공부</td>
      <td>SSE, WebsSocket</td>
      <td>채팅 서버 구현을 위한 방법 찾기</td>
      <td></td>
    </tr>
    <tr>
      <td>2022.02.07</td>
      <td>SSE로 구현</td>
      <td>WebFlux, SSE</td>
      <td>SSE vs WebSocket 비교</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/flux_example">link1</a>, 
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/simple_webflux">link2</a>, 
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/using_reactive_streams">link3</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.08</td>
      <td>WebSocket 구현</td>
      <td>WebSocket, sockJS, Stomp</td>
      <td>SSE vs WebSocket 비교</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/websocket_practice">link1</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.09</td>
      <td>Reactive Programming 공부하기</td>
      <td>Iterable, Observable, Observer, Reactive Streams, Subscription</td>
      <td>webflux 적용 시 기본개념의 부족함을 느낌</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/reactive_programming">link1</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.10</td>
      <td>Reactive Programming 공부하기(2)</td>
      <td>Operator</td>
      <td>webflux 이해하기</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/readme.md#operators">link1</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.11</td>
      <td>Reactive Programming 공부하기(3)</td>
      <td>Scheduler, ... </td>
      <td>webflux 이해하기</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/readme.md#operators">link1</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.12</td>
      <td>Chating Server Architecture</td>
      <td>Asynchronus, WorkThread, DefeeredResult Queue, Emitter</td>
      <td>
        <li>아, 근데 사실 왜 비동기로 처리하는게 좋은건지.. 제대로 이해를 못하고 있다..</li>
        <li>Netty를 사용하려는 이유: 네트워크에 대한 처리는 서버에 최대한 맡기고, 최대한 비즈니스로직에 집중하자.</li>
      </td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/han_demand/blob/main/record/Netty%26Tomcat.md">link1</a>
      </td>
    </tr>
    <tr>
      <td>2022.02.13</td>
      <td>Reactive Programming 공부하기</td>
      <td>CompletableFuture, ... </td>
      <td>
        다시 webflux 이해하기
      </td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/record/WebFlux.md">link1</a>
      </td>
    </tr>
  </table>
</details>

<details>
<summary>week 2</summary>
  <table>
    <th>Day</th><th>Content</th><th>Keyword</th><th>Why?</th><th>Trace</th>
    <tr>
      <td>2022.02.14</td>
      <td>MongoDB 걷어내기</td>
      <td></td>
      <td>비동기 DB 사용 -> 컬렉션 사용으로 변경하기</td>
      <td></td>
    </tr>
    <tr>
      <td>2022.02.15</td>
      <td>MongoDB 걷어내기</td>
      <td>tailable</td>
      <td>비동기 DB 사용 -> 컬렉션 사용으로 변경하기</td>
      <td><a target='_blank' href="https://github.com/Pawer0223/han_demand/tree/main/record/RemoveMongo.md">link1</a></td>
    </tr>
    <tr>
      <td>2022.02.16</td>
      <td>먼저 Security관련 코드적용시켜놓고 시작하기</td>
      <td>spring security</td>
      <td>외부 시스템 or 직접 적용 ?</td>
      <td><a target='_blank' href=""></a></td>
    </tr>
    <tr>
      <td>2022.02.17</td>
      <td>기본 코드 틀 잡아놓기.</td>
      <td></td>
      <td>security 직접 적용 - 일단 만들어놓자.</td>
      <td><a target='_blank' href=""></a></td>
    </tr>
    <tr>
      <td>2022.02.18</td>
      <td>security 적용 도메인 분석</td>
      <td></td>
      <td>security 적용 - 코드분석 및 간단 정리하기</li>
      </td>
      <td><a target='_blank' href="https://github.com/Pawer0223/han_demand/tree/main/user_service"></a></td>
    </tr>
    <tr>
      <td>2022.02.21</td>
      <td>
        <li>MSA 구축</li>
        <li>Security Filter를 API Gateway로 변경하기.</li>
      </td>
      <td>Discovery-Server, API-Gateway</td>
      <td>
        <li>채팅 서버 어플리케이션의 아키텍처를 따로 구축하고 싶었기 때문에.</li>
        <li>API-Gateway: 인증 및 인가에 대한 부분을 공통으로 필터링하고, 각 MSA를 호출</li>
        <li>Discovery-Server: 각 MSA의 정보를 등록하고, API-Gateway에서 Discovery</li>
      </td>
      <td><a target='_blank' href=""></a></td>
    </tr>
    <tr>
      <td>2022.02.22 ~ 02.27</td>
      <td>
        코딩테스트, 면접, 과제전형
      </td>
      <td></td>
      <td>
        진도를 못나감
      </td>
      <td><a target='_blank' href=""></a></td>
    </tr>
  </table>
</details>

<details>
<summary>week 3</summary>
  <table>
    <th>Day</th><th>Content</th><th>Keyword</th><th>Why?</th><th>conclusion</th><th>Trace</th>
    <tr>
      <td>2022.02.28</td>
      <td>Filter 고민</td>
      <td>filter</td>
      <td>로그인 정보 필터링을 apigateway에서, But 에러결과를 공통으로 어떻게 처리할 수 있지..</td>
      <td></td>
      <td><a href=""></a></td>
    </tr>
    <tr>
      <td>2022.03.01</td>
      <td>Filter 고민</td>
      <td>filter</td>
      <td>인증 처리에 대한 에러페이지까지 깔끔하게 구현하고 싶다.</td>
      <td>
        <li>GW에서 redirect나 직접 error페이지를 반환하려 시도했지만.. GW에서 너무 많은 책임을 지는것은 좋지않다.</li>
        <li>따라서 인증을 처리하는 서비스를 새롭게 구현할 예정.</li>
        <li>즉, GW에서 검증이 필요한 요청은 Authentication-Service로 redirect한다.</li>
        <li><a href="https://cloud.spring.io/spring-cloud-gateway/reference/html/#the-redirectto-gatewayfilter-factory">참조하자</a></li>
        <li>취소, JSON응답으로 보내는 방법찾았다. reject된 경우 클라이언트에서 제어하도록 하는게 좋을듯..</li>
      </td>
      <td><a href=""></a></td>
    </tr>
    <tr>
      <td>2022.03.02</td>
      <td>Security 개념다시 잡기</td>
      <td>SpringSecurity</td>
      <td>Security JWT발급 코드만 간단히 가져다 쓸려했는데.. 뭐하나 고칠려해도 손을못대겠다..</td>
      <td>
        <li>기존 보안검증 코드 지우고, 다시 짜자.</li>
        <li>Security 강의빨리듣자..</li>
      </td>
      <td><a href=""></a></td>
    </tr>
    <tr>
      <td>2022.03.03 ~ 03.04</td>
      <td>JWT 이해하기</td>
      <td>JWT, HttpBasic, Bearer 토큰</td>
      <td>JWT 이해하기</td>
      <td>
      </td>
      <td>
        <a href="https://github.com/Pawer0223/study_codes/tree/main/jwt_understand">링크</a>
      </td>
    </tr>
    <tr>
      <td>2022.03.05 ~ 03.13</td>
      <td>다른 과제전형 및 코딩테스트 대비</td>
      <td>JWT 이해까지 하고, 다른 과제 함</td>
      <td>진도를 못나감..</td>
      <td>
      </td>
      <td>
        <a href="https://github.com/Pawer0223/url_parsing_api">링크</a>
      </td>
    </tr>
  </table>
</details>
