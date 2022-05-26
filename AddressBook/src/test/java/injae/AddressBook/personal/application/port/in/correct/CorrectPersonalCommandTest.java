package injae.AddressBook.personal.application.port.in.correct;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CorrectPersonalCommandTest {
    @Test
    public void correctPersonalCommand_validate_test() {

        assertThatThrownBy(() -> {
            new CorrectPersonalCommand(
                    null,
                    "홍길동",
                    "서울시 중구",
                    "01033894029",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new CorrectPersonalCommand(
                    1L,
                    null,
                    "서울시 중구",
                    "01033894029",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new CorrectPersonalCommand(
                    1L,
                    "홍길동",
                    null,
                    "01033894029",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new CorrectPersonalCommand(
                    1L,
                    "홍길동",
                    "서울시 중구",
                    null,
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

    }
}
