package xyz.miaw.android.base.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import xyz.miaw.android.base.activities.AccountDetailsActivity;
import xyz.miaw.android.base.adapters.AccountsRecyclerViewAdapter;
import xyz.miaw.android.base.models.SharedAccount;

public class AccountsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View _view;
    private RecyclerView _recyclerView;
    private AccountsRecyclerViewAdapter _adapter;
    private SwipeRefreshLayout _swipeRefreshLayout;
    private List<SharedAccount> _sharedAccounts;
    private ProgressBar _progresBar;

    private int _page;
    private int _count;

//    private OnFragmentInteractionListener mListener;

    public AccountsFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AccountsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AccountsFragment newInstance(String param1, String param2) {
//        AccountsFragment fragment = new AccountsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(xyz.miaw.android.base.R.layout.fragment_accounts, container, false);

        // Init
        _view = view;
        _page = 1;
        _count = 0;

        _sharedAccounts = new ArrayList<SharedAccount>();
        _recyclerView = view.findViewById(xyz.miaw.android.base.R.id.recyclerview_accounts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        _recyclerView.setLayoutManager(layoutManager);


        // Adapter
        // Click
        AccountsRecyclerViewAdapter.AccountsRecyclerViewOnClickListener accountsRecyclerViewOnClickListener
                = (itemRecyclerView, accountNumber) -> {

            Intent intent = new Intent(this.getContext(), AccountDetailsActivity.class);
            intent.putExtra("", "");
            startActivity(intent);

        };

        _adapter = new AccountsRecyclerViewAdapter(_sharedAccounts, accountsRecyclerViewOnClickListener);
        _recyclerView.setAdapter(_adapter);

        _swipeRefreshLayout = view.findViewById(xyz.miaw.android.base.R.id.swiperefreshlayout_accounts);
        _swipeRefreshLayout.setColorSchemeColors(getResources().getColor(xyz.miaw.android.base.R.color.colorAccent));
        _swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // reset page count
                _page = 1;
                _count = 0;

                // clear items
                _sharedAccounts.clear();
                _adapter.removeAllFromList();

                // get accounts
                SharedAccount sharedAccount1 = new SharedAccount();
                sharedAccount1.AccountNumber = "dfsgdsfg";
                sharedAccount1.BakiOrNilai = 98.42;
                sharedAccount1.AccountStatus1 = "gg";
                sharedAccount1.AccountStatus2 = "ss";

                // populate
                _sharedAccounts.add(sharedAccount1);
                _adapter.addToList(_sharedAccounts);

                if (_sharedAccounts.size() == 0){
                    _recyclerView.setVisibility(View.GONE);
                    LinearLayout noOrdersLayout = _view.findViewById(xyz.miaw.android.base.R.id.linearlayout_accounts_noordersview);
                    noOrdersLayout.setVisibility(View.VISIBLE);
                }

                _recyclerView.scheduleLayoutAnimation();

                // mark refresh as finished
                _swipeRefreshLayout.setRefreshing(false);
            }
        });
        // TODO: refresh listener

        _progresBar = view.findViewById(xyz.miaw.android.base.R.id.progressbar_accounts);

        // Populate RecyclerView
        populateRecyclerView();

        return view;
    }

    private void populateRecyclerView(){

        _progresBar.setVisibility(View.GONE);

        SharedAccount sharedAccount1 = new SharedAccount();
        sharedAccount1.AccountNumber = "sxdasfd";
        sharedAccount1.BakiOrNilai = 20.45;
        sharedAccount1.AccountStatus1 = "xx";
        sharedAccount1.AccountStatus2 = "yy";

        _sharedAccounts.add(sharedAccount1);
        _adapter.addToList(_sharedAccounts);

        if (_sharedAccounts.size() == 0){
            _recyclerView.setVisibility(View.GONE);
            LinearLayout noOrdersLayout = _view.findViewById(xyz.miaw.android.base.R.id.linearlayout_accounts_noordersview);
            noOrdersLayout.setVisibility(View.VISIBLE);
        }

        // This initiates the animation set in android:layoutAnimation property
        // of the RecyclerView
        _recyclerView.scheduleLayoutAnimation();

    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
