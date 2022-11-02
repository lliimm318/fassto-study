# 교육
간단한 게시판 crud를 구현했습니다.
<br/></br>

#### Q1. 데이터를 어디까지 믿을 수 있을까요?

A1. 유저 토큰 검사를 통해서 권한이 있는 유저만 가능하도록 구현했고, 게시글 삭제 같은 경우에 토큰으로부터 유저 정보를 가져와서 게시글 작성자랑 비교해서 삭제하도록 구현했습니다. **데이터를 믿지 말아야한다!!!!**
<br/><br/>

#### Q2. 이 어플리케이션이 실행중인지 어떻게 알 수 있을까요?

A2-1. 어플리케이션으로 요청을 보내서 확인할 수 있습니다. spring에서 actuator를 implement를 하고, *{url}/actuator/health* 요청을 통해 살아있는지 여부를 response로 확인할 수 있습니다.   

A2-2. spring actuactor를 사용하지 않고 직접 구현할 수도 있습니다. get method를 사용해서 프로젝트의 실행 여부를 알 수 있는 api 구현했습니다.
<br/><br/>

#### Q3. java에는 LocalDateTIme, OffsetDateTime, ZonedDateTime 등등 시간을 나타내는 자료형이 많죠? 데이터 베이스에도 DateTIme, timeStemp등등이 있습니다. 이들의 차이점에 대해 말해주세요

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

#### Q4. 서버와 데이터베이스의 Default Time Zone은 어떻게 확인하고, 이 타임존을 설정해보고 우선순위는 어떻게 될까요?      
A4.<br/>
1. DataBase
> SELECT @@GLOBAL.time_zone, @@SESSION.time_zone, @@system_time_zone;
<img width="531" alt="스크린샷 2022-11-01 오전 10 04 22" src="https://user-images.githubusercontent.com/66578746/199136796-7b96c897-2cfa-4096-a08a-fdd0b61e9291.png">

> SELECT now();
<img width="209" alt="스크린샷 2022-11-01 오전 10 16 56" src="https://user-images.githubusercontent.com/66578746/199139813-f1ec3f68-3e98-45a6-bafb-3737238c43c0.png">
요로코롬 실행시키면 system_time_zone이 KTC라서 한국 표준 현재 시간이 나옵니당.

