package br.com.jeff.listapro.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTimeUtils {

    // Definição do formato padrão
    private static final DateTimeFormatter FORMATTER = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Converte LocalDateTime para String formatada.
     */
    public static String format(LocalDateTime dateTime) {
        return Optional.ofNullable(dateTime)
                .map(FORMATTER::format)
                .orElse("");
    }

    /**
     * Converte String formatada para LocalDateTime.
     */
    public static LocalDateTime parse(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }

    /**
     * Retorna o formatter (caso precise em outros lugares).
     */
    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }
}
