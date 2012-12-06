package com.bs;

//~--- JDK imports ------------------------------------------------------------

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICityService {
    public List getAllCity();
}
