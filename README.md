# spring-roomesacpe-waiting

# 🚀 1단계 - 인증 로직 리팩터링
## 요구사항
### 프로그래밍 요구사항
- [ ] auth 패키지를 nextstep 패키지로부터 분리한다
  - [ ] auth 패키지에서 nextstep로 의존하는 부분을 제거한다.
  - [ ] auth 패키지 내에서 스프링 빈으로 사용되던 객체를 Component Scan이 아닌 Java Configuration으로 빈 등록한다.
### 요구사항 설명
#### 패키지 분리
- auth 패키지를 nextstep 패키지로부터 분리한다.
  - to be 패키지 구조
![image](https://user-images.githubusercontent.com/67899393/215681853-422b23ff-630b-40c6-b7bf-e0cc0a22a836.png)

#### Java Configuration 활용
- 기존에 Component Scan으로 등록하던 객체를 Java Configuration을 이용하여 등록한다.
![image](https://user-images.githubusercontent.com/67899393/215681923-ee640c68-105e-4d69-9c0c-a435991ebf06.png)

- LoginController와 LoginService 객체도 Java Configuration을 이용하여 등록해야 한다.
## 힌트
### 패키지 의존성 확인
- 쉽게 생각할 수 있는 방법으로 auth패키지 내의 객체들 중 nextstep패키지 내에 있는 객체에 의존하는 부분을 제거하는 방법이 있음
### 패키지 의존성 제거
- 중간 객체를 이용한 의존성 사이클 제거
- 불필요한 의존 방향성을 제거하여 재사용 가능하게 변경

#### AS-IS
![image](https://user-images.githubusercontent.com/67899393/215682088-e356dfc3-06c6-4236-ad9c-985c8fc5c7be.png)

#### TO-BE
![image](https://user-images.githubusercontent.com/67899393/215682133-4ab4de5a-9160-4500-9387-c605a1e0f729.png)

### Component Scan이 아닌 방법으로 의존성 주입하기
- 아래와 같이 설정하였을 때 메서드를 통해서 의존성을 주입할 수 있음
- 고민해 보면 좋은 포인트는 각 메서드가 동작 할 때마다 객체가 생성되는 것 같은데 여러 객체가 생기지는 않을까?
- 이 부분을 학습해보는 것을 추천
![image](https://user-images.githubusercontent.com/67899393/215682214-15615074-73bd-4c95-a8c0-39de63615a97.png)

# 🚀 2단계 - 예약 대기 기능
## 상황 설명
- 예약 취소가 빈번해져서 다음 예약자를 구하기 번거로워졌다.
- 예약이 이뤄진 이후 대기를 신청할 수 있는 기능이 필요하다.
> 기능 요구사항에 나오지 않은 내용은 스스로 결정하여 구현한다.
## 요구사항
### 기능 요구사항
#### 예약 대기 신청
- [ ] 이미 예약이 된 스케줄 대상으로 예약 대기를 신청할 수 있다.
  - [ ] 예약이 없는 스케줄에 대해서 예약 대기 신청을 할 경우 예약이 된다.
#### 예약 대기 취소
- [ ] 자신의 예약 대기를 취소할 수 있다.
  - [ ] 자신의 예약 대기가 아닌 경우 취소할 수 없다.
#### 나의 예약 조회
- [ ] 나의 예약 목록을 조회할 수 있다.
  - [ ] 예약과 예약 대기를 나눠서 조회한다.
  - [ ] 예약은 reservation을 조회하고 예약 대기는 reservation-waiting을 조회한다.
  - [ ] 예약 대기의 경우 대기 순번도 함께 조회할 수 있다.

### API 설계
#### 예약 대기
```Java
POST /reservation-waitings HTTP/1.1
authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYzMjk4NTkwLCJleHAiOjE2NjMzMDIxOTAsInJvbGUiOiJBRE1JTiJ9.-OO1QxEpcKhmC34HpmuBhlnwhKdZ39U8q91QkTdH9i0
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "scheduleId": 1
}
```
```Java
HTTP/1.1 201 Created
Location: /reservation-waitings/1
```
#### 예약 목록 조회
```Java
GET /reservations/mine HTTP/1.1
authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYzMjk4NTkwLCJleHAiOjE2NjMzMDIxOTAsInJvbGUiOiJBRE1JTiJ9.-OO1QxEpcKhmC34HpmuBhlnwhKdZ39U8q91QkTdH9i0
```
```Java
HTTP/1.1 200 
Content-Type: application/json

[
    {
        "id": 1,
        "schedule": {
            "id": 1,
            "theme": {
                "id": 1,
                "name": "테마이름",
                "desc": "테마설명",
                "price": 22000
            },
            "date": "2022-08-11",
            "time": "13:00:00"
        }
    }
]
```
#### 예약 대기 목록 조회
```Java
GET /reservation-waitings/mine HTTP/1.1
authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYzMjk4NTkwLCJleHAiOjE2NjMzMDIxOTAsInJvbGUiOiJBRE1JTiJ9.-OO1QxEpcKhmC34HpmuBhlnwhKdZ39U8q91QkTdH9i0
```
```Java
HTTP/1.1 200 
Content-Type: application/json

[
    {
        "id": 1,
        "schedule": {
            "id": 3,
            "theme": {
                "id": 2,
                "name": "테마이름2",
                "desc": "테마설명2",
                "price": 20000
            },
            "date": "2022-08-20",
            "time": "13:00:00"
        },
        "waitNum": 12
    }
]
```
#### 예약 대기 취소
```Java
DELETE /reservation-waitings/1 HTTP/1.1
authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYzMjk5MDcwLCJleHAiOjE2NjMzMDI2NzAsInJvbGUiOiJBRE1JTiJ9.zgz7h7lrKLNw4wP9I0W8apQnMUn3WHnmqQ1N2jNqwlQ
```
```Java
HTTP/1.1 204 
```
