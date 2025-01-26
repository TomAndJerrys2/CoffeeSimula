import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<Float> ObjDims = new Vector<>();
        ObjDims.add(10.0f);
        ObjDims.add(20.0f);

        Vector<Float> LocDims = new Vector<>();
        LocDims.add(-0.958f);
        LocDims.add(0.32f);

        Vector<Float> MassDims = new Vector<>();
        MassDims.add(-10.0f);
        MassDims.add(0f);

        Vector<Float> ForceDims = new Vector<>();
        ForceDims.add(MassDims.indexOf(0) * 9.81f);
        ForceDims.add(0f);

        System2D mySystem = new System2D(ObjDims, ForceDims, LocDims, MassDims) {};

    }
}