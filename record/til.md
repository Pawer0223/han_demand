# Today I Learned

<details>
<summary>week 1</summary>
  <table>
    <th>Day</th><th>Content</th><th>Keyword</th><th>Why?</th><th>Trace</th>
    <tr>
      <td>2021.02.05</td>
      <td>문제 정의</td>
      <td>프로젝트 틀 잡기</td>
      <td>정리 및 문서화</td>
      <td></td>
    </tr>
    <tr>
      <td>2021.02.06</td>
      <td>채팅 서버 구현 방식 공부</td>
      <td>SSE, WebsSocket</td>
      <td>채팅 서버 구현을 위한 방법 찾기</td>
      <td></td>
    </tr>
    <tr>
      <td>2021.02.07</td>
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
      <td>2021.02.08</td>
      <td>WebSocket 구현</td>
      <td>WebSocket, sockJS, Stomp</td>
      <td>SSE vs WebSocket 비교</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/websocket_practice">link1</a>
      </td>
    </tr>
    <tr>
      <td>2021.02.09</td>
      <td>Reactive Programming 공부하기</td>
      <td>Iterable, Observable, Observer, Reactive Streams, Subscription</td>
      <td>webflux 적용 시 기본개념의 부족함을 느낌</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/tree/main/reactive_programming">link1</a>
      </td>
    </tr>
    <tr>
      <td>2021.02.10</td>
      <td>Reactive Programming 공부하기(2)</td>
      <td>Operator</td>
      <td>webflux 이해하기</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/readme.md#operators">link1</a>
      </td>
    </tr>
    <tr>
      <td>2021.02.11</td>
      <td>Reactive Programming 공부하기(3)</td>
      <td>Scheduler, ... </td>
      <td>webflux 이해하기</td>
      <td>
        <a target='_blank' href="https://github.com/Pawer0223/study_codes/blob/main/reactive_programming/readme.md#operators">link1</a>
      </td>
    </tr>
    <tr>
      <td>2021.02.12</td>
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
      <td>2021.02.13</td>
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
      <td>2021.02.14</td>
      <td>MongoDB 걷어내기</td>
      <td></td>
      <td>비동기 DB 사용 -> 컬렉션 사용으로 변경하기</td>
      <td></td>
    </tr>
    <tr>
      <td>2021.02.15</td>
      <td>MongoDB 걷어내기</td>
      <td>tailable</td>
      <td>비동기 DB 사용 -> 컬렉션 사용으로 변경하기</td>
      <td><a target='_blank' href="https://github.com/Pawer0223/han_demand/tree/main/record/RemoveMongo.md">link1</a></td>
    </tr>
    <tr>
      <td>2021.02.16</td>
      <td>먼저 Security관련 코드적용시켜놓고 시작하기</td>
      <td>spring security</td>
      <td>외부 시스템 or 직접 적용 ?</td>
      <td><a target='_blank' href=""></a></td>
    </tr>
    <tr>
      <td>2021.02.17</td>
      <td>기본 코드 틀 잡아놓기.</td>
      <td></td>
      <td>security 직접 적용 - 일단 만들어놓자.</td>
      <td><a target='_blank' href=""></a></td>
    </tr>
    <tr>
      <td>2021.02.18</td>
      <td>security 적용 도메인 분석</td>
      <td></td>
      <td>security 적용 - 코드분석 및 간단 정리하기</li>
      </td>
      <td><a target='_blank' href="https://github.com/Pawer0223/han_demand/tree/main/user_service"></a></td>
    </tr>
    <tr>
      <td>2021.02.21</td>
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
      <td>2021.02.22 ~ 02.27</td>
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
    <th>Day</th><th>Content</th><th>Keyword</th><th>Why?</th><th>Trace</th>
    <tr>
      <td>2021.02.28</td>
      <td>Filter 고민</td>
      <td>filter</td>
      <td>로그인 정보 필터링을 apigateway에서, But 에러결과를 공통으로 어떻게 처리할 수 있지..</td>
      <td><a href=""></a></td>
    </tr>
  </table>
</details>
