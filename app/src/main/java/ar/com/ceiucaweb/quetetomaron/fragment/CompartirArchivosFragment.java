package ar.com.ceiucaweb.quetetomaron.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ar.com.ceiucaweb.quetetomaron.R;
import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;
import ar.com.ceiucaweb.quetetomaron.manager.CarreraMockManager;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO refactor del llenado de Spinners!
        // Inflar el layout del Fragment y setear el contenido de los Spinners
        View view = inflater.inflate(R.layout.fragment_compartir_archivos, container, false);

        // Obtener las listas de Carrera y Materia
        QttCarreraManager carreraManager = CarreraMockManager.newInstance();

        Spinner carrerasSpinner = (Spinner) view.findViewById(R.id.carrera_spin);
        List<Carrera> carreras = carreraManager.fetchAllCarreras();
        // String[] nombresCarreras = {"Ambiental", "Electrónica", "Informática"};
        ArrayAdapter<Carrera> carrerasAdapter = new ArrayAdapter<Carrera>(getActivity(),
                android.R.layout.simple_list_item_1, carreras);
        // Specify the layout to use when the list of choices appears
        carrerasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        carrerasSpinner.setAdapter(carrerasAdapter);

        Spinner materiasSpinner = (Spinner) view.findViewById(R.id.materia_spin);
        ArrayAdapter<String> materiasAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        materiasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        materiasSpinner.setAdapter(materiasAdapter);

        return view;
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
