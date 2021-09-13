package com.ljy.earnpoint.domain.infra;

import com.ljy.earnpoint.domain.values.Point;

import javax.persistence.AttributeConverter;
import java.util.Objects;


public class PointConveter implements AttributeConverter<Point, Long> {

    @Override
    public Long convertToDatabaseColumn(Point point) {
        return point.get();
    }

    @Override
    public Point convertToEntityAttribute(Long won) {
        return Point.won(won);
    }
}
