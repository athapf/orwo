package de.thaso.orwo.fe.bean.overview;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OverviewModel
 *
 * @author thaler
 * @since 2017-06-06
 */
@Any
public class OverviewTableModel implements Serializable {

    private static final long serialVersionUID = -3371565388447341637L;

    private List<OverviewRowModel> overviewRowModelList = new ArrayList<>();

    public List<OverviewRowModel> getOverviewRowModelList() {
        return overviewRowModelList;
    }

    public void setOverviewRowModelList(final List<OverviewRowModel> overviewRowModelList) {
        this.overviewRowModelList = overviewRowModelList;
    }
}
