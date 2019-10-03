package pl.coderslab.app.converters;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PolishDayAndMonthNamesConverter {
    private String[][] monthEnglishPolishNames = {
            {"january","styczeń"}
            ,{"february","luty"}
            ,{"march","marzec"}
            ,{"april","kwiecień"}
            ,{"may","maj"}
            ,{"june","czerwiec"}
            ,{"july","lipiec"}
            ,{"august","sierpień"}
            ,{"september","wrzesień"}
            ,{"october","październik"}
            ,{"november","listopad"}
            ,{"december","grudzień"}};

    private String[][] dayEnglishPolishNames = {
            {"monday","poniedziałek"}
            ,{"tuesday","wtorek"}
            ,{"wednesday","środa"}
            ,{"thursday","czwartek"}
            ,{"friday","piątek"}
            ,{"saturday","sobota"}
            ,{"sunday","niedziela"}};

    public String parseMonthEnglishToPolish(String englishDay) {
        return Arrays.stream(monthEnglishPolishNames)
                .filter(m->m[0].equals(englishDay.toLowerCase()))
                .collect(Collectors.toList()).get(0)[1].toUpperCase();
    }

    public String parseDayEnglishToPolish(String englishDay) {
        return Arrays.stream(dayEnglishPolishNames)
                .filter(d->d[0].equals(englishDay.toLowerCase()))
                .collect(Collectors.toList()).get(0)[1].toUpperCase();
    }
}
