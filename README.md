# 교육
간단한 게시판 crud를 구현했습니다.
<br/></br>

## Q1. 데이터를 어디까지 믿을 수 있을까요?

유저 토큰 검사를 통해서 권한이 있는 유저만 가능하도록 구현했고, 게시글 삭제 같은 경우에 토큰으로부터 유저 정보를 가져와서 게시글 작성자랑 비교해서 삭제하도록 구현했습니다. **데이터를 믿지 말아야한다!!!!**
<br/><br/>

## Q2. 이 어플리케이션이 실행중인지 어떻게 알 수 있을까요?

1. 어플리케이션으로 요청을 보내서 확인할 수 있습니다. spring에서 actuator를 implement를 하고, *{url}/actuator/health* 요청을 통해 살아있는지 여부를 response로 확인할 수 있습니다.   

2. spring actuactor를 사용하지 않고 직접 구현할 수도 있습니다. get method를 사용해서 프로젝트의 실행 여부를 알 수 있는 api 구현했습니다.
<br/><br/>

## Q3. java에는 LocalDateTIme, OffsetDateTime, ZonedDateTime 등등 시간을 나타내는 자료형이 많죠? 데이터 베이스에도 DateTIme, timeStemp등등이 있습니다. 이들의 차이점에 대해 말해주세요
 
<img width="555" alt="thumb" src="https://user-images.githubusercontent.com/66578746/197448791-ce75ff21-6909-44c2-9c8e-75a37e357e9a.png">
<img width="478" alt="스크린샷 2022-10-24 오후 1 15 36 3" src="https://user-images.githubusercontent.com/66578746/197447569-09bc3dcd-45e7-4a6b-a8b4-b4c00b70856d.png">

**JAVA의 TIME API.**
1. LocalDate, LocalDateTime, LocalTime은 시간대에 대하 정보가 전혀 없는 API 입니다. 한국에서 2022-10-24T00:00:00이면 미국에서도 같은 시간입니다. 이러한 경우 생일 같은 경우에 적합합니다. 
2. OffsetDateTime은 LocalDateTime + ZoneOffset 입니다. ZoneOffset은 UTC 기준으로 시간을 나타낸 것이라서 우리나라의 경우 KST를 사용해서 UTC +09:00으로 표기 됩니다.
3. ZonedDateTime은 OffsetDateTime + ZoneRegion 입니다. 

**MySQL - DATETIME vs TIMESTEMP.**
DATETIME과 달리 TIMESTEMP는 time_zone으 가지고 있어서 시스템의 time_zone에 맞는 시간이 나오게 됩니다. 그래서 글로벌 서비스시에 TIME STEMP를 사용하는게 더 적합할 것 같습니다!
<br/><br/>

## Q4. 서버와 데이터베이스의 Default Time Zone은 어떻게 확인하고, 이 타임존을 설정해보고 우선순위는 어떻게 될까요?      
### 1. DataBase
> SELECT @@GLOBAL.time_zone, @@SESSION.time_zone, @@system_time_zone;
<img width="531" alt="스크린샷 2022-11-01 오전 10 04 22" src="https://user-images.githubusercontent.com/66578746/199136796-7b96c897-2cfa-4096-a08a-fdd0b61e9291.png">

> SELECT now(); system_time_zone이 KST라서 한국 표준시간이 결과로 나옵니다
<img width="209" alt="스크린샷 2022-11-01 오전 10 16 56" src="https://user-images.githubusercontent.com/66578746/199139813-f1ec3f68-3e98-45a6-bafb-3737238c43c0.png"> 
                      
### 2. Server
<img width="353" alt="스크린샷 2022-11-02 오후 3 18 08(3)" src="https://user-images.githubusercontent.com/66578746/199432727-7d5710ba-9794-4c72-8d82-0ead5de89f5b.png">
서버에서의 시간대를 확인할 수 있습니당

### 3. Server to DB
<img width="406" alt="스크린샷 2022-11-02 오후 4 40 07" src="https://user-images.githubusercontent.com/66578746/199428731-9e3bb233-dd37-4201-bcd4-fd1828c3a05e.png">
위 사진은 서버에서 데이터 베이스로 데이터를 저장한 결과입니다.<br/>          
1. 서버와 디비 모두 KST KST 시간대로 저장<br/>     
2. 서버는 KST, 디비는 UTC UTC 시간대로 저장<br/>       
3. 서버는 UTC, 디비는 KST UTC 시간대로 저장<br/>            
인데... 둘중에 하나만 UTC라도 UTC 기준으로 시간이 저장되는 것을 확인할 수 있었습니다! 

### 4. DB to Server
> TimeZone.setDefault 사용!
1. 서버는 UTC, 디비는 KST
<img width="301" alt="스크린샷 2022-11-03 오전 10 10 27(2)" src="https://user-images.githubusercontent.com/66578746/199630248-5c55727d-4882-4cd4-9b04-2acb90c30ef4.png">
둘 다 서버 UTC 기준으로 시간을 불러옵니다
<br/></br>
2. 서버는 KST, 디비는 UTC
<img width="299" alt="스크린샷 2022-11-03 오전 10 13 08(2)" src="https://user-images.githubusercontent.com/66578746/199630469-73045081-d21c-4d83-94ea-e081de9996b6.png">
저장된 시간이 UTC 기준이라서 서버 기준인 KST로 변환해서 출력됩니다.


### 5. OS 시간
1. OS의 시간대를 UTC, 서버는 KST
<img width="349" alt="스크린샷 2022-11-02 오후 7 53 32(2)" src="https://user-images.githubusercontent.com/66578746/199628927-6e05c2c1-8fcf-4d0d-9825-6f2b9b7e8fe3.png">
OS는 UTC이지만, 서버를 KST로 설정해서 LocalDateTime, TimeStamp 모두 KST 기준으로 출력된 것을 확인할 수 있었습니다.
<br/></br>            
2. OS는 UTC, 서버는 KST, 디비는 KST 
<img width="291" alt="스크린샷 2022-11-02 오후 7 58 13(2)" src="https://user-images.githubusercontent.com/66578746/199629248-897553b1-7edf-476f-aaff-9a3a8c0271ef.png">
디비에는 11월2일의 값이 저장되어 있습니다. 이걸 Timestamp는 KST 디비 그대로의 값을 불러왔고, LocalDateTime은 OS의 UTC 시간대에 맞춘 시간을 출력하는 것을 확인할 수 있었습니다.
<br/></br> 
3. OS는 KST, 서버와 디비는 UTC
<img width="291" alt="스크린샷 2022-11-03 오전 10 16 53(2)" src="https://user-images.githubusercontent.com/66578746/199630796-7a9916ba-e7a5-44ce-bdf7-7b34ca7b4351.png">
OS가 KST로 설정되어 있어도, 서버랑 디비가 UTC로 설정되어 있어서 값이 변하지 않았습니다/
<br/></br>

## Q5. ExceptionHandling을 왜 할까요?
**1. 개발시간 단축**<br/>
에러가 발생 했을 때에, 에러처리를 해주지 않으면 어디서 원인이 무엇인지 알 수 없어서 원인을 찾는데에 오랜 시간이 걸리게 됩니다. 에러 처리를 통해서 에러가 난 원인을 찾을 수 있고 에러를 해결하는 시간이 줄어들어 개발 시간을 단축할 수 있습니다.<br/>

**2. 해킹 위험 감소**<br/>
에러 핸들링을 해주지 않으면 사용자에게 에러가 그대로 노출됩니다. 그러면 어떤 데이터베이스를 사용하는지 등등의 정보가 노출 되어서 생기는 해킹의 위험을 에러 핸들링을 통해 이를 해결할 수 있습니다
<br/></br>

## Q6. @RestControllerAdvice와 @ControllerAdvice 의 차이!
 **둘은 @RestController와 @Controller의 차이처럼 HTTP Response Body가 생성되는 방식에 대한 차이가 있습니다!!!!**
  <br/></br>
 @Controller는 주로 View를 반환하기 위해 쓰입니다. 하지만 컨트롤러가 Data를 반환해야 하는 경우도 있습니다.. 이때 컨트롤러에서는 데이터를 반환하기 위해 @ResponseBody를 사용해야 하는데, 이를 통해 Controller도 Json 형태로 객체 데이터를 반환할 수 있게됩니다. @RestControllerAdvice도 @ResponseBody가 붙어 있어 응답을 Json으로 내려주는 차이가 있습니다!
 <br/></br>
 ControllerAdvice는 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해줍니다. ControllerAdvice 어노테이션 안에는 @Component 어노테이션이 있어서 ControllerAdvice가 선언된 클래스는 스프링 빈으로 등록됩니다. 그래서 전역적으로 에러를 핸들링 할 수 있게 된답니당
