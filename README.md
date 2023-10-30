# 🩸 Checkable

> 2022 서울여자대학교 소프트웨어융합학과 졸업 프로젝트로 진행한 Checkable(여성 시각장애인 대상 월경 가이드 애플리케이션) 레포지토리입니다.
>
> - 개발자: Women's Eye 우아(성지현, 이세연, 한채연)
> - 개발 기간: 2022.03 - 2022.12

`Check` + `Blood` = `Checkable`!

휴대폰의 카메라로 생리혈의 유무와 혈의 색상을 파악하여 월경의 시작 여부를 위생적으로 확인할 수 있게 돕고자 개발한 여성 시각장애인 대상 애플리케이션입니다.

## 👩‍👩‍👧 팀 소개

서울여자대학교 소프트웨어융합학과 학생으로 구성된 팀입니다.

| 성지현                                                                                                    | 이세연                                                                                                   | 한채연                                                                                                     |
| --------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| <img src="https://avatars.githubusercontent.com/u/69228045?v=4" alt='@jhsung23' width="130" height="130"> | <img src="https://avatars.githubusercontent.com/u/71398979?v=4" alt='@seeyeon' width="130" height="130"> | <img src="https://avatars.githubusercontent.com/u/78338944?v=4" alt='@cyeon1118' width="130" height="130"> |
| [@jhsung23](https://github.com/jhsung23)                                                                  | [@seeyeon](https://github.com/seeyeon)                                                                   | [@cyeon1118](https://github.com/cyeon1118)                                                                 |

## 🔍 프로젝트 기획 배경 및 목표

### 여성 시각장애인이 월경 시 겪게 되는 불편함
   생리혈을 구별하기 위해 직접 만져보거나 냄새를 맡아보는 등 촉각과 후각에 의존해야 하는데, 이 과정에서 위생 문제가 발생할 수 있고 매번 타인의 도움을 받기도 쉽지 않습니다.
### 연구와 지침의 불충분
   여성 장애인에 관한 연구는 활발히 이루어지지 않은 상황입니다.
   
   또한, [세계 장애인 여성의 건강 핸드북](https://www.nrc.go.kr/chmcpd/html/content.do?depth=pi&menu_cd=02_06_00_02)에서도, '첫 월경을 시작한 시각 장애인 여성은 혈액을 볼 수 없어 월경을 하고 있는지 분간하기 어려울 수 있습니다.. 몇달이 지나면 몸의 감각으로 파악할 수 있을 것입니다'라고 특별한 방법이 없으니 몸에 감각에 의존해야 한다고 말하고 있습니다.
### 여성 시각장애인 대상 앱의 부재
   기존 시각장애인 대상 앱은 음성 안내가 주 목적이며, 월경 관리 앱의 경우 비장애 여성을 대상으로 하고 있습니다.

따라서 `Checkable`은 **여성 시각 장애인의 건강하고 주체적인 삶을 도모**하고자 개발되었습니다.

## 🌟 기능 목록

### 생리대 스캔하기 기능

애플리케이션을 실행하여 카메라에 생리대를 비추면 생리혈의 유무와 색깔을 파악한 결과를 알 수 있습니다.

내장된 혈 탐지 모델은 실시간으로 생리혈을 찾아냅니다. 생리대가 감지된 시점부터 약 7초간 이미지를 분석하여 가장 높은 정확도를 갖는 결과를 안내합니다.

정확도에 따른 결과 처리는 다음 표와 같습니다.

| 정확도 | 결과 처리 |
|------|---------|
| 50% 초과 60% 이하 | 정확도 낮음 |
| 60% 초과 80% 이하 | 정확도 보통 |
| 80% 초과 100% 이하 | 정확도 높음 |

### 내 주변 병원 찾기 기능

휴대폰의 GPS를 통해 사용자의 위도, 경도 정보를 파악한 후 공공데이터 포탈 Open API에서 제공하는 병의원 목록 중 2km 내의 병원을 근거리 순으로 보여줍니다.

## 🎬 애플리케이션 시연 영상

[Checkable 시연 영상](https://youtu.be/wrBSA2s5jOs?t=371)

## 📱 실행 화면

| 홈화면 | 온보딩 화면 |
| ------ | ----------- |
|<img width="180" alt="홈화면" src="https://github.com/jhsung23/Checkable/assets/69228045/e91c9a86-a660-4546-bb4f-42f0f5349082">|<img width="800" alt="온보딩화면" src="https://github.com/jhsung23/Checkable/assets/69228045/98696b46-d05a-41b3-84f0-521049f709ae">|

| 생리대 스캔 화면 (detecting...) | 스캔 결과 화면 |
| ---------------- | -------------- |
|<img width="1000" alt="스캔화면" src="https://github.com/jhsung23/Checkable/assets/69228045/5a701057-887b-40ce-900d-6b5d753e576b">|<img width="800" alt="결과화면" src="https://github.com/jhsung23/Checkable/assets/69228045/71f4046d-9509-4af6-bf63-e3be2909175b">|

| 병원 안내 화면 |
| -------------- |
|<img width="300" alt="병원" src="https://github.com/jhsung23/Checkable/assets/69228045/7794e12d-c88d-4dfc-a17a-6d056d64adbb">|

## 🛠️ 기술 스택

<div>
  <img src="https://img.shields.io/badge/android studio-3DDC84?style=flat&logo=android studio&logoColor=white">
  <img src="https://img.shields.io/badge/java-D22128?style=flat&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/yolov4-00FFFF?style=flat&logo=yolo&logoColor=black">
  <img src="https://img.shields.io/badge/google colab-F9AB00?style=flat&logo=google colab&logoColor=white">
</div>
