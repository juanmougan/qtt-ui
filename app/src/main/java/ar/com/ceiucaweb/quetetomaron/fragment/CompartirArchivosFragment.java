package ar.com.ceiucaweb.quetetomaron.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ar.com.ceiucaweb.quetetomaron.R;
import ar.com.ceiucaweb.quetetomaron.adapter.MateriaAdapter;
import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;
import ar.com.ceiucaweb.quetetomaron.entidad.Materia;
import ar.com.ceiucaweb.quetetomaron.manager.CarreraMockManager;
import ar.com.ceiucaweb.quetetomaron.manager.MateriaMockManager;
import ar.com.ceiucaweb.quetetomaron.manager.QttCarreraManager;
import ar.com.ceiucaweb.quetetomaron.manager.QttMateriaManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompartirArchivosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompartirArchivosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompartirArchivosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = CompartirArchivosFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private QttCarreraManager carreraManager;
    private QttMateriaManager materiaManager;

    private List<Materia> materiasDeCarrera = new ArrayList<Materia>(0);
    private ListView materiasListView;
    private Spinner carreraSpinner;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompartirArchivosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompartirArchivosFragment newInstance(String param1, String param2) {
        CompartirArchivosFragment fragment = new CompartirArchivosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CompartirArchivosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO refactor - esto va en la Activity?
        carreraManager = CarreraMockManager.newInstance();
        materiaManager = MateriaMockManager.newInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar el layout del Fragment y setear el contenido de los Spinners
        View view = inflater.inflate(R.layout.fragment_compartir_archivos, container, false);

        this.carreraSpinner = prepararSpinnerCarrera(view);
        this.materiasListView = prepararListViewMateria(view);
        configurarListenerParaCargarMaterias();

        return view;
    }

    /**
     * Crear un Listener para cargar dinámicamente las Materias de la Carrera elegida
     */
    private void configurarListenerParaCargarMaterias() {
        this.carreraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "seleccionado item en posición: " + position);
                Carrera carreraSeleccionada = (Carrera) parent.getSelectedItem();
                CompartirArchivosFragment.this.materiasDeCarrera = CompartirArchivosFragment.this.
                        materiaManager.fetchMateriasDeCarrera(carreraSeleccionada);
                // Custom adapter? ver la tarjeta: https://trello.com/c/dl4Pkby2
                MateriaAdapter adapter = (MateriaAdapter) CompartirArchivosFragment.this.
                        materiasListView.getAdapter();
                // ArrayAdapter<Materia> adapter = (ArrayAdapter<Materia>) materiasListView.getAdapter();
                adapter.getFilter().filter(carreraSeleccionada.getNombre());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO puedo limpiar la lista desde una inner class?
                Log.d(TAG, "No se seleccionó nada");
            }
        });
    }

    private Spinner prepararSpinnerCarrera(View view) {
        Spinner carrerasSpinner = (Spinner) view.findViewById(R.id.carrera_spin);
        List<Carrera> carreras = carreraManager.fetchAllCarreras();
        ArrayAdapter<Carrera> carrerasAdapter = new ArrayAdapter<Carrera>(getActivity(),
                android.R.layout.simple_list_item_1, carreras);
        // Specify the layout to use when the list of choices appears
        carrerasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        carrerasSpinner.setAdapter(carrerasAdapter);
        return carrerasSpinner;
    }

    private ListView prepararListViewMateria(View view) {
        ListView materiasListView = (ListView) view.findViewById(R.id.materia_list);
        // ArrayAdapter<Materia> materiasAdapter = new ArrayAdapter<Materia>(getActivity(),
        //         android.R.layout.simple_list_item_1, materiasDeCarrera);
        MateriaAdapter materiasAdapter = MateriaAdapter.newInstance(getActivity(), materiasDeCarrera);
        materiasListView.setAdapter(materiasAdapter);
        return materiasListView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
