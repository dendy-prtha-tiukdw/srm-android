package id.ukdw.srmmobile.ui.pengumuman;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.UpdateSemingguResponse;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.pengumuman
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:38
 * <p>
 * Description : PengumumanNavigator
 */

public interface PengumumanNavigator {
    void onGetListPengumuman(List<UpdateSemingguResponse> pengumumanResponseList);
    void isLoading(boolean flag);

    void onGetError();

    void onServerError();
}
