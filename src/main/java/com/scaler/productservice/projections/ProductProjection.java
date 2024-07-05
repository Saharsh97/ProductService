package com.scaler.productservice.projections;

import lombok.Setter;

// who is Setting the values?
// Spring Sets the values of this object, from the MySQL rows.
// are you ever supposed to set the values by yourself? ___

// If you had made this a class, you can use @Setters,
// and set any value as needed.
// since you are getting values from DB,
// No one should be able to SET any other value on this Projection

// Spring wants you to only read the values. And never set them.
// Spring wants you to only have get() behaviours => interface.
public interface ProductProjection {
    String getId();
    String getName();
}
