package com.gepardec.wdg.client.personio.error;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PersonioError {
    ALREADY_APPLIED("{error:Applicant already applied to this position.}",
            "Du bist hartnäckig, dass gefällt uns. Leider wurde deine E-Mail-Adresse für diesen Job bereits verwendet. Für eine neue Bewerbung verwende bitte auch eine neue E-Mail-Adresse."),
    JOBID_NOT_VALID("{success:false,error:{code:0,message:The given data was invalid.,errors:{job_position_id:[The job position id must be an integer.]}}}",
            "Du bist nur noch einen kleinen Schritt von deinem Ziel entfernt. Die JobID ist fehlerhaft. Wirf einen Blick in die Jobausschreibung."),
    JOBID_NOT_FOUND("{error:Could not find the job position}",
            "Du bist nur noch einen kleinen Schritt von deinem Ziel entfernt. Die JobID ist fehlerhaft. Wirf einen Blick in die Jobausschreibung."),
    JOBID_EMPTY("{success:false,error:{code:0,message:The given data was invalid.,errors:{job_position_id:[Bitte geben Sie job position id ein, um fortzufahren.]}}}",
            "Du bist nur noch einen kleinen Schritt von deinem Ziel entfernt. Die JobID ist nicht vorhanden. Wirf einen Blick in die Jobausschreibung."),
    JOB_NOT_PUBLISHED("{error:The job position is not published}", "Netter Versuch. Ein Gepard war leider etwas schneller als du. Dieser Job ist nicht mehr verfügbar."),
    UNDEFINED("",
            "Hallelujah! In unser Service hat sich ein unerwarteter Fehler eingeschlichen. Unser DevOps-Team wurde soeben verständigt und löst das Problem. Wir informieren dich, sobald unser Service wieder verfügbar ist.Weitere Infos zum Support findest du hier >> <https://github.com/Gepardec/weckdengeparden/wiki>");

    private static final Map<String, PersonioError> applicationErrors = Stream.of(PersonioError.values())
            .collect(Collectors.toMap(error -> error.message.toUpperCase(), Function.identity()));
    public final String message;
    public final String clientMessage;

    PersonioError(final String message) {
        this(message, null);
    }

    PersonioError(final String message, final String clientMessage) {
        this.message = message;
        this.clientMessage = clientMessage;
    }

    public static PersonioError forMessage(final String message) {
        return Optional.ofNullable(applicationErrors.get(Optional.of(message).orElse("").toUpperCase()))
                .orElse(PersonioError.UNDEFINED);
    }

    public String getMessage() {
        return message;
    }

    public String getClientMessage() {
        return clientMessage;
    }
}
