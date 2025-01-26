import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public abstract class System2D {

    // Specifying the dimensions
    public int VEC_SIZE = 2;

    // Initializing Vectors of a standard body

    // Standard SystemVector

    // All initial capacity of 2
    // this represents a 1x2 array for a 2D Vector and 1x3 array for a 3D Vector
    // 3D Vectors will come later

    // 4 Main Vector Quantities in a Basic "System"
    // vec = {1.0f, 2.0f}
    Vector<Float> Dimensions = new Vector<>(VEC_SIZE);
    Vector<Float> Forces = new Vector<>(VEC_SIZE);
    Vector<Float> Location = new Vector<>(VEC_SIZE);
    Vector<Float> Mass = new Vector<>(VEC_SIZE);

    // Consider possible overload functions for different dimensions, forces and locations
    public System2D(Vector<Float> dimensions, Vector<Float> Forces,
                    Vector<Float> Location, Vector<Float> Mass) {
        this.Dimensions = setDimensions(dimensions);
        this.Forces = setForces(Forces);
        this.Location = setLocation(Location);
        this.Mass = setMass(Mass);
    }

    // Dimension Accessors
    // Dimensions
    @NotNull
    public Vector<Float> getDimensions() { return this.Dimensions;}
    public Vector<Float> setDimensions(Vector<Float> dim) { return this.Dimensions = dim; }

    // Forces
    @NotNull
    public Vector<Float> getForces() { return this.Forces; }
    public Vector<Float> setForces(Vector<Float> dim) { return this.Forces = dim;}

    // Location
    @NotNull
    public Vector<Float> getLocation() { return this.Location; }
    public Vector<Float> setLocation(Vector<Float> dim) { return this.Location = dim; }

    // Mass
    @NotNull
    public Vector<Float> getMass() { return this.Mass; }
    public Vector<Float> setMass(Vector<Float> dim) { return this.Mass = dim; }

    public float getVectorMagnitude(Vector<Float> dim) {
        float xComp = dim.elementAt(0);
        float yComp = dim.elementAt(1);

        float length = (float)Math.sqrt((xComp * xComp) + (yComp * yComp));

        return length;
    }

    // Vector Operators
    // Add Vectors
    public Vector<Float> addVec(Vector<Float> u, Vector<Float> v) {

        Vector<Float> tempVec = new Vector<Float>(VEC_SIZE);
        // Initial Solution - All methods will be optimized in the future
        try {
            float xComp = u.elementAt(0) + v.elementAt(0);
            float yComp = u.elementAt(1) + v.elementAt(1);

            tempVec.add(xComp);
            tempVec.add(yComp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Write to Log Files
        }

        return tempVec;
    }

    // Subtract Vectors
    public Vector<Float> subtractVec(Vector<Float> u, Vector<Float> v) {
        Vector<Float> tempVec = new Vector<Float>(VEC_SIZE);
        // Initial Solution - All methods will be optimized in the future
        try {
            float xComp = u.elementAt(0) - v.elementAt(0);
            float yComp = u.elementAt(1) - v.elementAt(1);

            tempVec.add(xComp);
            tempVec.add(yComp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Write to Log Files
        }

        return tempVec;
    }

    // Normalize vector
    // Note: the access modifier could change to protected
    // based on whether other classes will extend this one (most likely will)
    private Vector<Float> normalizeVec(Vector<Float> u) {
        // Note:
        // The conversion here between float -> double is offputting for me
        // As the JVM is handling most of the type casting i believe
        // i should be able to optimize this in the future updates / patches
        Vector<Float> v = new Vector<Float>(2);
        try {
            float xComp = u.elementAt(0);
            float yComp = u.elementAt(1);

            double length = Math.sqrt((xComp * xComp) + (yComp * yComp));

            xComp /= length;
            yComp /= length;

            v.add(0, xComp);
            v.add(1, yComp);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    // Scale a Normalized Vector
    public Vector<Float> scaleVec(float val, Vector<Float> u) {
        Vector<Float> normal = normalizeVec(u); // Normalize the vector

        float xComp = normal.elementAt(0);
        float yComp = normal.elementAt(1);

        float xUpdated = (xComp *= val);
        float yUpdated = (yComp *= val);
        normal.clear(); // clear vector in space for new result

        normal.add(0, xUpdated); normal.add(0, yUpdated);

        return normal;
    }

    // Calculate Angle between two vectors
    public float calculateAngle(Vector<Float> u, Vector<Float> v) {
        float xComp = u.elementAt(0);
        float yComp = u.elementAt(1);

        float xComp2 = v.elementAt(0);
        float yComp2 = v.elementAt(1);

        float uLength = getVectorMagnitude(u);
        float vLength = getVectorMagnitude(v);

        float angle = (float)Math.acos(((xComp*xComp2) + (yComp*yComp2) / (uLength*vLength)));

        return angle;
    }

    // Dot Product of Two Vectors
    public float dotProdVec(Vector<Float> u, Vector<Float> v) {
        // Vector u components
        float uxComp = u.elementAt(0);
        float uyComp = u.elementAt(1);
        // Vector v components
        float vxComp = v.elementAt(0);
        float vyComp = v.elementAt(1);
        // Vector Lengths
        float uLength = (float)Math.sqrt((uxComp * uxComp) + (uyComp * uyComp));
        float vLength = (float)Math.sqrt((vxComp * vxComp) + (vyComp * vyComp));

        float angle = calculateAngle(u, v);

        return (float)((uLength * vLength) * Math.cos(angle));
    }

    // Debug Functions - For printing and testing
    public void vecOut(Vector<Float> dim) {
        float x = dim.elementAt(0);
        float y = dim.elementAt(1);

        System.out.println("X: " + x + "\nY: " + y);
    }

    // Vector Transformations and Matrix Operations
}
