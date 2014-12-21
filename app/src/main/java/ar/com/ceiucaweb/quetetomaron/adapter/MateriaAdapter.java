package ar.com.ceiucaweb.quetetomaron.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import ar.com.ceiucaweb.quetetomaron.entidad.Materia;
import ar.com.ceiucaweb.quetetomaron.manager.MateriaMockManager;
import ar.com.ceiucaweb.quetetomaron.manager.QttMateriaManager;

/**
 * Adapter customizado para poder filtrar la ListView desde el Spinner
 * Created by juanmougan@gmail.com on 11/12/14.
 */
public class MateriaAdapter extends ArrayAdapter<Materia> implements Filterable {

    private MateriaFilter materiaFilter;
    private List<Materia> materiasList;

    public static MateriaAdapter newInstance(Context context, List<Materia> objects) {
        return new MateriaAdapter(context, android.R.layout.simple_list_item_1, objects);
    }

    private MateriaAdapter(Context context, int resource, List<Materia> objects) {
        super(context, resource, objects);
    }

    @Override
    public Filter getFilter() {
        if (this.materiaFilter == null) {
            this.materiaFilter = new MateriaFilter();
        }
        return this.materiaFilter;
    }

    private class MateriaFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // Si no hay valores que filtrar, devuelvo toda la lista
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = constraint;
                results.count = 0;
            } else {
                QttMateriaManager materiaManager = MateriaMockManager.newInstance();
                List<Materia> materiasFiltradas = materiaManager.fetchMateriasDeCarreraPorNombre((String) constraint);
                results.values = materiasFiltradas;
                results.count = constraint.length();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Aca se notifica al Adapter
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                materiasList = (List<Materia>) results.values;
                Log.d(MateriaAdapter.class.getSimpleName(), materiasList.toString());
                notifyDataSetChanged();
            }
        }
    }

}
