package com.api.arithmeticcalculator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Commons {

    static public String getUtcNow (){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.atOffset(ZoneOffset.of("-03:00")).format(formatter);
    }
}
