# 90-s-Computer-Simulator

## 기능


## 전원 


![스크린샷 2024-08-15 210429](https://github.com/user-attachments/assets/c61b89ad-31c5-4166-aeef-e02bb3f701cf)
![스크린샷 2024-08-15 210736](https://github.com/user-attachments/assets/2f3df718-8297-4e12-ba33-5273c528878a)
![스크린샷 2024-08-15 210753](https://github.com/user-attachments/assets/d4bd22ba-63d9-44e1-a95b-0570a33b789f)


전원이 안켜져 있을땐 전원 박스가 하얀색이며 
전원 박스를 눌러 켜져있는 경우엔 하늘색
전원이 켜져있는 상태에서 한번더 전원 박스를 눌러서 끄면 빨간색이 됩니다.

## 기본    

![스크린샷 2024-08-15 210852](https://github.com/user-attachments/assets/7f1711d2-a4fd-484f-81dc-1acc98ab26b5)


전원이 켜지면 아래 하단에 단어를 쓸수있습니다.


## ?


![스크린샷 2024-08-15 211050](https://github.com/user-attachments/assets/58ed65ab-4ba6-41c7-a62a-4f1099d25409)


?은 도움말이며 사용가능한 명령어를 화면에 보여줍니다.

## cls

![스크린샷 2024-08-15 211100](https://github.com/user-attachments/assets/00896ff9-4ce2-4db2-8a77-864c49f024a6)


cls는 화면에 있는 명령어들을 지우는 역할을 합니다.

## ls
![스크린샷 2024-08-15 211111](https://github.com/user-attachments/assets/e604cef7-70a5-4cee-b97f-da607035dc7a)


ls는 Main.java 파일안에있는 다른 파일을 보여줍니다.

## date
![스크린샷 2024-08-15 211124](https://github.com/user-attachments/assets/9c5afe5f-ec2f-416b-8f51-99b9d4a1ed1c)

date는 날짜를 알려주는 명령어입니다.

## ip

ip는 사용자의 ip를 알려주는 명령어입니다.

## del

del는  Main.java 파일안에있는 다른 파일을 삭제할수있습니다.


# 코드 설명
전원 관리: 사용자는 전원 버튼을 통해 시스템의 전원을 켜고 끌 수 있으며, 전원이 활성화되면 사용자 인터페이스(UI)가 가시화됩니다.

명령어 입력 및 처리: 사용자가 입력한 명령어는 텍스트 영역에 결과로 출력됩니다. 지원하는 명령어 목록은 다음과 같습니다:

cls: 텍스트 영역의 내용을 지웁니다.


?: 사용 가능한 명령어 목록을 표시합니다.


del: 파일을 삭제하며, 사용자 확인 과정을 요구합니다.


ls: 현재 디렉토리에 존재하는 파일 목록을 보여줍니다.


cd <directory>: 지정된 디렉토리로 현재 작업 디렉토리를 변경합니다.


date: 현재 날짜, 시간 및 요일을 출력합니다.


ip: 로컬 및 외부 IP 주소를 조회하여 출력합니다.


UI 구성 요소:

JTextArea: 명령어 실행 결과를 표시하는 영역입니다.


JTextField: 사용자가 명령어를 입력하는 텍스트 필드입니다.


JButton: 시스템의 전원을 켜고 끄는 기능을 수행하는 버튼입니다.


로딩 시뮬레이션: 전원 활성화 시 1초 간의 로딩 효과를 제공하며, 이와 함께 배경 음악이 재생됩니다.


메인 메서드: 프로그램의 진입점으로, GUI 구성 요소를 초기화하고 실행합니다.


###       그외

 #### File musicPath = new File("원하는WAV파일"); 코드 부분에서 
("원하는 wav파일")을 원하는 wav경로로 변경하면 전원을 킬때 원하는wav 소리가 들립니다.

#### 사용 예시 
(https://drive.google.com/file/d/1_sS_Sg7dgo_X6-xhwX0ZE-F8c2-QzTq4/view?usp=sharing)
