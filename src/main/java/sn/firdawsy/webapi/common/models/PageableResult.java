package sn.firdawsy.webapi.common.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by nureynisow on 12/04/2018.
 * for firdawsy
 */
@Data
@Builder
@NoArgsConstructor
public class PageableResult<T> {


    private int nbPages;

    private long nbElements;

    private List<T> content;

    public PageableResult(final int nbPages, final long nbElements, final List<T> content) {
        this.nbElements = nbElements;
        this.nbPages = nbPages;
        this.content = content;
    }

}
