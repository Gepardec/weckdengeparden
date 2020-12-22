package com.gepardec.wdg.validation;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.challenge.model.AnswerChallenge2;
import com.gepardec.wdg.challenge.validation.URLValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class URLValidatorTest {

    //@WINStage3: Irgendwie ein komischer Test für den URL-Validator, findest Du nicht auch?
    @Test
    void musterTest() {
        final int x = 5;
        Assertions.assertTrue(x == 7);
    }

}
