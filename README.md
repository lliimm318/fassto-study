# 교육
간단한 게시판 crud를 구현했습니다.



**사용기술** - Java17, Spring boot 2.7.4, MySQL, Spring data JPA 
<br/><br/>

**Q1. 데이터를 어디까지 믿을 수 있을까요?**

A1. 유저 토큰 검사를 통해서 권한이 있는 유저만 가능하도록 구현했고, 게시글 삭제 같은 경우에 토큰으로부터 유저 정보를 가져와서 게시글 작성자랑 비교해서 삭제하도록 구현했습니다. **데이터를 믿지 말아야한다!!!!**
<br/><br/>

**Q2.이 어플리케이션이 실행중인지 어떻게 알 수 있을까요?**

A2. 어플리케이션으로 요청을 보내서 확인할 수 있습니다. spring에서 actuator를 implement를 하고, *{url}/actuator/health* 요청을 통해 살아있는지 여부를 response로 확인할 수 있습니다.
<br/><br/>

**Q3. java에는 LocalDateTIme, offsetDateTime, ZonedDateTime 등등 시간을 나타내는 자료형이 많죠? 데이터 베이스에도 DateTIme, timeStemp등등이 있습니다. 이들의 차이점에 대해 말해주세요**

A3.   
**JAVA의 TIME API.**

**MySQL - DATETIME vs TIMESTEMP.**  
DATETIME과 달리 TIMESTEMP는 time_zone으 가지고 있어서 시스템의 time_zone에 맞는 시간이 나오게 됩니다. 그래서 글로벌 서비스시에 TIME STEMP를 사용하며 좋을 것 같다!
