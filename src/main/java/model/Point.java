package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private double xVal;
    private double yVal;
    private double rVal = 1.0;
    private long timezone;
}
