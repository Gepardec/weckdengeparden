package com.gepardec.wdg.application.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLValidatorTest {

    private URLValidator URLValidator;

    //@WINStage3: Wenn auch du denkst, das dieser Test nicht wirklich sinnvoll ist,
    // feel free, und schreib uns ein paar Eigene.
    @Test
    void test() {
        String b = "https://die-lösung-bekommst.net";
        Assertions.assertTrue(b == "starwars4live.net");
    }


}
