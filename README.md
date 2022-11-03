# 교육
간단한 게시판 crud를 구현했습니다.
<br/></br>

## Q1. 데이터를 어디까지 믿을 수 있을까요?

A1. 유저 토큰 검사를 통해서 권한이 있는 유저만 가능하도록 구현했고, 게시글 삭제 같은 경우에 토큰으로부터 유저 정보를 가져와서 게시글 작성자랑 비교해서 삭제하도록 구현했습니다. **데이터를 믿지 말아야한다!!!!**
<br/><br/>

## Q2. 이 어플리케이션이 실행중인지 어떻게 알 수 있을까요?

A2-1. 어플리케이션으로 요청을 보내서 확인할 수 있습니다. spring에서 actuator를 implement를 하고, *{url}/actuator/health* 요청을 통해 살아있는지 여부를 response로 확인할 수 있습니다.   

A2-2. spring actuactor를 사용하지 않고 직접 구현할 수도 있습니다. get method를 사용해서 프로젝트의 실행 여부를 알 수 있는 api 구현했습니다.
<br/><br/>

## Q3. java에는 LocalDateTIme, OffsetDateTime, ZonedDateTime 등등 시간을 나타내는 자료형이 많죠? 데이터 베이스에도 DateTIme, timeStemp등등이 있습니다. 이들의 차이점에 대해 말해주세요

A3.   
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
A4.<br/>
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
1. 서버는 UTC, 디비는 KST
<img width="258" alt="스크린샷 2022-11-02 오후 4 37 04(3)" src="https://user-images.githubusercontent.com/66578746/199433193-ec5d7cd1-4012-4ad6-ace3-a638e5c993d0.png">
2. 서버는 KST, 디비는 UTC
<img width="253" alt="스크린샷 2022-11-02 오후 4 39 01(3)" src="https://user-images.githubusercontent.com/66578746/199433254-2e766350-b443-4d6b-b7bc-db8ed0345720.png">
이건 예상했던 것과 결과가 달라서 놀랐습니다... 서버 기준에서 데이터를 불러오니까 서버가 KTC라면 UTC인 디비라도 KST 로 나올 것이라고 예상했었습니다... 이 경우에도 둘중에 하나만 UTC라도 UTC 기준으로 시간을 불러오는 것을 확인할 수 있었습니다.

### 5. OS 시간
1. OS의 시간대를 UTC, 서버는 KST
<img width="349" alt="스크린샷 2022-11-02 오후 7 53 32(2)" src="https://user-images.githubusercontent.com/66578746/199628927-6e05c2c1-8fcf-4d0d-9825-6f2b9b7e8fe3.png">
OS는 UTC이지만, 서버를 KST로 설정해서 LocalDateTime, TimeStamp 모두 KST 기준으로 출력된 것을 확인할 수 있었습니다.
2. OS는 UTC, 서버는 KST, 디비는 KST 
<img width="291" alt="스크린샷 2022-11-02 오후 7 58 13(2)" src="https://user-images.githubusercontent.com/66578746/199629248-897553b1-7edf-476f-aaff-9a3a8c0271ef.png">
디비에는 11월2일의 값이 저장되어 있습니다. 이걸 Timestamp는 KST 디비 그대로의 값을 불러왔고, LocalDateTime은 OS의 UTC 시간대에 맞춘 시간을 출력하는 것을 확인할 수 있었습니다.


