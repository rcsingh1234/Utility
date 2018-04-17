package com.mindtech.utilityclass.utility;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by RamChandra Singh on 27,September,2016
 * MindTech Solutions Pvt Ltd,
 * Mumbai , India.
 */
public class StringUtils {


    public static String blockCharacterSet = "~#^|$%&*!';";

    public static InputFilter filterText = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static boolean emailValidation(String str) {
        return str != null && android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }



}

