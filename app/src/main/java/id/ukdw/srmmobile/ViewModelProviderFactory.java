package id.ukdw.srmmobile;

import androidx.annotation.NonNull;
import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile
 * <p>
 * User: dendy
 * Date: 30/08/2020
 * Time: 7:14
 * <p>
 * Description : ViewModelProviderFactory
 */
public class ViewModelProviderFactory<T extends ViewModel> extends ViewModelProvider.NewInstanceFactory {
    private final Class<T> viewModelClass;
    private final Supplier<T> viewModelSupplier;

    public ViewModelProviderFactory(Class<T> viewModelClass, Supplier<T> viewModelSupplier) {
        this.viewModelClass = viewModelClass;
        this.viewModelSupplier = viewModelSupplier;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(viewModelClass)) {
            return (T) viewModelSupplier.get();
        } else {
            throw new IllegalArgumentException("Unknown Class name " + viewModelClass.getName());
        }
    }
}
