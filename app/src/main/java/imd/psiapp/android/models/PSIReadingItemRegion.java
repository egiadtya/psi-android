package imd.psiapp.android.models;

/**
 * this is interface for provide psi item data per region
 * for example if want to get psi only for east region
 * Created by egiadtya on 7/26/17.
 */

public interface PSIReadingItemRegion {
    /**
     * for getting psi data based on region
     *
     * @param region {@link String} of region, the value should be like <p>
     *               {@value PSIReadingsItem#EAST},
     *               {@value PSIReadingsItem#NATIONAL},
     *               {@value PSIReadingsItem#CENTRAL},
     *               {@value PSIReadingsItem#WEST},
     *               {@value PSIReadingsItem#NORTH},
     *               {@value PSIReadingsItem#SOUTH}
     *               </p>
     */
    double getData(String region);
}
