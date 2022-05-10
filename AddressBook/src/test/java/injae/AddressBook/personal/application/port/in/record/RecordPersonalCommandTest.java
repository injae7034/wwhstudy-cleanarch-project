package injae.AddressBook.personal.application.port.in.record;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.*;

class RecordPersonalCommandTest {
    @Test
    @DisplayName("RecordPersonalCommand 유효성검사")
    public void recordPersonalCommand_validate_test() {

        assertThatThrownBy(() -> {
            new RecordPersonalCommand(null,
                    "서울시 중구",
                    "01033894029",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand("홍길동",
                    null,
                    "01033894029",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand("홍길동",
                    "서울시 중구",
                    null,
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand(null,
                    null,
                    null,
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand("",
                    "",
                    "",
                    "hong@naver.com");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand("",
                    "",
                    "",
                    "");
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand("",
                    "",
                    "",
                    null);
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            new RecordPersonalCommand(null,
                    null,
                    null,
                    null);
        }).isInstanceOf(ConstraintViolationException.class);
    }
}
