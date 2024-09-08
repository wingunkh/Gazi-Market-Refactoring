<h1 align="center">🍆<strong> 가지마켓 </strong>🍆</h1>

<div align="center">
<strong>2023년 컴퓨터공학부 캡스톤 디자인</strong>
<br><br>
<em>가장 지능적인 중고 스마트폰 거래 모바일 앱, 가지마켓</em>
<br><br>
<em>written by wingunkh</em>
</div>

## 📑 목차

- 개요
- 주요 기능
- 기술 스택
- 시스템 구조도
- 팀원 및 담당 파트
<br>

## ❔ 개요

- 기존 중고 거래는 가격 기준이 없고, 사기 거래 위험으로 신뢰성 있는 거래망을 형성하기 어렵습니다.
- 이를 해결하기 위해 가지마켓은 스마트폰 상태 등급에 따른 시세를 제공하며, EXIF 메타데이터를 활용하여 사진이 촬영된 사진인지 다운로드된 사진인지 판별합니다.
- 또한, 딥러닝을 활용하여 스마트폰 기종을 자동 분류하고, Kakao Map API를 활용한 위치 기반 서비스와 Web Socket을 활용한 채팅 등 다양한 편의 기능을 제공합니다.
<br>

## 🎯 주요 기능

> ### 중고 거래 플랫폼 기본 기능
- 게시글 작성, 검색, 수정, 삭제 기능
- 방문 기록, 즐겨찾기 기록, 판매 기록 제공
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/9bc32683-61d9-4700-8ea0-8e30fc6780c9">
<br><br>

> ### 스마트폰 상태 등급에 따른 거래 시세 제공 기능
- 판매자는 스마트폰 상태를 기준으로 등급 설정 가능
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/569028bb-feab-4ce0-b56b-ffe4d1c58a8b">
<br><br>

> ### EXIF 메타데이터 활용 이미지 출처 판별 기능
- 게시글의 사진이 직접 촬영된 사진인지, 인터넷 등을 통해 다운로드된 사진인지 판별 가능
- 시세 기준 적정가이면서, 사진이 직접 촬영된 사진으로 판별됐을 경우 게시글이 보라색(안전)으로 표시
- 적정가에서 벗어났을 경우 또는 사진이 다운로드된 사진으로 판별됐을 경우 게시글이 노란색(주의)으로 표시
- 두 가지 경우에 모두 해당할 시 게시글이 빨간색(위험)으로 표시
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/21e59ced-f789-46a6-8902-2e267040125a">
<br><br>

> ### 딥러닝 활용 스마트폰 기종 자동 분류 기능
- 딥러닝 기술을 기반으로 판매자가 업로드한 스마트폰의 기종을 자동으로 분석
- Validation이 100%일 수 없음을 고려, 판매자가 직접 수정 가능
<br><br>

> ### Kakao Map API 활용 위치 기반 편의 기능
- 하단의 "지도" 메뉴를 통해 스마트폰 기종별 판매자 위치 확인 가능
- 마커를 클릭하여 해당 게시글로 즉시 이동 가능
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/5df53b81-12bc-464c-8ee1-03d0916e1744">
<br><br>

> ### 채팅 기능
- 판매자와 1:1 채팅 가능
<img width="325" height="720" alt="" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/742c5387-c581-4187-8116-97a7539f6866">
<br><br>

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
<br>

## 📜 시스템 구조도

<img width="895" alt="image" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/46231d07-c1e8-485b-abf8-e81eaf3c733c">
<br><br>

<!-- 
## 💾 데이터베이스 설계도

<img width="895" alt="image" src="https://github.com/wingunkh/findER-Refactored/assets/58140360/2591f2dd-2ec7-48e4-a6c8-6cc93e4337a6">
<br><br>
-->

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

> 김현근 : Spring API 서버 개발 / AWS EC2, 데이터베이스 구축
<br> / AWS S3 관련 기능 / EXIF 메타데이터를 통한 사진 출처 판별 기능 <br><br>
> 김성훈 : Spring API 서버 개발 / 딥러닝을 통한 스마트폰 기종 자동 분류 기능
<br> / 채팅 기능 <br><br>
> 김승우 : 관리자 웹 페이지 개발 (React) / Kakao Map API 관련 기능 <br><br>
> 박재윤 : 모바일 애플리케이션 개발 (React Native)
</div>
<br>

<!--
## 🥇 수상

<img width="550" height="800" alt="장려상 수상" src="https://github.com/wingunkh/Gazi-Market-Refactored/assets/58140360/0c7ef357-4bf5-40d9-9a6f-c0fdd3cfc051">
-->
