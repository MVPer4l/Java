import java.lang.Math;

public class Distancebetweenearth {
    public static void main(String[] args) {

        // TEST
        assert Math.abs(Distancebetweenearth.getDistancebetweenearth(90, -179, -89.5, -100) - 19959.48933) < 1e-2;

        // TEST_END

        // TEST
        assert Math.abs(Distancebetweenearth.getDistancebetweenearth(55, 85, 70, 77) - 1714.28983) < 1e-2;

        // TEST_END

        // TEST
        try {
            Distancebetweenearth.getDistancebetweenearth(1850, 50, 40, -30);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            Distancebetweenearth.getDistancebetweenearth(180, 90.1, 70, -50);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

    }

    public static double getDistancebetweenearth(double LatitudeOne, double LongitudeOne, double LatitudeTwo,
            double LongitudeTwo) {

        if (Math.abs(LatitudeOne) > 90 || Math.abs(LatitudeTwo) > 90) {
            throw new IllegalArgumentException("Latitude must be -90 to 90");
        }

        if (Math.abs(LongitudeOne) > 180 || Math.abs(LongitudeTwo) > 180) {
            throw new IllegalArgumentException("Longitude must be -180 to 180");
        }

        int r = 6371;
        double Distance;
        double RadiansLatitudeOne = Math.toRadians(LatitudeOne);
        double RadiansLongitudeOne = Math.toRadians(LongitudeOne);
        double RadiansLatitudeTwo = Math.toRadians(LatitudeTwo);
        double RadiansLongitudeTwo = Math.toRadians(LongitudeTwo);

        double deltalatitude = RadiansLatitudeTwo - RadiansLatitudeOne;
        double deltalongitude = RadiansLongitudeTwo - RadiansLongitudeOne;
        double cosLatOne = Math.cos(RadiansLatitudeOne);
        double cosLatTwo = Math.cos(RadiansLatitudeTwo);

        double deltaLatSinSquare = Math.sin((deltalatitude) / 2) * Math.sin((deltalatitude) / 2);
        double deltaLongSinSquare = Math.sin((deltalongitude) / 2) * Math.sin((deltalongitude) / 2);

        double a = deltaLatSinSquare + cosLatOne * cosLatTwo * deltaLongSinSquare;

        Distance = 2 * r * Math.asin(Math.sqrt(a));
        System.out.println(Distance);
        return Distance;

    }
}
