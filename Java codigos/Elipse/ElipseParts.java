public class ElipseParts {
    public static Elipse getElipseParts(Point center, double majorAxis, double minorAxis,
            double angleOfRotation) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null");
        }

        if (majorAxis < minorAxis) {
            throw new IllegalArgumentException("Major axis must be greater than the minor axis");
        }

        if (majorAxis < 0 || majorAxis == 0 || minorAxis < 0 || minorAxis == 0) {
            throw new IllegalArgumentException("Axis cannot be negative nor 0");
        }

        double hPointOfCenter = center.getX();
        double kPointOfCenter = center.getY();
        double semiaxisMajor = majorAxis / 2;
        double semiaxisMinor = minorAxis / 2;
        double distanceToFocus;
        Point focusOneCoordinates = new Point(0, 0);
        Point focusTwoCoordinates = new Point(0, 0);
        double angleInRads = Math.toRadians(angleOfRotation);
        Point vertexCoordinates = new Point(0, 0);
        Point oppositeVertexCoordinates = new Point(0, 0);

        String canonicEquation = String.format("(x - %.2f)^2 / %.2f^2 + (y - %.2f)^2 / %.2f^2 = 1", hPointOfCenter,
                semiaxisMajor, kPointOfCenter, semiaxisMinor);

        vertexCoordinates.xPoint = hPointOfCenter + Math.cos(angleInRads) * semiaxisMajor;
        vertexCoordinates.yPoint = kPointOfCenter + Math.sin(angleInRads) * semiaxisMajor;

        oppositeVertexCoordinates.xPoint = hPointOfCenter - Math.cos(angleInRads) * semiaxisMajor;
        oppositeVertexCoordinates.yPoint = kPointOfCenter - Math.sin(angleInRads) * semiaxisMajor;

        distanceToFocus = Math.sqrt(Math.pow(semiaxisMajor, 2) - Math.pow(semiaxisMinor, 2));

        focusOneCoordinates.xPoint = hPointOfCenter + Math.cos(angleInRads) * distanceToFocus;
        focusOneCoordinates.yPoint = kPointOfCenter + Math.sin(angleInRads) * distanceToFocus;

        focusTwoCoordinates.xPoint = hPointOfCenter - Math.cos(angleInRads) * distanceToFocus;
        focusTwoCoordinates.yPoint = kPointOfCenter - Math.sin(angleInRads) * distanceToFocus;

        return new Elipse(canonicEquation, vertexCoordinates, oppositeVertexCoordinates, focusOneCoordinates,
                focusTwoCoordinates);
    }

    public static class Elipse {
        String canonicEquation;
        Point vertexCoordinates;
        Point oppositeVertexCoordinates;
        Point focusOneCoordinates;
        Point focusTwoCoordinates;

        Elipse(String canonicEquation, Point vertexCoordinates, Point oppositeVertexCoordinates,
                Point focusOneCoordinates, Point focusTwoCoordinates) {
            this.canonicEquation = canonicEquation;
            this.vertexCoordinates = vertexCoordinates;
            this.oppositeVertexCoordinates = vertexCoordinates;
            this.focusOneCoordinates = focusOneCoordinates;
            this.focusTwoCoordinates = focusTwoCoordinates;
        }

        @Override
        public boolean equals(Object otherObject) {
            if (this == otherObject)
                return true;
            if (otherObject == null || getClass() != otherObject.getClass())
                return false;

            Elipse otherElipse = (Elipse) otherObject;
            if (!canonicEquation.equals(otherElipse.canonicEquation)) {
                return false;
            }
            if (!vertexCoordinates.equals(otherElipse.vertexCoordinates)) {
                return false;
            }
            if (!oppositeVertexCoordinates.equals(otherElipse.oppositeVertexCoordinates)) {
                return false;
            }
            if (!focusOneCoordinates.equals(otherElipse.focusOneCoordinates)) {
                return false;
            }
            if (!focusTwoCoordinates.equals(otherElipse.focusTwoCoordinates)) {
                return false;
            }
            return true;
        }

    }

    public static class Point {
        public double xPoint;
        public double yPoint;

        public Point(double xPoint, double yPoint) {
            this.xPoint = xPoint;
            this.yPoint = yPoint;
        }

        public double getX() {
            return xPoint;
        }

        public double getY() {
            return yPoint;
        }

        public boolean equals(Object otherObject) {
            if (this == otherObject)
                return true;
            if (otherObject == null || getClass() != otherObject.getClass())
                return false;

            Point otherPoint = (Point) otherObject;
            return Math.abs(otherPoint.xPoint - xPoint) < 1e-2 && Math.abs(otherPoint.yPoint - yPoint) < 1e-2;
        }
    }
}