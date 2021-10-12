# 자동차 경주 게임
## 진행 방법
* 자동차 경주 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

##기능 목록

* 전진 랜덤값 생성 : 게임 진행에 필요한 랜덤 값을 생성한다. 
* 자동차 이름, 전진 횟수 관리 : 게임에 참여하고있는 자동차의 이름 목록과 각 자동차의 전진 횟수를 관리한다.
* 게임 진행, 운영 : 게임을 진행하고 우승을 판단한다.
* 출력 : 게임의 진행 및 결과를 출력한다.

## 기능 조건 목록

* 게임
  * 시작(입력)
    * 사용자는 게임 시작을 위해 아래 정보를 입력한다.
      * 자동차의 이름 목록. 쉼표를 기준으로 n대 만큼 입력함(예. pobi,crong,honux)
        * 이름은 5자 이하
      * 이동을 시도할 횟수(이하 `이동 시도 횟수`)
  * 진행
    * `이동 시도 횟수`만큼 자동차는 이동하며 이동이 끝나면 게임은 종료된다.
    * 가장 이동을 많이 한 자동차가 우승하며 한 대 이상일 수 있다.
  * 자동차
    * 이동
      * 자동차는 한 번의 횟수 동안 한 칸 이동 하거나(전진) 또는 안하거나(멈춤) 둘 중 하나를 수행한다.
      * 한 번의 횟수에서 0~9까지의 랜덤 값을 생성하여 생성된 숫자가 4 이상일 경우 전진, 3 이하의 경우 멈춘다.
* 공통
  * 사용자가 잘못된 값을 입력할 경우 "[ERROR]"로 시작하는 에러 메세지를 출력 후 입력을 다시 받는다.
