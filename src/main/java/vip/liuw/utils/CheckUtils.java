package vip.liuw.utils;

public class CheckUtils {

    public static boolean isNull(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof CharSequence) {
            return StringUtils.isBlank((String) obj);
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        return false;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isAnyNull(Object... objs) {
        for (Object obj : objs) {
            if (isNull(obj))
                return true;
        }
        return objs.length == 0;
    }

    public static boolean isNoneNull(Object... objs) {
        for (Object obj : objs) {
            if (isNull(obj))
                return false;
        }
        return objs.length > 0;
    }

    public static boolean isAllNull(Object... objs) {
        for (Object obj : objs) {
            if (isNotNull(obj))
                return false;
        }
        return true;
    }
}
