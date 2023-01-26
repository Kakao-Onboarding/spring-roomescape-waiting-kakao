package controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;

public class WaitingControllerTest extends AbstractControllerTest {

    static final String DEFAULT_PATH = "/reservation-waitings";

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class create {

        //        @DisplayName("멤버 생성 성공")
        //        @Test
        //        void should_returnLocation_when_givenRequest() {
        //            var request = createRequest();
        //
        //            var response = post(WaitingControllerTest.this.given(), DEFAULT_PATH, request);
        //
        //            then(response)
        //                    .statusCode(HttpStatus.CREATED.value())
        //                    .header("Location", "/members/1");
        //        }
        //
        //        @DisplayName("username이 중복될 경우 예외 발생")
        //        @Test
        //        void should_throwException_when_usernameDuplicate() {
        //            var expectedException = ErrorMessage.MEMBER_CONFLICT;
        //            var request = createRequest();
        //
        //            post(WaitingControllerTest.this.given(), DEFAULT_PATH, request);
        //            var response = post(WaitingControllerTest.this.given(), DEFAULT_PATH, request);
        //
        //            thenThrow(response, expectedException);
        //        }
        //
        //        @DisplayName("입력이 공백일 경우 예외 발생")
        //        @ParameterizedTest
        //        @MethodSource
        //        void should_throwException_when_invalidRequest(MemberRequest request) {
        //            var response = post(WaitingControllerTest.this.given(), DEFAULT_PATH, request);
        //
        //            then(response)
        //                    .statusCode(HttpStatus.BAD_REQUEST.value())
        //                    .body(
        //                            containsString("[username은 공백일 수 없습니다.]"),
        //                            containsString("[name은 공백일 수 없습니다.]"),
        //                            containsString("[password는 공백일 수 없습니다.]"),
        //                            containsString("[phone은 공백일 수 없습니다.]")
        //                    );
        //        }
        //
        //
        //        List<Arguments> should_throwException_when_invalidRequest() {
        //            return List.of(
        //                    Arguments.of(new MemberRequest()),
        //                    Arguments.of(new MemberRequest("", "", "", "")),
        //                    Arguments.of(new MemberRequest(" ", " ", " ", " "))
        //            );
        //        }
    }

    //    MemberRequest createRequest() {
    //        return new MemberRequest(
    //                "username",
    //                "password",
    //                "username",
    //                "010-1234-5678"
    //        );
    //    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getById {

        //        @DisplayName("멤버 조회 성공")
        //        @Test
        //        void should_returnMember_when_memberExists() {
        //            var request = createRequest();
        //            post(WaitingControllerTest.this.given(), DEFAULT_PATH, request);
        //
        //            var response = get(WaitingControllerTest.this.given(), DEFAULT_PATH + "/1");
        //
        //            then(response)
        //                    .statusCode(HttpStatus.OK.value())
        //                    .body("id", equalTo(1))
        //                    .body("username", equalTo(request.getUsername()))
        //                    .body("password", equalTo(request.getPassword()))
        //                    .body("name", equalTo(request.getName()))
        //                    .body("phone", equalTo(request.getPhone()))
        //                    .body("role", equalTo("NORMAL"));
        //        }
        //
        //        @DisplayName("멤버가 없을 경우 빈 body 반환")
        //        @Test
        //        void should_returnNull_when_memberNotExists() {
        //            var response = get(WaitingControllerTest.this.given(), DEFAULT_PATH + "/1");
        //
        //            then(response)
        //                    .statusCode(HttpStatus.OK.value())
        //                    .content(emptyString());
        //        }
    }
}