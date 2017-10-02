package com.example.a5280081.firebasecloudmessagingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a5280081.firebasecloudmessagingapp.R;
import com.example.a5280081.firebasecloudmessagingapp.model.Package;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageAdapterViewHolder> {

    private List<Package> mPackageList;

    public PackageAdapter(List<Package> packageList) {
        mPackageList = packageList;
    }

    @Override
    public PackageAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new PackageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PackageAdapterViewHolder holder, int position) {
        Package pack = mPackageList.get(position);
        holder.packageId.setText(Integer.toString(pack.getPackageId()));
        holder.packageName.setText(pack.getPackageName());
    }

    @Override
    public int getItemCount() {
        return mPackageList.size();
    }

    public void swapAdapter(List<Package> packageList){
        mPackageList = packageList;
        notifyDataSetChanged();
    }

    public class PackageAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.package_id) TextView packageId;
        @BindView(R.id.package_name) TextView packageName;
        @BindView(R.id.package_divider) View mlistDivider;

        public PackageAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
