# :pushpin: Dicogram
> sns기능과 음성채팅을 통합한 서비스  
> 데모사이트 링크

</br>

## 1. 제작 기간 & 참여 인원
- 2023년 11월 28일 ~ 12월 11일
- 4인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 17.0.6
  - Oracle 11
  - Tomcat 9.0
  - JSP
#### `Front-end`
  - HTML
  - CSS
  - JS
  - JSP

</br>

## 3. ERD 설계
![](https://github.com/SMHRD-2021-KDT-BigData-19/dicogram/assets/83918497/338121bf-c3fa-46d3-8861-f90913185491)


## 4. 핵심 기능
이 서비스의 핵심 기능은 컨텐츠 등록 기능입니다.  
사용자는 단지 컨텐츠의 카테고리를 선택하고, URL만 입력하면 끝입니다.  
이 단순한 기능의 흐름을 보면, 서비스가 어떻게 동작하는지 알 수 있습니다.  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
![](https://github.com/SMHRD-2021-KDT-BigData-19/dicogram/assets/83918497/96add4a8-9c9f-4319-a969-ae1f6f163cf8)

### 4.2. 사용자 요청
![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_vue.png)

- **URL 정규식 체크** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b587bbff4dce02e3bec4f4787151a9b6fa326319/frontend/src/components/PostInput.vue#L67)
  - Vue.js로 렌더링된 화면단에서, 사용자가 등록을 시도한 URL의 모양새를 정규식으로 확인합니다.
  - URL의 모양새가 아닌 경우, 에러 메세지를 띄웁니다.

- **Axios 비동기 요청** :pushpin: [코드 확인]()
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.

### 4.3. Controller

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_controller.png)

- **요청 처리** :pushpin: [코드 확인](https://github.com/JungHyung2/gitio.io/blob/d35d29b64c0e8b9653862bdcc1e6b997d2436ec9/index.html#L57C1-L57C202)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.

- **결과 응답** :pushpin: [코드 확인]()
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.

### 4.4. Service

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service1.png)

- **Http 프로토콜 추가 및 trim()** :pushpin: [코드 확인]()
  - 사용자가 URL 입력 시 Http 프로토콜을 생략하거나 공백을 넣은 경우,  
  올바른 URL이 될 수 있도록 Http 프로토콜을 추가해주고, 공백을 제거해줍니다.

- **URL 접속 확인** :pushpin: [코드 확인]()
  - 화면단에서 모양새만 확인한 URL이 실제 리소스로 연결되는지 HttpUrlConnection으로 테스트합니다.
  - 이 때, 빠른 응답을 위해 Request Method를 GET이 아닌 HEAD를 사용했습니다.
  - (HEAD 메소드는 GET 메소드의 응답 결과의 Body는 가져오지 않고, Header만 확인하기 때문에 GET 메소드에 비해 응답속도가 빠릅니다.)

  ![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service2.png)

- **Jsoup 이미지, 제목 파싱** :pushpin: [코드 확인]()
  - URL 접속 확인결과 유효하면 Jsoup을 사용해서 입력된 URL의 이미지와 제목을 파싱합니다.
  - 이미지는 Open Graphic Tag를 우선적으로 파싱하고, 없을 경우 첫 번째 이미지와 제목을 파싱합니다.
  - 컨텐츠에 이미지가 없을 경우, 미리 설정해둔 기본 이미지를 사용하고, 제목이 없을 경우 생략합니다.


### 4.5. Repository

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_repo.png)

- **컨텐츠 저장** :pushpin: [코드 확인]()
  - URL 유효성 체크와 이미지, 제목 파싱이 끝난 컨텐츠는 DB에 저장합니다.
  - 저장된 컨텐츠는 다시 Repository - Service - Controller를 거쳐 화면단에 송출됩니다.

</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. 컨텐츠 필터와 페이징 처리 문제
- 이 서비스가 페이스북이나 인스타그램 처럼 가볍게, 자주 사용되길 바라는 마음으로 개발했습니다.  
때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  
다양한 기준(카테고리, 사용자, 등록일, 인기도)의 게시물 필터 기능을 넣어서 이를 보완하고자 했습니다.

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

</br>

### 5.2. 서버와 클라이언트 구현
- 채팅방을 구현하기 위해 서버와 클라이언트의 개념을 익히고 이클립스 console창으로 서로 다른 컴퓨터 간에 채팅이 가능하게 했습니다. 하지만 이를 웹 어플리케이션으로 채팅방을 구현하는데 알맞지 않아 웹 소켓을 사용해 이클립스 Servlet에서 사용하는 코드로 수정했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">
  
  ~~~java
  package Server;
  
  import java.io.*;
  import java.net.*;
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.List;
  
  public class MultiServer {
  	
  	public static void main(String[] args) {
  		MultiServer multiServer = new MultiServer();
  		multiServer.start();
  	}
  	
  	public void start() {
  		ServerSocket serverSocket = null;
  		Socket socket = null;
  		try {
  			serverSocket = new ServerSocket(8000);
  			while (true) {
  				System.out.println("[클라이언트 연결대기중]");
  				socket = serverSocket.accept();
  				
  				// client가 접속할때마다 새로운 스레드 생성
  				ReceiveThread receiveThread = new ReceiveThread(socket);	
  				receiveThread.start();
  			}
  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			if (serverSocket!=null) {
  				try {
  					serverSocket.close();
  					System.out.println("[서버종료]");
  				} catch (IOException e) {
  					e.printStackTrace();
  					System.out.println("[서버소켓통신에러]");
  				}
  			}
  		}
  	}
  }
  
  class ReceiveThread extends Thread {
  	
  //	static List<BufferedWriter> list = Collections.synchronizedList(new ArrayList<BufferedWriter>());
  	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
  	
  	Socket socket = null;
  	BufferedReader in = null;
  //	BufferedWriter out = null;
  	PrintWriter out = null;
  			
  	public ReceiveThread (Socket socket) {
  		this.socket = socket;
  		try {
  			out = new PrintWriter(socket.getOutputStream());
  //			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
  			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  			list.add(out);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  		
  	@Override
  	public void run() {
  
  		String name = "";
  		try {
  			// 최초1회는 client이름을 수신
  			name = in.readLine();
  			System.out.println("[" + name + " 새연결생성]");	
  			sendAll("[" + name + "]님이 들어오셨습니다.");
  			
  			while (in != null) {
  				String inputMsg = in.readLine();
  				if("quit".equals(inputMsg)) break;
  				sendAll(name + ">>" + inputMsg);
  			}
  		} catch (IOException e) {
  			System.out.println("[" + name + " 접속끊김]");
  		} finally {
  			sendAll("[" + name + "]님이 나가셨습니다");
  			list.remove(out);
  			try {
  				socket.close();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
  		System.out.println("[" + name + " 연결종료]");
  	}
  	
  	private void sendAll (String s) {
  		for (PrintWriter out: list) {
  			out.println(s);
  			out.flush();
  		}
  	}
  }
~~~
</div>
</details>
<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">
</div>
</details>
  - 참고
    - [https://nowonbun.tistory.com/285](https://nowonbun.tistory.com/285)
<br>

- 기존 서버 파일은 단일 서버로 클라이언트를 연결했었으나, 채팅방을 다수 생성하기 위해 HashMap을 도입하여 하나의 서버에서 여러 채팅방을 효과적으로 관리할 수 있도록 서버 코드를 수정하였습니다.
<br>
- 채팅방을 새로 생성하고 DB에 추가할 때, 자동으로 생성된 채팅방 코드를 서버에 전달하기 위해 path parameter 형식으로 서버 URL주소와 함께 채팅방 코드를 전송하였습니다.
<br>
- 실시간 채팅 중에 메시지를 받을 때, 메시지를 보낸 상대방의 프로필과 닉네임 정보를 채팅방에 표시하기 위해 메시지를 보낼 때 다음과 같은 형식의 문자열 데이터를 사용하고, 이후에 match 함수를 활용하여 데이터를 추출하도록 수정했습니다.
<br>
- 메시지를 받거나 보낼 때, 화면에 채팅방 형식으로 구현하기 위해 <ul> 태그 아래에 <li> 태그를 추가로 생성하여 메시지 내용과 프로필 사진, 시간, 닉네임을 확인할 수 있도록 구현했습니다.
<br>
- DB에 메시지 내용과 사용자id, 채팅방코드을 저장하기 위해 JSP 파일에서 Servlet으로 데이터를 fetch하여 전송하였습니다.

## 6. 그 외 트러블 슈팅
<details>
<summary>DataBase 이미지 불러오기 오류</summary>
<div markdown="1">

- String fileName = getSubmittedFileName(imagePart)
   데이터 베이스에 fileName으로 저장하였으나 
   불러올때 어려움을 겪어
- MemberMapper.xml에 아래 sql문을 추가해서 SELECT MAX(POSTID) FROM POSTS
- MAX게시물번호로 프로젝트 내부에 저장하여 게시물번호에 경로를 붙여서 불러옴 https://github.com/SMHRD-2021-KDT-BigData-19/dicogram/blob/ad0c92fb4fce6b8ff22c8be93971a6f0e8d7790f/Dicogram/src/main/webapp/Main.jsp#L89C25-L89C76
 


</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">
  
  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)
  
</div>
</details>

<details>
<summary>팝업창 CSS적용 오류</summary>
<div markdown="1">
  
  - Main.jsp에 게시물추가창을 JS로 팝업창을 열어서 버튼을 CLASS로 합쳐서 수정하려고 했으나 적용이 되지않아 ID를 적용시켜 수정함
  - 
  
</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">
  
  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정
  
</div>
</details>
    
<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">
  
  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```
  
  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.
   
</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">
  
  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
        - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)
   
</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">
  
  - @JsonIgnoreProperties 사용으로 해결
    - 참고
        - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
        - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)
        
</div>
</details>  
    
<details>
<summary> H2 접속문제</summary>
<div markdown="1">
  
  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        
</div>
</details> 
    
<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">
  
   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)
        
</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">
  
  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

        In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

        So, you are having two responses for the **OPTION** and **DELETE** call.

        see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)
        
</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">
  
  - UserAgent 설정으로 해결
        - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
        - [http://www.useragentstring.com/](http://www.useragentstring.com/)
        
</div>
</details> 
    
<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">
  
  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.
    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();

            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```
        
</div>
</details> 
    
<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">
  
  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        
</div>
</details> 
    
</br>

## 6. 회고 / 느낀점

