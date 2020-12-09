package id.ukdw.srmmobile.ui.daftarkelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.KelasResponse;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.daftarkelas
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:21
 * <p>
 * Description : DaftarKelasNavigator
 */
public interface DaftarKelasNavigator {

    void updateListDaftarKelas(List<KelasResponse> kelasList);
    void onGetError();
    void onServerError();
}
