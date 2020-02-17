package com.example.cirminal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    int Count=0,bind=0,creat=0,ii=0;
    List<Crime> arrCrime=new ArrayList<>();
   // Toolbar toolbar;
   private Callbacks mCallbacks;



    public interface Callbacks {
        void onCrimeSelected(Crime crime);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
//        toolbar=view.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        CrimeLab crimeLab = CrimeLab.getInctance(getActivity());
//        List<Crime> crimes = crimeLab.getCrimes();
//        mAdapter = new CrimeAdapter(crimes);       // Adapter ندخل المصفوفة الى
//        mCrimeRecyclerView.setAdapter(mAdapter);   //ندخل ال Adapter ال RecyclerView
        updateUI();
        return view;
    }
    public void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInctance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Crime crime = new Crime();
                CrimeLab.getInctance(getActivity()).addCrime(crime);

           /*
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getmId());
                startActivity(intent);
                */
                updateUI();
                mCallbacks.onCrimeSelected(crime);
                //////////////
                return true;
            case R.id.app_home:
                updateSubtitle(); return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.getInctance(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.getSupportActionBar().setSubtitle(subtitle);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.app_menu, menu);
    }

    //////////////////////////////////////////////////////////////////////////
    private class CrimeAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Crime> mCrimes;
        int index;
    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        index=i;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
         //   Toast.makeText(getActivity(),ii+" on Create View Holder "+ ++creat,Toast.LENGTH_SHORT).show();

            return new ViewHolder(layoutInflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder crimeHolder, int i) {


       //   Toast.makeText(getActivity(),ii+"  "+i+" on Bind View Holder "+ ++bind,Toast.LENGTH_SHORT).show();
crimeHolder.bind(mCrimes.get(i));

//            crimeHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // Toast.makeText(getActivity(), mCrimes.get(index).getmId().toString(), Toast.LENGTH_SHORT).show();
//                  Intent intent = CrimeActivity.newIntent(getActivity(),mCrimes.get(index).getmId());
//                  //  Intent intent=CrimePagerActivity.newIntent(getActivity(),mCrimes.get(index).getmId());
//
//                    startActivity(intent);
//
//
////                    Intent intent=new Intent(getActivity(),CrimeActivity.class);
////                    intent.putExtra("zzz", mCrimes.get(index).getmId());
////                    startActivity(intent);
//                }
//            });
        }
    @Override
    public int getItemCount() {
     //   Toast.makeText(getActivity(), " Count "+ ++Count, Toast.LENGTH_SHORT).show();
        return mCrimes.size();    }

        public void setCrimes(List<Crime> crimes)
        {
            mCrimes = crimes;
        }

   }
        //-------------------------------------------------
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView uImg;
            TextView mTitle, mDate;
Crime crime;

            public ViewHolder(@NonNull LayoutInflater inflater, ViewGroup viewGroup) {
                super(inflater.inflate(R.layout.onholder_constrint, viewGroup, false));
               itemView.setOnClickListener(this);
                uImg = itemView.findViewById(R.id.imgc);
                mTitle = itemView.findViewById(R.id.mTitlec);
                mDate = itemView.findViewById(R.id.mDatec);

            }



            public void bind(Crime C) {
                crime=C;
               uImg.setVisibility(C.ismSolved()?View.VISIBLE:View.GONE);
              mDate.setText(C.getmDate().toString());
              mTitle.setText(C.getmTitle());
            }

            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(), mCrimes.get(index).getmId().toString(), Toast.LENGTH_SHORT).show();
            /*
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getmId());
                Toast.makeText(getActivity(), crime.getmId()+"", Toast.LENGTH_SHORT).show();
                //  Intent intent=CrimePagerActivity.newIntent(getActivity(),mCrimes.get(index).getmId());

                startActivity(intent);*/


                mCallbacks.onCrimeSelected(crime);



//                    Intent intent=new Intent(getActivity(),CrimeActivity.class);
//                    intent.putExtra("zzz", mCrimes.get(index).getmId());
//                    startActivity(intent);
            }
        }  }
