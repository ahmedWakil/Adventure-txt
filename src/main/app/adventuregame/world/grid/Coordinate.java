package adventuregame.world.grid;

import adventuregame.world.grid.Exceptions.IncompatibleSizeException;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {
     private List<Integer> v;
     private final int size;

     public Coordinate(List<Integer> v) {
         this.size = v.size();
         this.v = v;
     }

     public int getCoordinateAti(int i) {
         return v.get(i);
     }

     public int getSize() {
         return size;
     }

     public Coordinate add(Coordinate a) throws IncompatibleSizeException {
         return add(this, a);
     }

     public static Coordinate add(Coordinate a, Coordinate b) throws IncompatibleSizeException {
         if (a.getSize() != b.getSize())  {
             throw new IncompatibleSizeException("Coordinates with different sizes cannot be added!");
         }

         List<Integer> result = new ArrayList<>(a.getSize());
         for (int i = 0; i < a.getSize(); i++) {
             result.add(a.getCoordinateAti(i) + b.getCoordinateAti(i));
         }

         return new Coordinate(result);
     }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!this.getClass().getName().equals(obj.getClass().getName())) {
            return false;
        }

        Coordinate u = (Coordinate) obj;
        if(this.size != u.size) {
            return false;
        }

        for (int i = 0; i < this.size; i++) {
            if (this.getCoordinateAti(i) != u.getCoordinateAti(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 1 + 7*this.v.hashCode() + 31*Integer.hashCode(size);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("(");

        for(Integer i : v) {
            result.append(i);
            result.append(", ");
        }

        result.delete(result.length()-2, result.length());
        result.append(")");
        return result.toString();
    }
}
