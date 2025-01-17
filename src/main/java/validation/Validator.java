package validation;


import database.Result;

import static java.lang.Math.abs;

public class Validator {

    public static Boolean check(Result point) {
        double x = point.getX();
        double y = point.getY();
        double R = point.getR();
        if (x >= 0 && y >= 0) return x <= R && y <= R;
        if (x >= 0 && y <= 0) return x + abs(y) <= R / 2;
        if (x <= 0 && y < 0) return false;
        return x * x + y * y <= R * R;
    }

    private static Boolean validateParam(double param, int min, int max) {
        return param >= min && param <= max;
    }

    public static boolean validate(Result point) {
        try {
            Validatable annotationX = point.getClass().getDeclaredField("x").getAnnotation(Validatable.class);
            Validatable annotationY = point.getClass().getDeclaredField("y").getAnnotation(Validatable.class);
            Validatable annotationR = point.getClass().getDeclaredField("r").getAnnotation(Validatable.class);
            return validateParam(point.getX(), annotationX.min(), annotationX.max()) &&
                    validateParam(point.getY(), annotationY.min(), annotationY.max()) &&
                    validateParam(point.getR(), annotationR.min(), annotationR.max());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
