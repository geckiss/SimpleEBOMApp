package web;

public class Part {

    private String _type;
    private String _name;
    private int _length;
    private int _width;
    private double _weight;
    private double _cost;

    public Part(String pType, String pName, int pLength, int pWidth, double pWeight, double pCost) {
        if (pType != "" && pType != null) {
            _type = pType;
        }

        if (pName != "" && pName != null) {
            _name = pName;
        }

        if (pLength >= 0) {
            _length = pLength;
        }

        if (pWidth >= 0) {
            _width = pWidth;
        }

        if (pWeight >= 0.0) {
            _weight = pWeight;
        }

        if (pCost >= 0.0) {
            _cost = pCost;
        }

    }

    // Getters
    public String GetType() {
        return this._type;
    }

    public String GetName() {
        return this._name;
    }

    public int GetLength() {
        return this._length;
    }

    public int GetWidth() {
        return this._width;
    }

    public double GetWeight() {
        return this._weight;
    }

    public double GetCost() {
        return this._cost;
    }

    // Setters
    public boolean SetType(String newType) {
        if (newType != "" && newType != null) {
            this._type = newType;
            return true;
        }

        return false;
    }

    public boolean SetName(String newName) {
        if (newName != "" && newName != null) {
            this._name = newName;
            return true;
        }

        return false;
    }

    public boolean SetLength(int newLength) {
        if (newLength >= 0) {
            this._length = newLength;
            return true;
        }

        return false;
    }

    public boolean SetWidth(int newWidth) {
        if (newWidth >= 0) {
            this._width = newWidth;
            return true;
        }

        return false;
    }

    public boolean SetWeight(double newWeight) {
        if (newWeight >= 0.0) {
            this._weight = newWeight;
            return true;
        }

        return false;
    }

    public boolean SetCost(double newCost) {
        if (newCost >= 0.0) {
            this._cost = newCost;
            return true;
        }

        return false;
    }
}
