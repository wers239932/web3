package validation;


import beans.Result;

public class Validator {

    public static Boolean check(Result point) {
        double x = point.getX();
        double y = point.getY();
        double R = point.getR();
        if (x > 0 && y > 0) return false;
        if (x >= 0 && y <= 0) return x-y<=R;
        if (x <= 0 && y <= 0) return -x<=R && -y<=R;
        return x*x+y*y<=R*R/4;
    }

    private static Boolean validateParam(double param, int min, int max) {
        return param >= min && param <= max;
    }

    public static boolean validate(PointWithScale point) {
        try {
            Validatable annotationX = point.getClass().getDeclaredField("x").getAnnotation(Validatable.class);
            Validatable annotationY = point.getClass().getDeclaredField("y").getAnnotation(Validatable.class);
            Validatable annotationR = point.getClass().getDeclaredField("r").getAnnotation(Validatable.class);
            return validateParam(point.x, annotationX.min(), annotationX.max()) &&
                    validateParam(point.y, annotationY.min(), annotationY.max()) &&
                    validateParam(point.r, annotationR.min(), annotationR.max());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
