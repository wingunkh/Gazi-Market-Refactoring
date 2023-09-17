<h1 align="center">🍆<strong> 가지 마켓 </strong>🍆</h1>

<div align="center">
<strong>2023 한성대학교 컴퓨터공학부 캡스톤 디자인</strong>
<br><br>
<em>가장 지능적인 중고 스마트폰 거래 플랫폼, 가지마켓</em>
<br><br>
<em>written by wingunkh</em>
</div>

## 📑 목차

- 개요
- 주요 기능
- 기술 스택
- 시스템 구조도
- 팀원 및 담당 파트
- 수상

## 📓 개요

기존 대한민국에서 인기 있는 중고 거래 플랫폼인 "중고나라", "당근마켓" 등을 벤치마킹하여 <br>
중고 스마트폰을 거래할 수 있는 독자적인 플랫폼을 개발하였습니다. <br>
<br>
기존 중고 거래의 특성상 정해진 가격이 존재하지 않기 때문에, 사용자들은 명확한 기준에 근거한 거래를 기대하기 어렵습니다. <br>
또한 허위 매물 등 수많은 사기 거래에 대한 위험성이 존재하는 등 사용자들이 신뢰할 수 있는 거래망을 형성하기 어려운 것이 현실입니다. <br>
<br>
이러한 신뢰성에 대한 문제에 따라 가지마켓은 상품 상태에 따른 시세를 실시간으로 제공함과 동시에 <br>
사진의 메타 데이터 중 EXIF 데이터를 활용하여 해당 상품의 사진이 판매자가 직접 촬영한 사진인지 또는 인터넷 등을 통해 다운로드 받은 사진인지 판별하는 기능을 제공합니다. <br>
<br>
또한 딥러닝 기술을 기반으로 판매하고자 하는 스마트폰의 기종을 분류하는 기능을 제공함과 동시에 <br>
카카오 맵 API를 활용한 위치 기반 서비스와 웹 소켓 기술을 활용한 채팅 기능 등 다양한 편의 기능을 제공합니다.

## 🎯 주요 기능

> ### 중고 거래 플랫폼 기능
- "중고나라", "당근마켓" 등을 벤치마킹
- 로그인과 게시글 작성, 검색, 수정, 삭제 기능
- 게시글별 채팅, 즐겨찾기, 신고하기, 판매 완료 기능
- 방문 내역, 판매 내역, 관심 게시글 내역 제공
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/a8cf1d75-5a89-40ea-9574-881503fb3370">
<br><br>

> ### 실시간 시세 제공 기능
- 판매자는 상품 판매글 작성 중 손상도를 기준으로 등급 설정 가능
- 이때 판매자는 실시간으로 해당 손상도에 따른 시세 확인 가능
- 구매자는 상품 판매글에서 실시간으로 시세 확인 가능
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/d6820d98-0816-46f5-8cab-12f9e6c63609">
<br><br>

> ### EXIF 데이터를 통한 사진 출처 판별 기능
- 메타 데이터 중 EXIF 데이터를 기반으로 해당 사진이 직접 촬영된 사진인지, 인터넷 등을 통해 다운로드 받은 사진인지 판별 가능
- 시세 기준으로 적정 가격임과 동시에 사진이 직접 촬영된 사진으로 판별됐을 경우 게시글이 보라색(안전)으로 표시
- 적정가에서 벗어났을 경우 및 다운로드 받은 사진으로 판별됐을 경우 중 한 가지 경우에 해당할 시 게시글이 노란색(주의)으로 표시
- 두 가지 경우에 모두 해당할 시 게시글이 빨간색(위험)으로 표시
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/592c2653-5c09-4b4d-9dc4-94426fe1c1cd">
<br><br>

> ### 딥러닝을 통한 스마트폰 기종 분류 기능
- 딥러닝 기술을 기반으로 판매자가 업로드한 스마트폰의 기종을 자동으로 분석
- Validation이 100%일 수 없음을 고려, 판매자가 직접 수정 가능
<br><br>

> ### 카카오 맵 API 활용 위치 기반 편의 기능
- 게시글에서 판매자의 위치 정보 제공
- 하단의 "지도" 메뉴를 통해 스마트폰 기종별 판매자의 위치 확인 기능 제공
- 마커를 클릭하여 해당 거래 게시글로 즉시 이동 가능
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/0a8a9d12-cabb-47e5-9d18-826017c5db99">
<br><br>

> ### 채팅 기능
- 게시글에서 판매자와 채팅하기 기능 제공
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/b11159a9-8c4e-4477-85bd-6843fec96011">

## 📌 기술 스택

<div>
    <table>
        <tr>
            <td colspan="2" align="center">
                Language
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white">
                <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">
                <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Library & Framework
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
                <img src="https://img.shields.io/badge/react native-61DAFB?style=for-the-badge&logo=react&logoColor=black">
                <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
                <img src="https://img.shields.io/badge/flask-000000?style=for-the-badge&logo=flask&logoColor=white">
                <img src="https://img.shields.io/badge/tensorflow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white">
                <img src="https://img.shields.io/badge/keras-D00000?style=for-the-badge&logo=keras&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Server
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> 
                <img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"> 
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Database
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
                <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Tool
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
                <img src="https://img.shields.io/badge/jupyter notebook-F37626?style=for-the-badge&logo=jupyter&logoColor=white">
                <img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                etc.
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
                <img src="https://img.shields.io/badge/jira software-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=white">
            </td>
        </tr>
    </table>
</div>

## 🧰 시스템 구조도

<img width="895" alt="image" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/4d518df7-72f4-4d58-b592-2bacb82c40a1">

## 👨‍👨‍👦‍👦 팀원 및 담당 파트

<div sytle="overflow:hidden;">
<table>
  <tr>
    <td colspan="2" align="center"><strong>Back-End</strong></td>
    <td colspan="2" align="center"><strong>Front-End</strong></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/wingunkh"><img src="https://avatars.githubusercontent.com/u/58140360?v=4" width="150px;" alt="김현근"/><br/><sub><b>김현근</b></sub></a>
    </td>
    <td align="center">
      <a href="https://github.com/tjdgns99"><img src="https://avatars.githubusercontent.com/u/117345938?v=4" width="150px" alt="김성훈"/><br/><sub><b>김성훈</b></sub></a>
    </td>
    <td align="center">
      <a href="https://github.com/qkrwodbs"><img src="https://avatars.githubusercontent.com/u/51899615?v=4" width="150px" alt="박재윤"/><br/><sub><b>박재윤</b></sub></a>
    </td>
    <td align="center">
      <a href="https://github.com/BURI966"><img src="https://avatars.githubusercontent.com/u/108705172?v=4" width="150px" alt="김승우"/><br/><sub><b>김승우</b></sub></a>
    </td>
  </tr>
</table>

> 김현근 : Spring Boot 사용 백엔드 어플리케이션 개발 / Amazon Web Service EC2 관리 및 S3 관련 기능
<br> / EXIF 데이터를 통한 사진 출처 판별 기능 / 데이터베이스 설계 담당 <br><br>
> 김성훈 : Spring Boot 사용 백엔드 어플리케이션 개발 / 딥러닝을 통한 스마트폰 기종 분류 기능
<br> / 채팅 기능 / 데이터베이스 설계 담당 <br><br>
> 김승우 : React 사용 관리자 웹 페이지 개발 / 카카오 맵 API 관련 기능 담당 <br><br>
> 박재윤 : React Native 사용 사용자 모바일 어플리케이션 개발 담당

</div>

## 🥇 수상

<img width="550" height="800" alt="장려상 수상" src="https://github.com/wingunkh/Gazi-Market/assets/58140360/af1e34f0-9729-4320-85a0-f2a2b1f040ba">
