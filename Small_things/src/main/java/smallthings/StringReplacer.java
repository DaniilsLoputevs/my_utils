package smallthings;

import java.util.Map;

public class StringReplacer {
    private final Map<Character, Character> map = Map.ofEntries(
            Map.entry('Ъ', 'Ч'),
            Map.entry('г', 'а'),
            Map.entry('ф', 'с'),
            Map.entry('х', 'т'),
            Map.entry('я', 'ь'),
            Map.entry('ч', 'ф'),
            Map.entry('л', 'и'),
            Map.entry('Л', 'и'),
            Map.entry('р', 'н'),
            Map.entry('о', 'л'),
            Map.entry('с', 'о'),
            Map.entry('ё', 'г'),
            Map.entry('ы', 'ш'),
            Map.entry('у', 'р'),

            Map.entry('ж', 'д'),
            Map.entry('в', 'я'),
            Map.entry('б', 'ю'),
            Map.entry('Д', 'Б'),
            Map.entry('д', 'б'),
            Map.entry('з', 'е'),
            Map.entry('м', 'й'),
            Map.entry('т', 'п'),
            Map.entry('ш', 'х'),
            Map.entry('н', 'к'),
            Map.entry('к', 'з'),

            Map.entry('ц', 'у'),
            Map.entry('е', 'в'),
            Map.entry('п', 'м'),
            Map.entry('ю', 'ы'),
            Map.entry('й', 'ж'),
            Map.entry('а', 'э')

//            Map.entry('е', 'и'),
//            Map.entry('Т', 'Б'), // б в Ж З
//            Map.entry('т', 'б')

    );

    private String convert(String source) {
        var builder = new StringBuilder();
        for (var each : source.toCharArray()) {
            var temp = map.get(each);
            if (temp != null) {
                builder.append(temp);
            } else {
                builder.append(Character.toUpperCase(each));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String rsc = "Ъгфхя члргоярсёс ылчуг: 0. Лрчг " +
                "жов обдлхзозм тгфшгосн: е ахсм кгёгжнз ефз тгугпзхую егйрю. Дцжяхз ерлпгхзоярю!";

        String rsl = new StringReplacer().convert(rsc);

        System.out.println(rsc);
        System.out.println(rsl);

    }
}
