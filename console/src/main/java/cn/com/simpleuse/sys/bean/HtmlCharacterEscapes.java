package cn.com.simpleuse.sys.bean;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.springframework.web.util.HtmlUtils;


/**
 * read more
 * <p>
 * https://github.com/rwinch/spring-jackson-owasp
 * <p>
 * https://www.owasp.org/index.php/XSS_(Cross_Site_Scripting)_Prevention_Cheat_Sheet#Output_Encoding_Rules_Summary
 * <p>
 * https://stackoverflow.com/questions/25403676/initbinder-with-requestbody-escaping-xss-in-spring-3-2-4
 * <p>
 * <p>
 * <p>
 * just for response json xss
 */


public class HtmlCharacterEscapes extends CharacterEscapes {
    private static final long serialVersionUID = -6452567429985199639L;
    private final int[] ESCAPES;

    public HtmlCharacterEscapes() {
        ESCAPES = standardAsciiEscapesForJSON();
        for (int i = 0; i < ESCAPES.length; i++) {
//            if(!(Character.isAlphabetic(i) || Character.isDigit(i))) {  // for JavaScript Encoding
            if (i == '<' || i == '>') { //  || i == '\'' || i == '\"' || i  == '&'|| i  == '/'   // for HTML Entity Encoding
                ESCAPES[i] = CharacterEscapes.ESCAPE_CUSTOM;
            }
        }
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
//        String unicode = String.format("\\u%04x", ch)
//        String unicode = Encode.forHtml(Character.toString((char)ch));
        String unicode = HtmlUtils.htmlEscape(Character.toString((char) ch));
        return new SerializedString(unicode);
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return ESCAPES;
    }
}
