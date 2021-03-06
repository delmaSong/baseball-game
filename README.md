# baseball-game

## 온라인 테스트 단계별 구현사항
[소스 위치 바로가기](https://github.com/delmaSong/baseball-game/tree/master/src/baseball_game)
### 1단계

> BaseballGame.java

- __playGame()__
  - 게임의 첫 시작
  - 아웃 카운트가 3이 될때까지 내부에서 메소드를 호출해 실행시킴
  - 아웃카운트가 3이 되면 finishGame()을 호출하고 게임 종료
  
- __drawRandom()__
  - 실행시마다 0~3까지의 정수값을 임의로 뽑아 randomResult에 저장
  - randomResult의 값에 따라 스트라이크/볼/아웃/안타 카운트가 증가
  - 아웃/안타의 경우 comeNextHitter()를 호출
  
- __cntPoint()__
  - 스트라이크 카운트 3번인 경우, 0으로 초기화되고 아웃카운트는 1 증가
  - 볼 카운트 4번인 경우, 0으로 초기화되고 안타카운트 1 증가
  - 스트라이크, 볼, 아웃 카운트 출력
  
- __comeNextHitter()__
  - 다음 타자의 입장을 알리는 문자열 출력
  - 스트라이크 카운트/볼 카운트 0으로 초기화
  
- __finishGame()__
  - 아웃카운트가 3일 경우에 호출됨
  - 최종 안타수와 게임 오버 출력



### 2단계

> TeamCompetition.java, TeamData.java, TeamPoint.java

#### TeamData.java

- 각 팀 데이터 객체.
- 팀의 이름을 담을 문자열 변수와 멤버들의 정보를 담을 어레이 리스트 선언

#### TeamPoint.java

- 각 팀의 점수 객체. 
- 안타, 스트라이크, 볼, 아웃, 포인트를 기록할 변수와 점수 변동을 입력하는 updatePoint()

#### TeamCompetition.java

- __selectMenu()__
  - 1,2,3의 선택지 출력 후 사용자의 입력 값 받음
  - 1 입력시 데이터를 입력할 수 있는 inputTeamInfo() 호출
  - 2 입력시 이미 입력한 데이터를 출력할 수 있는 printTeamInfo() 호출
  - 3 입력시 입력한 두팀 간 시합을 할 수 있는 doCompetition() 호출
  - 그 외 다른 문자열 입력시 "다시 선택해주세요"라는 안내말 출력
  - endFlag가 true가 될때까지 실행
  
- __inputTeamInfo()__
  - 두 팀의 이름과 각팀당 9명의 타자 정보를 입력받음
  - 각팀의 타자 정보는 tmpList라는 임시 리스트에 담겨지고,  모든 타자의 정보가 담겨지면 팀의 이름과 함께 TeamData 클래스 객체가 생성되어 teamInfo 리스트에 담겨짐
  
- __printTeamInfo()__
  - teamInfo 리스트에 담겨진 내용이 없는 경우 입력된 데이터가 없다는 안내말 출력
  - teamInfo 리스트에 담겨진 내용이 있는 경우 각 팀 이름 출력 후, 어레이 리스트의 인덱스 번호와 함께 입력된 타자 정보 출력 
  
- __doCompetition()__
  - 시합에 참여하는 팀 이름, 시작 회차, 선공격팀 이름 출력
  - compCnt가 7이 될때까지 nowPlayer() 반복 호출
  - compCnt가 7이면 finishGame() 호출
  
- __nowPlayer()__
  - 현재 이닝이 '회초'일 경우 1번팀, '회말'일 경우 2번팀의 선수의 이름 출력
  - 해당 선수의 타율을 담아 countHitRate(double h) 호출해 리턴한 결과값을 result 변수에 담음
  - result가 안타/아웃의 경우 nextPlayer() 호출. 그렇지 않은 경우, nowPlayer() 다시 실행됨
  
- __countHitRate(double h)__
  - Double형의 난수값을 소수점 셋째자리까지 만듦
  - 해당 난수 값이 0보다 크고 0.1보다 작거나 같을 경우, 아웃 변수 1 증가 및 결과 값으로 "아웃" 리턴
  - 해당 난수 값이 0.1보다 크고 0.1+h(해당 선수의 타율) 보다 작을 경우, 안타 변수 1 증가 및 결과 값으로 "안타" 리턴
  - 안타 플래그(안타 4회 이상일 경우 true) 의 경우 포인트 변수 1 증가
  - 해당 난수 값이 0.1+h 보다 크거나 같고 (1-h)/2-0.05+0.1+h 보다 작거나 같은 경우 스트라이크 변수 1 증가 및 결과 값으로 "스트라이크" 리턴
  - 해당 난수 값이 (1-h)/2-0.05+0.1+h 보다 크거나 같고 1.0보다 작은 경우 볼 변수 1 증가 및 결과 값으로 "볼" 리턴
  - 결과값 출력 및 각각의 아웃,안타,스트라이크, 볼, 안타, 포인트를 담아 countPoint() 호출
  
- __countPoint(int out, int strike, int ball, int anta, int point)__
  - 현재 이닝이 '회초'일 경우 1번팀, '회말'일 경우 2번팀의 updatePoint()호출 및 cntSBO() 호출
  
- __cntSBO(TeamPoint tp)__
  - 해당 팀의 스트라이크가 3일 경우 0으로 초기화 및 아웃 카운트 1 증가
  - 해당 팀의 볼 카운트가 4일 경우 0으로 초기화 및 안타 카운트 1 증가
  - 해당 팀의 안타 카운트가 4이거나 안타 플래그가 false일 경우 안타 카운트 0으로 초기화 및 포인트 1 증가, 안타 플래그 true로 변경
  - 해당 팀의 아웃 카운트가 3일 경우 아웃카운트, 안타카운트 0으로 초기화 및 안타플래그 false로 변경. turnSwitch() 호출
  - 해당 팀의 스트라이크, 볼, 아웃 카운트 출력 
  
- __turnSwitch()__
  - 현재 이닝 "회초"일 경우 "회말"로 변경하고, nowTeam을 "회말" 팀으로 변경
  - 현재 이닝 "회말"일 경우 "회초"로 변경하고, nowTeam을 "회초" 팀으로 변경
  - 현재 회차카운트(compCnt)와 이닝, 현재 공격팀 출력 
  
- __finishGame()__
  - endFlag를 true로 변경
  - 시합한 두 팀의 포인트를 출력 후 게임을 종료
  
- __printScoreboard()__
  - 점수판 출력
  

